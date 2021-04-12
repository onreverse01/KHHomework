package board.model.vo;

import java.sql.Date;

public class BoardEx extends Board{
	private int commentCount;
	
	public BoardEx() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardEx(int no, String title, String writer, String content, Date regDate, int readCount,
			Attachment attach) {
		super(no, title, writer, content, regDate, readCount, attach);
		// TODO Auto-generated constructor stub
	}

	public BoardEx(int commentCount) {
		super();
		this.commentCount = commentCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "BoardEx [commentCount=" + commentCount + ", getNo()=" + getNo() + ", getTitle()=" + getTitle()
				+ ", getWriter()=" + getWriter() + ", getContent()=" + getContent() + ", getRegDate()=" + getRegDate()
				+ ", getReadCount()=" + getReadCount() + ", getAttach()=" + getAttach() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
}
