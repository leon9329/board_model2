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
@WebServlet("*.do") // �������� �޾ƾ� �ϱ� ������ �Ѱ��� �̸������ϸ� �ȵȴ�.
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

		// �� ����
		if (command.equals("/BoardAddAction.do")) {
			try {
				action = new BoardAddAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // �� ���
		else if (command.equals("/BoardListAction.do")) {
			try {
				action = new BoardListAction();
				forward = action.execute(request, response);

			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			//�� �ۼ� ������ �̵�(��� ȭ�鿡�� �۾��� ������ ��)
		}else if(command.equals("/BoardWrite.do")) {
			System.out.println("BoardWrite");
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./board/board_write.jsp");
		}//�� ������
		else if(command.equals("/BoardDetailAction.do")) {
			try {
				action=new BoardDetailAction();
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			//��� ���� ���� ���� ���ؿ���(�θ�����)
		}else if(command.equals("/BoardReplyAction.do")) {
			try {
				action=new BoardReplyAction();
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			//��� �ޱ�
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
