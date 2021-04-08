package com.company.chess.board.logic;

import com.company.chess.pieces.NullPiece;
import com.company.chess.pieces.Piece;

public class Cell {
	private boolean isFilled;
	private Piece filledPiece;
	private final Point point;

	public Cell(Piece filledPiece, Point point) {
		super();
		if(filledPiece instanceof NullPiece) {
			this.isFilled = false;
		}
		else {
			this.isFilled = true;
			filledPiece.setCell(this); 
		}
		this.filledPiece = filledPiece;
		this.point = point;
	}

	public boolean isFilled() {
		return isFilled;
	}

	public Piece getFilledPiece() {
		return filledPiece;
	}

	public void setFilledPiece(Piece filledPiece) {
		this.filledPiece = filledPiece;
		if(filledPiece instanceof NullPiece) {
			isFilled = false;
		}
		else {
			isFilled = true;
		}
	}

	public Point getPoint() {
		return point;
	}

	@Override
	public String toString() {
		return "isFilled = " + isFilled + ", filledPiece = " + filledPiece + ", point = " + point;
	}
}
