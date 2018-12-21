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

public class searchBoardDao {
	private static searchBoardDao instance;

	private searchBoardDao() {
	}

	public static searchBoardDao getInstance() {
		if (instance == null) {
			instance = new searchBoardDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public List<String> getKeyword(String keyword) throws SQLException{
		List<String> list 		= new ArrayList<String>();
		
		String resultKeyword=null;
		String resultInout=null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs    = null;
		String sql = "select inout, area from board where inout in (select replace(bcd,'_') from common where mnm=?) and area in (select mcd from common where mnm=?) and rownum = 1";
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			rs = pstmt.executeQuery();
			if(rs.next()){
				resultInout=rs.getString("inout");
				resultKeyword=rs.getString("area");
			}
			list.add(resultInout);
			list.add(resultKeyword);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			
		}
		return list;
	}

	public List getTotalCnt(String keyword) throws SQLException {
		List<Board> list 		= new ArrayList<Board>();	
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		
		Connection conn = null;
		ResultSet rs    = null;
		ResultSet rs2   = null;
		ResultSet rs3   = null;

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		int tot = 0;
		String sql = "select posting_no from BOARD where inout in (select replace(bcd,'_') from common where mnm=replace(?,'#')) and area in (select mcd from common where mnm=replace(?,'#')) and posting_no in (select posting_no from hashtag where hashtag like ?)";
		
		String sql2 = "select posting_no from BOARD where posting_no in (select posting_no from hashtag where hashtag like ?)";
		String sql3 = "select count(*) from BOARD where posting_no=? and posting_no in (select posting_no from hashtag where hashtag like ?)";
		try {
			if(!keyword.contains("#")){
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				pstmt.setString(3, "#%");
				rs = pstmt.executeQuery();
				while(rs.next()){
					tmp.add(rs.getInt(1));
				}
				
			}else if(keyword.contains("#")){
				conn = getConnection();
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, keyword);
				rs2 = pstmt2.executeQuery();
				
				while(rs2.next()){
					
					tmp.add(rs2.getInt(1));
					
					System.out.println("??????????????>>>>>>" + rs2.getInt(1));
					
					pstmt3 = conn.prepareStatement(sql3);
					pstmt3.setInt(1, rs2.getInt(1));
					pstmt3.setString(2,keyword);
					rs3 = pstmt3.executeQuery();
					
				}
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return tmp;
	}
	
	public List<Board> listBoard(int startRow, int endRow, String keyword) throws SQLException {
		List<Board> list 		= new ArrayList<Board>();		
		Connection conn			= null; 
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql=null;
		System.out.println("list Start...");
		sql = "SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from  (select * from board where inout in (select replace(bcd,'_') from common where mnm=?) and area in (select mcd from common where mnm=?) ) a ,bphoto  bp where a.posting_no = bp.posting_no and bp.pho_no = (SELECT MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no)) aa where rn between ? and ?";		
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);	
			
			rs = pstmt.executeQuery();
	
		
			while (rs.next()) {				
				Board board = new Board();
				board.setPosting_no(rs.getInt("posting_no"));
				board.setBoard_title(rs.getString("board_title"));
				board.setLook(rs.getInt("look"));
				board.setScrap_cnt(rs.getInt("scrap_cnt"));		
				board.setUser_id(rs.getString("user_id"));	
				board.setPhoto(rs.getString("photo"));	
				
				//추가
				board.setInout(rs.getString("inout"));	
				board.setArea(rs.getString("area"));		
				list.add(board);				

				System.out.println("**************board_look:" + rs.getInt("look"));
				System.out.println("board_title->"+rs.getString("board_title"));

			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs 	  !=null)  rs.close();
			if (pstmt != null) pstmt.close();
			if (conn  !=null)  conn.close();
		}
		return list;
	}

//	public List<Board> listBest(int start, int end) throws SQLException{
//		List<Board> list 		= new ArrayList<Board>();		
//		Connection conn			= null; 
//		PreparedStatement pstmt = null;
//		ResultSet rs 			= null;
//		System.out.println("listBest Start...");
//	
//		String sql = "SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from  (select * from board) a ,\r\n" + 
//				"                                           bphoto  bp\r\n" + 
//				"                                     where a.posting_no = bp.posting_no\r\n" + 
//				"                                     and bp.pho_no = (SELECT MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no) \r\n" + 
//				"             ) aa\r\n" + 
//				"where rn between ? and ? order by look desc";	
//		
//		try {
//			
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {				
//				Board board = new Board();
//				board.setPosting_no(rs.getInt("posting_no"));
//				board.setBoard_title(rs.getString("board_title"));
//				board.setLook(rs.getInt("look"));
//				board.setScrap_cnt(rs.getInt("scrap_cnt"));		
//				board.setUser_id(rs.getString("user_id"));	
//				board.setPhoto(rs.getString("photo"));	
//				list.add(board);				
//
//
//			}
//		} catch(Exception e) {	System.out.println(e.getMessage()); 
//		} finally {
//			if (rs 	  !=null)  rs.close();
//			if (pstmt != null) pstmt.close();
//			if (conn  !=null)  conn.close();
//		}
//		return list;
//	}

	public List<Board> listBest(int start, int end) throws SQLException{
		List<Board> list 		= new ArrayList<Board>();		
		Connection conn			= null;
		
		PreparedStatement pstmt = null;
		Statement stmt  = null;

		ResultSet rs1 			= null;
		ResultSet rs2 			= null;

		System.out.println("listBest Start...");
	
		String sql1 = "select *\r\n" + 
				"from (select row_number() over(order by look desc) rnum, board.* from board where user_id!='admin')\r\n" + 
				"where rnum between "+ start +" and " + end;
		
		String sql2 = "SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from  (select * from board) a ,\r\n" + 
				"                                           bphoto  bp\r\n" + 
				"                                     where a.posting_no = bp.posting_no\r\n" + 
				"                                     and bp.pho_no = (SELECT MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no) \r\n" + 
				"             ) aa\r\n" + 
				"where posting_no=?";
		
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs1 = stmt.executeQuery(sql1);

			while(rs1.next()) {
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, rs1.getInt("posting_no"));
				rs2 = pstmt.executeQuery();
				
				if(rs2.next()) {
					Board board = new Board();
					board.setPosting_no(rs2.getInt("posting_no"));
					board.setBoard_title(rs2.getString("board_title"));
					board.setLook(rs2.getInt("look"));
					board.setScrap_cnt(rs2.getInt("scrap_cnt"));		
					board.setUser_id(rs2.getString("user_id"));	
					board.setPhoto(rs2.getString("photo"));	
					list.add(board);
				}
			}

		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs1 	  !=null)  rs1.close();
			if (rs2 	  !=null)  rs2.close();
			if (pstmt != null) pstmt.close();
			if (conn  !=null)  conn.close();
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

