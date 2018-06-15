package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.util.function.Function;

public class InsertDimTables {
	
	public static void load(Connection cn, String dir, Boolean batchMode) throws Exception{	
		// pass a function to transform each String from the result set in order; F.s will single quote strings, F.d will fix the dates.
		// F.n just passes the String back
		// The size of the argument lists must match so each field gets a function.  
		// 
		if (batchMode) {
			cn.setAutoCommit(false);
		}
	
		
		TableInsert.load(dir+"dosageform.csv",cn,"Dim.DosageForm",batchMode, " ( DosageFormSID, DosageFormIEN, DosageForm ) ",F.s,F.s,F.s);
		TableInsert.load(dir+"immunizationname.csv",cn,"Dim.ImmunizationName",batchMode," (ImmunizationNameSID, ImmunizationName,CVXCode, MaxInSeries) " 
				,F.s,F.s,F.s,F.s);
		TableInsert.load(dir+"localdrug.csv",cn,"Dim.LocalDrug",batchMode,
				 "( LocalDrugSID,LocalDrugIEN,Sta3n,LocalDrugNameWithDose,NationalDrugSID,NationalDrugNameWithDose )",F.s,F.s,F.s,F.s,F.s,F.s);
		TableInsert.load(dir+"location.csv",cn,"Dim.Location",batchMode," ( LocationSID,LocationName) ",F.s,F.g);
		TableInsert.load(dir + "maritalstatus.csv", cn, "Dim.MaritalStatus",batchMode, "(MaritalStatusSID,MaritalStatusCode)", F.s,F.s );
		TableInsert.load(dir+"nationaldrug.csv", cn, "Dim.NationalDrug",batchMode, "(NationalDrugSID,DrugNameWithDose,DosageFormSID,InactivationDate,VUID)",
				F.s,F.s,F.s,F.d,F.s);  
		TableInsert.load(dir + "orderstatus.csv", cn, "Dim.OrderStatus",batchMode, "(OrderStatusSID,OrderStatus)", F.s,F.s);
		
		TableInsert.load(dir + "orderableitem.csv", cn, "Dim.OrderableItem",  false,
					" (OrderableItemSID, OrderableItemName, IVBaseFlag, IVAdditiveFlag) ",
					F.s, F.s, F.s, F.s );
		
		TableInsert.load(dir + "pharmacyorderableitem.csv", cn, "Dim.PharmacyOrderableItem", batchMode,
				"(PharmacyOrderableItemSID,PharmacyOrderableItem,SupplyFlag)", F.s, F.g,F.s);
		
		TableInsert.load(dir+"providernarrative.csv",cn,"Dim.ProviderNarrative",batchMode,"(ProviderNarrativeSID,ProviderNarrative )",F.s,F.s);
		TableInsert.load(dir + "reaction.csv", cn, "Dim.Reaction", batchMode,"(ReactionSID,Reaction,VUID)", F.s,F.s,F.s );
		TableInsert.load(dir + "sta3n.csv", cn, "Dim.Sta3n", batchMode,"(Sta3n,Sta3nName,TimeZone)", F.s,F.g,F.s );
		TableInsert.load(dir+"vistapackage.csv", cn,"Dim.VistaPackage",batchMode,"(VistaPackageSID,VistaPackage)",F.s,F.s);	
	}
}


