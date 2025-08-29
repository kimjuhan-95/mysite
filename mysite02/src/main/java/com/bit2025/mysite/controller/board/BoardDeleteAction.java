package com.bit2025.mysite.controller.board;

import com.bit2025.mysite.dao.BoardDao;
import com.bit2025.mysite.vo.BoardVo;
import com.bit2025.mysite.vo.UserVo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class BoardDeleteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser"); // 로그인 정보

        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }

        Long no = Long.parseLong(request.getParameter("no"));
        BoardDao dao = new BoardDao();

        // 글 정보 가져오기
        BoardVo vo = dao.findByNo(no);

        // 본인 글만 삭제 가능
        if (vo != null && vo.getUserNo().equals(authUser.getNo())) {
            dao.delete(no);
        }

        response.sendRedirect(request.getContextPath() + "/board/list");
    }
}
