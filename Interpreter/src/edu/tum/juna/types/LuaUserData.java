package edu.tum.juna.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import edu.tum.juna.exceptions.LuaIOException;

public class LuaUserData {
	private RandomAccessFile raf;
	private final String fm;

	public LuaUserData(String name, String mode) throws FileNotFoundException {
		raf = new RandomAccessFile(name, "rw");
		fm = mode;
	}

	public LuaUserData(File file, String mode) throws FileNotFoundException {
		raf = new RandomAccessFile(file, "rw");
		fm = mode;
	}

	public void setRaf(RandomAccessFile f) {
		raf = f;
	}

	public RandomAccessFile getRaf() {
		if (raf == null) {
			throw new LuaIOException("attemp to handle with closed file");
		}

		return raf;
	}

	public String getFm() {
		if (raf == null) {
			throw new LuaIOException("attemp to handle with closed file");
		}

		return fm;
	}
}
