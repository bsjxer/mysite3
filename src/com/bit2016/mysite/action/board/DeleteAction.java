package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		HttpSession session = request.getSession();
		
		UserVo user = (UserVo) session.getAttribute("authUser");
		
		String sNo = request.getParameter("no");
		Long no = Long.parseLong(sNo);
		Long user_no = user.getNo();
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setUser_no(user_no);
		
		BoardDao dao = new BoardDao();
		dao.delete( vo );
		
		WebUtil.redirect( request, response, "/mysite3/board");
	}

}
