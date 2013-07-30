package edu.tum.lua.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class LuaUserData {
	public final RandomAccessFile raf;
	public final String fm;

	public LuaUserData(String name, String mode) throws FileNotFoundException {
		raf = new RandomAccessFile(name, "rw");
		fm = mode;
	}

	public LuaUserData(File file, String mode) throws FileNotFoundException {
		raf = new RandomAccessFile(file, "rw");
		fm = mode;
	}
}
