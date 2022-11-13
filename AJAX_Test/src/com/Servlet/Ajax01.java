package com.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/ajax"})
public class Ajax01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String jsonSrt = "[{\"name\":\"lishi\",\"age\":20,\"addr\":\"深圳\"},{\"name\":\"小明\",\"age\":23,\"addr\":\"baoan\"}]";
        out.println(jsonSrt);
    }
}
