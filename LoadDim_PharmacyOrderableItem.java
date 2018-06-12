package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.util.function.Function;

public class LoadDim_PharmacyOrderableItem {

	public static String  truncateTo50AndQuote(String inStr) {
		if (inStr.length() > 50 ) {return( "'" + inStr.substring(0,50) + "'" );}
		else { return( "'" + inStr + "'" ); }	
	}
	
	//TODO - getting currying to work so you can pass a maxlen parameter and still have a varargs list of Function
	// that takes one parameter.
	
	public static Function<String, String> g = (String inStr) -> LoadDim_PharmacyOrderableItem.truncateTo50AndQuote(inStr);
	
	
	public static void load(Connection cn, String dir, Boolean batchMode) throws Exception {

		if (batchMode) {
			cn.setAutoCommit(false);
		}

		TableBatch.load(dir + "pharmacyorderableitem.csv", cn, "Dim.PharmacyOrderableItem", batchMode,
				"(PharmacyOrderableItemSID,PharmacyOrderableItem,SupplyFlag)", F.s, LoadDim_PharmacyOrderableItem.g,
				F.s);
	}

}
