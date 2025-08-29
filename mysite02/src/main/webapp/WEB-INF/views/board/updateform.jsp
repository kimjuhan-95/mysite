<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>게시글 수정</title>
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <h2>게시글 수정</h2>

    <form action="${pageContext.request.contextPath}/board/update" method="post">
        <input type="hidden" name="no" value="${board.no}">

        <table>
            <tr>
                <td>제목</td>
                <td><input type="text" name="title" value="${board.title}" size="60"></td>
            </tr>
            <tr>
                <td>내용</td>
                <td>
                    <textarea name="content" rows="10" cols="80">${board.content}</textarea>
                </td>
            </tr>
        </table>

        <input type="submit" value="수정">
        <a href="${pageContext.request.contextPath}/board/view?no=${board.no}">취소</a>
    </form>
</div>
</body>
</html>
