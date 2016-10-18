package com.bit2016.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.dao.UserDao;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session == null) {
			WebUtil.redirect(request, response, "/mysite3/main");
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		
		if (authUser != null) {
			UserDao dao = new UserDao();
			UserVo userVo = dao.get(authUser.getNo());

			request.setAttribute("userVo", userVo);

			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
		}
	}

}
