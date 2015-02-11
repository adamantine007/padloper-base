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


/**
 * Controller that process four types of requests for inner data bae: add, get, remove, commit.
 * This servlet created to process POST requests, all GET requests redirects to PAGE_ERROR.
 * Also this servlet uses Gson library to convert entity to JSON format.
 *
 * @see GsonParser
 * @see HttpServlet
 * @see Person
 * @see PersonDao
 *
 * @author Ruslan Kurchenko
 */

public class PersonController extends HttpServlet {
    private static final String TYPE = "type";
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String SECOND_NAME = "secondName";
    private static final String NUMBER = "number";

    private static final String PAGE_ERROR = "/index.html";

    private static final Parser parser = new GsonParser();


    /**
     * Redirects all requests with GET type to PAGE_ERROR
     *
     * @param req request from client
     * @param resp response to client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(PAGE_ERROR);
    }


    /**
     * Gets type of request and forward it to 'processRequest'
     * method along with HttpServletRequest and HttpServletResponse
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter(TYPE);

        processRequest(type, req, resp);
    }

    /**
     * Using switch/case statement chooses what request will process by type
     *
     * @param type of request that was sent from client
     * @throws IOException
     */
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
                commit(resp);
                break;
            default:
                resp.sendRedirect(PAGE_ERROR);
        }
    }

    /**
     * Adds person to database and send response to client
     *
     * @throws IOException
     */
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


    /**
     * Selects person by ID from database and sends info about it,
     * or send message that 'no such person in DB'
     *
     * @throws IOException
     */
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


    /**
     * Removes person by ID from database and and sends info about it,
     * or send message that 'no such person in DB'
     *
     * @throws IOException
     */
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

    /**
     * Commits data that client changed and sends successfully answer
     *
     * @throws IOException
     */
    private void commit(HttpServletResponse resp) throws IOException {
        PersonDao dao = PersonDaoImpl.getInstance();
        dao.commitData();

        System.err.println("Data base committed");

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write("Successfully committed");
    }
}
