package com.henko.controller;

import com.henko.dao.PersonDao;
import com.henko.dao.exception.NoSuchEntityException;
import com.henko.dao.impl.PersonDaoImpl;
import com.henko.entity.Person;
import com.henko.parser.Parser;
import com.henko.parser.impl.GsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonController extends HttpServlet {
    private static final String TYPE = "type";
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String SECOND_NAME = "secondName";
    private static final String NUMBER = "number";

    private static final String PAGE_ERROR = "/index.html";

    private static final Parser parser = new GsonParser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(PAGE_ERROR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter(TYPE);

        processRequest(type, req, resp);
    }

    private void processRequest(String type, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch (type) {
            case "add":
                add(req, resp);
                break;
            case "get":
                get(req, resp);
                break;
            case "remove":
                remove(req, resp);
                break;
            case "commit":
                commit(req, resp);
                break;
            default:
                resp.sendRedirect(PAGE_ERROR);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID));
        String firstName = req.getParameter(FIRST_NAME);
        String secondName = req.getParameter(SECOND_NAME);
        String number = req.getParameter(NUMBER);

        Person person = new Person(id, firstName, secondName, number);

        PersonDao dao = PersonDaoImpl.getInstance();
        dao.addById(person);

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(firstName + " " + secondName + " was added.");

        System.err.println("Object : " + person + " was add to DB.");
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID));
        PersonDao dao = PersonDaoImpl.getInstance();

        try {
            Person person = dao.getById(id);
            String jsonPerson = parser.toJson(person);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write(jsonPerson);

            System.err.println("Object : " + person + " got from DB.");
        } catch (NoSuchEntityException e) {
            resp.getWriter().println("No such person in DB.");
        }
    }

    private void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(ID));
        PersonDao dao = PersonDaoImpl.getInstance();

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        try {
            Person p = dao.removeById(id);

            resp.getWriter().write(p.getFirstName() + " " + p.getSecondName() + " was deleted.");

            System.err.println("Object : " + p + " removed from DB.");
        } catch (NoSuchEntityException e) {
            resp.getWriter().write("A person with ID - " + id + " not found.");
        }
    }

    private void commit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PersonDao dao = PersonDaoImpl.getInstance();
        dao.commitData();

        System.err.println("Data base committed");

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write("Successfully committed");
    }
}
