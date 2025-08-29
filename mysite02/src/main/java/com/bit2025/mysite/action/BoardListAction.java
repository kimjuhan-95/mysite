package com.bit2025.mysite.action;

import com.bit2025.mysite.dao.BoardDao;
import com.bit2025.mysite.vo.BoardVo;
import jakarta.servlet.http.*;
import java.util.List;

public class BoardListAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<BoardVo> list = new BoardDao().findAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
    }
}
