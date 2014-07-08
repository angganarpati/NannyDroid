package com.neti.question.model;

public class QuestionPsikologi {
	private int ID;
	private String QUESTION;
	private String A;
	private String B;
	private String C;
	private String D;
	private String ANSWER;
	public QuestionPsikologi()
	{
		ID=0;
		QUESTION="";
		A="";
		B="";
		C="";
		D="";
		ANSWER="";
	}
	public QuestionPsikologi(String qUESTION, String A, String B, String C, String D, 
			String aNSWER) {
		
		QUESTION = qUESTION;
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;
		ANSWER = aNSWER;
	}
	public int getID()
	{
		return ID;
	}
	public String getQUESTION() {
		return QUESTION;
	}
	public String getA() {
		return A;
	}
	public String getB() {
		return B;
	}
	public String getC() {
		return C;
	}
	public String getD() {
		return D;
	}
	
	public String getANSWER() {
		return ANSWER;
	}
	public void setID(int id)
	{
		ID=id;
	}
	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}
	public void setA(String A) {
		this.A = A;
	}
	public void setB(String B) {
		this.B = B;
	}
	public void setC(String C) {
		this.C = C;
	}
	public void setD(String D) {
		this.D = D;
	}
	public void setANSWER(String aNSWER) {
		ANSWER = aNSWER;
	}
	
}
