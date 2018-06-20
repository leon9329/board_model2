package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardDetailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardDetailAction");
		
		System.out.println("go");
		int num=Integer.parseInt(request.getParameter("num"));
		String page=request.getParameter("page");
		System.out.println("num:"+num);
		System.out.println("page:"+page);
		
		BoardDAO dao=BoardDAO.getInstance();
		BoardBean board=dao.updateCount(num);
		
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/board_view.jsp");
		System.out.println("in BoardDetailAction path="+forward.getPath());
		
		return forward;
	}

}
