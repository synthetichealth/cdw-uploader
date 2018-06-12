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
	
}