	public List<Accom> listAccom(int start, int end) throws SQLException{
		// TODO Auto-generated method stub
		List<Accom> list 		= new ArrayList<Accom>();		
		Connection conn			= null; 
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		System.out.println("listAccom Start...");
	
		String sql = "SELECT * FROM (select rownum rn ,a.*, AP.PHOTO from  (select * from accom where accom_rat>=3.5) a ,aphoto  ap where a.accom_no = ap.accom_no and ap.pho_no = (SELECT MIN(PHO_NO) FROM aphoto WHERE accom_no = a.accom_no) ) aa where rn between ? and ? order by accom_rat desc";
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
	
		
			while (rs.next()) {				
				Accom accom = new Accom();
				accom.setAccom_no(rs.getInt("accom_no"));
				accom.setAccom_name(rs.getString("accom_name"));
				accom.setAccom_rat(rs.getInt("accom_rat"));	
				accom.setArea(rs.getString("area"));	
				accom.setPhoto(rs.getString("accom_photo"));		
				System.out.println("photo->"+rs.getString("accom_photo"));
				list.add(accom);				


			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs 	  !=null)  rs.close();
			if (pstmt != null) pstmt.close();
			if (conn  !=null)  conn.close();
		}
		return list;
	}
//	public List<Board> listBoardLook(int startRow, int totCntBorad, String inout, String area) 
//		throws SQLException{
//		List<Board> list 		= new ArrayList<Board>();		
//		Connection conn			= null; 
//		PreparedStatement pstmt = null;
//		ResultSet rs 			= null;
//		String sql=null;
//		System.out.println("list Start...");
//		sql = "SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from  (select * from board) a ,\r\n" + 
//				"                                           bphoto  bp\r\n" + 
//				"                                     where a.posting_no = bp.posting_no\r\n" + 
//				"                                     and bp.pho_no = (SELECT MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no) \r\n" + 
//				"             ) aa\r\n" + 
//				"where rn between ? and ? and inout=? and area=?"
//				+ "order by look desc";		
//		
//		try {
//			
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, startRow);
//			pstmt.setInt(2, totCntBorad);	
//			pstmt.setString(3, inout);
//			pstmt.setString(4, area);
//			rs = pstmt.executeQuery();
//	
//		
//			while (rs.next()) {				
//				Board board = new Board();
//				board.setPosting_no(rs.getInt("posting_no"));
//				board.setBoard_title(rs.getString("board_title"));
//				board.setLook(rs.getInt("look"));
//				board.setScrap_cnt(rs.getInt("scrap_cnt"));		
//				board.setUser_id(rs.getString("user_id"));	
//				board.setPhoto(rs.getString("photo"));	
//				
//				//추가
//				board.setInout(rs.getString("inout"));	
//				board.setArea(rs.getString("area"));		
//				list.add(board);				
//
//				System.out.println("**************board_look:" + rs.getInt("look"));
//				System.out.println("board_title->"+rs.getString("board_title"));
//
//			}
//		} catch(Exception e) {	System.out.println(e.getMessage()); 
//		} finally {
//			if (rs 	  !=null)  rs.close();
//			if (pstmt != null) pstmt.close();
//			if (conn  !=null)  conn.close();
//		}
//		return list;
//	}
	
	public List<Board> listBoardLook(int start, int end, String keyword) 
			throws SQLException{
			List<Board> list 		= new ArrayList<Board>();		
			
			Connection conn			= null; 
			PreparedStatement pstmt = null;
			
			
			ResultSet rs1 			= null;
			ResultSet rs2 			= null;

			System.out.println("list Start...");
			
			String sql1 = "select *\r\n" + 
					"from (select row_number() over(order by look desc) rnum, board.* from board where inout in (select replace(bcd,'_') from common where mnm=?) and area in (select mcd from common where mnm=?) )\r\n" + 
					"where rnum between " + start + " and " + end;
			
			String sql2 = "SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from  (select * from board) a ,\r\n" + 
					"                                           bphoto  bp\r\n" + 
					"                                     where a.posting_no = bp.posting_no\r\n" + 
					"                                     and bp.pho_no = (SELECT MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no) \r\n" + 
					"             ) aa\r\n" + 
					"where posting_no=?"
					+ "order by look desc";		
			
			try {
				
				conn = getConnection();
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				rs1 = pstmt.executeQuery();
				
				while(rs1.next()) {
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, rs1.getInt("posting_no"));
				
					rs2 = pstmt.executeQuery();
					
					if(rs2.next()) {
						Board board = new Board();
						board.setPosting_no(rs2.getInt("posting_no"));
						board.setBoard_title(rs2.getString("board_title"));
						board.setLook(rs2.getInt("look"));
						board.setScrap_cnt(rs2.getInt("scrap_cnt"));		
						board.setUser_id(rs2.getString("user_id"));	
						board.setPhoto(rs2.getString("photo"));	
						
						//추가
						board.setInout(rs2.getString("inout"));	
						board.setArea(rs2.getString("area"));		
						list.add(board);				

						System.out.println("**************board_look:" + rs2.getInt("look"));
						System.out.println("board_title->"+rs2.getString("board_title"));

					}
				}
				
			} catch(Exception e) {	System.out.println(e.getMessage()); 
			} finally {
				if (rs1 	  !=null)  rs1.close();
				if (rs2 	  !=null)  rs2.close();
				if (pstmt != null) pstmt.close();
				if (conn  !=null)  conn.close();
			}
		return list;
	}
	
	public List<Board> listBoardScrap(int start, int end, String keyword) throws SQLException{
			List<Board> list 		= new ArrayList<Board>();		
			
			Connection conn			= null; 
			Statement stmt  		= null;
			PreparedStatement pstmt = null;
			
			ResultSet rs1 			= null;
			ResultSet rs2			= null;

			
			System.out.println("listBoardScrap Start...");
		
			
			String sql1 = "select *\r\n" + 
					"from (select row_number() over(order by scrap_cnt desc) rnum, board.* from board where inout in (select replace(bcd,'_') from common where mnm=?) and area in (select mcd from common where mnm=?) )\r\n" + 
					"where rnum between " + start + " and " + end;
			
			String sql2 = "SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from  (select * from board ) a , bphoto  bp where a.posting_no = bp.posting_no  and bp.pho_no = (SELECT MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no)  ) aa where posting_no=?";
						

			try {	
					conn = getConnection();
					pstmt = conn.prepareStatement(sql1);
					pstmt.setString(1, keyword);
					pstmt.setString(2, keyword);
					rs1 = pstmt.executeQuery();
				
					while(rs1.next()) {
						pstmt = conn.prepareStatement(sql2);
						pstmt.setInt(1, rs1.getInt("posting_no"));
				
						rs2 = pstmt.executeQuery();
					
						if(rs2.next()) {
							Board board = new Board();
							board.setPosting_no(rs2.getInt("posting_no"));
							board.setBoard_title(rs2.getString("board_title"));
							board.setLook(rs2.getInt("look"));
							board.setScrap_cnt(rs2.getInt("scrap_cnt"));		
							board.setUser_id(rs2.getString("user_id"));	
							board.setPhoto(rs2.getString("photo"));	
							//추가
							board.setInout(rs2.getString("inout"));	
							board.setArea(rs2.getString("area"));		
							list.add(board);				
						}
					}
			} catch(Exception e) {	System.out.println(e.getMessage()); 
			} finally {
				if (rs1 	  !=null)  rs1.close();
				if (rs2 	  !=null)  rs2.close();
				if (pstmt != null) pstmt.close();
				if (conn  !=null)  conn.close();
			}
		return list;
	}

	
	
	
	
	public List<Board> getHashtag(int start, int end, String keyword)  throws SQLException{
		List<Board> list 		= new ArrayList<Board>();
		Connection conn			= null; 
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
	
		String sql="SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from  (select * from board where posting_no in (select posting_no from hashtag where hashtag=?)) a ,bphoto  bp where a.posting_no = bp.posting_no and bp.pho_no = (SELECT MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no)) aa where rn between ? and ?";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);	
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {		
				Board board = new Board();
				board.setPosting_no(rs.getInt("posting_no"));
				board.setBoard_title(rs.getString("board_title"));
				board.setLook(rs.getInt("look"));
				board.setScrap_cnt(rs.getInt("scrap_cnt"));		
				board.setUser_id(rs.getString("user_id"));	
				board.setPhoto(rs.getString("photo"));	
				
				//추가
				board.setInout(rs.getString("inout"));	
				board.setArea(rs.getString("area"));		
				list.add(board);				
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			if (rs 	  !=null)  rs.close();
			if (pstmt != null) pstmt.close();
			if (conn  !=null)  conn.close();
		}
		return list;
	}

	public List<Board> listHashtagLook(int start, int end, String keyword) throws SQLException{
		List<Board> list 		= new ArrayList<Board>();		
		
		Connection conn			= null; 
		
		Statement stmt= null; 
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		ResultSet rs			= null;
		ResultSet rs2			= null;
		
		System.out.println("listHashtagLook Start...");
	
		
		String sql1 = "SELECT * FROM (select rownum rn, a.* from (select * from board where posting_no in (select posting_no from hashtag where hashtag = ? ) order by look desc) a ) aa where rn between ? and ?";	
		
		String sql2 = "SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from (select * from board where posting_no = ? ) a ,bphoto  bp where a.posting_no = bp.posting_no and bp.pho_no = (SELECT MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no))";	
		
		try {	
				
				conn = getConnection();
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, keyword);	
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {

					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, rs.getInt("posting_no"));
					rs2=pstmt2.executeQuery();
//					
					if(rs2.next()){
						Board board = new Board();
						board.setPosting_no(rs2.getInt("posting_no"));
						board.setBoard_title(rs2.getString("board_title"));
						board.setLook(rs2.getInt("look"));
						board.setScrap_cnt(rs2.getInt("scrap_cnt"));		
						board.setUser_id(rs2.getString("user_id"));	
						board.setPhoto(rs2.getString("photo"));	
						//추가
						board.setInout(rs2.getString("inout"));	
						board.setArea(rs2.getString("area"));		
						list.add(board);	
					
					}
					
				}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs 	  !=null)  rs.close();
			if (rs2 	  !=null)  rs.close();

			if (pstmt != null) pstmt.close();
			if (pstmt2 != null) pstmt2.close();

			if (conn  !=null)  conn.close();
		}
	return list;
	}
	
	public List<Board> listHashtagScrap(int start, int end, String keyword) throws SQLException{
		List<Board> list 		= new ArrayList<Board>();		
		
		Connection conn			= null; 
		
		Statement stmt= null; 
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		ResultSet rs			= null;
		ResultSet rs2			= null;
		
		System.out.println("listHashtagLook Start...");
	
		
		String sql1 = "SELECT * FROM (select rownum rn, a.* from (select * from board where posting_no in (select posting_no from hashtag where hashtag = ? ) order by scrap_cnt desc) a ) aa where rn between ? and ?";	
		
		String sql2 = "SELECT * FROM (select rownum rn ,a.*, BP.PHOTO from (select * from board where posting_no = ? ) a ,bphoto  bp where a.posting_no = bp.posting_no and bp.pho_no = (SELECT MAX(PHO_NO) FROM bphoto WHERE posting_no = a.posting_no))";	
		
		try {	
				
				conn = getConnection();
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, keyword);	
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {

					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, rs.getInt("posting_no"));
					rs2=pstmt2.executeQuery();
//					
					if(rs2.next()){
						Board board = new Board();
						board.setPosting_no(rs2.getInt("posting_no"));
						board.setBoard_title(rs2.getString("board_title"));
						board.setLook(rs2.getInt("look"));
						board.setScrap_cnt(rs2.getInt("scrap_cnt"));		
						board.setUser_id(rs2.getString("user_id"));	
						board.setPhoto(rs2.getString("photo"));	
						//추가
						board.setInout(rs2.getString("inout"));	
						board.setArea(rs2.getString("area"));		
						list.add(board);	
					
					}
					
				}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs 	  !=null)  rs.close();
			if (rs2 	  !=null)  rs.close();

			if (pstmt != null) pstmt.close();
			if (pstmt2 != null) pstmt2.close();

			if (conn  !=null)  conn.close();
		}
	return list;
	}
}
