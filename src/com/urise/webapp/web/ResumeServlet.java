package com.urise.webapp.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class  ResumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //response.setHeader("Content-Type","text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name");
        response.getWriter().write(name  ==null ? "Hello Resumes!" : "Hello" + name + "!");
        printTable(request, response);
    }

    private void printTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        HtmlUtils hu = new HtmlUtils();

        out.print(hu.createHtmlHeader("resumes"));
        out.print(hu.getTableHead("center", 1));
        out.print(hu.getTH("center", "uuid"));
        out.print(hu.getTH("center", "fullName"));
    }
}
