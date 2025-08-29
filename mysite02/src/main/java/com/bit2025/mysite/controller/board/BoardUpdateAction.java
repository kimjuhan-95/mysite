package com.bit2025.mysite.controller.board;

import com.bit2025.mysite.dao.BoardDao;
import com.bit2025.mysite.vo.BoardVo;
import com.bit2025.mysite.vo.UserVo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class BoardUpdateAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }

        Long no = Long.parseLong(request.getParameter("no"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardDao dao = new BoardDao();
        BoardVo vo = dao.findByNo(no);

        if (vo != null && vo.getUserNo().equals(authUser.getNo())) {
            vo.setTitle(title);
            vo.setContent(content);
            dao.update(vo);
        }

        response.sendRedirect(request.getContextPath() + "/board/view?no=" + no);
    }
}
