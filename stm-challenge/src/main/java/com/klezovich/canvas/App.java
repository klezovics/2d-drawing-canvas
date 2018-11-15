package com.klezovich.canvas;

import java.util.Scanner;

import com.klezovich.canvas.domain.Canvas;
import com.klezovich.canvas.domain.CanvasCommand;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Hello, please enter the command");
    	
    	Scanner in = new Scanner( System.in );
    	Canvas c = new Canvas();
    	CanvasCommand cc;
    	
        while(true) {
        	
        	String cmdStr = in.nextLine().trim();
        	cmdStr.trim();
        	//System.out.println(cmdStr);
        	
        	try {
               cc = CanvasCommand.parseCommandString(cmdStr);		
        	}catch( Exception e) {
        		System.out.println(e.getMessage());
        		continue;
        	}
        	
        	
        	try {
        	  c.execute(cc);
        	}catch( Exception e) {
        	  System.out.println(e.getMessage());
        	  continue;
        	}
        	
        	c.draw();
        	
        }
    }
}
