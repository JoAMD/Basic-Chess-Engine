package com.company.chess;

import com.company.chess.board.logic.ChessModel;
import com.company.chess.board.logic.ChessView;
import com.company.chess.board.logic.InputProcessor;

public class ChessTestDrive {

	public static void main(String[] args) {
		ChessModel chessModel = new ChessModel();
		InputProcessor inputProcessor = new InputProcessor(chessModel);
		ChessView chessView = new ChessView(inputProcessor, chessModel);
		chessView.startGame();
	}

}
