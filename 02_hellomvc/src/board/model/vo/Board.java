package board.model.vo;

import java.sql.Date;

/**
 * 
 * VO객체 ------- 행(레코드)와 짝을 이룬다.
 * 필드   -------- 컬럼
 *
 */
public class Board {
	
	private int no;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int readCount;
	private Attachment attach;
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Board(int no, String title, String writer, String content, Date regDate, int readCount, Attachment attach) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
		this.attach = attach;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Attachment getAttach() {
		return attach;
	}
	public void setAttach(Attachment attach) {
		this.attach = attach;
	}
	
	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", readCount=" + readCount + ", attach=" + attach + "]";
	}
	
	
}
