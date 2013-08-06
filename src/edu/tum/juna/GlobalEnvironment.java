package edu.tum.juna;

import edu.tum.juna.stdlib.Assert;
import edu.tum.juna.stdlib.DoFile;
import edu.tum.juna.stdlib.Error;
import edu.tum.juna.stdlib.GetMetatable;
import edu.tum.juna.stdlib.Getfenv;
import edu.tum.juna.stdlib.IPairs;
import edu.tum.juna.stdlib.Load;
import edu.tum.juna.stdlib.LoadFile;
import edu.tum.juna.stdlib.LoadString;
import edu.tum.juna.stdlib.Next;
import edu.tum.juna.stdlib.NotImplementedFunction;
import edu.tum.juna.stdlib.PCall;
import edu.tum.juna.stdlib.Pairs;
import edu.tum.juna.stdlib.Print;
import edu.tum.juna.stdlib.RawEqual;
import edu.tum.juna.stdlib.RawGet;
import edu.tum.juna.stdlib.RawSet;
import edu.tum.juna.stdlib.Require;
import edu.tum.juna.stdlib.Select;
import edu.tum.juna.stdlib.SetMetatable;
import edu.tum.juna.stdlib.Setfenv;
import edu.tum.juna.stdlib.ToNumber;
import edu.tum.juna.stdlib.ToString;
import edu.tum.juna.stdlib.Type;
import edu.tum.juna.stdlib.Unpack;
import edu.tum.juna.stdlib.VoidFunction;
import edu.tum.juna.stdlib.Xpcall;
import edu.tum.juna.stdlib.coroutine.Create;
import edu.tum.juna.stdlib.coroutine.Resume;
import edu.tum.juna.stdlib.coroutine.Running;
import edu.tum.juna.stdlib.coroutine.Status;
import edu.tum.juna.stdlib.coroutine.Wrap;
import edu.tum.juna.stdlib.coroutine.Yield;
import edu.tum.juna.stdlib.io.FileClose;
import edu.tum.juna.stdlib.io.FileFlush;
import edu.tum.juna.stdlib.io.FileOpen;
import edu.tum.juna.stdlib.io.FileRead;
import edu.tum.juna.stdlib.io.FileSeek;
import edu.tum.juna.stdlib.io.FileWrite;
import edu.tum.juna.stdlib.math.Abs;
import edu.tum.juna.stdlib.math.Acos;
import edu.tum.juna.stdlib.math.Asin;
import edu.tum.juna.stdlib.math.Atan;
import edu.tum.juna.stdlib.math.Atan2;
import edu.tum.juna.stdlib.math.Ceil;
import edu.tum.juna.stdlib.math.Cos;
import edu.tum.juna.stdlib.math.Cosh;
import edu.tum.juna.stdlib.math.Deg;
import edu.tum.juna.stdlib.math.Floor;
import edu.tum.juna.stdlib.math.Log;
import edu.tum.juna.stdlib.math.Log10;
import edu.tum.juna.stdlib.math.Max;
import edu.tum.juna.stdlib.math.Min;
import edu.tum.juna.stdlib.math.Modf;
import edu.tum.juna.stdlib.math.Pow;
import edu.tum.juna.stdlib.math.Rad;
import edu.tum.juna.stdlib.math.Random;
import edu.tum.juna.stdlib.math.Sin;
import edu.tum.juna.stdlib.math.Sinh;
import edu.tum.juna.stdlib.math.Sqrt;
import edu.tum.juna.stdlib.math.Tan;
import edu.tum.juna.stdlib.math.Tanh;
import edu.tum.juna.stdlib.os.Clock;
import edu.tum.juna.stdlib.os.Date;
import edu.tum.juna.stdlib.os.Difftime;
import edu.tum.juna.stdlib.os.Rename;
import edu.tum.juna.stdlib.os.Time;
import edu.tum.juna.stdlib.string.Byte;
import edu.tum.juna.stdlib.string.Char;
import edu.tum.juna.stdlib.string.Find;
import edu.tum.juna.stdlib.string.Format;
import edu.tum.juna.stdlib.string.GMatch;
import edu.tum.juna.stdlib.string.Len;
import edu.tum.juna.stdlib.string.Lower;
import edu.tum.juna.stdlib.string.Match;
import edu.tum.juna.stdlib.string.Rep;
import edu.tum.juna.stdlib.string.Reverse;
import edu.tum.juna.stdlib.string.Sub;
import edu.tum.juna.stdlib.string.Upper;
import edu.tum.juna.stdlib.table.Concat;
import edu.tum.juna.stdlib.table.Insert;
import edu.tum.juna.stdlib.table.MaxN;
import edu.tum.juna.stdlib.table.Remove;
import edu.tum.juna.stdlib.table.Sort;
import edu.tum.juna.types.LuaTable;

