package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardReplyAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardReplyAction");
		String page=request.getParameter("page");
		int num=Integer.parseInt(request.getParameter("num"));
		
		//부모의 정보를 넘긴다.
		BoardDAO dao=BoardDAO.getInstance();
		BoardBean board=dao.getContent(num);
		
		request.setAttribute("page", page);
		request.setAttribute("board", board);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board/board_reply.jsp");
		
		return forward;
	}

}
