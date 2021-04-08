package com.company.chess.pieces;

import com.company.chess.board.logic.Board;
import com.company.chess.board.logic.Colour;
import com.company.chess.player.Move;
import com.company.chess.player.Player;

//Null pattern
public class NullPiece extends Piece {

	public NullPiece(Colour colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Board board, Move move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeMove(Board board, Move move, Player player) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
