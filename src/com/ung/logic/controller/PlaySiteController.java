package com.ung.logic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ung.logic.model.service.LogicService;
import com.ung.logic.model.vo.Player;
import com.ung.logic.model.vo.Question;

public class PlaySiteController {
	private int[][] columnHint;
	private int[][] rowHint;
	private int[][] Answer;
	private LogicService ls;
	private int buttonSize;
	private int imgSize;
	private int topHeight;
	private int FrameHeight;
	private int FrameWidth;
	private int cursorHeight;
	public PlaySiteController(int QCode) {
		ls = new LogicService();
		Question q = ls.getQuestion(QCode);
		System.out.println(q);
		columnHint = q.getColumnHint();
		rowHint = q.getRowHint();
		Answer = q.getAnswer();
		if(Answer[0].length<20) {//이미지
			imgSize=30;
		}else {
			imgSize=60;
		}
		if(Answer.length>24) {//버튼
			buttonSize=20;
		} else {
			buttonSize=35;
		}
		topHeight=60;
		if(Answer.length>24) {
			topHeight=40;			
		}
		int rowMax = 0;
		int columnMax = 0;
		for (int i = 0; i < rowHint.length; i++) {
			if (rowHint[i].length > rowMax) {
				rowMax = rowHint[i].length;
			}
		}
		for (int i = 0; i < columnHint.length; i++) {
			if (columnHint[i].length > columnMax) {
				columnMax = columnHint[i].length;
			}
		}
		this.cursorHeight=100;
		FrameWidth = (rowHint.length + columnMax) * buttonSize;
		FrameHeight = (columnHint.length + rowMax) * buttonSize + cursorHeight + topHeight;

	}
	public int getCursorHeight(){
		return cursorHeight;
	}
	public int getTopheight() {
		return topHeight;
	}
	public int getFrameHeight() {
		return FrameHeight;
	}
	
	public int getFrameWidht() {
		return FrameWidth;
	}
	
	public int getButtonSize() {
		return buttonSize;
	}
	public int getImgSize() {
		return buttonSize;
	}
	
	public char[][] getCharAnswer() {
		char[][] result = new char[Answer.length][Answer[0].length];
		for (int i = 0; i < Answer.length; i++) {
			for (int j = 0; j < Answer.length; j++) {
				if (Answer[i][j] == 1) {
					result[i][j] = 'O';
				} else {
					result[i][j] = 'X';
				}
			}
		}
		return result;
	}
	
	public int[][] getAnswer(){
		return Answer;
	}
	
	public int[][] getRow() {
		return rowHint;
	}

	public int[][] getColumn() {
//		int[]Temp;
//		int cLength=columnHint.length;
//		for(int i=0;i<cLength;i++) {
//			Temp=columnHint[i];
//			columnHint[i]=columnHint[cLength-1-i];
//			columnHint[cLength-1-i]=Temp;
//			}
		return columnHint;
	}

	/**
	 * @return 정답이 몇개 있는지 반환하는 메소드입니다,
	 */
	public int getBlank() {
		int blank = 0;
		for (int i = 0; i < Answer.length; i++) {
			for (int j = 0; j < Answer[i].length; j++) {
				if (Answer[i][j] == 1) {
					blank++;
				}
			}
		}
		return blank;

	}
	public int getBlank(Player p) {
		int blank = 0;
		char[][]current=p.getCurrentData();
		for (int i = 0; i < Answer.length; i++) {
			for (int j = 0; j < Answer[i].length; j++) {
				if (Answer[i][j] == 1&&current[i][j]!='O') {
					blank++;
					}
				}
				
			}
		
		return blank;
		
	}
	
	

	public boolean checkWrong(int x, int y) {
		boolean result = false;
		if (Answer[x][y] == 0) {
			result = true;
		}
		return result;
	}

	public boolean checkRight(int x, int y) {
		boolean result = false;
		if (Answer[x][y] == 1) {
			result = true;
		}
		return result;
	}
	/**
	 * 
	 * @param Current 진행상황
	 * @return x좌표 y좌표가 담긴 2칸짜리 배열
	 */
	public int[] getHint(char[][] Current){
		List<int[]>Blank=new ArrayList<int[]>();
		for (int i = 0; i < Current.length; i++) {
			for (int j = 0; j < Current[0].length; j++) {
				if((Current[i][j]=='\0'||Current[i][j]=='?')&&Answer[i][j]==1) {
					int[]point= {i,j};
					Blank.add(point);
				}
			}
		}
		int random=(int)(Math.random()*Blank.size());
		System.out.println(random);
		int[] Hint=Blank.get(random);

		return Hint;
	}
	public void clearGame(Player player,int QCode) {
		player.getClearList().add(QCode);
		ls.SaveClearList(player);
		player.setCurrentData(null);
		player.setCurrentCode(0);
	}
	public void SaveCurrent(Player player) {
		ls.SaveCurrent(player);
	}
}
