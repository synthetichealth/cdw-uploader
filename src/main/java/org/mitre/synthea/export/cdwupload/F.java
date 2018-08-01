package org.mitre.synthea.export.cdwupload;
import java.util.function.BiFunction;
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
	
	//DONE - getting currying to work so you can pass a maxlen parameter and still have a varargs list of Function
	// that takes one parameter.
	public static Function<String, String> g = (String inStr) -> Util.truncateTo50AndQuote(inStr);

	public static Function<String, String> nl = (String inStr) -> Util.singleQuoteStringOrNull(inStr);

	// helper function to curry in Java
	// https://stackoverflow.com/questions/42822820/java-curry-an-existing-static-function
	public static <T, U, R> Function<T, Function<U, R>> curry(BiFunction<T, U, R> function) {
		return a -> b -> function.apply(a, b);
	}

	// curry the function that truncates the input string to the maximum allowed; now you can pass in the maximum length
	// that allowed and return a function that takes a String and returns a String.   That lets us use the varargs list 
	// of functions that all have the same signature; i.e. Function<String, String>
	public static Function<Integer, Function<String, String>> quoteTrunc = curry(Util::singleQuoteStringAndTruncate);
	
	public static Function<Integer, Function<String, String>> quoteTruncNull = curry(Util::singleQuoteStringAndTruncateNull);

	// write a wrapper around quoteTrunc to make it less verbose
	public static Function<String, String> qt(int maxLength) {
		return (quoteTrunc.apply(maxLength));
	}
	
	public static Function<String, String> qtn(int maxLength) {
		return (quoteTruncNull.apply(maxLength));
	}
	
	public static Function<String, String> rnd = (String inStr) -> Util.roundToIntsingleQuoteStringOrNull(inStr);
	
}
