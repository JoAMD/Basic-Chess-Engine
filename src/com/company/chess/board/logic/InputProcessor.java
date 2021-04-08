package com.company.chess.board.logic;

public class InputProcessor {
	ChessModel chessModel;

	public InputProcessor(ChessModel chessModel) {
		super();
		this.chessModel = chessModel;
	}
	
	public Point validateInputCoordinates(String input, boolean isDestination, Colour playerColour) {
		
		if(input.length() != 2) {
			System.out.println("Input length not 2");
			return null;
		}
		
		int c1 = input.charAt(0) - 97;
		if(0 <= c1 && c1 < 8) {
			
		}
		else {
			System.out.println("Input is out of bounds!");
			return null;
		}
		
		int c2;
		try {
			c2 = Integer.parseInt(input.charAt(1) + "") - 1;
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		
		// ---------------------------------
		// refactor later!
		
		Point point = new Point(c2, c1);
		
		boolean isFilled = chessModel.isCellFilled(point);

		if(isFilled) {
			Colour pieceColour = chessModel.getColourOfPieceInCell(point);
			//should be in piece class
			if(isDestination) {
				if(playerColour != pieceColour) {
					chessModel.addPoint(playerColour); // refactor!!!!!!!!!!!!!!!!!
					return new Point(c2, c1);
				}
				else {
					System.out.println("same colour piece at destination");
					return null;
				}
			}
			else {
				if(playerColour == pieceColour) {
					return new Point(c2, c1);
				}
				else {
					System.out.println("thats not your piece");
					return null;
				}
			}
		}
		else {
			// check intermediate too
			if(isDestination)
				return new Point(c2, c1);
			else
				return null;
		}
		
		// ---------------------------------
	}
}
