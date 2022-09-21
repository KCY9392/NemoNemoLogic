package com.ung.logic.model.vo;

public class Question {
	private final int QCode;
	private int[][] rowHint;
	private int[][] columnHint;
	private int[][] Answer;

	public Question(int qCode, int[][] rowHint, int[][] columnHint, int[][] answer) {
		super();
		QCode = qCode;
		this.rowHint = rowHint;
		this.columnHint = columnHint;
		Answer = answer;
	}

	public int getQCode() {
		return QCode;
	}

	public int[][] getRowHint() {
		return rowHint;
	}

	public void setRowHint(int[][] rowHint) {
		this.rowHint = rowHint;
	}

	public int[][] getColumnHint() {
		return columnHint;
	}

	public void setColumnHint(int[][] columnHint) {
		this.columnHint = columnHint;
	}

	public int[][] getAnswer() {
		return Answer;
	}

	public void setAnswer(int[][] answer) {
		Answer = answer;
	}

	@Override
	public String toString() {
		int Max = 0;
		int Max2 = 0;
		for (int i = 0; i < columnHint.length; i++) {
			if (columnHint[i].length > Max) {
				Max = columnHint[i].length;
			}
		}
		for (int i = 0; i < rowHint.length; i++) {
			if (rowHint[i].length > Max2) {
				Max2 = columnHint[i].length;
			}
		}

		StringBuffer sb = new StringBuffer();
		for (int i = Max2-1; i >=0; i--) {
			for (int j = 0; j < Max; j++) {
				sb.append("  ");
			}
			for (int j = 0; j < columnHint.length; j++) {
				if (columnHint[j].length > i) {
					sb.append(columnHint[j][i]);
				} else {
					sb.append(" ");
				}
				sb.append(" ");

			}
			sb.append(" ");
			sb.append("\n\n");

		}
		for (int i = 0; i < Answer.length; i++) {
			for (int j = Max-1; j >= 0; j--) {
				if (rowHint[i].length > j)
					sb.append(rowHint[i][j]);
				else {
					sb.append(" ");
				}

				sb.append(" ");
			}
			sb.append(" ");
			for (int j = 0; j < Answer[i].length; j++) {
				sb.append(Answer[i][j]);
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
