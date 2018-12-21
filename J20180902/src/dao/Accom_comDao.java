package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Accom_comDao {
	private static Accom_comDao instance;
	private Accom_comDao() {}
	public static Accom_comDao getInstance() {
		if (instance == null) {	
			instance = new Accom_comDao();		
			}
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
	// Service -> DAO (6 단계)
	public int getaccom() throws SQLException {
		Connection conn = null;	
		Statement stmt= null; 
		ResultSet rs = null;    
		int tot = 0;
		String sql = "select * from accom inner join aphoto" + 
				"on accom.accom_no = aphoto.accom_no" +
				"where accom_no = ?";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}

	public List<Acphoto> listAphoto(int accom_no,int com_no) throws SQLException {
		List<Acphoto> list = new ArrayList<Acphoto>();
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		// String sql = "select * from board order by num desc";
		// mysql select * from board order by num desc limit startPage-1,10;
		 String sql = "SELECT  * FROM  aphoto  WHERE accom_no=? and com_no=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accom_no);
			pstmt.setInt(2, com_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Acphoto acphoto = new Acphoto();
			
				acphoto.setAccom_no(rs.getInt("accom_no"));
				acphoto.setPhoto(rs.getString("photo"));
				acphoto.setCom_no(rs.getInt("com_no"));
					
				list.add(acphoto);
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
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int tot = 0;
		PreparedStatement pstmt		= null; 
		String sql = "select count(*) from Accom_com ";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
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
	public int TotalCnt(int accom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt		= null;
		ResultSet rs = null;
		int tot = 0;
		 
		String sql = "select count(*) from Accom_com where accom_no=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accom_no);
			rs = pstmt.executeQuery();
			if (rs.next())
				tot = rs.getInt(1);
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
		return tot;
	}
	public List<Accom_com> listAccom_com(int startRow, int endRow,int accom_no) throws SQLException {
		List<Accom_com> list = new ArrayList<Accom_com>();		
		Connection conn= null; PreparedStatement pstmt = null;
		ResultSet rs = null;	 	
//		String sql = "SELECT * FROM (select rownum rn ,a.*, AP.PHOTO from  (select * from accom_com order by accom_no desc) a ,\r\n" + 
//				"				                                          aphoto ap \r\n" + 
//				"				                                     where a.accom_no = ap.accom_no \r\n" + 
//				"				                                     and ap.pho_no = (SELECT MAX(PHO_NO) FROM aphoto WHERE accom_no = a.accom_no) \r\n" + 
//				"				             ) aa\r\n" + 
//				"				where rn between ? and ? and accom_no=?";
		String sql = "SELECT * FROM (select rownum rn ,a.*, AP.PHOTO from  (select * from accom_com order by accom_no desc) a ,\n" + 
				"												                                          acphoto ap \n" + 
				"                                where a.accom_no = ap.accom_no and a.com_no=ap.com_no) aa where rn between ? and ? and aa.accom_no=? order by aa.com_no desc";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, accom_no);	
			rs = pstmt.executeQuery();
		
			while (rs.next()) {				
				Accom_com accom_com = new Accom_com();
				accom_com.setAccom_no(rs.getInt("accom_no"));
				accom_com.setUser_id(rs.getString("user_id"));			
				accom_com.setReview_cont(rs.getString("review_cont"));	
				accom_com.setAccom_rat(rs.getFloat("accom_rat"));
				accom_com.setSaving_pnt(rs.getInt("saving_pnt"));
				accom_com.setPhoto(rs.getString("photo"));
				accom_com.setCom_no(rs.getInt("com_no"));	
				list.add(accom_com);				
			} 		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public Accom_com select1(int num, int accom_no ) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt= null; ResultSet rs = null;
		String sql = "select * from accom_com where com_no=? and accom_no=?";		
		Accom_com accom_com = new Accom_com();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, accom_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				accom_com.setAccom_no(rs.getInt("accom_no"));
				accom_com.setAccom_rat(rs.getFloat("accom_rat"));
				accom_com.setSaving_pnt(rs.getInt("saving_pnt"));
				accom_com.setReview_cont(rs.getString("review_cont"));
				accom_com.setUser_id(rs.getString("user_id"));
				accom_com.setCom_no(rs.getInt("com_no"));
			}

		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return accom_com;
	}
	
	public Accom select(int accom_no) throws SQLException {
		Connection conn = null;	Statement stmt= null; ResultSet rs = null;
		Statement stmt2= null; ResultSet rs2 = null;
		String sql = "select * from accom where accom_no="+accom_no;	
		String sql2 = "select accom_no,round(avg(accom_rat),2) from accom_com where accom_no="+accom_no+"group by (accom_no)";
		Accom accom = new Accom();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(sql2);
			if (rs.next()) {
				accom.setAccom_no(rs.getInt("accom_no"));
				accom.setAccom_name(rs.getString("accom_name"));
				accom.setAccom_type(rs.getString("accom_type"));
				accom.setAccom_addr(rs.getString("accom_addr"));				
				accom.setAccom_func(rs.getString("accom_func"));
				accom.setAccom_map(rs.getString("accom_map"));
				accom.setAccom_cont(rs.getString("accom_cont"));
				if(rs2.next()){
					accom.setAccom_rat(rs2.getFloat("round(avg(accom_rat),2)"));
				}
				accom.setInout(rs.getString("inout"));
				accom.setArea(rs.getString("area"));
			}

		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (stmt != null) stmt.close();
			if (conn !=null) conn.close();
		}
		return accom;
	}
	
	public List<Accom_com> listCom_no(int accom_no) throws SQLException {
		List<Accom_com> list = new ArrayList<Accom_com>();		
		Connection conn= null; PreparedStatement pstmt = null;
		ResultSet rs = null;	 	
		String sql = "select * \r\n" + 
				"from accom_com a, acphoto ac \r\n" + 
				"where a.accom_no=ac.accom_no and a.com_no=ac.com_no and a.accom_no=? order by a.com_no desc";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accom_no);					
			rs = pstmt.executeQuery();
		
			while (rs.next()) {				
				Accom_com accom_com = new Accom_com();
				accom_com.setAccom_no(rs.getInt("accom_no"));
				accom_com.setUser_id(rs.getString("user_id"));			
				accom_com.setReview_cont(rs.getString("review_cont"));	
				accom_com.setAccom_rat(rs.getFloat("accom_rat"));
				accom_com.setSaving_pnt(rs.getInt("saving_pnt"));
				accom_com.setCom_no(rs.getInt("com_no"));
				accom_com.setPhoto(rs.getString("photo"));
				list.add(accom_com);				
			} 		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public List<Accom_com> listAccom_comNo(int accom_no) throws SQLException {
		List<Accom_com> list = new ArrayList<Accom_com>();		
		Connection conn= null; PreparedStatement pstmt = null;
		ResultSet rs = null;	 	
		String sql = "select rownum rn, a.* from (SELECT * FROM accom_com \n" + 
				"LEFT OUTER JOIN acphoto\n" + 
				"ON accom_com.com_no = acphoto.com_no and accom_com.accom_no=acphoto.accom_no where accom_com.accom_no=?)a";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accom_no);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {				
				Accom_com accom_com = new Accom_com();
				accom_com.setAccom_no(rs.getInt("accom_no"));
				accom_com.setUser_id(rs.getString("user_id"));			
				accom_com.setReview_cont(rs.getString("review_cont"));	
				accom_com.setAccom_rat(rs.getFloat("accom_rat"));
				accom_com.setSaving_pnt(rs.getInt("saving_pnt"));
				accom_com.setPhoto(rs.getString("photo"));
				accom_com.setCom_no(rs.getInt("com_no"));	
				list.add(accom_com);				
			} 		
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public int scrapDel(int accom_no, String user_id) throws SQLException {
		Connection conn 	= null;	
		PreparedStatement pstmt		= null; 
		ResultSet rs 		= null;    
		int resultScrap = 0;
		String sql = "delete scrap where accom_no=? and user_id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, accom_no);
			pstmt.setString(2, user_id);
			resultScrap = pstmt.executeUpdate();
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) 		rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn !=null) 	conn.close();
		}
		return resultScrap;
	}
	public Scrap selectScrap (int accom_no) throws SQLException {
		Scrap scrap = new Scrap();
		Connection conn 		= null;		
		PreparedStatement pstmt			= null; 
		ResultSet rs 			= null;		
		String sql= "select *from scrap where accom_no=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);				
			pstmt.setInt(1, accom_no);
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
		String sql1 	= "select nvl(max(scrap_no),0) from scrap where user_id=? and scrap_cd='A'";
		String sql2  	= "insert into scrap values(?,?,?,null,?,?)";

		
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
			pstmt.setInt(4, 1);
			pstmt.setInt(5, scrap.getAccom_no());

			result = pstmt.executeUpdate();
			
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	public int likeCntBoard(int accom_no) throws SQLException {
		Connection conn 	= null;	
		PreparedStatement pstmt		= null; 
		ResultSet rs 		= null;    		
		int tot = 0;
		String sql = "select count(*) from scrap where accom_no=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, accom_no);
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
	
	public int checkScrap(int accom_no, String user_id) throws SQLException {
		Connection conn 	= null;	
		PreparedStatement pstmt		= null; 
		ResultSet rs 		= null;    
		int result9 = 0;
		String sql = "select scrap_cd from scrap where accom_no=? and user_id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, accom_no);
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
	public List<Accom_comDao> getList(int i, int totCnt) throws SQLException{
		Connection conn = null;	PreparedStatement pstmt= null;
		ResultSet rs = null;
		List<Accom_comDao> list = new ArrayList<Accom_comDao>();
		String sql = "select nvl(max(com_no),0) from accom_com where accom_no=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, accom_no);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1) + 1;  
			
			while (rs.next()) {
				Aphoto aphoto = new Aphoto();
			
				aphoto.setaccom_no(rs.getInt("accom_no"));
				aphoto.setPhoto(rs.getString("photo"));
				aphoto.setPho_no(rs.getInt("pho_no"));
					
				//list.add(aphoto);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return list;
	}
	public int getMaxcom_no(int accom_no) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			ResultSet rs = null;
		String sql="select nvl(max(com_no),0) from accom_com where accom_no=?";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accom_no);
			rs = pstmt.executeQuery();
			rs.next();
			
			result = rs.getInt(1) + 1;  
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}
		return result;
	}
	public int insert(Accom_com accom_com) throws SQLException{
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			ResultSet rs = null;
		String sql="insert into accom_com values(?,?,?,?,?,?)";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accom_com.getAccom_no());		
			pstmt.setString(2, accom_com.getUser_id());
			pstmt.setString(3, accom_com.getReview_cont());
			pstmt.setFloat(4, accom_com.getAccom_rat());
			pstmt.setInt(5, accom_com.getSaving_pnt());
			pstmt.setInt(6, accom_com.getCom_no());
		
			result = pstmt.executeUpdate();
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	public int insert(Acphoto acphoto) throws SQLException {
		Connection conn = null;	PreparedStatement pstmt= null; 
		int result = 0;			ResultSet rs = null;
		String sql="insert into acphoto values(?,?,?,?)";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, acphoto.getAccom_no());		
			pstmt.setString(2, acphoto.getUser_id());
			pstmt.setInt(3, acphoto.getCom_no());
			pstmt.setString(4, acphoto.getPhoto());
			result = pstmt.executeUpdate();
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	
}