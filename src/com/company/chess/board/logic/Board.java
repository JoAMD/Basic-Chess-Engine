package com.company.chess.board.logic;

import com.company.chess.pieces.Bishop;
import com.company.chess.pieces.King;
import com.company.chess.pieces.Knight;
import com.company.chess.pieces.NullPiece;
import com.company.chess.pieces.Pawn;
import com.company.chess.pieces.Piece;
import com.company.chess.pieces.Queen;
import com.company.chess.pieces.Rook;

public class Board {
	private Cell[][] cells;

	public Board(ChessModel chessModel) {
		super();
		initializeCells(chessModel);
	}

	public Cell getCell(Point point) {
		return cells[point.getArrayX()][point.getArrayY()];
	}
	
	void initializeCells(ChessModel chessModel) {
		
		cells = new Cell[8][8];
		
		//other pieces
		
		cells[0][0] = new Cell(new Rook(Colour.White), new Point(0, 0));
		cells[0][1] = new Cell(new Knight(Colour.White), new Point(0, 1));
		cells[0][2] = new Cell(new Bishop(Colour.White), new Point(0, 2));
		
		cells[0][3] = new Cell(new Queen(Colour.White), new Point(0, 3));
		Piece kingWhite = new King(Colour.White);
		cells[0][4] = new Cell(kingWhite, new Point(0, 4));
		chessModel.setKingWhite(kingWhite);
		
		cells[0][5] = new Cell(new Bishop(Colour.White), new Point(0, 5));
		cells[0][6] = new Cell(new Knight(Colour.White), new Point(0, 6));
		cells[0][7] = new Cell(new Rook(Colour.White), new Point(0, 7));

		//pawn
		for(int j = 0; j < 8; ++j) {
			cells[1][j] = new Cell(new Pawn(Colour.White), new Point(1, j));
		}
		
		for(int i = 2; i < 6; ++i) {
			for(int j = 0; j < 8; ++j) {
				cells[i][j] = new Cell(new NullPiece(null), new Point(i, j));
			}
		}
		
		for(int j = 0; j < 8; ++j) {
			cells[6][j] = new Cell(new Pawn(Colour.Black), new Point(6, j));
		}
		
		//other pieces

		cells[7][0] = new Cell(new Rook(Colour.Black), new Point(7, 0));
		cells[7][1] = new Cell(new Knight(Colour.Black), new Point(7, 1));
		cells[7][2] = new Cell(new Bishop(Colour.Black), new Point(7, 2));
		
		cells[7][3] = new Cell(new Queen(Colour.Black), new Point(7, 3));
		Piece kingBlack = new King(Colour.Black);
		cells[7][4] = new Cell(kingBlack, new Point(7, 4));
		chessModel.setKingBlack(kingBlack);
		
		cells[7][5] = new Cell(new Bishop(Colour.Black), new Point(7, 5));
		cells[7][6] = new Cell(new Knight(Colour.Black), new Point(7, 6));
		cells[7][7] = new Cell(new Rook(Colour.Black), new Point(7, 7));
		
		for(int j = 0; j < 8; ++j) {
			for(int i = 7; i >= 6; --i) {
				chessModel.addPiece(Colour.Black, cells[i][j].getFilledPiece());
			}
			for(int i = 1; i >= 0; --i) {
				chessModel.addPiece(Colour.White, cells[i][j].getFilledPiece());
			}
		}

	}
	
	public boolean isCellFilled(Point point) {
		return cells[point.getArrayX()][point.getArrayY()].isFilled();
	}
	
	public Piece getPieceInCell(Point point) {
		return getCell(point).getFilledPiece();
	}
	
	// call isCellFilled before this
	public Colour getColourOfPieceInCell(Point point) {
		return getPieceInCell(point).getColour();
	}
	
	void displayCells() {
		for(int i = 7; i >= 0; --i) {
			for(int j = 0; j < 8; ++j) {
				System.out.print((char)(j + 97) + "" + (i + 1));
				String[] name = cells[i][j].getFilledPiece().getClass().toString().split("\\.");
				System.out.print(" " + name[name.length - 1]);
				for(int z = 0; z < 10 - name[name.length - 1].length(); ++z) {
					System.out.print(" ");
				}
				System.out.print("|  ");
			}
			System.out.println();
		}
	}

	public void setFilledPiece(Point point, Piece piece) {
		Cell cell = getCell(point);
		cell.setFilledPiece(piece);
	}
	
}
