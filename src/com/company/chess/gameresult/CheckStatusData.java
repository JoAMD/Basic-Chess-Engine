package com.company.chess.gameresult;

import com.company.chess.board.logic.Point;
import com.company.chess.pieces.Piece;

public class CheckStatusData {
	private final CheckStatus checkStatus;
	private final Piece attackingPiece;
	private final Point[] hint;

	public CheckStatusData(CheckStatus checkStatus) {
		super();
		this.checkStatus = checkStatus;
		this.attackingPiece = null;
		this.hint = null;
	}
	
	public CheckStatusData(CheckStatus checkStatus, Piece attackingPiece, Point[] hint) {
		super();
		this.checkStatus = checkStatus;
		this.attackingPiece = attackingPiece;
		this.hint = hint;
	}

	public CheckStatus getCheckStatus() {
		return checkStatus;
	}

	public Piece getAttackingPiece() {
		return attackingPiece;
	}

	public Point[] getHint() {
		return hint;
	}
	
}
