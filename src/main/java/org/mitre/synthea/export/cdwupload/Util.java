package org.mitre.synthea.export.cdwupload;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.function.Function;

public class Util {
	public final static String COMMA_S = "\",\"";
	public final static String COMMA = ",";
	public static String propFilePath = "/Users/garygryan/db.properties";

	// single quote a String; deal with edge cases of nulls
	public static String singleQuoteString(String inStr) {
		if (inStr == null || inStr.length() == 0 || inStr.equals("''")) {
			return ("''");
		}
		if (inStr.contains("'")) {
			return ("'" + inStr.replaceAll("'", "''") + "'");
		} else {
			return ("'" + inStr + "'");
		}
	}
	
	// single quote a String; deal with edge cases of nulls
	public static String singleQuoteStringOrNull(String inStr) {
		if (inStr == null || inStr.length() == 0 || inStr.equals("''")) {
			return (null);
		}
		if (inStr.contains("'")) {
			return ("'" + inStr.replaceAll("'", "''") + "'");
		} else {
			return ("'" + inStr + "'");
		}
	}
	
	// single quote a String; deal with edge cases of nulls
	public static String roundToIntsingleQuoteStringOrNull(String inStr) {
		if (inStr == null || inStr.length() == 0 || inStr.equals("''") || inStr.toLowerCase().contains("null")) {
			return (null);
		}
		try {
			Integer myInt =  Math.round( Float.parseFloat(inStr)  );
			return(myInt.toString());
		}
		catch (Exception e) {
			System.out.println("************** cant convert to int:" + inStr + ":");
			return(null);
		}
	}
	
	
	
	// single quote a String; deal with edge cases of nulls
	public static String singleQuoteStringAndTruncate(String inStr, int maxLen) {
		
		if (inStr.length() > maxLen) {
			inStr = inStr.substring(0,maxLen);
		}
		if (inStr == null || inStr.length() == 0 || inStr.equals("''")) {
			return ("''");
		}
		if (inStr.contains("'")) {
			return ("'" + inStr.replaceAll("'", "''") + "'");
		} else {
			return ("'" + inStr + "'");
		}
	}
	
	// single quote a String; deal with edge cases of nulls
	public static String singleQuoteStringAndTruncateNull(int maxLen, String inStr) {
		if (inStr == null || inStr.length() == 0 || inStr.equals("''")) {
			return (null);
		}
		if (inStr.length() > maxLen) {
			inStr = inStr.substring(0,maxLen);
		}
		if (inStr == null || inStr.length() == 0 || inStr.equals("''")) {
			return ("''");
		}
		if (inStr.contains("'")) {
			return ("'" + inStr.replaceAll("'", "''") + "'");
		} else {
			return ("'" + inStr + "'");
		}
	}
	
	
	//same as above, but with parameters reversed for currying
	public static String singleQuoteStringAndTruncate(int maxlen, String inStr) {
		return(singleQuoteStringAndTruncate( inStr,  maxlen));
	}

	// single quote an string containing an int; deal with nulls
	public static String singleQuoteMissingInt(String inStr) {
		if (inStr == null || inStr.length() == 0) {
			return ("''");
		} else {
			return (inStr);
		}
	}

	//fix fixISO8601dates that are missing the seconds
	public static String fixISO8601dateOld(String isoDate) {
		if (isoDate.length() > 17 ) return( isoDate);
		if (isoDate.length() < 3) return ("1900-01-01T00:00:00Z");	
		String longDate = isoDate.replace("Z", ":00Z");
		return (longDate);
	}
	
	//fix fixISO8601dates that are missing the seconds
	public static String fixISO8601date(String isoDate) {
		if (isoDate.length() > 17 ) return( "'" + isoDate + "'");
		if (isoDate.length() < 3) return (null);
		String longDate = isoDate.replace("Z", ":00Z");
		return (longDate);
	}
	
	//fix fixISO8601dates that are missing the seconds
	public static String quoteStringfixISO8601date(String isoDate) {
		if (isoDate.length() > 17 ) return( singleQuoteString(isoDate));
		if (isoDate.length() < 3) return (singleQuoteString("1900-01-01T00:00:00Z"));
		String longDate = singleQuoteString(isoDate.replace("Z", ":00Z"));
		return (longDate);
	}
	
	public static String noOp(String inStr) {
		return(inStr);
	}
	
	public static String maxDecimal_9_4(String inStr) {	
		Integer myInt = Integer.parseInt(inStr);
		if (myInt <= 20235) {
			return(inStr);
		}
		else {
			return("20235");
		}
	}
	
	public static String  truncateTo50AndQuote(String inStr) {
		if (inStr.length() > 50 ) {return( "'" + inStr.substring(0,50) + "'" );}
		else { return( "'" + inStr + "'" ); }	
	}

	public static void updateLoadedRecordCount(HashMap<String,Integer> rowsLoadedPerTable , String tableName,Integer loaded) {
		Integer lastVal = rowsLoadedPerTable.get(tableName);
		if (lastVal != null) {
			Integer newVal = lastVal + loaded;
			rowsLoadedPerTable.put(tableName, newVal);
		}
		else {
			rowsLoadedPerTable.put(tableName, loaded);
		}
	}
	
	public static Properties getProperties() {
		String propFilePath = Util.propFilePath;
		Properties dbProps = new Properties();
		return(dbProps);
	}
	
	public static String fileToString(String fullPath) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(sb.toString());	
	}
}


