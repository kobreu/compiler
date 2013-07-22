
public class Tinput implements AST {
	
	protected Tnumber first;
	protected Tident var;
	protected Tnumber second;

	public Tinput(Tnumber n, Tident i, Tnumber nn) {
		first = n;
		var = i;
		second = nn;
	}
	
	@Override
	public String toString() {
		return first.toString() + "<=" + var.toString() + "<=" + second.toString();
	}

}
