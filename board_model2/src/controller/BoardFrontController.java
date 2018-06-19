package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.ActionForward;
import service.BoardAddAction;
import service.BoardDetailAction;
import service.BoardListAction;
import service.BoardReplyAction;
import service.BoardReplyView;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.do") // 여러개를 받아야 하기 때문에 한개의 이름으로하면 안된다.
public class BoardFrontController extends HttpServlet {

	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String RequeqstURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String command = RequeqstURI.substring(ContextPath.length());

		// RequestURI=/model2/BoardAddAction.do
		// ContextPath=/model2
		// command=/BoardAddAction.do

		System.out.println("RequestURI = " + RequeqstURI);
		System.out.println("ContextPath = " + ContextPath);
		System.out.println("command = " + command);

		Action action = null;
		ActionForward forward = null;

		// 글 쓰기
		if (command.equals("/BoardAddAction.do")) {
			try {
				action = new BoardAddAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // 글 목록
		else if (command.equals("/BoardListAction.do")) {
			try {
				action = new BoardListAction();
				forward = action.execute(request, response);

			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			//글 작성 폼으로 이동(목록 화면에서 글쓰기 눌렀을 때)
		}else if(command.equals("/BoardWrite.do")) {
			System.out.println("BoardWrite");
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./board/board_write.jsp");
		}//상세 페이지
		else if(command.equals("/BoardDetailAction.do")) {
			try {
				action=new BoardDetailAction();
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			//댓글 폼에 갖고갈 정보 구해오기(부모정보)
		}else if(command.equals("/BoardReplyAction.do")) {
			try {
				action=new BoardReplyAction();
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			//댓글 달기
		}else if(command.equals("/BoardReplyView.do")) {
			try {
				action=new BoardReplyView();
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
		}
		

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}// (doProcess) end

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("get");

		doProcess(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		System.out.println("post");

		doProcess(request, response);
	}

}
