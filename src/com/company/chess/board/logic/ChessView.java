package com.company.chess.board.logic;

import java.util.Scanner;

import com.company.chess.gameresult.CheckStatusData;
import com.company.chess.pieces.Piece;
import com.company.chess.player.Move;

public class ChessView {
	private InputProcessor inputProcessor;
	private ChessModel chessModel;

	public ChessView(InputProcessor inputProcessor, ChessModel chessModel) {
		super();
		this.inputProcessor = inputProcessor;
		this.chessModel = chessModel;
	}

	public void startGame() {
		chessModel.initializeBoard();
		InputLoop();
	}

	// -------------- Input --------------
	// refactor to other classes later

	public void InputLoop() {
		
		Scanner scanner = new Scanner(System.in);
		Colour playerColour = Colour.White;
		Move currentMove;
		Point start = null;
		Point end = null;
		boolean breakOuter = false;
		boolean continueOuter = false;

		//display
		displayBoard();
		
		do {
			
			System.out.println("To exit game input \"exit\" at any time");

			do {
				System.out.println(playerColour + "'s" + " turn, input start piece coordinates eg: a1");
				System.out.println("input \'r\' to restart this turn");
				String input = scanner.next();
				System.out.println("current i/p: " + input);
				if (input.equals("exit")) {
					breakOuter = true;
					break;
				}
				else if(input.equals("r")) {
					continueOuter = true;
					break;
				}

				start = handleInput(input, false, playerColour);
				if (start == null) {
					System.out.println("Bad input! Go again please");
					continue;
				} else {
					break;
				}
			} while (true);

			if (breakOuter)
				break;
			if (continueOuter) {
				continueOuter = false;
				continue;
			}
			
			do {
				System.out.println(playerColour + "'s" + " turn, input destination coordinates");
				System.out.println("input \'r\' to restart this turn");
				String input = scanner.next();
				System.out.println("current i/p: " + input);
				if (input.equals("exit")) {
					breakOuter = true;
					break;
				}
				else if(input.equals("r")) {
					continueOuter = true;
					break;
				}

				end = handleInput(input, true, playerColour);
				if (end == null) {
					System.out.println("Bad input! Go again please");
					continue;
				} else {
					break;
				}
			} while (true);

			if (breakOuter)
				break;
			if (continueOuter) {
				continueOuter = false;
				continue;
			}

			currentMove = new Move(start, end, chessModel.getPieceInCell(start), chessModel.getPieceInCell(end));
			
			//validate move and execute
			if(!validateMoveAndExecute(currentMove)) { // check for checkmate and check for own piece in validation
				System.out.println("Invalid Move!");
				continue;
			}

			playerColour = playerColour == Colour.White ? Colour.Black : Colour.White;
			
			// (colour switch done)
			//check and checkmate checking after move is done (for opposite colour)
			CheckStatusData checkStatusData = chessModel.checkForCheck(playerColour, true);
			Piece attackingPiece = checkStatusData.getAttackingPiece();
			Point[] hint = checkStatusData.getHint();	
			
			switch (checkStatusData.getCheckStatus()) {
			case Check: {
				System.out.println(chessModel.oppositeColour(attackingPiece.getColour()) 
						+ " King is under check! by " 
						+ attackingPiece + " at " 
						+ attackingPiece.getCell().getPoint());
				System.out.println("can move out of checkmate: start = " + hint[0] + " & end = " + hint[1]);
				break;
			}
			case Checkmate: {
				System.out.println(chessModel.oppositeColour(attackingPiece.getColour()) 
						+ " King is under checkmate! by " 
						+ attackingPiece + " at " 
						+ attackingPiece.getCell().getPoint());
				System.out.println("Game over"); //send result
				breakOuter = true;
				break;
			}
			case Safe: {
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + checkStatusData.getCheckStatus());
			}
			
			//display
			displayBoard();
			chessModel.displayPoints();
			
			if (breakOuter)
				break;

			
		} while (true);
		
		// refactor to other classes later
		
		scanner.close();
	}

	Point handleInput(String input, boolean isDestination, Colour playerColour) {
		return inputProcessor.validateInputCoordinates(input, isDestination, playerColour);
	}
	
	boolean validateMoveAndExecute(Move move) {
		return chessModel.validateMoveAndExecute(move);
	}

	// refactor to other classes later
	// ----------- End of Input -----------
	
	//Display
	
	private void displayBoard() {
		chessModel.displayBoard();
	}
	
	//End of Display
}
