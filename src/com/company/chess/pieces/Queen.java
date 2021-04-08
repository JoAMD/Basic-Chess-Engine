package com.company.chess.pieces;

import com.company.chess.board.logic.Board;
import com.company.chess.board.logic.Colour;
import com.company.chess.board.logic.Point;
import com.company.chess.player.Move;

public class Queen extends Piece {

	public Queen(Colour colour) {
		super(colour);
	}

	@Override
	public boolean canMove(Board board, Move move) {
		Point end = move.getEnd();
		Colour endColour = board.getColourOfPieceInCell(end);
		//check colour of pieces
		if(endColour == getColour()) { //done in input processor too, "same colour piece at destination" //refactor
			return false;
		}
		
		Point start = move.getStart();
		int xDiff = Math.abs(start.getArrayX() - end.getArrayX());
		int yDiff = Math.abs(start.getArrayY() - end.getArrayY());
		
		if((xDiff == yDiff) || (xDiff * yDiff == 0)) {
			boolean clearPath = true;
			int minX = Math.min(start.getArrayX(), end.getArrayX());
			int maxX = Math.max(start.getArrayX(), end.getArrayX());
			int minY = Math.min(start.getArrayY(), end.getArrayY());
			int maxY = Math.max(start.getArrayY(), end.getArrayY());
			
			int xAdd = (xDiff == 0) ? 0 : 1;
			int yAdd = (yDiff == 0) ? 0 : 1;
			int i = minX + 1;
			int j = minY + 1;
			if(xDiff == 0) {
				i-=2;
			}
			else if(yDiff == 0) {
				j-=2;
			}
			
			while(i < maxX && j < maxY) {
				if(board.isCellFilled(new Point(i, j))) {
					clearPath = false;
					break;
				}
				i += xAdd; 
				j += yAdd;
			}
			
			return clearPath;
		}
		
		return false;
	}

}
