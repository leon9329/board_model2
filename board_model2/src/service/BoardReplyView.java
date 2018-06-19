package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardReplyView implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardReplyView");
		
		request.setCharacterEncoding("utf-8");
		
		int num=Integer.parseInt(request.getParameter("num"));
		int board_re_ref=Integer.parseInt(request.getParameter("board_re_ref"));
		int board_re_seq=Integer.parseInt(request.getParameter("board_re_seq"));
		int board_re_lev=Integer.parseInt(request.getParameter("board_re_lev"));
		String page=request.getParameter("page");
		
		BoardDAO dao=BoardDAO.getInstance();
		BoardBean board=new BoardBean();
		
		board.setBoard_num(num);
		board.setBoard_re_ref(board_re_ref);
		board.setBoard_re_seq(board_re_seq);
		board.setBoard_re_lev(board_re_lev);
		board.setBoard_name(request.getParameter("board_name"));
		board.setBoard_pass(request.getParameter("board_pass"));
		board.setBoard_subject(request.getParameter("board_subject"));
		board.setBoard_content(request.getParameter("board_content"));
		
		int result=dao.reply(board);
		System.out.println(result);
		
		
		ActionForward forward=new ActionForward();
		forward.setPath("/BoardDetailAction.do?num="+num+"&page="+page);
		forward.setRedirect(false);
		
		
		return forward;
	}

}
