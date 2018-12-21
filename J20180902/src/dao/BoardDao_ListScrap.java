package dao;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao_ListScrap {

private static BoardDao_ListScrap instance;

	private BoardDao_ListScrap() {
	}

	public static BoardDao_ListScrap getInstance() {
		if (instance == null) 
			instance = new BoardDao_ListScrap();
		
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {

			Context    ctx  = new InitialContext();
			DataSource ds   = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		int tot = 0;
		String sql = "select count(*) from BOARD";
		
        try {

			conn = getConnection();
			stmt = conn.createStatement();
			rs   = stmt.executeQuery(sql);
			
			if (rs.next())
				tot = rs.getInt(1);
		
        } catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return tot;
	}

	public List<Board> list(int startRow, int endRow) throws SQLException {
		List<Board> list = new ArrayList<Board>();		
		Connection conn         = null; 
		
		String hashTag = "";

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2= null;

		ResultSet rs 			= null;
		ResultSet rs2			= null;
		
        System.out.println("*****Scrap list Start...");
	
		String sql = "SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from  (select * from board order by scrap_cnt desc) a ,\r\n" + 
				"                                           bphoto  bp\r\n" + 
				"                                     where a.posting_no = bp.posting_no\r\n" + 
				"                                     and bp.pho_no = (select MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no) \r\n" + 
				"             ) aa\r\n" + 
				"where rn between ? and ? order by scrap_cnt desc";
		
		String hashTagSql = "select hashtag from hashtag where posting_no = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);			
			rs = pstmt.executeQuery();


			while (rs.next()) {
				
				//해시태그
				pstmt2 = conn.prepareStatement(hashTagSql);
				pstmt2.setInt(1,rs.getInt("posting_no"));
				rs2	= pstmt2.executeQuery();
				
				while (rs2.next()) {
					hashTag += rs2.getString("hashTag");
				}
				System.out.println("*********************" + hashTag);

				Board board = new Board();
				board.setPosting_no(rs.getInt("posting_no"));
				board.setBoard_title(rs.getString("board_title"));
				board.setLook(rs.getInt("look"));
				board.setScrap_cnt(rs.getInt("scrap_cnt"));		
				board.setUser_id(rs.getString("user_id"));	
				board.setPhoto(rs.getString("photo"));		
				board.setHashTag(hashTag);
				
				list.add(board);				

				System.out.println("**************board_look:" + rs.getInt("look"));
				System.out.println("board_title->"+rs.getString("board_title"));
				
				hashTag = "";

			} 

			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn !=null)	conn.close();
		}
		return list;
	}
}
