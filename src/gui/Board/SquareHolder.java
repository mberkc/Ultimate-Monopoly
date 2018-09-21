package gui.Board;

import java.awt.Dimension;
import java.util.ArrayList;
import Main.Main;

public class SquareHolder {
	private ArrayList<Square> squares = new ArrayList<Square>();

	SquareHolder() {
		generateSquares();
	}

	private void generateSquares() {
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double height = screenSize.getHeight();
		int boardLength;

		if(height<1000)
			boardLength = 675;
		else
			boardLength = 1025;

		int squareLength = (int) boardLength / 17;

		int secondTrack = (int) ((boardLength * 0.75) + (squareLength * 2));
		if (boardLength < 999) {
			for (int i = 0; i < Main.gameSquares.length; i++) {
				if (i == 0)
					squares.add(new Square(secondTrack, secondTrack, i));
				else if (i <= 10)
					squares.add(new Square((int) ((secondTrack - squareLength + 10) - (i * squareLength)), secondTrack,
							i));

				else if (i <= 20)
					squares.add(new Square((int) ((squareLength * 2) + 10 + (squareLength * 1.4)),
							((int) ((boardLength - (squareLength * 2))) - ((i - 9) * squareLength) - 5), i));

				else if (i <= 30)
					squares.add(new Square(
							(int) ((2 * squareLength + (2.3 * squareLength)) + ((i - 20) * squareLength)),
							(4 * squareLength), i));

				else if (i < 40)
					squares.add(new Square((int) (boardLength - (squareLength * 2)),
							(4 * squareLength) + ((i - 30) * squareLength), i));

				else if (i <= 54)
					squares.add(new Square((int) (boardLength - (squareLength * (i - 39))),
							(boardLength), i));

				else if (i <= 68)
					squares.add(new Square((int) (squareLength * 1.64),
							(boardLength - squareLength * (i - 53)), i));

				else if (i <= 82)
					squares.add(new Square((int) (squareLength * 2 + ((i - 67) * squareLength) - squareLength / 1.5),
							squareLength, i));

				else if (i < 96)
					squares.add(new Square((int) (boardLength - (squareLength / 3)),
							(int) (squareLength * 2.3 + ((i - 82) * squareLength)), i));

				else if (i <= 102)
					squares.add(new Square((int) ((boardLength - (4 * squareLength)) - (squareLength * (i - 95)) + 10),
							(int) ((boardLength - (5 * squareLength))), i));

				else if (i <= 108)
					squares.add(new Square((int) ((5.6 * squareLength)),
							(int) ((boardLength - (4.9 * squareLength)) - (squareLength * (i - 102))), i));

				else if (i <= 114)
					squares.add(new Square((int) ((6.4 * squareLength)) + (squareLength * (i - 108)),
							(int) 6 * squareLength - squareLength / 4, i));

				else if (i <= 120)
					squares.add(new Square((int) (boardLength - 4 * squareLength),
							(int) (((6.1 * squareLength)) + (squareLength * (i - 114))), i));
			}


		} else {
			for (int i = 0; i < Main.gameSquares.length; i++) {
				if (i == 0)
					squares.add(new Square(secondTrack, secondTrack, i));
				else if (i <= 10)
					squares.add(new Square((int) ((secondTrack - squareLength + 10) - (i * squareLength)), secondTrack
							- squareLength / 2, i));

				else if (i <= 20)
					squares.add(new Square((int) ((squareLength * 2) + 10 + (squareLength * 1.4)),
							((int) ((boardLength - (squareLength * 2))) - ((i - 9) * squareLength) - 15), i));

				else if (i <= 30)
					squares.add(new Square(
							(int) ((2 * squareLength + (2.1 * squareLength)) + ((i - 20) * squareLength)),
							(int) (3.7 * squareLength), i));

				else if (i < 40)
					squares.add(new Square((int) (boardLength - (squareLength * 2.3)),
							(int) ((3.8 * squareLength) + ((i - 30) * squareLength)), i));

				else if (i <= 54)
					squares.add(new Square((int) (boardLength - 5 - (squareLength * (i - 39))),
							(int) (boardLength - squareLength / 1.7), i));

				else if (i <= 68)
					squares.add(new Square((int) (squareLength * 1.64),
							(boardLength - 9 - squareLength * (i - 53)), i));

				else if (i <= 82)
					squares.add(new Square((int) (squareLength * 2 + ((i - 68) * squareLength)),
							(int) (squareLength * 1.3), i));

				else if (i < 96)
					squares.add(new Square((int) (boardLength - (squareLength / 3)),
							(int) (squareLength * 1.95 + ((i - 82) * squareLength)), i));

				else if (i <= 102)
					squares.add(new Square((int) ((boardLength - (4.1 * squareLength)) - (squareLength * (i - 95))),
							(int) ((boardLength - (5 * squareLength))), i));

				else if (i <= 108)
					squares.add(new Square((int) ((5.6 * squareLength)),
							(int) ((boardLength - (5.1 * squareLength)) - (squareLength * (i - 102))), i));

				else if (i <= 114)
					squares.add(new Square((int) ((6 * squareLength)) + (squareLength * (i - 108)),
							(int) 6 * squareLength - squareLength / 2, i));

				else if (i <= 120)
					squares.add(new Square((int) (boardLength - 4.4 * squareLength),
							(int) (((5.85 * squareLength)) + (squareLength * (i - 114))), i));
			}
		}

	}

	public void addSquare(int x, int y, int iD) {
	}

	public Square getSquare(int id) {
		if (id < squares.size() && id >= 0)
			return squares.get(id);
		if (id == -1)
			return new Square(360, 360, -1);
		return null;
	}
}


class Square {

	private int X  = 5;
	private int Y  = 5;
	private int ID = 0;

	public Square(int x, int y, int iD) {
		X = x;
		Y = y;
		ID = iD;
	}

	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}
