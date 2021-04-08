package com.company.chess.player;

import com.company.chess.board.logic.Point;
import com.company.chess.pieces.Piece;

public class Move {
	private final Point start;
	private final Point end;
	private final Piece piece;
	private Piece attackedPiece;

	public Point getStart() {
		return start;
	}

	public Piece getPiece() {
		return piece;
	}

	public Point getEnd() {
		return end;
	}

	public Move(Point start, Point end, Piece piece, Piece attackedPiece) {
		super();
		this.start = start;
		this.end = end;
		this.piece = piece;
		this.attackedPiece = attackedPiece;
	}

	public Piece getAttackedPiece() {
		return attackedPiece;
	}

	@Override
	public String toString() {
		return "start = "+ start + ", end =" + end + ", piece = " + piece + ", attackedPiece = "+ attackedPiece;
	}
	
	
}
