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
	    	//로그인한 유저만 글쓰기 가능 -> alret로 알려주고 로그인 페이지로 이동 
	    	//request.setAttribute("alertType", "loginChecked");
	    	//response.sendRedirect(request.getContextPath() + "/user?a=joinchecked");
	    	//request.getRequestDispatcher("/WEB-INF/views/common/alert.jsp").forward(request, response);
	    	response.sendRedirect(request.getContentType() + "/user?a=login");
	        return;
	    } 
	    
	    String title = request.getParameter("title");
	    String contents = request.getParameter("contents");
	    
	    if(title == null || title.equals("")) {
	    	//title안적고 글쓰기 불가 -> alert로 알려주고 다시 현재 글쓰기 페이지 보여주기 
	    	response.sendRedirect(request.getContextPath());
	    	return;
	    }
	    
	    BoardVo boardVo = new BoardVo();
	    boardVo.setTitle(title);
	    boardVo.setContents(contents);
	    boardVo.setHit(0);
	    boardVo.setGroupNo(0);
	    boardVo.setOrderNo(1);
	    boardVo.setDepth(0);
	    boardVo.setUserNo(authUser.getNo());
	    
	    new BoardDao().insert(boardVo);
	    
	    response.sendRedirect(request.getContextPath() + "/board");
	}
    
}
