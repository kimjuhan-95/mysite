package com.bit2025.mysite.action;

import com.bit2025.mysite.dao.BoardDao;
import com.bit2025.mysite.vo.BoardVo;
import jakarta.servlet.http.*;

public class BoardWriteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 로그인 유저 가져오기 (세션)
        HttpSession session = request.getSession();
        Long userNo = (Long) session.getAttribute("authUserNo");

        BoardVo vo = new BoardVo();
        vo.setTitle(title);
        vo.setContent(content);
        vo.setUserNo(userNo);
        vo.setGroupNo(1); // 일단 기본값, 나중에 답글 처리에서 수정
        vo.setOrderNo(1);
        vo.setDepth(0);

        new BoardDao().insert(vo);

        response.sendRedirect(request.getContextPath() + "/board?a=list");
    }
}
