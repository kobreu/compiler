/*
 * Generated by classgen, version 1.5
 * 31.07.13 15:23
 */

package edu.tum.juna.ast;

import edu.tum.juna.ast.VisitorNode;
import edu.tum.juna.parser.location.Location;

public abstract class SyntaxNode implements VisitorNode {

	private Location marker;

	private Location start;

	private Location end;

	public final Location getMarker() {
		return marker;
	}

	public final void setMarker(Location location) {
		this.marker = location;
	}

	public final Location getEnd() {
		return end;
	}

	public final Location getStart() {
		return start;
	}

	public final void setEnd(Location end) {
		this.end = end;
	}

	public final void setStart(Location start) {
		this.start = start;
	}

	public abstract SyntaxNode getParent();

	public abstract void setParent(SyntaxNode parent);
}
