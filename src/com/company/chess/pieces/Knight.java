package com.company.chess.pieces;

import com.company.chess.board.logic.Board;
import com.company.chess.board.logic.Colour;
import com.company.chess.board.logic.Point;
import com.company.chess.player.Move;

public class Knight extends Piece {

	public Knight(Colour colour) {
		super(colour);
	}

	@Override
	public boolean canMove(Board board, Move move) {
		Point end = move.getEnd();
		Colour endColour = board.getColourOfPieceInCell(end);
		//check colour of pieces
		if(endColour == getColour()) {
			return false;
		}
		
		Point start = move.getStart();
		int xDiff = Math.abs(start.getBoardX() - end.getBoardX());
		int yDiff = Math.abs(start.getBoardY() - end.getBoardY());
		return xDiff * yDiff == 2;
	}

}
