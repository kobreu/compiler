package edu.tum.lua.types;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LuaUserData {
	private final RandomAccessFile raf;
	private final String fm;

	public LuaUserData(String name, String mode) throws IOException {
		raf = new RandomAccessFile(name, mode);
		fm = mode;
	}

	public LuaUserData(File file, String mode) throws IOException {
		raf = new RandomAccessFile(file, mode);
		fm = mode;
	}

	public long length() throws IOException {
		return raf.length();
	}

	public void read(byte[] fileBytes) throws IOException {
		raf.read(fileBytes);
	}

	public long getFilePointer() throws IOException {
		return raf.getFilePointer();
	}

	public void seek(long pos) throws IOException {
		raf.seek(pos);
	}

	public int read() throws IOException {
		return raf.read();
	}

	public String readLine() {
		try {
			return raf.readLine();
		} catch (EOFException eof) {
			return null;
		} catch (IOException ioe) {
			return "";
		}
	}
}
