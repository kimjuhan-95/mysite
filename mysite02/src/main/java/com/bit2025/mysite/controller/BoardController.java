package com.bit2025.mysite.controller;

import com.bit2025.mysite.action.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

//@WebServlet("/board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String actionName = request.getParameter("a");
        Action action = null;

        if ("list".equals(actionName)) {
            action = new BoardListAction();
        } else if ("writeform".equals(actionName)) {
            action = new BoardWriteFormAction();
        } else if ("write".equals(actionName)) {
            action = new BoardWriteAction();
        } else {
            action = new BoardListAction(); // 기본 동작
        }

        try {
            action.execute(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
