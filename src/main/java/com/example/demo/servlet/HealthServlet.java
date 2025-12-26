package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple servlet for health check — verified by tests for:
 * 1. @WebServlet annotation presence and urlPatterns
 * 2. Extends HttpServlet
 * 3. Class is public and instantiable
 * 4. Protected doGet(HttpServletRequest, HttpServletResponse)
 * 5. No fields declared
 * 6. Class name contains "Health"
 */
@WebServlet(urlPatterns = "/health")
public class HealthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("OK");
        out.flush();
    }
}
