package location;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.tum.lua.ast.Block;
import util.ParserUtil;

public class TestLocation {

	@Test
	public void test() throws Exception {
		Block b = ParserUtil.loadString("function blub() end");
	}

}
