package org.mitre.synthea.export.cdwupload;
import java.util.function.Function;

public class F {
	// single quote the String
	public static Function<String, String> s = (String inStr) -> Util.singleQuoteString(inStr);
	
	// fix the date and single quote the String
	public static Function<String, String> e = (String inStr) -> Util.quoteStringfixISO8601date(inStr);

	// fix the date and single quote the String
	public static Function<String, String> d = (String inStr) -> Util.fixISO8601date(inStr);

	// just return the String unaltered
	public static Function<String, String> n = (String inStr) -> Util.noOp(inStr);
	
	public static Parameter2Function<Integer, String,  String> st = ( Integer len, String inStr) -> Util.singleQuoteStringAndTruncate(inStr,len);

	
	
	public static Function<String, String>  m = (String inStr) -> Util.maxDecimal_9_4(inStr);
	
	
	//TODO - getting currying to work so you can pass a maxlen parameter and still have a varargs list of Function
	// that takes one parameter.
	
	public static Function<String, String> g = (String inStr) -> Util.truncateTo50AndQuote(inStr);
	
}

