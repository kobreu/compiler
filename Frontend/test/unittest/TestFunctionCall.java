package unittest;

import static org.junit.Assert.*;

import java.io.IOException;

import main.Main;

import org.junit.Test;

import edu.tum.lua.ast.*;

public class TestFunctionCall {

	@Test
	public void test() {
		String[] args = new String[2];
		args[0] = "testinput/arith/arith_simple.lua";
		try {
			Chunk chunk = (new Main()).run(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
