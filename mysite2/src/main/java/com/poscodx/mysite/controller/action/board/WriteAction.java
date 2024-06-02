package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
	    if (session == null) {
	        response.sendRedirect(request.getContextPath());
	        return;
	    }
	    
		UserVo authUser = (UserVo) session.getAttribute("authUser");
	    if (authUser == null) {
	    	response.sendRedirect(request.getContentType() + "/user?a=login");
	        return;
	    } 
	    
	    String title = request.getParameter("title");
	    String contents = request.getParameter("contents");
	    if(title == null || title.equals("")) {
	    	response.sendRedirect(request.getContextPath());
	    	return;
	    }
	    
	    Long updateGroupNo = new BoardDao().getNextGroupNo();
	    
	    BoardVo boardVo = new BoardVo();
	    boardVo.setTitle(title);
	    boardVo.setContents(contents);
	    boardVo.setHit(0);
	    boardVo.setGroupNo(updateGroupNo);
	    boardVo.setOrderNo(0L); 
	    boardVo.setDepth(0); 
	    boardVo.setUserNo(authUser.getNo());
	    
	    System.out.println("새글 작성의 boardVo " + boardVo);
	    new BoardDao().insert(boardVo);
	    System.out.println("새글 작성");
	    
	    response.sendRedirect(request.getContextPath() + "/board");
	}
    
}
