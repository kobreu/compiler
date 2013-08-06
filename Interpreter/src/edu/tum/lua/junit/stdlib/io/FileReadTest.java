package edu.tum.lua.junit.stdlib.io;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.tum.lua.exceptions.LuaBadArgumentException;
import edu.tum.lua.stdlib.io.FileRead;
import edu.tum.lua.types.LuaUserData;

public class FileReadTest {

	private LuaUserData raf;

	@Before
	public void setup() throws IOException {
		File file = File.createTempFile("test", ".txt");
		FileOutputStream fos = new FileOutputStream(file);

		System.out.println(file.getPath());

		String testInput = "Testfile: 24.9, Hello World.\nMore Numbers\t23, 4.52.";

		byte[] text = new byte[testInput.length()];
		for (int i = 0; i < testInput.length(); i++) {
			text[i] = (byte) testInput.charAt(i);
		}
		fos.write(text);
		fos.close();

		raf = new LuaUserData(file.getPath(), "r");
	}

	@Test(expected = LuaBadArgumentException.class)
	public void testBadArgs() {
		FileRead fr = new FileRead();
		fr.apply(raf, "bla");
	}

	@Test
	public void testAll() {
		FileRead fr = new FileRead();

		assertEquals("Testfile: 24.9, Hello World.\nMore Numbers\t23, 4.52.", fr.apply(raf, "*a").get(0));
	}

	@Test
	public void testNumber() {
		FileRead fr = new FileRead();

		assertEquals(null, fr.apply(raf, "*n").get(0));
		assertEquals("Testfile: ", fr.apply(raf, 10.0).get(0));
		assertEquals(24.9, fr.apply(raf, "*n").get(0));
		assertEquals(", Hello World.\nMore Numbers\t", fr.apply(raf, 28.0).get(0));
		assertEquals(23.0, fr.apply(raf, "*n").get(0));
		assertEquals(", ", fr.apply(raf, 2.0).get(0));
		assertEquals(4.52, fr.apply(raf, "*n").get(0));
	}

	@Test
	public void testLine() {
		FileRead fr = new FileRead();

		assertEquals("Testfile: 24.9, Hello World.", fr.apply(raf).get(0));
		assertEquals("More Numbers\t23, 4.52.", fr.apply(raf, "*l").get(0));
		assertEquals(null, (fr.apply(raf, "*l").get(0)));
	}

	@Test
	public void testChars() {
		FileRead fr = new FileRead();

		assertEquals("Te", fr.apply(raf, 2.0).get(0));
		assertEquals("st", fr.apply(raf, 2.5).get(0));
		assertEquals("file: 24.9,", fr.apply(raf, 11.9).get(0));
		assertEquals("", fr.apply(raf, 0.0).get(0));
		assertEquals(null, fr.apply(raf, 50.0).get(0));
	}
}
