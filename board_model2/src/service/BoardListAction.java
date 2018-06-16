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
		
		int page_size=10;		//******기본변수1 한 화면에 보여 줄 글 갯수******
		int currentPage=0;	    //****기본변수2 현제 페이지******
		int count=0;			//******기본변수3 총 데이터 갯수******
		
		String page=request.getParameter("page");	//넘어온 page값 처리하는 부분
		if(page == null) {
			page="1";					//페이지값이 안넘어 왔으면 1로 초기화
		}
		
		currentPage=Integer.parseInt(page);//현제 page번호
		int startRow=(currentPage-1)*page_size+1;	//게시글을 화면에 뿌리는 시작행.데이터 보여주는 것은 DB의 rownum을 내림차순으로 보여준다.
		int endRow=currentPage*page_size;			//게시글을 화면에 뿌리는 마지막 행
		
		List<BoardBean> boardList=new ArrayList<>();	//글 리스트를 db에서 받아올 list타입 변수
		
		boardList=dao.getList(startRow,endRow);
		System.out.println("boardList:"+boardList);
		
		count=dao.getCount();
		System.out.println("count:"+count);
		
		int view_size=10; //몇 페이지씩 보여줄 것인가. 페이지 밑에 보면 다음 페이지로 넘기는 부분에 표시될 숫자 갯수.[이전][1][2][3][4][다음]
																						//[start]	[end]
		int startPage = (currentPage-1)/view_size*10+1;	//페이지넘기는 부분의 시작 num		
		
		int endPage = startPage+view_size-1;	//페이지 넘기는 부분의 끝 num
		int maxPage = (count/page_size) + (((count%page_size)==0) ? 0:1) ;
		
		if(endPage>maxPage) {//하단에 보여줄 마지막 페이지가 maxPage보다 크면 안됨
			endPage=maxPage;
		}
		
		request.setAttribute("page", page);//현제페이지
		request.setAttribute("count", count);//페이지사이즈
		request.setAttribute("boardList", boardList);//게시글리스트
		request.setAttribute("startPage", startPage);//하단에 띄울 시작페이지
		request.setAttribute("endPage", endPage);//하단에 띄울 마지막 페이지
		request.setAttribute("maxPage", maxPage);//총 페이지 수 
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/board_list.jsp");
		
		
		return forward;
	}

}
