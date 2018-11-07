package com.klezovich.stm_challenge.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.klezovich.stm_challenge.domain.CanvasCommand.CanvasCommandType;
import com.klezovich.stm_challenge.domain.CanvasCommand.LineType;

public class Canvas {

	char[][] grid;
	int height;
	int width;

	public void execute(CanvasCommand cc) {

		if (cc.getCmdType() == CanvasCommandType.CREATE) {
			executeCreateCommand(cc);
		}

		if (cc.getCmdType() == CanvasCommandType.DRAW_LINE) {
			executeDrawLineCommand(cc);
		}

		if (cc.getCmdType() == CanvasCommandType.DRAW_RECTANGLE) {
			executeDrawRectangleCommand(cc);
		}

		if (cc.getCmdType() == CanvasCommandType.FILL) {
			executeFillCommand(cc);
		}

		if (cc.getCmdType() == CanvasCommandType.QUIT) {
			executeQuitCommand(cc);
		}

	}

	public void draw() {

		if (grid == null)
			return;

		drawCanvasVerticalBorder();

		for (int rowNum = 0; rowNum < height; rowNum++) {
			drawCanvasRow(rowNum);
		}

		drawCanvasVerticalBorder();
	}

	private void colorCell(CanvasCell cc, char color) {
		grid[cc.getY() - 1][cc.getX() - 1] = color;
	}

	private void executeFillCommand(CanvasCommand cc) {

		int x = cc.getX1();
		int y = cc.getY1();
		char color = cc.getColor();

		if (!coordinatesFitInCanvas(cc))
			throw new RuntimeException("The supplied coordinate is outside of the current canvas");

		CanvasCell c = new CanvasCell(x, y);
		Queue<CanvasCell> q = new LinkedList<CanvasCell>();
		q.add(c);

		while (!q.isEmpty()) {

			CanvasCell curCel = q.poll();
			List<CanvasCell> lc = getNeighbourCells(curCel, color);
			q.addAll(lc);
			colorCell(curCel, color);
			System.out.println("Coloring:" + curCel.getX() + " " + curCel.getY());
			this.draw();
		}

	}

	private List<CanvasCell> getNeighbourCells(CanvasCell c, char color) {

		List<CanvasCell> li = new ArrayList<CanvasCell>();

		int x = c.getX();
		int y = c.getY();

		// Lower neighbour
		int x1 = x;
		int y1 = y + 1;

		if (y1 <= height && grid[y1 - 1][x1 - 1] != 'x' && grid[y1 - 1][x1 - 1] != color)
			li.add(new CanvasCell(x1, y1));

		// Upper neighbour
		int x2 = x;
		int y2 = y - 1;

		if (y2 >= 1 && grid[y2 - 1][x2 - 1] != 'x' && grid[y2 - 1][x2 - 1] != color)
			li.add(new CanvasCell(x2, y2));

		// Left neighbour
		int x3 = x - 1;
		int y3 = y;

		if (x3 >= 1 && grid[y3 - 1][x3 - 1] != 'x' && grid[y3 - 1][x3 - 1] != color)
			li.add(new CanvasCell(x3, y3));

		// Right neighbour
		int x4 = x + 1;
		int y4 = y;

		if (x4 <= width && grid[y4 - 1][x4 - 1] != 'x' && grid[y4 - 1][x4 - 1] != color)
			li.add(new CanvasCell(x4, y4));

		return li;
	}

	private void executeQuitCommand(CanvasCommand cc) {
		System.exit(0);
	}

	private void executeDrawRectangleCommand(CanvasCommand cc) {

		if (!coordinatesFitInCanvas(cc))
			throw new RuntimeException("Rectangle coordinates do not fit into the canvas");

		int x1 = cc.getX1();
		int y1 = cc.getY1();
		int x2 = cc.getX2();
		int y2 = cc.getY2();

		drawLine(x1, y1, x1, y2);
		drawLine(x1, y2, x2, y2);
		drawLine(x2, y2, x2, y1);
		drawLine(x2, y1, x1, y1);

	}

	private boolean coordinatesFitInCanvas(CanvasCommand cc) {

		int x1 = cc.getX1();
		int x2 = cc.getX2();
		int y1 = cc.getY1();
		int y2 = cc.getY2();

		if (x1 > width)
			return false;

		if (x2 > width)
			return false;

		if (y1 > height)
			return false;

		if (y2 > height)
			return false;

		return true;
	}

	private void executeCreateCommand(CanvasCommand cc) {

		width = cc.getWidth();
		height = cc.getHeight();
		grid = new char[height][width];

	}

	private void executeDrawLineCommand(CanvasCommand cc) {

		if (!coordinatesFitInCanvas(cc))
			throw new RuntimeException("Line coordinates do not fit into the canvas");

		int x1 = cc.getX1();
		int x2 = cc.getX2();
		int y1 = cc.getY1();
		int y2 = cc.getY2();

		drawLine(x1, y1, x2, y2);

	}

	private void drawLine(int x1, int y1, int x2, int y2) {

		// Horizontal line
		if (y1 == y2) {
			for (int ii = Math.min(x1, x2); ii <= Math.max(x1, x2); ii++) {
				grid[y1 - 1][ii - 1] = 'x';
			}
		}

		// Vertical line
		if (x1 == x2) {
			for (int ii = Math.min(y1, y2); ii <= Math.max(y1, y2); ii++) {
				grid[ii - 1][x1 - 1] = 'x';
			}
		}

	}

	private void drawCanvasVerticalBorder() {

		System.out.print("-");

		for (int ii = 0; ii < width; ii++) {
			System.out.print("-");
		}

		System.out.println("-");

	}

	private void drawCanvasRow(int rowNum) {
		System.out.print("|");

		for (int ii = 0; ii < width; ii++) {
			System.out.print(grid[rowNum][ii]);
		}

		System.out.print("|\n");
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof Canvas)) {
			return false;
		}

		Canvas c = (Canvas) o;

		int otherHeight = c.height;
		if (otherHeight != this.height)
			return false;

		int otherWidth = c.width;
		if (otherWidth != this.width)
			return false;

		char[][] otherGrid = c.grid;
		for (int ii = 0; ii < this.height; ii++) {
			for (int jj = 0; jj < this.width; jj++) {

				if (this.grid[ii][jj] != otherGrid[ii][jj])
					return false;

			}
		}

		return true;
	}

	public static void main(String[] args) {

	}
}
