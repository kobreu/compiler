package unittest;

import static org.junit.Assert.*;

import java.io.IOException;

import main.Main;

import org.junit.Test;

import visitors.Prog;

public class TestFunctionCall {

	@Test
	public void test() {
		String[] args = new String[2];
		args[0] = "testinput/arith/arith_simple.lua";
		try {
			Prog prog = (new Main()).run(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
