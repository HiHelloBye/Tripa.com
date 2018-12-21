package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class adminBoardDao {
	private static adminBoardDao instance;
	private adminBoardDao() {}
	public static adminBoardDao getInstance() {
		if (instance == null) {	instance = new adminBoardDao();		}
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { System.out.println(e.getMessage());	}
		return conn;
	}
	public List<Board> list(int startRow, int endRow) throws SQLException {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "select * from (select rownum rn ,a.* from " + 
					" (select * from board) a ) "+
					" where rn between ? and ? and user_id='admin'";
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Board board = new Board();
					//글번호, 글제목, 등록일, 유저아이디, 조회수
				
					board.setPosting_no(rs.getInt("posting_no"));
					board.setBoard_title(rs.getString("board_title"));
					board.setRegi_date(rs.getString("regi_date"));
					board.setUser_id(rs.getString("user_id"));
					board.setLook(rs.getInt("look"));
					list.add(board);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public int getTotalCnt() throws SQLException {
		Connection conn = null;	Statement stmt= null; 
		ResultSet rs = null;    int tot = 0;
		String sql = "select count(*) from board";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) tot=rs.getInt(1);
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
	
	public int getTotalCntAdmin() throws SQLException {
		Connection conn = null;	Statement stmt= null; 
		ResultSet rs = null;    int tot = 0;
		String sql = "select count(*) from board where user_id='admin'";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) tot=rs.getInt(1);
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
	public int delete(String posting_no, String user_pass) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;		    ResultSet rs = null;
		String sql1 = "select user_pass from member where user_pass=? and user_id='admin'";
		String sql="delete from board where posting_no=?";
		try {
			String dbPasswd = "";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, user_pass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPasswd = rs.getString(1); 
				if (dbPasswd.equals(user_pass)) {
					rs.close();  pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, posting_no);
					result = pstmt.executeUpdate();
				} else result = 0;
			} else result = -1;
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	public int insert(Board board) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			ResultSet rs = null;
		String sql1 = "select nvl(max(posting_no),0) from board";
		String sql="insert into board values(?,?,sysdate,null,?,'admin',0,null,null,null,null,null)";
		try {		
			System.out.println("****시작****");
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1) + 1;  
			rs.close();   
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, board.getBoard_title());
			pstmt.setString(3, board.getContent_text());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	public Board select(int posting_no) throws SQLException{
		Connection conn = null;	Statement stmt= null; ResultSet rs = null;
		String sql = "select * from board where posting_no="+posting_no;
		Board board = new Board();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {				
				board.setPosting_no(rs.getInt("posting_no"));
				board.setBoard_title(rs.getString("board_title"));
				board.setRegi_date(rs.getString("regi_date"));
				board.setSaving_pnt(rs.getInt("saving_pnt"));
				board.setContent_text(rs.getString("content_text"));
				board.setUser_id(rs.getString("user_id"));
				board.setLook(rs.getInt("look"));
				board.setInout(rs.getString("inout"));
				board.setArea(rs.getString("area"));
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return board;
	}
	public int update(Board board) throws SQLException{
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			
		String sql="update board set board_title=?,content_text=?, regi_date=sysdate where posting_no=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_title());
			pstmt.setString(2, board.getContent_text());
			
			pstmt.setInt(3, board.getPosting_no());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			conn.close();
			pstmt.close();
		}
		return result;
	}
	public List<Board> listBoard(int startRow, int endRow) throws SQLException{
		// TODO Auto-generated method stub
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "select * from (select rownum rn ,a.* from " + 
					" (select * from board) a ) "+
					" where rn between ? and ? and user_id!='admin'";
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Board board = new Board();
					//글번호, 글제목, 등록일, 유저아이디, 조회수
				
					board.setPosting_no(rs.getInt("posting_no"));
					board.setBoard_title(rs.getString("board_title"));
					board.setRegi_date(rs.getString("regi_date"));
					board.setUser_id(rs.getString("user_id"));
					board.setLook(rs.getInt("look"));
					list.add(board);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public int getNoticeTotalCnt() throws SQLException {
		Connection conn = null;	Statement stmt= null; 
		ResultSet rs = null;    int tot = 0;
		String sql = "select count(*) from board where user_id='admin'";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) tot=rs.getInt(1);
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
	
	public List<Board> listNoticeBoard(int startRow, int endRow) throws SQLException{
		// TODO Auto-generated method stub
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "select * from (select rownum rn ,a.* from " + 
					" (select * from board) a ) "+
					" where rn between ? and ? and user_id='admin'";
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Board board = new Board();
					//글번호, 글제목, 등록일, 유저아이디, 조회수
				
					board.setPosting_no(rs.getInt("posting_no"));
					board.setBoard_title(rs.getString("board_title"));
					board.setRegi_date(rs.getString("regi_date"));
					board.setUser_id(rs.getString("user_id"));
					board.setLook(rs.getInt("look"));
					list.add(board);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
}
