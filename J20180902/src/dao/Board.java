package dao;

public class Board {
	private int	   posting_no;
	private String board_title;
	private String regi_date;
	private int    saving_pnt;
	private String content_text;
	private String user_id;
	private int    look;
	private int    scrap_cnt;
	private String inout;
	private String area;	
	// *******양으뜸: 협의필요
	private String photo;  
	// 해시태그용
	private String hashTag;
	private String start_date;
	private String end_date;

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

	public int getScrap_cnt() {
		return scrap_cnt;
	}
	
	public void setScrap_cnt(int scrap_cnt) {
		this.scrap_cnt = scrap_cnt;
	}

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public int getPosting_no() {
		return posting_no;
	}
	public void setPosting_no(int posting_no) {
		this.posting_no = posting_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(String regi_date) {
		this.regi_date = regi_date;
	}
	public int getSaving_pnt() {
		return saving_pnt;
	}
	public void setSaving_pnt(int saving_pnt) {
		this.saving_pnt = saving_pnt;
	}
	public String getContent_text() {
		return content_text;
	}
	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getLook() {
		return look;
	}
	public void setLook(int look) {
		this.look = look;
	}
	public String getInout() {
		return inout;
	}
	public void setInout(String inout) {
		this.inout = inout;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
	
	
}
