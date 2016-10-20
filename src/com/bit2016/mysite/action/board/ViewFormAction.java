package com.bit2016.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class ViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String sNo = request.getParameter("no");
		Long no = Long.parseLong(sNo);

		BoardDao dao = new BoardDao();
		BoardVo vo2 = dao.view(no);
		dao.updateHit(no);

		request.setAttribute("vo2", vo2);
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");

	}

}
