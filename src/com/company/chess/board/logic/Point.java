package com.company.chess.board.logic;

public class Point {
	private final int x;
	private final int y;
	
	public int getArrayX() {
		return x;
	}
	
	public int getArrayY() {
		return y;
	}
	
	public int getBoardX() {
		return y;
	}
	
	public int getBoardY() {
		return x;
	}
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return (char)(y + 97) + "" + (x + 1);
	}
}
