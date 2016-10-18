package com.bit2016.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.vo.UserVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if( session == null ){
			WebUtil.redirect(request, response, "/mysite3/main");
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if( authUser == null ){
			WebUtil.redirect(request, response, "/mysite3/main");
			return;	
		}
		
		// logout 처리
		session.removeAttribute( "authUser" );
		session.invalidate();
		
		WebUtil.redirect(request, response, "/mysite3/main");
	}

}
