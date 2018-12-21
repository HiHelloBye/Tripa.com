package dao;

public class Scrap {
	private String user_id;
	private String scrap_cd;
	private int scrap_no;
	private int posting_no;
	private int like_check;
	private int accom_no;
	private int com_no;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getScrap_cd() {
		return scrap_cd;
	}
	public void setScrap_cd(String scrap_cd) {
		this.scrap_cd = scrap_cd;
	}
	public int getScrap_no() {
		return scrap_no;
	}
	public void setScrap_no(int scrap_no) {
		this.scrap_no = scrap_no;
	}
	public int getPosting_no() {
		return posting_no;
	}
	public void setPosting_no(int posting_no) {
		this.posting_no = posting_no;
	}
	public int getLike_check() {
		return like_check;
	}
	public void setLike_check(int like_check) {
		this.like_check = like_check;
	}
	public int getAccom_no() {
		return accom_no;
	}
	public void setAccom_no(int accom_no) {
		this.accom_no = accom_no;
	}
	public int getCom_no() {
		return com_no;
	}
	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}
	
	public String getAccom_name() {
		return accom_name;
	}
	public void setAccom_name(String accom_name) {
		this.accom_name = accom_name;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	private String accom_name;
	private String board_title;
}
