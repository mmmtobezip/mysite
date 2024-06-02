package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.Paging;

//게시판 메인
public class BoardAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		if(request.getParameter("p") != null) {
			page = Integer.parseInt(request.getParameter("p"));
			System.out.println(page);
		}
		
		Paging paging = new Paging();
		paging.setPage(page); //current page 전달 
		paging.setPageInfo(); //paging 정보 전달 
        
		request.setAttribute("paging", paging);

		request.setAttribute("list", new BoardDao().findByPage(paging.getPage(), paging.getDisplayRow()));
		request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
	}
}