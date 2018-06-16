package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardBean;

public class BoardListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardListAction");
		
		BoardDAO dao=BoardDAO.getInstance();
		
		int page_size=10;		//******�⺻����1 �� ȭ�鿡 ���� �� �� ����******
		int currentPage=0;	    //****�⺻����2 ���� ������******
		int count=0;			//******�⺻����3 �� ������ ����******
		
		String page=request.getParameter("page");	//�Ѿ�� page�� ó���ϴ� �κ�
		if(page == null) {
			page="1";					//���������� �ȳѾ� ������ 1�� �ʱ�ȭ
		}
		
		currentPage=Integer.parseInt(page);//���� page��ȣ
		int startRow=(currentPage-1)*page_size+1;	//�Խñ��� ȭ�鿡 �Ѹ��� ������.������ �����ִ� ���� DB�� rownum�� ������������ �����ش�.
		int endRow=currentPage*page_size;			//�Խñ��� ȭ�鿡 �Ѹ��� ������ ��
		
		List<BoardBean> boardList=new ArrayList<>();	//�� ����Ʈ�� db���� �޾ƿ� listŸ�� ����
		
		boardList=dao.getList(startRow,endRow);
		System.out.println("boardList:"+boardList);
		
		count=dao.getCount();
		System.out.println("count:"+count);
		
		int view_size=10; //�� �������� ������ ���ΰ�. ������ �ؿ� ���� ���� �������� �ѱ�� �κп� ǥ�õ� ���� ����.[����][1][2][3][4][����]
																						//[start]	[end]
		int startPage = (currentPage-1)/view_size*10+1;	//�������ѱ�� �κ��� ���� num		
		
		int endPage = startPage+view_size-1;	//������ �ѱ�� �κ��� �� num
		int maxPage = (count/page_size) + (((count%page_size)==0) ? 0:1) ;
		
		if(endPage>maxPage) {//�ϴܿ� ������ ������ �������� maxPage���� ũ�� �ȵ�
			endPage=maxPage;
		}
		
		request.setAttribute("page", page);//����������
		request.setAttribute("count", count);//������������
		request.setAttribute("boardList", boardList);//�Խñ۸���Ʈ
		request.setAttribute("startPage", startPage);//�ϴܿ� ��� ����������
		request.setAttribute("endPage", endPage);//�ϴܿ� ��� ������ ������
		request.setAttribute("maxPage", maxPage);//�� ������ �� 
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/board_list.jsp");
		
		
		return forward;
	}

}
