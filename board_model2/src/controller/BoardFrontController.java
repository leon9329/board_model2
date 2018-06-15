package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.ActionForward;
import service.BoardAddAction;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.do")//여러개를 받아야 하기 때문에 한개의 이름으로하면 안된다.
public class BoardFrontController extends HttpServlet {
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String RequeqstURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String command = RequeqstURI.substring(ContextPath.length()); 

		//RequestURI=/model2/BoardAddAction.do
		//ContextPath=/model2
		//command=/BoardAddAction.do
		
		System.out.println("RequestURI = "+RequeqstURI);
		System.out.println("ContextPath = "+ContextPath);
		System.out.println("command = "+command);
		
		Action action=null;
		ActionForward forward=null;
		
		if(command.equals("/BoardAddAction.do")) {
			try {
				action=new BoardAddAction();
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}//(doProcess) end

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("get");
		
		doProcess(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		System.out.println("post");
		
		doProcess(request, response);
	}

}
