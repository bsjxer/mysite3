package com.bit2016.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.action.guestbook.GuestbookActionFactory;
import com.bit2016.web.Action;
import com.bit2016.web.ActionFactory;

@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );

		String actionName = request.getParameter( "a" );
		
		ActionFactory af = new GuestbookActionFactory();
		Action action = af.getAction( actionName );
		action.execute(request, response);
		
		/*
		if( "add".equals( actionName )){
			//add 요청에 대한 처리
			request.setCharacterEncoding( "UTF-8" );
			String name = request.getParameter( "name" );
			String content = request.getParameter( "content" );
			String password = request.getParameter( "pass" );
			
			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setContent(content);
			vo.setPassword(password);
			
			GuestBookDao dao = new GuestBookDao();
			dao.insert( vo );
			
			//redirect
			response.sendRedirect( "/guestbook2/gb" );
		} else if( "deleteform".equals( actionName )){
			//form 요청에 대한 처리
			RequestDispatcher rd = request.getRequestDispatcher( "/WEB-INF/views/deleteform.jsp");
			rd.forward( request, response);
		} else if( "delete".equals( actionName )){
			String no = request.getParameter("no");
			String pass = request.getParameter("pass");

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(Long.parseLong(no));
			vo.setPassword(pass);

			GuestBookDao dao = new GuestBookDao();
			dao.delete(vo);

			//redirect
			response.sendRedirect("/guestbook2/gb");
		} else {
			//default action처리
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getList();
			
			//request 범위에 모델 데이터 저장
			request.setAttribute( "list", list );
			
			//forwarding(request 연장, request dispatch)
			RequestDispatcher rd = request.getRequestDispatcher( "/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
