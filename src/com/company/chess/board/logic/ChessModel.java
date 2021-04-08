package com.company.chess.board.logic;

import java.util.ArrayList;

import com.company.chess.gameresult.CheckStatus;
import com.company.chess.gameresult.CheckStatusData;
import com.company.chess.pieces.Piece;
import com.company.chess.player.Move;
import com.company.chess.player.Player;

public class ChessModel {
	private Board board;
	private final Player playerWhite;
	private final Player playerBlack;
	private Piece kingWhite;
	private Piece kingBlack;
	
	public Piece getKing(Colour colour) {
		if(colour == Colour.White) {
			return kingWhite;
		}
		else {
			return kingBlack;
		}
		
	}

	public void setKingWhite(Piece kingWhite) {
		this.kingWhite = kingWhite;
	}
	
	public void setKingBlack(Piece kingBlack) {
		this.kingBlack = kingBlack;
	}

	public void initializeBoard() {
		board = new Board(this);
		
	}
	
	public ChessModel() {
		super();
		playerWhite = new Player(Colour.White);
		playerBlack = new Player(Colour.Black);
	}

	boolean isCellFilled(Point point) {
		return board.isCellFilled(point);
	}
	
	Piece getPieceInCell(Point point) {
		return board.getPieceInCell(point);
	}
	
	// call isCellFilled before this
	public Colour getColourOfPieceInCell(Point point) {
		if(isCellFilled(point)) {
			return board.getColourOfPieceInCell(point);
		}
		return null;
	}
	
	boolean validateMoveAndExecute(Move move) {
		if(isCellFilled(move.getStart())) {
			//start is filled
			
			//delegate to piece
			boolean canMove = move.getPiece().canMove(board, move);
			
			Player player = getPlayer(move.getPiece().getColour());
			
			
			//check for check condition for current king here
			if(moveCheckForCheckAndUndo(move, player, true, false)) {
				System.out.println("Own king under check! cant move");
				return false;
			}
			
//			canMove &= checkForCheck(move.getPiece().getColour());
			
			if(!canMove) {
				player.undoLastMove(board);
			}
			return canMove;
		}
		return false;
	}
	
	boolean moveCheckForCheckAndUndo(Move move, Player player, boolean isCheckCheckmate, boolean undoMove) {
		move.getPiece().makeMove(board, move, player);
		boolean result;
		CheckStatusData checkStatusData = checkForCheck(move.getPiece().getColour(), isCheckCheckmate);
		if(checkStatusData.getCheckStatus() != CheckStatus.Safe) {
			result = true;
		}
		else {
			result = false;
		}
		if(undoMove) {
			player.undoLastMove(board);
		}
		return result;
	}
	
	void displayBoard() {
		board.displayCells();
	}
	
	private Player getPlayer(Colour colour) {
		if(colour == Colour.White) {
			return playerWhite;
		}
		else {
			return playerBlack;
		}
	}
	
	void addPoint(Colour colour) {
		getPlayer(colour).addPoint();
	}
	
	void addPiece(Colour colour, Piece piece) {
		getPlayer(colour).addPiece(piece);
	}
	
	ArrayList<Piece> getPieces(Colour colour) {
		return getPlayer(colour).getPieces();
	}
	
	CheckStatusData checkForCheck(Colour kingColour, boolean isCheckCheckmate) {
		Colour opponentColour = oppositeColour(kingColour);
		ArrayList<Piece> pieces = getPieces(opponentColour);
		//maybe delegate this to player class? since it has the pieces array already
		for (Piece piece : pieces) {
			if(piece.isDead()) {
				continue;
			}
			Piece currentKing = getKing(kingColour);
			if(piece.canMove(board, new Move(piece.getCell().getPoint(), currentKing.getCell().getPoint(), piece, currentKing))) {
				//king under check
				
				/*
				 * Checkmate
				 * check if king can move out of los
				 * Find no of attackers
				 * loop through them,
				 * each loop, check if we can kill the attacker or block los (loop through path, including start point)
				 * but if more than one attacker is there king will have to move out of the los
				 */
				//checking if king can move out of los
				boolean isCheckmate;
				Point[] hint = null;
				
				if(isCheckCheckmate) {
					
					isCheckmate = true;
					Point start = currentKing.getCell().getPoint();
					for(int i = -1; i <= 1; ++i) {
						for(int j = -1; j <= 1; ++j) {
							if(Math.abs(i) <= 1 && Math.abs(j) <= 1) {
								int endX = start.getArrayX() + i;
								int endY = start.getArrayY() + j;
								if(endX < 0 || 8 <= endX ||
									endY < 0 || 8 <= endY) {
									continue;
								}
								Point end = new Point(endX, endY);
								Move move = new Move(start, 
										end, 
										currentKing,
										board.getPieceInCell(end));
								boolean canMoveOutOfLOS = currentKing.canMove(board, move);
								if(canMoveOutOfLOS) {
									//check if check is caused here
									
									if(moveCheckForCheckAndUndo(move, getPlayer(kingColour), false, true)) {
										//still checkmate...
										isCheckmate = true;
									}
									else {
										hint = new Point[2];
										hint[0] = start;
										hint[1] = end;
										isCheckmate = false;
										break;
									}
								}
							}
						}
					}
				}
				else {
					isCheckmate = false;
				}
				if(!isCheckmate) {
					//if not checkmate:
					return new CheckStatusData(CheckStatus.Check, piece, hint);
				}
				else {
					return new CheckStatusData(CheckStatus.Checkmate, piece, null);
				}
			}
		}
		return new CheckStatusData(CheckStatus.Safe);
	}
	
	Colour oppositeColour(Colour colour) {
		if(colour == Colour.White) {
			return Colour.Black;
		}
		else {
			return Colour.White;
		}
	}
	
	void displayPoints() {
		System.out.println("----- Points -----");
		System.out.println("White player: " + playerWhite.getPoints());
		System.out.println("Black player: " + playerBlack.getPoints());
	}
}
