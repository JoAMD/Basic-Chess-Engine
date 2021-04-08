package com.company.chess.pieces;

import com.company.chess.board.logic.Board;
import com.company.chess.board.logic.Cell;
import com.company.chess.board.logic.Colour;
import com.company.chess.player.Move;
import com.company.chess.player.Player;

public abstract class Piece {
	private final Colour colour;
	private Cell cell;
	private boolean isDead;

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public Colour getColour() {
		return colour;
	}

	public Piece(Colour colour) {
		super();
		this.colour = colour;
		isDead = false;
	}
	
	public abstract boolean canMove(Board board, Move move);

	public void makeMove(Board board, Move move, Player player) {
		var start = move.getStart();
		var end = move.getEnd();
		board.getPieceInCell(start).cell = board.getCell(end);
		if(board.isCellFilled(end)) {
			Piece attackedPiece = board.getPieceInCell(end);
			attackedPiece.cell = null;
			attackedPiece.isDead = true;
			//remove this piece from player's piece list
			player.removePiece(attackedPiece);
		}
		board.setFilledPiece(start, new NullPiece(null));
		board.setFilledPiece(end, move.getPiece());
		
		player.setLastMove(move);

	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		if(cell == null) {
			System.out.println("cell is null!!!!!!!!!!!!!!!!!!!!");
		}
		this.cell = cell;
	}

	@Override
	public String toString() {
		return getClass().getName() + " of " + colour;
	}

	public boolean isDead() {
		return isDead;
	}
	
}
