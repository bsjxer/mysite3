package com.bit2016.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.UserDao;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter( "name" );
		String email = request.getParameter( "email" );
		String password = request.getParameter( "password" );
		String gender = request.getParameter( "gender" );
		
		UserVo vo = new UserVo();
		vo.setName( name );
		vo.setEmail( email );
		vo.setPassword(password);
		vo.setGender(gender);
		
		UserDao dao = new UserDao();
		dao.insert(vo);
		
		WebUtil.redirect(request, response, "/mysite3/user?a=joinsuccess");
	}

}
