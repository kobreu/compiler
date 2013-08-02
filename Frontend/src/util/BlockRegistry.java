package util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import edu.tum.lua.ast.Block;

public class BlockRegistry {

	private static final Map<Block, File> map = new HashMap<>();

	public static void register(Block b, File f) {
		map.put(b, f);
	}

	public static File lookup(Block b) {
		return map.get(b);
	}
}
