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
		
		String file=multi.getParameter("board_file");
		if(!file.equals("")) {
			board.setBoard_file(file);
		}
		
		BoardDAO dao=BoardDAO.getInstance();
		int result=dao.insert(board);
		
		request.setAttribute("board", board);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board_list.jsp");
		
		
		
		return null;
	}
	

}
