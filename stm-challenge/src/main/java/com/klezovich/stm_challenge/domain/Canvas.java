package com.klezovich.stm_challenge.domain;

import com.klezovich.stm_challenge.domain.CanvasCommand.CanvasCommandType;
import com.klezovich.stm_challenge.domain.CanvasCommand.LineType;

public class Canvas {

	char[][] grid; 
	int height; 
	int width; 
	
	
	public void execute( CanvasCommand cc ) {
		
		if( cc.getCmdType() == CanvasCommandType.CREATE ) {
			executeCreateCommand(cc);
		}
		
		if( cc.getCmdType() == CanvasCommandType.DRAW_LINE ) {
			executeDrawLineCommand(cc);
		}
		
	}
	
	private void executeCreateCommand( CanvasCommand cc ) {
		
		width = cc.getWidth();
		height = cc.getHeight();
		grid = new char[height][width];
		
	}
	
	private void executeDrawLineCommand( CanvasCommand cc ) {
		
		int x1 = cc.getX1();
		int x2 = cc.getX2();
		int y1 = cc.getY1();
		int y2 = cc.getY2();
		
		if( x1 > width )
			throw new RuntimeException("Coordinate x1 out of canvas range");
		
		if( x2 > width )
			throw new RuntimeException("Coordinate x2 out of canvas range");
		
		if( y1 > height )
			throw new RuntimeException("Coordinate y1 out of canvas range");
		
		if( y2 > height )
			throw new RuntimeException("Coordinate y2 out of canvas range");
		
		if( cc.getLineType() == LineType.HORIZONTAL ) {
			for(int ii=Math.min(x1, x2); ii<=Math.max(x1, x2); ii++ ) {
				grid[y1-1][ii-1] = 'x';
			}
		}
		
        if( cc.getLineType() == LineType.VERTICAL ) {
			for(int ii=Math.min(y1, y2); ii<=Math.max(y1, y2); ii++ ) {
				grid[ii-1][x1-1] = 'x';
			}
		}
		
	}
	
	public void draw() {
		
		if( grid == null )
			return; 
		
		drawCanvasVerticalBorder();
		
		for( int rowNum=0; rowNum<height; rowNum++ ) {
			drawCanvasRow(rowNum);
		}
		
		drawCanvasVerticalBorder();
	}
	
	private void drawCanvasVerticalBorder() {
		
		System.out.print("-");
		
		for( int ii=0; ii<width; ii++ ) {
		   	System.out.print("-");
		}
		
		System.out.println("-");
		
	}
	
	private void drawCanvasRow(int rowNum ) {
		System.out.print("|");
		
		for( int ii=0; ii<width; ii++ ) {
		   	System.out.print( grid[rowNum][ii]);
		}
		
		System.out.print("|\n");
	}
	
	
	public static void main(String[] args) {
		
	}
}
