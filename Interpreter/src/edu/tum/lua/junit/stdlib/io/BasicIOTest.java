package edu.tum.lua.junit.stdlib.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import edu.tum.lua.stdlib.io.FileRead;
import edu.tum.lua.stdlib.io.FileWrite;
import edu.tum.lua.stdlib.io.Open;

public class BasicIOTest {

	@Test
	public void simpleIO() throws IOException {
		String filename = File.createTempFile("test", ".txt").getPath();

		System.out.println(filename);

		Object file = new Open().apply(filename, "w").get(0);

		assertTrue(new FileWrite().apply(file, "Testfile: 24.9, Hello World.\nMore Numbers\t23, 4.52.").size() == 0);

		file = new Open().apply(filename, "r").get(0);

		assertEquals("Testfile: 24.9, Hello World.", new FileRead().apply(file).get(0));

		file = new Open().apply(filename, "a").get(0);

		assertTrue(new FileWrite().apply(file, "\nend of file").size() == 0);

		file = new Open().apply(filename, "r").get(0);

		assertEquals("Testfile: 24.9, Hello World.\nMore Numbers\t23, 4.52.\nend of file",
				new FileRead().apply(file, "*a").get(0));

		file = new Open().apply(filename, "w").get(0);

		assertTrue(new FileWrite().apply(file, "Hello World").size() == 0);

		file = new Open().apply(filename, "r+").get(0);

		assertEquals("Hello World", new FileRead().apply(file, "*a").get(0));
	}

}