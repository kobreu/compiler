package edu.tum.juna.junit.parser.util;

import org.junit.Test;

import edu.tum.juna.parser.ParserUtil;
import edu.tum.juna.parser.exception.StatementNotFinishedException;
import edu.tum.juna.parser.exception.SyntaxError;

public class TestParserUtil {

	@Test(expected=StatementNotFinishedException.class)
	public void testLoadStringInteractive1() throws StatementNotFinishedException, SyntaxError {
		ParserUtil.loadStringInteractive("a=");
	}
	
	@Test(expected=StatementNotFinishedException.class)
	public void testLoadStringInteractive2() throws StatementNotFinishedException, SyntaxError {
		ParserUtil.loadStringInteractive("for i in 1,2 do");
	}
	
	@Test(expected=StatementNotFinishedException.class)
	public void testLoadStringInteractive3() throws StatementNotFinishedException, SyntaxError {
		ParserUtil.loadStringInteractive("function a()");
	}
	
	@Test(expected=StatementNotFinishedException.class)
	public void testLoadStringInteractive4() throws StatementNotFinishedException, SyntaxError {
		ParserUtil.loadStringInteractive("repeat    ");
	}
	
	@Test(expected=SyntaxError.class)
	public void testLoadStringInteractive5() throws StatementNotFinishedException, SyntaxError {
		ParserUtil.loadStringInteractive("a==");
	}

}
