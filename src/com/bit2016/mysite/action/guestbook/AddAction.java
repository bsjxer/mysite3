package com.bit2016.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.GuestBookDao;
import com.bit2016.mysite.vo.GuestBookVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		WebUtil.redirect( request, response, "/mysite3/gb");
	}

}
