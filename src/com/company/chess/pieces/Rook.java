package com.company.chess.pieces;

import com.company.chess.board.logic.Board;
import com.company.chess.board.logic.Colour;
import com.company.chess.board.logic.Point;
import com.company.chess.player.Move;

public class Rook extends Piece {

	public Rook(Colour colour) {
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
		if(start.getArrayX() == end.getArrayX()) {
			//checking if its the same point, move to caller of this function instead //wont happen since filled piece is of same colour
			if(start.getArrayY() != end.getArrayY()) {
				boolean clearPath = true;
				int min = Math.min(start.getArrayY(), end.getArrayY());
				int max = Math.max(start.getArrayY(), end.getArrayY());
				for(int i = min + 1; i < max; i++) {
					if(board.isCellFilled(new Point(start.getArrayX(), i))) {
						clearPath = false;
						break;
					}
				}
				return clearPath;
			}
			else {
				return false;
			}
		}
		else {
			if(start.getArrayY() == end.getArrayY()) {
				boolean clearPath = true;
				int min = Math.min(start.getArrayX(), end.getArrayX());
				int max = Math.max(start.getArrayX(), end.getArrayX());
				
				for(int i = min + 1; i < max; i++) {
					Point pointChecking = new Point(i, start.getArrayY());
					if(board.isCellFilled(pointChecking)) {
						clearPath = false;
						break;
					}
				}
				
				return clearPath;
			}
			else {
				return false;
			}
		}
		
	}

}
