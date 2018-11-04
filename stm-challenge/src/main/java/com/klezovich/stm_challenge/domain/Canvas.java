package com.klezovich.stm_challenge.domain;

public class Canvas {

	int[][] grid; 
	int h; 
	int w; 
	
	
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
		
		System.out.print("|");
		
		for( int ii=0; ii<w; ii++ ) {
		   	System.out.print("-");
		}
		
		System.out.print("|");
	}
	
	private void drawCanvasRow(int rowNum ) {
		System.out.print("|");
		
		for( int ii=0; ii<w; ii++ ) {
		   	System.out.print( grid[rowNum][ii]);
		}
		
		System.out.print("|");
	}
	
	
	public static void main(String[] args) {
		
	}
}
