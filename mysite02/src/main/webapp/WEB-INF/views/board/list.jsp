<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLine", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="container">
        <div id="header">
            <h1>MySite</h1>
            <ul>
                <li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
                <li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
                <li><a href="${pageContext.request.contextPath }/user/update">회원정보수정</a></li>
                <li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
                <c:if test="${not empty authUser}">
                    <li>${authUser.name}님 안녕하세요 ^^;</li>
                </c:if>
            </ul>
        </div>

        <div id="content">
            <div id="board">
                <!-- 검색폼 -->
                <form id="search_form" action="${pageContext.request.contextPath }/board/list" method="get">
                    <input type="text" id="kwd" name="kwd" value="${param.kwd}">
                    <input type="submit" value="찾기">
                </form>

                <!-- 게시판 목록 -->
                <table class="tbl-ex">
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>조회수</th>
                        <th>작성일</th>
                        <th>&nbsp;</th>
                    </tr>                
                    
                    <c:forEach var="vo" items="${list}" varStatus="status">
                        <tr>
                            <td>${vo.no}</td>
                            <td style="text-align:left; padding-left:${(vo.depth-1) * 20}px">
                                <c:if test="${vo.depth > 1}">
                                    <img src="${pageContext.request.contextPath }/assets/images/reply.png">
                                </c:if>
                                <a href="${pageContext.request.contextPath }/board/view?no=${vo.no}">
                                    ${vo.title}
                                </a>
                            </td>
                            <td>${vo.userName}</td>
                            <td>${vo.hit}</td>
                            <td><fmt:formatDate value="${vo.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <!-- 로그인한 사용자 == 작성자일 경우 삭제 버튼 -->
                                <c:if test="${not empty authUser and authUser.no == vo.userNo}">
                                    <a href="${pageContext.request.contextPath }/board/delete?no=${vo.no}"
                                       class="del"
                                       onclick="return confirm('정말 삭제하시겠습니까?');"
                                       style='background:url("${pageContext.request.contextPath }/assets/images/recycle.png") no-repeat 0 0;'>
                                       삭제
                                    </a>
                                </c:if>
                                <c:if test="${empty authUser or authUser.no != vo.userNo}">
                                    &nbsp;
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                
                <!-- pager (추후 PageVo 적용해서 동적 처리) -->
                <div class="pager">
                    <ul>
                        <li><a href="">◀</a></li>
                        <li><a href="">1</a></li>
                        <li class="selected">2</li>
                        <li><a href="">3</a></li>
                        <li>4</li>
                        <li>5</li>
                        <li><a href="">▶</a></li>
                    </ul>
                </div>                    
                
                <!-- 글쓰기 버튼: 로그인 상태일 때만 보이도록 -->
                <div class="bottom">
                    <c:if test="${not empty authUser}">
                        <a href="${pageContext.request.contextPath }/board/writeform" id="new-book">글쓰기</a>
                    </c:if>
                </div>              
            </div>
        </div>

        <div id="navigation">
            <ul>
                <li><a href="">안대혁</a></li>
                <li><a href="">방명록</a></li>
                <li><a href="">게시판</a></li>
            </ul>
        </div>

        <div id="footer">
            <p>(c)opyright 2015, 2016, 2017, 2018</p>
        </div>
    </div>
</body>
</html>
