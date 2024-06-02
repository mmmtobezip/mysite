package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.mysite.dao.BoardDao;

public class ReplyAction implements Action{

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
	    String parentNo = request.getParameter("parentNo");
	    if(title == null || title.equals("")) {
	    	request.setAttribute("alertType", "writeFormNoti");
	    	request.getRequestDispatcher("/WEB-INF/views/common/alert.jsp").forward(request, response);
	    	return;
	    }
	    
	    BoardVo boardVo = new BoardVo();
	    BoardVo parentVo = new BoardDao().findByNo(Long.parseLong(parentNo));
	    
	    boardVo.setTitle(title);
	    boardVo.setContents(contents);
	    boardVo.setHit(0);
	    boardVo.setGroupNo(parentVo.getGroupNo());
	    boardVo.setOrderNo(parentVo.getOrderNo() + 1);
	    boardVo.setDepth(parentVo.getDepth() + 1);
	    boardVo.setUserNo(authUser.getNo());

	    new BoardDao().insertReply(boardVo, parentVo);
	    
	    response.sendRedirect(request.getContextPath() + "/board");
	    
	}
}
