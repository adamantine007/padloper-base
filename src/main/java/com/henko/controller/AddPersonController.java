package com.henko.controller;

import com.henko.dao.PersonDao;
import com.henko.dao.impl.PersonDaoImpl;
import com.henko.entity.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addpersoncontroller")
public class AddPersonController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("firstName");
        name += " " + request.getParameter("secondName");
        String number = request.getParameter("number");
        Person person = new Person(id, name, number);

        PersonDao dao = PersonDaoImpl.getInstance();
        dao.addById(person);

        response.setContentType("text/xml");
        response.getWriter().println("<serverResponse>" + name + " succeeded added to the DB.</serverResponse>");

        System.err.println("Object : " + person + " was add to DB.");
    }
}
