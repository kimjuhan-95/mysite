package com.bit2025.mysite.dao;

import com.bit2025.mysite.vo.BoardVo;
import java.sql.*;
import java.util.*;

public class BoardDao {
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/boarddb?characterEncoding=utf8&serverTimezone=UTC",
            "root", "비밀번호"); // ← DB 정보 수정
    }
    public void update(BoardVo vo) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setLong(3, vo.getNo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<BoardVo> findAll() {
        List<BoardVo> list = new ArrayList<>();
        String sql = "SELECT b.no, b.title, b.hit, b.reg_date, u.username " +
                     "FROM board b JOIN user u ON b.user_no=u.no " +
                     "ORDER BY b.group_no DESC, b.order_no ASC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                BoardVo vo = new BoardVo();
                vo.setNo(rs.getLong("no"));
                vo.setTitle(rs.getString("title"));
                vo.setHit(rs.getInt("hit"));
                vo.setRegDate(rs.getTimestamp("reg_date"));
                vo.setUserName(rs.getString("username"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(BoardVo vo) {
        String sql = "INSERT INTO board (title, content, hit, reg_date, user_no, group_no, order_no, depth) " +
                     "VALUES (?, ?, 0, NOW(), ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setLong(3, vo.getUserNo());
            pstmt.setInt(4, vo.getGroupNo());
            pstmt.setInt(5, vo.getOrderNo());
            pstmt.setInt(6, vo.getDepth());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public void increaseHit(Long no) {
    String sql = "UPDATE board SET hit = hit + 1 WHERE no = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setLong(1, no);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public BoardVo findByNo(Long no) {
    BoardVo vo = null;
    String sql = "SELECT no, title, content, user_no, hit, reg_date FROM board WHERE no = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setLong(1, no);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            vo = new BoardVo();
            vo.setNo(rs.getLong("no"));
            vo.setTitle(rs.getString("title"));
            vo.setContent(rs.getString("content"));
            vo.setUserNo(rs.getLong("user_no"));
            vo.setHit(rs.getInt("hit"));
            vo.setRegDate(rs.getString("reg_date"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return vo;
}
public void delete(Long no) {
    String sql = "DELETE FROM board WHERE no = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setLong(1, no);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
