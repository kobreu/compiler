package edu.tum.juna.parser.exception;

/**
 * Thrown when a string is parsed that is not a finished statement, but could be
 * finished by some more meaningful input.
 * 
 * @author KB
 * 
 */
public class StatementNotFinishedException extends Exception {
	private static final long serialVersionUID = 6587137400090250302L;
}
