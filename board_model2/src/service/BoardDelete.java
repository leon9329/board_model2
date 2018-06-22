package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardDelete implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardDelete");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String page=request.getParameter("page");
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("board_pass");
		System.out.println("page: "+page);
		System.out.println("page: "+num );
		System.out.println("page: "+pass);
		
		BoardDAO dao=BoardDAO.getInstance();
		BoardBean old=dao.getContent(num);
		
		if(!old.getBoard_pass().equals(pass)) {
			out.println("<script>");
			out.println("alert('비번이 틀렸습니다.')");
			out.println("history.go(-1)");
			out.println("</script>");
			return null;
		}else {
			int result=dao.delete(num);
			System.out.println("result:"+result);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/BoardListAction.do?page="+page);
		
		return forward;
	}

}
