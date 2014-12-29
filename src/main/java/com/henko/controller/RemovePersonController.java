package com.henko.controller;


import com.henko.dao.PersonDao;
import com.henko.dao.exception.NoSuchEntityException;
import com.henko.dao.impl.PersonDaoImpl;
import com.henko.entity.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removepersoncontroller")
public class RemovePersonController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("removeId"));
        PersonDao dao = PersonDaoImpl.getInstance();
        Person person;
        response.setContentType("text/xml");
        try {
            person = dao.removeById(id);
            System.err.println("Object : " + person + " removed from DB.");
            response.getWriter().println("<serverResponse>" + person.getName() + " successfully removed from DB</serverResponse>");
        } catch (NoSuchEntityException e) {
            response.getWriter().println("<serverResponse>No such person in DB.</serverResponse>");
        }
    }
}
