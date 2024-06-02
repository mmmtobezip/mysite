package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class ViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		
		String cookieName = "view_" + no;
        Cookie[] cookies = request.getCookies();
        boolean isGet = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                	isGet = true;
                    cookie.setMaxAge(1*24*60*60); 
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        if (!isGet) {
            new BoardDao().updateHit(no);
            Cookie cookie = new Cookie(cookieName, "true");
            cookie.setMaxAge(1*24*60*60); // 하루 동안 유효
            response.addCookie(cookie);
        }
        
//		request.setAttribute("no", no);
//		request.setAttribute("title", boardVo.getTitle());
//		request.setAttribute("regDate", boardVo.getRegDate());
//		request.setAttribute("contents", boardVo.getContents());
//		request.setAttribute("userName", boardVo.getUserName());
//		request.setAttribute("userNo", boardVo.getUserNo());
//		
		request.setAttribute("boardVo", new BoardDao().findByNo(no));
		
		request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);
	}

}
