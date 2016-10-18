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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session == null) {
			WebUtil.redirect(request, response, "/mysite3/main");
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser != null) {

			UserDao dao = new UserDao();
			UserVo vo = new UserVo();

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			vo.setNo(authUser.getNo());
			vo.setName(name);
			vo.setPassword(password);
			vo.setGender(gender);

			authUser.setName(name);
			authUser = dao.update(vo);

		}
		WebUtil.redirect(request, response, "/mysite3/main");
	}

}
