package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardListAction");
		
		int num=Integer.parseInt(request.getParameter("num"));
		String page=request.getParameter("page");
		System.out.println("BoardListAction¾È page: "+page);
		BoardDAO dao=BoardDAO.getInstance();
		BoardBean board= dao.getContent(num);
		
		request.setAttribute("page", page);
		request.setAttribute("board", board);
		
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/board_modify.jsp");
		return forward;
	}

}
