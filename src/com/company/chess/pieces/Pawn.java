package com.company.chess.pieces;

import com.company.chess.board.logic.Board;
import com.company.chess.board.logic.Colour;
import com.company.chess.player.Move;

public class Pawn extends Piece {

	public Pawn(Colour colour) {
		super(colour);
	}

	@Override
	public boolean canMove(Board board, Move move) {
		int yDirection = getColour() == Colour.White ? +1 : -1;
		boolean result = move.getEnd().getBoardY() - move.getStart().getBoardY() == yDirection;
		
		if(result) {
			int boardXDiff = Math.abs(move.getEnd().getBoardX() - move.getStart().getBoardX());
			if(boardXDiff == 1) {
				if(board.isCellFilled(move.getEnd())) { //not actually needed in this case since nullpiece returns null for colour
					if(board.getColourOfPieceInCell(move.getEnd())
							!= getColour()){
						return true;
					}
				}
			}
			else if(boardXDiff == 0) {
				if(!board.isCellFilled(move.getEnd())) {
					return true;
				}
			}
		}
		return false;
	}

}
