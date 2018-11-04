package com.klezovich.stm_challenge.domain;

import com.klezovich.stm_challenge.domain.CanvasCommand.CanvasCommandType;

public class Canvas {

	char[][] grid; 
	int h; 
	int w; 
	
	
	public void execute( CanvasCommand cc ) {
		
		if( cc.getCmdType() == CanvasCommandType.CREATE ) {
			executeCreateCommand(cc);
		}
		
	}
	
	private void executeCreateCommand( CanvasCommand cc ) {
		
		w = cc.getWidth();
		h = cc.getHeight();
		grid = new char[h][w];
		
	}
	
	public void draw() {
		
		if( grid == null )
			return; 
		
		drawCanvasVerticalBorder();
		
		for( int rowNum=0; rowNum<h; rowNum++ ) {
			drawCanvasRow(rowNum);
		}
		
		drawCanvasVerticalBorder();
	}
	
	private void drawCanvasVerticalBorder() {
		
		System.out.print("-");
		
		for( int ii=0; ii<w; ii++ ) {
		   	System.out.print("-");
		}
		
		System.out.println("-");
		
	}
	
	private void drawCanvasRow(int rowNum ) {
		System.out.print("|");
		
		for( int ii=0; ii<w; ii++ ) {
		   	System.out.print( grid[rowNum][ii]);
		}
		
		System.out.print("|\n");
	}
	
	
	public static void main(String[] args) {
		
	}
}
