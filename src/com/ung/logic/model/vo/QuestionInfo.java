package com.ung.logic.model.vo;

import java.util.StringTokenizer;

public class QuestionInfo {
	private final int QCODE;
	private String QName;
	private String size;
	private String Level;
	private int rowSize;
	private int columSize;
	private boolean clear;
	
	public QuestionInfo(int QCode,String QName,String size,String Level) {
		this.QCODE=QCode;
		this.QName=QName;
		this.size=size;
		this.clear=false;
		StringTokenizer st=new StringTokenizer(size,"*");
		this.rowSize=Integer.parseInt(st.nextToken());
		this.columSize=Integer.parseInt(st.nextToken());
	}
	public String getQName() {
		return QName;
	}
	public void setQName(String qName) {
		QName = qName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getRowSize() {
		return rowSize;
	}
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}
	public int getColumSize() {
		return columSize;
	}
	public void setColumSize(int columSize) {
		this.columSize = columSize;
	}
	public boolean isClear() {
		return clear;
	}
	public void setClear(boolean clear) {
		this.clear = clear;
	}
	public int getQCODE() {
		return QCODE;
	}
	@Override
	public String toString() {
		return " 문제번호 "+QCODE+" 문제이름 : "+QName+" 크기 : "+size+" 클리어여부 : "+clear;
	}
	
	
}
