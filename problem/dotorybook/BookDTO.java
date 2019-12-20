package problem.dotorybook;

import java.sql.Date;

public class BookDTO {
	// 1. 변수
	private String bno;  
	private String bname; 
	private int price;        
	private String company; 
	private String writer; 
	private Date regdate;    
	
	// 2. 생성자
	public BookDTO() {}
	
	public BookDTO(String bno, String bname, int price, String company, String writer, Date regdate) {
		super();
		this.bno = bno;
		this.bname = bname;
		this.price = price;
		this.company = company;
		this.writer = writer;
		this.regdate = regdate;
	}
	
	public BookDTO(String bname, int price, String company, String writer) {
		super();
		this.bname = bname;
		this.price = price;
		this.company = company;
		this.writer = writer;
	}

	public BookDTO(String bno, String bname, int price, String company, String writer) {
		super();
		this.bno = bno;
		this.bname = bname;
		this.price = price;
		this.company = company;
		this.writer = writer;
	}

	// 3. getter setter
	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	// 4. toString()
	@Override
	public String toString() {
//		return "BookDTO [bno=" + bno + ", bname=" + bname + ", price=" + price + ", company=" + company + ", writer="
//				+ writer + ", regdate=" + regdate + "]";
		
		return bno + "\t" + bname + "\t" + price + "\t" + company + "\t"
		+ writer + "\t" + regdate;
	}
	
	
}
