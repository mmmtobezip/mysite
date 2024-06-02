package com.poscodx.mysite.vo;

import com.poscodx.mysite.dao.BoardDao;

public class Paging {
	private int page = 1; //current page
	private int totalCount; //total row 
	private int beginPage; 
	private int endPage; 
	private int displayRow = 5; //한 페이지에서 보여줄 게시글 개수 
	private int displayPage = 5; //한 페이지에서 보여줄 페이지 개수
	private int totalPage; //전체 페이지 수 
	private boolean isPrevExist; // 이전 페이지 존재 여부 
	private boolean isNextExist;  // 다음 페이지 존재 여부 
	
	public Paging() {
		BoardDao boardDao = new BoardDao();
		this.totalCount = boardDao.getTotalRowCnt();
		this.setTotalPage((int) Math.ceil(totalCount/(double)displayRow));
	}
	
	public void setPageInfo() { 	
		this.endPage = (int) (Math.ceil((double) this.page/this.displayPage) * this.displayPage);
		this.beginPage = this.endPage - this.displayPage + 1;
		
		this.setNextExist(true);
		this.setPrevExist(true);
		
		this.isPrevExist = this.beginPage > 1;
		
		if(totalPage <= endPage) {
			this.endPage = this.totalPage;
			this.setNextExist(false);
		}
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public boolean isPrevExist() {
		return isPrevExist;
	}
	public void setPrevExist(boolean isPrevExist) {
		this.isPrevExist = isPrevExist;
	}
	public boolean isNextExist() {
		return isNextExist;
	}
	public void setNextExist(boolean isNextExist) {
		this.isNextExist = isNextExist;
	}
}
