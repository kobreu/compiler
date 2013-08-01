package edu.tum.lua;

import edu.tum.lua.stdlib.Assert;
import edu.tum.lua.stdlib.DoFile;
import edu.tum.lua.stdlib.Error;
import edu.tum.lua.stdlib.GetMetatable;
import edu.tum.lua.stdlib.Getfenv;
import edu.tum.lua.stdlib.IPairs;
import edu.tum.lua.stdlib.Load;
import edu.tum.lua.stdlib.LoadFile;
import edu.tum.lua.stdlib.LoadString;
import edu.tum.lua.stdlib.Next;
import edu.tum.lua.stdlib.NotImplementedFunction;
import edu.tum.lua.stdlib.PCall;
import edu.tum.lua.stdlib.Pairs;
import edu.tum.lua.stdlib.Print;
import edu.tum.lua.stdlib.RawEqual;
import edu.tum.lua.stdlib.RawGet;
import edu.tum.lua.stdlib.RawSet;
import edu.tum.lua.stdlib.Require;
import edu.tum.lua.stdlib.Select;
import edu.tum.lua.stdlib.SetMetatable;
import edu.tum.lua.stdlib.Setfenv;
import edu.tum.lua.stdlib.ToNumber;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.stdlib.Type;
import edu.tum.lua.stdlib.Unpack;
import edu.tum.lua.stdlib.VoidFunction;
import edu.tum.lua.stdlib.Xpcall;
import edu.tum.lua.stdlib.coroutine.Create;
import edu.tum.lua.stdlib.coroutine.Resume;
import edu.tum.lua.stdlib.coroutine.Running;
import edu.tum.lua.stdlib.coroutine.Status;
import edu.tum.lua.stdlib.coroutine.Wrap;
import edu.tum.lua.stdlib.coroutine.Yield;
import edu.tum.lua.stdlib.io.FileClose;
import edu.tum.lua.stdlib.io.FileFlush;
import edu.tum.lua.stdlib.io.FileOpen;
import edu.tum.lua.stdlib.io.FileRead;
import edu.tum.lua.stdlib.io.FileSeek;
import edu.tum.lua.stdlib.io.FileWrite;
import edu.tum.lua.stdlib.math.Abs;
import edu.tum.lua.stdlib.math.Acos;
import edu.tum.lua.stdlib.math.Asin;
import edu.tum.lua.stdlib.math.Atan;
import edu.tum.lua.stdlib.math.Atan2;
import edu.tum.lua.stdlib.math.Ceil;
import edu.tum.lua.stdlib.math.Cos;
import edu.tum.lua.stdlib.math.Cosh;
import edu.tum.lua.stdlib.math.Deg;
import edu.tum.lua.stdlib.math.Floor;
import edu.tum.lua.stdlib.math.Log;
import edu.tum.lua.stdlib.math.Log10;
import edu.tum.lua.stdlib.math.Max;
import edu.tum.lua.stdlib.math.Min;
import edu.tum.lua.stdlib.math.Pow;
import edu.tum.lua.stdlib.math.Rad;
import edu.tum.lua.stdlib.math.Random;
import edu.tum.lua.stdlib.math.Sin;
import edu.tum.lua.stdlib.math.Sinh;
import edu.tum.lua.stdlib.math.Sqrt;
import edu.tum.lua.stdlib.math.Tan;
import edu.tum.lua.stdlib.math.Tanh;
import edu.tum.lua.stdlib.os.Clock;
import edu.tum.lua.stdlib.os.Date;
import edu.tum.lua.stdlib.os.Difftime;
import edu.tum.lua.stdlib.os.Rename;
import edu.tum.lua.stdlib.os.Time;
import edu.tum.lua.stdlib.string.Byte;
import edu.tum.lua.stdlib.string.Char;
import edu.tum.lua.stdlib.string.Find;
import edu.tum.lua.stdlib.string.Format;
import edu.tum.lua.stdlib.string.GMatch;
import edu.tum.lua.stdlib.string.Len;
import edu.tum.lua.stdlib.string.Lower;
import edu.tum.lua.stdlib.string.Match;
import edu.tum.lua.stdlib.string.Rep;
import edu.tum.lua.stdlib.string.Reverse;
import edu.tum.lua.stdlib.string.Sub;
import edu.tum.lua.stdlib.string.Upper;
import edu.tum.lua.stdlib.table.Concat;
import edu.tum.lua.stdlib.table.Insert;
import edu.tum.lua.stdlib.table.MaxN;
import edu.tum.lua.stdlib.table.Remove;
import edu.tum.lua.stdlib.table.Sort;
import edu.tum.lua.types.LuaTable;

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

		// TODO math.huge math.pi
		// TODO set implemented functions
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
		math.set("exp", new edu.tum.lua.stdlib.math.Exp());
		math.set("floor", new Floor());
		math.set("fmod", new NotImplementedFunction());
		math.set("frexp", new NotImplementedFunction());
		math.set("ldexp", new NotImplementedFunction());
		math.set("log", new Log());
		math.set("log10", new Log10());
		math.set("max", new Max());
		math.set("min", new Min());
		math.set("modf", new NotImplementedFunction());
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
		os.set("remove", new edu.tum.lua.stdlib.os.Remove());
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
