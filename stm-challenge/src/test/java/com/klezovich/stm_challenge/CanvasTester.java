package com.klezovich.stm_challenge;

import java.io.File;
import java.util.List;

import com.klezovich.stm_challenge.domain.Canvas;
import com.klezovich.stm_challenge.domain.CanvasCommand;

public class CanvasTester {

	public boolean test( File f ) {
		
		Canvas cFromFile = Canvas.createFromFile(f);
		List<CanvasCommand> cLi = Canvas.parseCommandListFromFile(f);		
		Canvas cFromCmd = new Canvas();	
		
		for( CanvasCommand cmd : cLi ) {
			cFromCmd.execute(cmd);
		}
		
		if( cFromCmd.equals(cFromFile) )
			return true;
		
		return false;
		
	}
}
