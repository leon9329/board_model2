package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardModifyView implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardModifyView");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String path=request.getRealPath("boardUpload");
		int size=1024*1024;
		
		MultipartRequest multi = 
				new MultipartRequest(request,path,size,"utf-8",new DefaultFileRenamePolicy());
		
		int num=Integer.parseInt(multi.getParameter("nun"));
		String page=multi.getParameter("page");
		String board_pass=multi.getParameter("board_pass");
		
		BoardDAO dao=BoardDAO.getInstance();
		BoardBean old=dao.getContent(num);
		
		BoardBean board=new BoardBean();
		
		board.setBoard_num(num);
		board.setBoard_name(multi.getParameter("board_name"));
		board.setBoard_pass(multi.getParameter("board_pass"));
		board.setBoard_subject(multi.getParameter("board_subject"));
		board.setBoard_content(multi.getParameter("board_content"));

		String board_file=multi.getFilesystemName("board_file");
		if(board_file != null) {
			board.setBoard_file(board_file);
		}else {
			board.setBoard_file(old.getBoard_file());
		}
		
		if(!old.getBoard_pass().equals(board_pass)) {
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.')");
			out.println("history.go(-1)");
			out.println("</script>");
			out.close();
			return null;
			
		}else {
			//dao.setUpdate(board);
		}
		
		return null;
	}

}