public class GlobalEnvironment extends LuaTable {

	public GlobalEnvironment(String[] arg) {
		this();

		LuaTable argTable = new LuaTable();

		for (int i = 0; i < arg.length; i++) {
			argTable.set(i + 1.0, arg[i]);
		}

		set("arg", argTable);
	}

	/** Create a new global environment. */
	public GlobalEnvironment() {

		LuaTable _package = new LuaTable();
		LuaTable _package_loaded = new LuaTable();
		_package.set("loaded", _package_loaded);
		_package.set("path", "./?.lua");
		set("package", _package);

		set("assert", new Assert());
		set("collectgarbage", new VoidFunction());
		set("dofile", new DoFile(this));
		set("error", new Error());
		set("_G", this);
		set("getfenv", new Getfenv(this));
		set("getmetatable", new GetMetatable());
		set("ipairs", new IPairs());
		set("load", new Load());
		set("loadfile", new LoadFile());
		set("loadstring", new LoadString());
		set("next", new Next());
		set("pairs", new Pairs());
		set("pcall", new PCall());
		set("print", new Print());
		set("require", new Require(this));
		set("rawequal", new RawEqual());
		set("rawget", new RawGet());
		set("rawset", new RawSet());
		set("select", new Select());
		set("setfenv", new Setfenv());
		set("setmetatable", new SetMetatable());
		set("tonumber", new ToNumber());
		set("tostring", new ToString());
		set("type", new Type());
		set("unpack", new Unpack());
		set("_VERSION", "Java Lua -1");
		set("xpcall", new Xpcall());

		LuaTable coroutine = new LuaTable();
		LuaTable string = new LuaTable();

		string.set("byte", new Byte());
		string.set("char", new Char());
		string.set("dump", new VoidFunction());
		string.set("find", new Find());
		string.set("format", new Format());
		string.set("gmatch", new GMatch());
		string.set("gsub", new VoidFunction());
		string.set("len", new Len());
		string.set("lower", new Lower());
		string.set("match", new Match());
		string.set("rep", new Rep());
		string.set("reverse", new Reverse());
		string.set("sub", new Sub());
		string.set("upper", new Upper());

		LuaTable table = new LuaTable();

		table.set("concat", new Concat());
		table.set("maxn", new MaxN());
		table.set("insert", new Insert());
		table.set("remove", new Remove());
		table.set("sort", new Sort());

		LuaTable math = new LuaTable();
		math.set("abs", new Abs());
		math.set("acos", new Acos());
		math.set("asin", new Asin());
		math.set("atan", new Atan());
		math.set("atan2", new Atan2());
		math.set("ceil", new Ceil());
		math.set("cos", new Cos());
		math.set("cosh", new Cosh());
		math.set("deg", new Deg());
		math.set("exp", new edu.tum.juna.stdlib.math.Exp());
		math.set("floor", new Floor());
		math.set("fmod", new NotImplementedFunction());
		math.set("frexp", new NotImplementedFunction());
		math.set("ldexp", new NotImplementedFunction());
		math.set("log", new Log());
		math.set("log10", new Log10());
		math.set("max", new Max());
		math.set("min", new Min());
		math.set("modf", new Modf());
		math.set("pi", Math.PI);
		math.set("pow", new Pow());
		math.set("rad", new Rad());
		math.set("random", new Random());
		math.set("randomseed", new NotImplementedFunction());
		math.set("sin", new Sin());
		math.set("sinh", new Sinh());
		math.set("sqrt", new Sqrt());
		math.set("tan", new Tan());
		math.set("tanh", new Tanh());

		coroutine.set("create", new Create());
		coroutine.set("resume", new Resume());
		coroutine.set("running", new Running());
		coroutine.set("status", new Status());
		coroutine.set("wrap", new Wrap());
		coroutine.set("yield", new Yield());

		LuaTable io = new LuaTable();

		io.set("open", new FileOpen());
		io.set("flush", new FileFlush());
		io.set("close", new FileClose());
		io.set("read", new FileRead());
		io.set("write", new FileWrite());
		io.set("seek", new FileSeek());

		LuaTable os = new LuaTable();
		LuaTable debug = new LuaTable();

		os.set("clock", new Clock());
		os.set("date", new Date());
		os.set("difftime", new Difftime());
		os.set("execute", new NotImplementedFunction());
		os.set("exit", new NotImplementedFunction());
		os.set("getenv", new NotImplementedFunction());
		os.set("remove", new edu.tum.juna.stdlib.os.Remove());
		os.set("rename", new Rename());
		os.set("setlocale", new NotImplementedFunction());
		os.set("time", new Time());
		os.set("tmpname", new NotImplementedFunction());

		set("coroutine", coroutine);

		set("string", string);
		set("table", table);
		set("math", math);
		set("io", io);
		set("os", os);
		set("debug", debug);
	}
}
