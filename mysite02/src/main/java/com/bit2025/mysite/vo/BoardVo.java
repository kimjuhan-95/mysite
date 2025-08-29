package com.bit2025.mysite.vo;

import java.util.Date;

public class BoardVo {
    private Long no;
    private String title;
    private String content;
    private int hit;
    private Date regDate;
    private Long userNo;
    private String userName;

    private int groupNo;
    private int orderNo;
    private int depth;

    // getter / setter
    public Long getNo() { return no; }
    public void setNo(Long no) { this.no = no; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getHit() { return hit; }
    public void setHit(int hit) { this.hit = hit; }

    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }

    public Long getUserNo() { return userNo; }
    public void setUserNo(Long userNo) { this.userNo = userNo; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public int getGroupNo() { return groupNo; }
    public void setGroupNo(int groupNo) { this.groupNo = groupNo; }

    public int getOrderNo() { return orderNo; }
    public void setOrderNo(int orderNo) { this.orderNo = orderNo; }

    public int getDepth() { return depth; }
    public void setDepth(int depth) { this.depth = depth; }
}
