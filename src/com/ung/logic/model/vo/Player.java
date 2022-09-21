package com.ung.logic.model.vo;

import java.util.Set;
import java.util.TreeSet;

public class Player {
	private String playerId;
	private int CurrentCode;
	private char[][] currentData;//진행정보를 저장
	private Set<Integer> clearList;
	public Player() {
		
	}
	public Player(String playerId, int CurrentCode,char[][] currentData, Set<Integer> clearList) {
		this.playerId = playerId;
		this.CurrentCode=CurrentCode;
		this.currentData = currentData;
		this.clearList = clearList;
	}
	public Player(String playerId) {
		this.playerId = playerId;
		this.clearList=new TreeSet<Integer>();
	}
	public String getPlayerId() {
		return playerId;
	}
	public int getCurrentCode() {
		return CurrentCode;
	}
	public void setCurrentCode(int currentCode) {
		CurrentCode = currentCode;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public char[][] getCurrentData() {
		return currentData;
	}
	public void printCurrentData() {
		System.out.println("");
	}
	public void setCurrentData(char[][] currentData) {
		this.currentData = currentData;
	}
	public Set<Integer> getClearList() {
		return clearList;
	}
	
	@Override
	public String toString() {
		int size;
		if(clearList==null) {
			size=0;
		}else {
			size=clearList.size();
		}
		return "계정명"+playerId+"클리어한 게임수 "+size;
	}
	
	
}
