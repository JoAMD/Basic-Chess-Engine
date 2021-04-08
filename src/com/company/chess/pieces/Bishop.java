package com.company.chess.pieces;

import com.company.chess.board.logic.Board;
import com.company.chess.board.logic.Colour;
import com.company.chess.board.logic.Point;
import com.company.chess.player.Move;

public class Bishop extends Piece {

	public Bishop(Colour colour) {
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
		
		if(xDiff == yDiff) {
			boolean clearPath = true;
			int minX = Math.min(start.getArrayX(), end.getArrayX());
			int maxX = Math.max(start.getArrayX(), end.getArrayX());
			int minY = Math.min(start.getArrayY(), end.getArrayY());
			int maxY = Math.max(start.getArrayY(), end.getArrayY());
			int i = minX + 1;
			int j = minY + 1;
			while(i < maxX) {
				if(board.isCellFilled(new Point(i, j))) {
					clearPath = false;
					break;
				}
				i++; 
				j++;
			}
			return clearPath;
		}
		
		return false;
	}

}
