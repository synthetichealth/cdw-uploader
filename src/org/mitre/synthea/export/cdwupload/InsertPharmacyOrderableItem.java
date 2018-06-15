package org.mitre.synthea.export.cdwupload;
import java.sql.Connection;
import java.util.function.Function;

public class InsertPharmacyOrderableItem {
		//TODO - getting currying to work so you can pass a maxlen parameter and still have a varargs list of Function
		// that takes one parameter.
	
		public static void load(Connection cn, String dir, Boolean batchMode) throws Exception {
			if (batchMode) {
				cn.setAutoCommit(false);
			}
			TableInsert.load(dir + "pharmacyorderableitem.csv", cn, "Dim.PharmacyOrderableItem", batchMode,
					"(PharmacyOrderableItemSID,PharmacyOrderableItem,SupplyFlag)", F.s, 
					F.g,
					F.s);
		}
}
