package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;
import javax.naming.*;

public class BoardDao_Single {
	private static BoardDao_Single instance;
	
	private BoardDao_Single() {}
	public static BoardDao_Single getInstance() {
		if (instance == null) 	
			instance = new BoardDao_Single();		
		
			return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		
		try {
			
			Context ctx 	= new InitialContext();
			DataSource ds 	= (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn 			= ds.getConnection();
		
		} catch(Exception e) {  System.out.println(e.getMessage());	}
		return conn;
	}
	
	public List<Bphoto> listPhoto(int posting_no) throws SQLException {
		List<Bphoto> list		= new ArrayList<Bphoto>();
		Connection conn 		= null;	
		PreparedStatement pstmt	= null;
		ResultSet rs 			= null;
		
		String sql = "SELECT  bp.posting_no, bp.photo,bp.pho_no FROM    bphoto bp WHERE bp.posting_no=?";

		try {
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setInt(1, posting_no);
			rs 		= pstmt.executeQuery();
			
			while (rs.next()) {
				Bphoto bphoto = new Bphoto();
			
				bphoto.setPosting_no(rs.getInt("posting_no"));
				bphoto.setPhoto(rs.getString("photo"));
				bphoto.setPho_no(rs.getInt("pho_no"));
					
				list.add(bphoto);
			}
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn !=null) 	conn.close();
		}
		return list;
	}
	
	
	
