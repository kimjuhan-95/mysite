package com.bit2025.mysite.action;

import jakarta.servlet.http.*;

public class BoardWriteFormAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(request, response);
    }
}
