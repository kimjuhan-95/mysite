package com.bit2025.mysite.controller.board;

import com.bit2025.mysite.dao.BoardDao;
import com.bit2025.mysite.vo.BoardVo;
import com.bit2025.mysite.vo.UserVo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class BoardUpdateFormAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }

        Long no = Long.parseLong(request.getParameter("no"));
        BoardDao dao = new BoardDao();
        BoardVo vo = dao.findByNo(no);

        // 본인 글만 수정 가능
        if (vo == null || !vo.getUserNo().equals(authUser.getNo())) {
            response.sendRedirect(request.getContextPath() + "/board/list");
            return;
        }

        request.setAttribute("board", vo);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/updateform.jsp");
        rd.forward(request, response);
    }
}
