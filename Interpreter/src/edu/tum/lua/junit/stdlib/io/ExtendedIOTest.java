package edu.tum.lua.junit.stdlib.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import edu.tum.lua.stdlib.io.FileOpen;
import edu.tum.lua.stdlib.io.FileRead;
import edu.tum.lua.stdlib.io.FileSeek;
import edu.tum.lua.stdlib.io.FileWrite;

public class ExtendedIOTest {

	@Test
	public void simpleIO() throws IOException {
		String filename = File.createTempFile("test", ".txt").getPath();

		Object file = new FileOpen().apply(filename, "w").get(0);

		assertTrue(new FileWrite().apply(file, "Hello World").size() == 0);

		file = new FileOpen().apply(filename, "w+").get(0);

		assertTrue(new FileWrite().apply(file, "Hello World").size() == 0);
		assertEquals("Hello World", new FileRead().apply(file, "*a").get(0));

		assertTrue(new FileSeek().apply(file, "set").get(0) != null);
		assertTrue(new FileWrite().apply(file, "Startline\n").size() == 0);

		assertEquals("Startline\nd", new FileRead().apply(file, "*a").get(0));

		assertTrue(new FileSeek().apply(file, "end").get(0) != null);
		assertTrue(new FileWrite().apply(file, "\nEndline").size() == 0);

		assertEquals("Startline\nd\nEndline", new FileRead().apply(file, "*a").get(0));

		assertTrue(new FileSeek().apply(file, "set", 10.0).get(0) != null);
		assertTrue(new FileWrite().apply(file, "Second Line\n").size() == 0);

		assertEquals("Startline\nSecond Line\n", new FileRead().apply(file, "*a").get(0));

	}

}