	public List<Board_Com> listComment(int posting_no) throws SQLException {
		List<Board_Com> list 	= new ArrayList<Board_Com>();
		Connection conn 		= null;	
		PreparedStatement pstmt	= null;
		ResultSet rs 			= null;
	
	 	String sql = "SELECT  * FROM board_com WHERE posting_no=? order by re_level,re_step";

		try {
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setInt(1, posting_no);
			rs 		= pstmt.executeQuery();
			
			while (rs.next()) {
				Board_Com board_Com = new Board_Com();
			
				board_Com.setPosting_no(rs.getInt("posting_no"));
				board_Com.setComm_no(rs.getInt("comm_no"));
				board_Com.setComments(rs.getString("comments"));
				board_Com.setUser_id(rs.getString("user_id"));
				board_Com.setRegi_date(rs.getString("regi_date"));
				board_Com.setRef(rs.getInt("ref"));	
				board_Com.setRe_step(rs.getInt("re_step"));
				board_Com.setRe_level(rs.getInt("re_level"));
				
				list.add(board_Com);
			}
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	
	//
	public int insert(Board board) throws SQLException {		
		Connection conn 		= null;	
		PreparedStatement pstmt	= null; 
		
		int result 		= 0;			
		ResultSet rs 	= null;
		String sql1 	= "select max(posting_no) from board";
		String sql2  	= "insert into board values(?,?,sysdate,?,?,?,?,?,?,?,?,?)";
		//String sql2 ="update board set re_step = re_step+1 where " +
		//	" ref=? and re_step > ?";
		
		try {			
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql1);
			rs 		= pstmt.executeQuery();
			
			rs.next();
			int posting_no = rs.getInt(1) +1;
			
			rs.close(); 
			pstmt.close();

			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt   (1, posting_no);
			pstmt.setString(2, board.getBoard_title());
			pstmt.setInt   (3, board.getSaving_pnt());
			pstmt.setString(4, board.getContent_text());
			pstmt.setString(5, board.getUser_id());
			pstmt.setInt   (6, board.getLook());
			pstmt.setInt   (7, board.getScrap_cnt());
			pstmt.setString(8, board.getInout());
			pstmt.setString(9, board.getArea());
			pstmt.setString(10,board.getStart_date());
			pstmt.setString(11, board.getEnd_date());
			
			result = pstmt.executeUpdate();
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}

	public Board select(int num) throws SQLException {
		Connection conn = null;	
		Statement stmt	= null; 
		ResultSet rs 	= null;
		String sql 		= "select * from board where posting_no=" + num;
		
		Board board 	= new Board();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs 	 = stmt.executeQuery(sql);
			
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
			if (rs !=null) 		rs.close();
			if (stmt != null) 	stmt.close();
			if (conn !=null) 	conn.close();
		}
		return board;
	}
	
	public String getDate() throws SQLException {
		String sysdate 			= null; 
		PreparedStatement pstmt	= null;
		Connection conn 		= null;
		ResultSet rs			= null;
		
		try {
        	conn 		= getConnection();
            String sql 	= "SELECT to_char(sysdate, 'yy/mm/dd') FROM dual";
 
            conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
			rs 		= pstmt.executeQuery();
			rs.next();
			sysdate = rs.getString(1);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());       		
		} finally {
			if (rs != null)   	rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn != null) 	conn.close();
		}
        return sysdate;
    }
	
	public void readCount(int num) throws SQLException {
		Connection conn 		= null;	
		PreparedStatement pstmt	= null; 
		
		String sql	= "update board set look=look+1 where posting_no=?";
		
		try {
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setInt(1, num);			
			pstmt.executeUpdate();
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		
		} finally {
			if (pstmt != null) 	pstmt.close();
			if (conn !=null) 	conn.close();
		}
	}
	
	public int update(Board board) throws SQLException {
		Connection conn 		= null;	
		PreparedStatement pstmt	= null; 
		
		String sql = "update board set board_title=?, content_text=? where posting_no=?";
		
		try {
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_title());	
			pstmt.setString(2, board.getContent_text());
			pstmt.setInt(3, board.getPosting_no());
			pstmt.executeUpdate();
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) 	pstmt.close();
			if (conn !=null)	conn.close();
		}
		return 1;
	}
	
	//delete문 수정해야함
	//건들지않음
	public int delete(String user_id, String user_pass,int num) throws SQLException {
		Connection conn 		= null;	
		PreparedStatement pstmt	= null; 
		ResultSet rs 			= null;
		
		int result				= 0;		    

		String sql1 = "select user_pass from member where user_id=?";
//		String sql="delete from board where posting_no=?";
		String sql ="delete from board where user_id=? and posting_no=?";
		
		try {
			String dbPasswd = "";
			
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql1);
			pstmt.setString(1, user_id);
			
			rs 		= pstmt.executeQuery();
			
			if (rs.next()) {
				dbPasswd = rs.getString(1); 
				
				if (dbPasswd.equals(user_pass)) {
					rs.close();  
					pstmt.close();
					
					pstmt 	= conn.prepareStatement(sql);
					pstmt.setString(1, user_id);
					pstmt.setInt(2, num);
					result 	= pstmt.executeUpdate();
				
				} else result = 0;
			
			} else result = -1;
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null)	 pstmt.close();
			if (conn !=null)	 conn.close();
		}
		return result;
	}
	
	public int getTotalCnt() throws SQLException {
		Connection conn 	= null;	
		Statement stmt		= null; 
		ResultSet rs 		= null;    
		
		int tot = 0;
		String sql = "select count(*) from board";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs 	 = stmt.executeQuery(sql);
			
			if (rs.next()) 
				tot = rs.getInt(1);

		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (stmt != null) 	stmt.close();
			if (conn !=null) 	conn.close();
		}
		return tot;
	}
	
	public int select2(int num) throws SQLException {
		Connection conn = null;	
		Statement stmt	= null; 
		ResultSet rs 	= null;

		String sql = "select count(posting_no) from hashtag where posting_no=" + num;
		
		int Pnum = 0;
		
		try {
			conn 	= getConnection();
			stmt 	= conn.createStatement();
			rs 		= stmt.executeQuery(sql);
			
			if (rs.next()) 
				Pnum = rs.getInt(1);
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return Pnum;
	}
	
	public String select3(int num, int a) throws SQLException {
		
		Connection conn 		= null;	
		PreparedStatement pstmt = null; 
		ResultSet rs 			= null;
		
		String sql 	= "select hashtag from hashtag where posting_no=? and hash_no=?";
		String B 	= null;
		
		try {
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, a);
			rs = pstmt.executeQuery();
			
			if (rs.next()) 
				B = rs.getString(1);
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn !=null) 	conn.close();
		}
		return B;
	}

	public Board select4 (int num, String user_id ) throws SQLException {
		Board board 	= new Board();
		
		Connection conn 		= null;	
		PreparedStatement pstmt	= null;
		Statement stmt			= null; 
		ResultSet rs 			= null;
		
		System.out.println("kkk select num->" + num);
		String sql= "select * from board bd, member mb where mb.user_id=? and posting_no=?" + num;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs 	 = stmt.executeQuery(sql);
			
			if (rs.next()) {
				board.setPosting_no(rs.getInt("posting_no"));
				board.setUser_id(rs.getString("user_id"));
				board.setBoard_title(rs.getString("board_title"));
				board.setContent_text(rs.getString("content_text"));
				board.setSaving_pnt(rs.getInt("saving_pnt"));
				board.setLook(rs.getInt("look"));
//				board.setIp(rs.getString("ip"));
				board.setRegi_date(rs.getString("regi_date"));
				board.setInout(rs.getString("inout"));
				board.setArea(rs.getString("area"));
//				board.setRef(rs.getInt("ref"));
//				board.setRe_level(rs.getInt("re_level"));
//				board.setRe_step(rs.getInt("re_step"));
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)   rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		return board;
	}
	
	public String loc(int num) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt	= null; 
		ResultSet rs 	= null; String loc = null;
		String A = null; String B = null;
		String sql = "select inout,area from board where posting_no=?";
		String sql2 = "select bmn, mnm from common where bcd=? and mcd=?";
		try {
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();
				A = rs.getString(1);
				B = rs.getString(2);
				
				
				rs.close(); pstmt.close();
				pstmt 	= conn.prepareStatement(sql2);
				pstmt.setString(1, A);
				pstmt.setString(2, B);
				rs 	= pstmt.executeQuery();
				rs.next();
				loc = rs.getString(1) + rs.getString(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)   rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return loc;
	}
	
	public String tripDate(int num) throws SQLException {
		String tripDate = null;
		Connection conn = null;	PreparedStatement pstmt	= null; 
		ResultSet rs 	= null; 
		String A = null; String B = null;
		String sql = "select start_date,end_date from board where posting_no=?";
			try {
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();
				A = rs.getString(1);
				B = rs.getString(2);
			
				tripDate = rs.getString(1) + " ~ " + rs.getString(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)   rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return tripDate;
	}
	
	
	public Scrap selectScrap (int num) throws SQLException {
		Scrap scrap = new Scrap();
		Connection conn 		= null;		
		PreparedStatement pstmt			= null; 
		ResultSet rs 			= null;		
		String sql= "select *from scrap where posting_no=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, num);
			rs 	 = pstmt.executeQuery();
			
			if (rs.next()) {				
				scrap.setUser_id(rs.getString(1));
				scrap.setScrap_cd(rs.getString(2));
				scrap.setScrap_no(rs.getInt(3));	
				scrap.setPosting_no(rs.getInt(4));
				scrap.setLike_check(rs.getInt(5));
				scrap.setAccom_no(rs.getInt(6));
				scrap.setCom_no(rs.getInt(7));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)   rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return scrap;
	}
	
	public int insertScrap(Scrap scrap,String user_id) throws SQLException {		
		Connection conn 		= null;	
		PreparedStatement pstmt	= null; 
		
		int result 		= 0;			
		ResultSet rs 	= null;
		String sql1 	= "select nvl(max(scrap_no),0) from scrap where user_id=? and scrap_cd='B'";
		String sql2  	= "insert into scrap values(?,?,?,?,?,null)";

		
		try {	
			conn 	= getConnection();
			pstmt 	= conn.prepareStatement(sql1);
			pstmt.setString(1, user_id);
			rs 		= pstmt.executeQuery();			
			rs.next();
			int num = rs.getInt(1)+1;
			System.out.println("****시작****"+num);
			
			rs.close(); 
			pstmt.close();	
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString   (1, user_id);
			//pstmt.setInt   (1, board.getPosting_no()+1);
			pstmt.setString(2, scrap.getScrap_cd());
			pstmt.setInt   (3, num);
			pstmt.setInt(4, scrap.getPosting_no());
			pstmt.setInt(5, scrap.getLike_check());

			result = pstmt.executeUpdate();
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	
	public int likeCntBoard(int num) throws SQLException {
		Connection conn 	= null;	
		PreparedStatement pstmt		= null; 
		ResultSet rs 		= null;    		
		int tot = 0;
		String sql = "select count(*) from scrap where posting_no=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs 	 = pstmt.executeQuery();
			
			if (rs.next()) 
				tot = rs.getInt(1);

		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn !=null) 	conn.close();
		}
		return tot;
	}
	
	public int checkScrap(int num, String user_id) throws SQLException {
		Connection conn 	= null;	
		PreparedStatement pstmt		= null; 
		ResultSet rs 		= null;    
		int result9 = 0;
		String sql = "select scrap_cd from scrap where posting_no=? and user_id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result9 = 1;
			} else { 
				result9 = 0;
			}
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn !=null) 	conn.close();
		}
		return result9;
	}
	
	public int scrapDel(int num, String user_id) throws SQLException {
		Connection conn 	= null;	
		PreparedStatement pstmt		= null; 
		ResultSet rs 		= null;    
		int result8 = 0;
		String sql = "delete scrap where posting_no=? and user_id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, user_id);
			result8 = pstmt.executeUpdate();
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn !=null) 	conn.close();
		}
		return result8;
	}
	public int delete(String user_id, int num) throws SQLException {
		Connection conn 		= null;	
		PreparedStatement pstmt	= null; 
		ResultSet rs 			= null;		
		int result				= 0;		    

//		String sql1 = "select user_pass from member where user_id=?";
//		String sql="delete from board where posting_no=?";
		String sql ="delete from board where user_id=? and posting_no=?";
		
		try {
			conn 	= getConnection();
		
				pstmt 	= conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, num);
				result 	= pstmt.executeUpdate();
		
		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null)	 pstmt.close();
			if (conn !=null)	 conn.close();
		}
		return result;
	}
	
	public int scrapUpdate(int likeCntBoard, int num) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result2 = 0;			
		String sql="update board set scrap_cnt=? where posting_no=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, likeCntBoard);
			pstmt.setInt(2, num);
						
			result2 = pstmt.executeUpdate();
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result2;
	}
	public int ascPnt(String user_id,int saving_pnt) throws SQLException {
		int result2 = 0;
		Connection conn = null;	PreparedStatement pstmt= null; ResultSet rs = null;
		String sql = "update member set saving_pnt=saving_pnt+? where user_id =?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, saving_pnt);
			pstmt.setString(2, user_id);
			
			result2 = pstmt.executeUpdate();
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result2;
	}
	
	public String getHashtag(int posting_no) throws SQLException {
		Connection conn			= null; 
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
	
		String hashTag = "";

		String hashTagSql = "select hashtag from hashtag where posting_no = ?";

		try {
			
			conn = getConnection();			
		
			//해시태그
			pstmt = conn.prepareStatement(hashTagSql);
			pstmt.setInt(1, posting_no);
			rs	= pstmt.executeQuery();
				
			while (rs.next()) {
				hashTag += " " + rs.getString("hashTag");
			}
			
			System.out.println("*********************" + hashTag);
				
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs 	  	!= null)  	rs.close();
			if (pstmt 	!= null) 	pstmt.close();
			if (conn  	!=null)  	conn.close();
		}
		return hashTag;
	}
	
	public int ascUser_Pnt(String user_id) throws SQLException {
		int result9 = 0;
		Connection conn = null;	PreparedStatement pstmt= null; ResultSet rs = null;
		String sql = "update member set saving_pnt=saving_pnt+500 where user_id=?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			result9 = pstmt.executeUpdate();
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result9; 
	}
	public String getArea(String area)  throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs    = null;
		
		String sql = "select mcd from common where mnm=?";
		
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, area);			
				rs = pstmt.executeQuery();
				if (rs.next()) {
					area = rs.getString("mcd");
				}
				System.out.println("여기뽑히는지확인하기: "+area);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			if (rs 	  	!= null)  	rs.close();
			if (pstmt 	!= null) 	pstmt.close();
			if (conn  	!=null)  	conn.close();
		}
		return area;
	}			
	
	
}