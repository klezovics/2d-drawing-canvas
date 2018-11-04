package com.klezovich.stm_challenge.domain;

public class CanvasCommand {
	
	private CanvasCommandType cmdType;
	private int width;
	private int height;
	private int x1;
	private int x2;
	private int y1; 
	private int y2; 
	private char c; 
	
	public enum CanvasCommandType{
	  CREATE, LINE, RECTANGE, FILL, QUIT;
	}
	
	private CanvasCommand(){		
	}
	
	static CanvasCommand parseCommandString ( String cmdStr ) {
	
		CanvasCommand c = new CanvasCommand();
		return c; 
	}

	public CanvasCommandType getCmdType() {
		return cmdType;
	}

	public void setCmdType(CanvasCommandType cmdType) {
		this.cmdType = cmdType;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}
	
	
}
