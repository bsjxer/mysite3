package com.bit2016.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.GuestBookDao;
import com.bit2016.mysite.vo.GuestBookVo;
import com.bit2016.web.Action;

import net.sf.json.JSONObject;

public class AjaxAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		String name = request.getParameter( "name" );
		String content = request.getParameter( "content" );
		String password = request.getParameter( "password" );
		
		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setContent(content);
		vo.setPassword(password);
		
		GuestBookDao dao = new GuestBookDao();
		Long no = dao.insert( vo );
		GuestBookVo guestbookVo = dao.get( no );
		
		//respons
		response.setContentType( "application/json; charset=utf-8" );
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "result", "success" );
		map.put( "data", guestbookVo );
		
		response.getWriter().println( JSONObject.fromObject(map).toString() );
		
		
	}

}
