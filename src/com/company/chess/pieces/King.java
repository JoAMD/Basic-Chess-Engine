package com.company.chess.pieces;

import com.company.chess.board.logic.Board;
import com.company.chess.board.logic.Colour;
import com.company.chess.board.logic.Point;
import com.company.chess.player.Move;

public class King extends Piece {

	public King(Colour colour) {
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
		int x = Math.abs(start.getArrayX() - end.getArrayX()); 
        int y = Math.abs(start.getArrayY() - end.getArrayY()); 
        return x <= 1 && y <= 1;
	}

}
