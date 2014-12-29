package com.henko.controller;

import com.henko.dao.PersonDao;
import com.henko.dao.impl.PersonDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/commitpersondbcontroller")
public class CommitPersonDbController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDao dao = PersonDaoImpl.getInstance();
        dao.commitData();
        System.out.println("Data base commited");
        response.setContentType("text/xml");
        response.getWriter().println("<serverResponse>Commit successfully done.<serverResponse>");
    }
}
