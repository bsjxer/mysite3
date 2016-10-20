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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "UTF-8" );
		HttpSession session = request.getSession();
		
		UserVo user = (UserVo) session.getAttribute("authUser");
		
		String title = request.getParameter( "title" );
		String content = request.getParameter( "content" );
		String sNo = request.getParameter( "no" );
		Long no = Long.parseLong( sNo );
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNo( no );
		
		BoardDao dao = new BoardDao();
		dao.update( vo );
		
		WebUtil.redirect( request, response, "/mysite3/board");
	}

}
