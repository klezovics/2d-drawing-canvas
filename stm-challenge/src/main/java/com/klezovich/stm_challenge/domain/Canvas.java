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
		
		if( cc.getCmdType() == CanvasCommandType.DRAW_RECTANGLE ) {
			executeDrawRectangleCommand(cc);
		}
		
		if( cc.getCmdType() == CanvasCommandType.QUIT ) {
			executeQuitCommand(cc);
		}
		
	}
	
	private void executeQuitCommand( CanvasCommand cc ) {
		System.exit(0);
	}
	
	private void executeDrawRectangleCommand( CanvasCommand cc ) {

		if( !coordinatesFitInCanvas(cc) )
		  throw new RuntimeException("Rectangle coordinates do not fit into the canvas");
		
		int x1 = cc.getX1();
		int y1 = cc.getY1();
		int x2 = cc.getX2();
		int y2 = cc.getY2();
		
		drawLine(x1,y1,x1,y2);
		drawLine(x1,y2,x2,y2);
		drawLine(x2,y2,x2,y1);
		drawLine(x2,y1,x1,y1);
		
	}
	
	private boolean coordinatesFitInCanvas( CanvasCommand cc ) {
		
		int x1 = cc.getX1();
		int x2 = cc.getX2();
		int y1 = cc.getY1();
		int y2 = cc.getY2();
		
		if( x1 > width )
			return false;
		
		if( x2 > width )
			return false;
			
		if( y1 > height )
			return false;
			
		if( y2 > height )
			return false;
			
		
		return true;
	}
	
	private void executeCreateCommand( CanvasCommand cc ) {
		
		width = cc.getWidth();
		height = cc.getHeight();
		grid = new char[height][width];
		
	}
	
	private void executeDrawLineCommand( CanvasCommand cc ) {
		
		if( !coordinatesFitInCanvas(cc) )
		  throw new RuntimeException("Line coordinates do not fit into the canvas");
			
		int x1 = cc.getX1();
		int x2 = cc.getX2();
		int y1 = cc.getY1();
		int y2 = cc.getY2(); 
		
		drawLine(x1,y1,x2,y2);

	}
	
	private void drawLine( int x1, int y1, int x2, int y2) {

		// Horizontal line
		if( y1 == y2 ) {
			for(int ii=Math.min(x1, x2); ii<=Math.max(x1, x2); ii++ ) {
				grid[y1-1][ii-1] = 'x';
			}
		}
		
		// Vertical line
        if( x1 == x2 ) {
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
