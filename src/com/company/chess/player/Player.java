package com.company.chess.player;

import java.util.ArrayList;

import com.company.chess.board.logic.Board;
import com.company.chess.board.logic.Cell;
import com.company.chess.board.logic.Colour;
import com.company.chess.pieces.Piece;

public class Player {
	private int points;
	private final Colour colour;
	private ArrayList<Piece> pieces;
	int noOfPieces = 0;
	private Move lastMove;
	public Player(Colour colour) {
		this.points = 0;
		this.colour = colour;
		pieces = new ArrayList<>(16);
	}
	public int getPoints() {
		return points;
	}
	public void addPoint() {
		this.points++;
	}
	public Colour getColour() {
		return colour;
	}
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	public void addPiece(Piece piece) {
		pieces.add(piece);
		noOfPieces++;
	}
	public void removePiece(Piece piece) {
		pieces.remove(piece);
	}
	
	public Move getLastMove() {
		return lastMove;
	}
	public void setLastMove(Move lastMove) {
		this.lastMove = lastMove;
	}
	public void undoLastMove(Board board) {
		Piece attackedPiece = lastMove.getAttackedPiece();
		if(attackedPiece != null) {
			Cell cell = board.getCell(lastMove.getEnd());
			attackedPiece.setCell(cell);
			attackedPiece.setDead(false);;
			cell.setFilledPiece(attackedPiece);
		}
		Cell cell = board.getCell(lastMove.getStart());
		lastMove.getPiece().setCell(cell);
		cell.setFilledPiece(lastMove.getPiece());
		lastMove = null;
	}
}
