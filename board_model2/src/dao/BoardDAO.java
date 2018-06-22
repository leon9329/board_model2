package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardBean;

public class BoardDAO {
	//싱글톤
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	//커넥션 풀 
	public Connection getConnection() throws Exception{
		
		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/Orcl");
  		return ds.getConnection();
	}
	
	//글 작성
	public int insert(BoardBean board) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			String sql="insert into model2 values(model2_seq.nextval,"
					+ "?,?,?,?,?,model2_seq.nextval,?,?,?,sysdate)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_pass());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, board.getBoard_file());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		return result;
	}
	
	//화면에 출력할 게시글 리스트를 구하는 함수
	public List<BoardBean> getList(int start, int end) {
		List<BoardBean> boardList=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql="select * from(select rownum rnum,board_num,board_name,board_pass,board_subject,"
					+ "board_content,board_file,board_re_ref,board_re_lev,board_re_seq,"
					+ "board_readcount,board_date from (select * from model2 order by board_re_ref desc,"
					+ "board_re_seq asc)) where rnum >= ? and rnum <= ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean board=new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_pass(rs.getString("board_pass"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getTimestamp("board_date"));
				
				boardList.add(board);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		return boardList;
	}
	
	//데이터 갯수 얻는 함수
	public int getCount() {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			String sql="select count(*) from model2";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		return result;
	
	}
	
	//조회수 1증가 + 해당 글의 정보 갖고오기(1개)
	public BoardBean updateCount(int num) {
		BoardBean board=new BoardBean();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
			conn=getConnection();
			
			String sql="update model2 set board_readcount=board_readcount+1 where board_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
	
			sql="select * from model2 where board_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_pass(rs.getString("board_pass"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getTimestamp("board_date"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		
		
		return board;
	}
	//게시글 1개 갖고오기
	public BoardBean getContent(int num) {
		BoardBean board=new BoardBean();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			
			String sql="select * from model2 where board_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_pass(rs.getString("board_pass"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getTimestamp("board_date"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		return board;
	}
	//댓글 달기
	public int reply(BoardBean board) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql="update model2 set board_re_seq=board_re_seq+1"
					+ " where board_re_ref=? and board_re_seq > ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_re_ref());
			pstmt.setInt(2, board.getBoard_re_seq());
			pstmt.executeUpdate();
			
			sql="insert into model2 values(model2_seq.nextval,"
					+ "?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_pass());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, "");
			pstmt.setInt(6, board.getBoard_re_ref());
			pstmt.setInt(7, board.getBoard_re_lev()+1);
			pstmt.setInt(8, board.getBoard_re_seq()+1);
			pstmt.setInt(9, 0);
			
			result=pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
	
	//정보 수정
	public int setUpdate(BoardBean board) {
		int result=0;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			String sql="update model2 set board_name=?,board_subject=?,board_content=?,board_file=? where board_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setString(4, board.getBoard_file());
			pstmt.setInt(5, board.getBoard_num());
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
	//삭제
	public int delete(int num) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			String sql="delete from model2 where board_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		return result;
	}
}
