package com.klezovich.stm_challenge.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CanvasCommand {
	
	private static final String createCmdRegExpStr = "C ([0-9]+) ([0-9]+)";
	private static final String lineCmdRegExpStr = "L ([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+)";
	private static final String rectCmdRegExpStr = "R ([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+)";
	private static final String fillCmdRegExpStr = "B ([0-9]+) ([0-9]+) ([a-z])";
	private static final String quitCmdRegExpStr = "Q";
	
	private CanvasCommandType cmdType;
	private LineType lineType; 
	private int width;
	private int height;
	private int x1;
	private int x2;
	private int y1; 
	private int y2; 
	private char color; 
	
	public String toString() {
		
		String str = cmdType.toString();
		
		if( cmdType == CanvasCommandType.CREATE )
			return str+" "+width+" "+height;
		
		if( cmdType == CanvasCommandType.DRAW_LINE )
			return str+" "+x1+" "+y1+" "+x2+" "+y2;
		
		if( cmdType == CanvasCommandType.DRAW_RECTANGLE )
			return str+" "+x1+" "+y1+" "+x2+" "+y2;
		
		if( cmdType == CanvasCommandType.FILL )
			return str+" "+x1+" "+y1+" "+color;
		
		
		return str;
	}
	
	public enum CanvasCommandType{
	  CREATE, DRAW_LINE, DRAW_RECTANGLE, FILL, QUIT;
	}
	
	public enum LineType{
		VERTICAL, HORIZONTAL;
	}
	
	private CanvasCommand(){		
	}
	
	public static CanvasCommand parseCommandString ( String cmdStr ) {
	
		CanvasCommand c = new CanvasCommand();
		
		Pattern p = Pattern.compile( createCmdRegExpStr );
		Matcher m = p.matcher( cmdStr );
		if( m.matches() ) {
		    
			c.setCmdType( CanvasCommandType.CREATE );
			c.setWidth( Integer.parseInt( m.group(1) )  );
			c.setHeight( Integer.parseInt( m.group(2) ) );
			return c; 
		}
		
		
		p = Pattern.compile( lineCmdRegExpStr );
		m = p.matcher( cmdStr );
		if( m.matches() ) {
		    
			c.setCmdType( CanvasCommandType.DRAW_LINE );
			
			int x1 = Integer.parseInt( m.group(1) );
			int y1 = Integer.parseInt( m.group(2) );
			int x2 = Integer.parseInt( m.group(3) );
			int y2 = Integer.parseInt( m.group(4) );
			
			if( x1<=0 || x2<=0 || y1 <=0 || y2 <=0 ) {
				throw new RuntimeException("All line coordinates must be non-negative");
			}
			
			if( x1 == x2 ) {
				c.setLineType( LineType.VERTICAL );
			}
			else if( y1 == y2 ) {
				c.setLineType( LineType.HORIZONTAL );
			}
			else 
			{
				throw new RuntimeException("Line must be either vertical (x1=x2) or horizontal (y1=y2)");
			}
			
			c.setX1( x1 );
			c.setY1( y1 );
			c.setX2( x2 );
			c.setY2( y2 );
			
			return c; 
		}
		
		p = Pattern.compile( rectCmdRegExpStr );
		m = p.matcher( cmdStr );
		if( m.matches() ) {
		    
			c.setCmdType( CanvasCommandType.DRAW_RECTANGLE );
			
			int x1 = Integer.parseInt( m.group(1) );
			int y1 = Integer.parseInt( m.group(2) );
			int x2 = Integer.parseInt( m.group(3) );
			int y2 = Integer.parseInt( m.group(4) );
			
			if( x1<=0 || x2<=0 || y1 <=0 || y2 <=0 ) {
				throw new RuntimeException("All line coordinates must be non-negative");
			}
			
			c.setX1( x1 );
			c.setY1( y1 );
			c.setX2( x2 );
			c.setY2( y2 );
			
			return c; 
		}
		
		p = Pattern.compile( fillCmdRegExpStr );
		m = p.matcher( cmdStr );
		if( m.matches() ) {
			c.setCmdType( CanvasCommandType.FILL );
			
			int x1 = Integer.parseInt( m.group(1) );
			int y1 = Integer.parseInt( m.group(2) );
			char color = m.group(3).charAt(0);
			
			if( x1<=0 || y1 <=0 ) {
				throw new RuntimeException("All coordinates must be non-negative");
			}
			
			c.setX1( x1 );
			c.setY1( y1 );
			c.setColor( color );
			
			return c;
		}
		
		p = Pattern.compile( quitCmdRegExpStr );
		m = p.matcher( cmdStr );
		if( m.matches() ) {
			c.setCmdType( CanvasCommandType.QUIT );
			return c;
		}
		
		throw new RuntimeException("Unknown command:'" + cmdStr +"'");
		
		
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

	
	public LineType getLineType() {
		return lineType;
	}

	public void setLineType(LineType lineType) {
		this.lineType = lineType;
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

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}
	
	
}
