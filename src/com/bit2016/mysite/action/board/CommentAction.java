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

public class CommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		HttpSession session = request.getSession();
		
		UserVo user = (UserVo) session.getAttribute("authUser");
		
		String title = request.getParameter( "title" );
		String content = request.getParameter( "content" );
		Long user_no = user.getNo();
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUser_no(user_no);
		
		
		String sNo = request.getParameter("no");
		Long no = Long.parseLong(sNo);

		BoardDao dao = new BoardDao();
		BoardVo vo2 = dao.view(no);
		
		
		BoardDao dao2 = new BoardDao();
		dao2.commentInsert( vo, vo2 );
		
		WebUtil.redirect( request, response, "/mysite3/board");

	}

}
