package com.poscodx.mysite.vo;

public class BoardVo {
  private Long no;
  private String title;
  private String contents;
  private int hit;
  private String regDate;
  private Long groupNo;
  private Long orderNo;
  private int depth;
  private Long userNo;
  private String userName;

  public Long getNo() {
    return no;
  }

  public void setNo(Long long1) {
    this.no = long1;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public int getHit() {
    return hit;
  }

  public void setHit(int hit) {
    this.hit = hit;
  }

  public String getRegDate() {
    return regDate;
  }

  public void setRegDate(String regDate) {
    this.regDate = regDate;
  }

  public Long getGroupNo() {
    return groupNo;
  }

  public void setGroupNo(Long groupNo) {
    this.groupNo = groupNo;
  }

  public Long getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(Long orderNo) {
    this.orderNo = orderNo;
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public Long getUserNo() {
    return userNo;
  }

  public void setUserNo(Long userNo) {
    this.userNo = userNo;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
