package com.bit2025.mysite.controller.board;

import com.bit2025.mysite.dao.BoardDao;
import com.bit2025.mysite.vo.BoardVo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class BoardViewAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String noStr = request.getParameter("no");
        Long no = Long.parseLong(noStr);

        BoardDao dao = new BoardDao();

        // 조회수 증가
        dao.increaseHit(no);

        // 글 정보 가져오기
        BoardVo vo = dao.findByNo(no);

        request.setAttribute("board", vo);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
        rd.forward(request, response);
    }
}
