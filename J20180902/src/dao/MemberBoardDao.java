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

public class MemberBoardDao {
	private static MemberBoardDao instance;
	public MemberBoardDao() {}
	public static MemberBoardDao getInstance() {
		if (instance == null) {	instance = new MemberBoardDao();		}
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
	
    public int getTotalCnt() throws SQLException {
		Connection conn = null;	Statement stmt= null; 
		ResultSet rs = null;    int tot = 0;
		String sql = "select count(*) from member";
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
    
    public int getTotalCnt1(String user_id) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;    int tot = 0;
		System.out.println("user_id->" + user_id);
		String sql = "select count(*) from RESERVATION where user_id=?";
		System.out.println("sql1->" + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			System.out.println("sql2->" + sql);
			if (rs.next()) tot=rs.getInt(1);
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
	
    public int getTotalCnt2(String user_id) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;    int tot = 0;
		System.out.println("user_id->" + user_id);
		String sql = "select count(*) from board where user_id=?";
		System.out.println("sql1->" + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			System.out.println("sql2->" + sql);
			if (rs.next()) tot=rs.getInt(1);
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
    
    public int getTotalCnt3(String user_id) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;    int tot = 0;
		System.out.println("user_id->" + user_id);
		String sql = "select count(*) from scrap where user_id=?";
		System.out.println("sql1->" + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			System.out.println("sql2->" + sql);
			if (rs.next()) tot=rs.getInt(1);
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
    
    public int getTotalCnt4(String user_id) throws SQLException {
 		Connection conn = null;	
 		PreparedStatement pstmt= null; 
 		ResultSet rs = null;    int tot = 0;
 		System.out.println("user_id->" + user_id);
 		String sql = "select count(*) from accom_com where user_id=?";
 		System.out.println("sql1->" + sql);
 		try {
 			conn = getConnection();
 			pstmt = conn.prepareStatement(sql);
 			pstmt.setString(1, user_id);
 			rs = pstmt.executeQuery();
 			System.out.println("sql2->" + sql);
 			if (rs.next()) tot=rs.getInt(1);
 		} catch(Exception e) {	System.out.println(e.getMessage()); 
 		} finally {
 			if (rs !=null) rs.close();
 			if (pstmt != null) pstmt.close();
 			if (conn !=null) conn.close();
 		}
 		return tot;
 	}
	
    public List<Member> list(int startRow, int endRow, String user_id) throws SQLException {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "select * from (select rownum rn ,a.* from " + 
					" (select * from member) a ) "+
					" where rn between ? and ? and user_id =?";
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				pstmt.setString(3, user_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Member member = new Member();
					//���̵�, �̸�, �̸���, ��ȭ��ȣ, ������
					member.setUser_id(rs.getString("user_id"));
					member.setUser_name(rs.getString("user_name"));
					member.setUser_email(rs.getString("user_email"));
					member.setUser_cell(rs.getString("user_cell"));
					member.setSaving_pnt(rs.getInt("saving_pnt"));
					member.setTime(rs.getDate("time"));
					list.add(member);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
    
	public List<Reservation> list2(String user_id, int startRow, int endRow) throws SQLException {
		List<Reservation> list2 = new ArrayList<Reservation>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		System.out.println("MemberBoardDao.java->" + user_id);
		
		String sql = "SELECT T.*, a.accom_name FROM" + 
				" (SELECT rownum rn , R.*" + 
				" FROM reservation R" + 
				" WHERE R.user_id = ?) T, accom a" + 
				" WHERE T.rn between ? and ?" + 
				" and t.accom_no = a.accom_no";
		
		System.out.println(sql);
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				System.out.println();
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Reservation reservation = new Reservation();
					reservation.setRoom_no(rs.getString("room_no"));
					reservation.setReser_no(rs.getInt("reser_no"));
					reservation.setOut_date(rs.getString("out_date"));
					reservation.setIn_date(rs.getString("in_date"));
					reservation.setAccom_name(rs.getString("accom_name"));
					reservation.setAccom_no(rs.getInt("accom_no"));
					reservation.setFlag(rs.getInt("flag"));
					System.out.println("********************" + rs.getInt("flag"));
					list2.add(reservation);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list2;
	}
	
	public List<Board> list3(int startRow, int endRow, String user_id) throws SQLException {
		List<Board> list3 = new ArrayList<Board>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		
		String sql = "SELECT T.* FROM (SELECT rownum rn , B.*" +
				 					  " FROM board B, member M" +
				 					  " WHERE B.user_id = M.user_id) T" +
				 					  " WHERE T.rn between ? and ? and T.user_id=?";
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				pstmt.setString(3, user_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Board board = new Board();
					board.setPosting_no(rs.getInt("posting_no"));
					board.setBoard_title(rs.getString("board_title"));
					board.setRegi_date(rs.getString("regi_date"));
					board.setSaving_pnt(rs.getInt("saving_pnt"));
					list3.add(board);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list3;
	}
	public List<Accom_com> list4(int startRow, int endRow, String user_id) throws SQLException {
		List<Accom_com> list4 = new ArrayList<Accom_com>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		
		String sql = "SELECT T.* FROM (SELECT rownum rn , C.*,A.accom_name\r\n" + 
				"							 					   FROM accom_com C, accom A\r\n" + 
				"								 					   WHERE C.accom_no= A.accom_no and C.user_id=?) T                                                     \r\n" + 
				"								 					   WHERE T.rn between ? and ? order by accom_no,com_no asc";
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Accom_com accom_com = new Accom_com();
					accom_com.setAccom_name(rs.getString("accom_name"));
					accom_com.setAccom_no(rs.getInt("accom_no"));
					accom_com.setReview_cont(rs.getString("review_cont"));
					accom_com.setAccom_rat(rs.getFloat("accom_rat"));
					accom_com.setCom_no(rs.getInt("com_no"));
					accom_com.setSaving_pnt(rs.getInt("saving_pnt"));
					list4.add(accom_com);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list4;
	}

	public List<Scrap> listReview4(int startRow, int endRow, String user_id) throws SQLException {
		List<Scrap> listReview4 = new ArrayList<Scrap>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "SELECT T.*, B.board_title FROM" + 
				" (SELECT rownum rn , S.*" + 
				" FROM scrap S, member M" + 
				" WHERE S.user_id = M.user_id) T, board B" + 
				" WHERE T.rn between ? and ? and T.user_id=?" + 
				" and T.posting_no = B.posting_no";
		
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				pstmt.setString(3, user_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Scrap scrap = new Scrap();
					scrap.setScrap_no(rs.getInt("scrap_no"));
					scrap.setPosting_no(rs.getInt("posting_no"));
					scrap.setBoard_title(rs.getString("board_title"));
					listReview4.add(scrap);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return listReview4;
	}
	
	public List<Scrap> listTravel4(int startRow, int endRow, String user_id) throws SQLException {
		List<Scrap> listTravel4 = new ArrayList<Scrap>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "SELECT T.*, A.accom_name FROM" + 
				" (SELECT rownum rn , S.*" + 
				" FROM scrap S, member M" + 
				" WHERE S.user_id = M.user_id) T, accom A" + 
				" WHERE T.rn between ? and ? and T.user_id=?" + 
				" and T.accom_no = A.accom_no";
		
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				pstmt.setString(3, user_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Scrap scrap = new Scrap();
					scrap.setScrap_no(rs.getInt("scrap_no"));
					scrap.setAccom_no(rs.getInt("accom_no"));
					scrap.setAccom_name(rs.getString("accom_name"));
					listTravel4.add(scrap);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return listTravel4;
	}
    public int ReservationDelete(int reser_no) throws SQLException { 
		Connection conn = null;
		PreparedStatement pstmt = null;
 	   	String SQL= "DELETE FROM RESERVATION WHERE reser_no = ?";
 	    
 	    try {
			conn = getConnection();
 	        pstmt = conn.prepareStatement(SQL);
 	        pstmt.setInt(1, reser_no);
			return pstmt.executeUpdate();
			
 	    } catch (Exception e) { 
 	        System.out.println(e.toString());
 	    } finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
 	    return -1; 
    }
    
    public int ReviewDelete(int posting_no) throws SQLException { 
		Connection conn = null;
		PreparedStatement pstmt = null;
 	   	String SQL= "DELETE FROM BOARD WHERE posting_no = ?";
 	    
 	    try {
			conn = getConnection();
 	        pstmt = conn.prepareStatement(SQL);
 	        pstmt.setInt(1, posting_no);
			return pstmt.executeUpdate();
			
 	    } catch (Exception e) { 
 	        System.out.println(e.toString());
 	    } finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
 	    return -1; 
    }
    public int ReviewDelete2(int accom_no, int com_no) throws SQLException { 
		Connection conn = null;
		PreparedStatement pstmt = null;
 	   	String SQL= "DELETE FROM accom_com WHERE accom_no = ? and com_no=?";
 	    
 	    try {
			conn = getConnection();
 	        pstmt = conn.prepareStatement(SQL);
 	        pstmt.setInt(1, accom_no);
 	       pstmt.setInt(2, com_no);
			return pstmt.executeUpdate();
			
 	    } catch (Exception e) { 
 	        System.out.println(e.toString());
 	    } finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
 	    return -1; 
    }
    
    public int ScrapDelete(int scrap_no) throws SQLException { 
		Connection conn = null;
		PreparedStatement pstmt = null;
 	   	String SQL= "DELETE FROM scrap WHERE scrap_no = ?";
 	    
 	    try {
			conn = getConnection();
 	        pstmt = conn.prepareStatement(SQL);
 	        pstmt.setInt(1, scrap_no);
			return pstmt.executeUpdate();
			
 	    } catch (Exception e) { 
 	        System.out.println(e.toString());
 	    } finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
 	    return -1; 
    }
    
    
    public int ascUser_Pnt(int reser_no, String user_id) throws SQLException {
    	
		int result9 = 0;
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = " UPDATE member SET saving_pnt = saving_pnt + "
				    + " (SELECT rsaving_pnt FROM reservation WHERE reser_no = ?) "
				    + " WHERE user_id = ?";
		
		System.out.println("ascUser_Pnt user_id - > " + user_id);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reser_no);
			pstmt.setString(2, user_id);
			result9 = pstmt.executeUpdate();
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result9;
	}
	public List<Scrap> listAccom(int startRow, int endRow, String user_id) throws SQLException {
		List<Scrap> listAccom = new ArrayList<Scrap>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "SELECT T.*, A.* FROM(SELECT rownum rn , S.* FROM scrap S, member M WHERE S.user_id = M.user_id) T, Accom A WHERE T.rn between ? and ? and T.user_id=? and T.accom_no = A.accom_no";
		
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				pstmt.setString(3, user_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Scrap scrap = new Scrap();
					scrap.setScrap_no(rs.getInt("scrap_no"));
					scrap.setBoard_title(rs.getString("accom_no"));
					listAccom.add(scrap);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return listAccom;
	}

}