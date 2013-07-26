package unittest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import util.ParserUtil;
import edu.tum.lua.ast.*;

public class TestFunctionCall {

	@Test
	public void test() {
		String[] args = new String[2];
		args[0] = "testinput/arith/arith_simple.lua";
		try {
			Block chunk = ParserUtil.parse(args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
