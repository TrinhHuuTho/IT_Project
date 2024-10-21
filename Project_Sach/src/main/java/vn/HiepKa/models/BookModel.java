package vn.HiepKa.models;

import java.io.Serializable;
import java.sql.Date;

public class BookModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int bookid;
    private String title;
    private int authorid; // Khóa ngoại từ bảng Author
    private String authorname; // Lưu tên tác giả để thuận tiện
    private String content;
    private Date createdat;
    private String imagesbook;
    
	public BookModel() {
		super();
	}

	public BookModel(int bookid, String title, int authorid, String authorname, String content, Date createdat,
			String imagesbook) {
		super();
		this.bookid = bookid;
		this.title = title;
		this.authorid = authorid;
		this.authorname = authorname;
		this.content = content;
		this.createdat = createdat;
		this.imagesbook = imagesbook;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	public String getImagesbook() {
		return imagesbook;
	}

	public void setImagesbook(String imagesbook) {
		this.imagesbook = imagesbook;
	}

	@Override
	public String toString() {
		return "BookModel [bookid=" + bookid + ", title=" + title + ", authorid=" + authorid + ", authorname="
				+ authorname + ", content=" + content + ", createdat=" + createdat + ", imagesbook=" + imagesbook + "]";
	}


    
    
}
