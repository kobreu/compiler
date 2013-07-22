
public class VariableNotConstrainedException extends Exception {
	
	private String varname;

	public VariableNotConstrainedException(String varname) {
		this.varname = varname;
	}

	public String getVarname() {
		return varname;
	}

}
