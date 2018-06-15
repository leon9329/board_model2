package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardBean;

public class BoardDAO {
	//教臂沛
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	//目池记 钱 
	public Connection getConnection() throws Exception{
		
		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/Orcl");
  		return ds.getConnection();
	}
	
	//臂 累己
	public int insert(BoardBean board) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		
		try {
			conn=getConnection();
			String sql="insert into model2 values(model2_seq.nextval,?,?,?,?,?,model2_seq.nextval,?,?,?,sysdate";
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
	
	
}
