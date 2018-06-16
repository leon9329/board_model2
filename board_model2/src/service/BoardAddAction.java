package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardAddAction");
		
		String path=request.getRealPath("boardUpload");
		int size=1024*1024;
		
		MultipartRequest multi=new MultipartRequest(request, path, size, "utf-8",new DefaultFileRenamePolicy());
		
		BoardBean board=new BoardBean();
		board.setBoard_name(multi.getParameter("board_name"));
		board.setBoard_pass(multi.getParameter("board_pass"));
		board.setBoard_subject(multi.getParameter("board_subject"));
		board.setBoard_content(multi.getParameter("board_content"));
		board.setBoard_file(multi.getParameter("board_file"));
	
		BoardDAO dao=BoardDAO.getInstance();
		int result=dao.insert(board);
		System.out.println("result:"+result);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardListAction.do");
		
		return forward;
	}
	

}
