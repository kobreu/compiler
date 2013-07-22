import java.util.ArrayList;
import java.util.List;

public class Tinputlist implements AST {

	private Tinputlist inputlist;
	private Tinput input;

	public Tinputlist(Tinputlist inl, Tinput in) {
		inputlist = inl;
		input = in;
	}

	public Tinputlist(Tinput in) {
		input = in;
	}

	public String toString() {
		if (inputlist != null)
			return (inputlist + ";\nINPUT " + input + ";");
		else
			return ("INPUT " + input.toString() + "");
	}
	
	public void checkcontext() {
		// NOTHING TO DO
	}
	
	public List<Tinput> asList() {
		ArrayList<Tinput> inputs = new ArrayList<Tinput>();
		if(inputlist != null) {
			inputs.addAll(inputlist.asList());
		}
		inputs.add(input);
		return inputs;
	}

}
