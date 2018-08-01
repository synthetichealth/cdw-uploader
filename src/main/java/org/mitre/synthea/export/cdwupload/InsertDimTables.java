package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.util.HashMap;
import java.util.function.Function;

public class InsertDimTables {
	
	public static void load(Connection cn, String dir, Boolean batchMode, HashMap<String,Integer> rowsLoadedPerTable ) throws Exception{	
		// pass a function to transform each String from the result set in order; F.s will single quote strings, F.d will fix the dates.
		// F.n just passes the String back
		// The size of the argument lists must match so each field gets a function.  
		// 
		if (batchMode) {
			cn.setAutoCommit(false);
		}
		
		TableInsert.load(dir+"collectionsample.csv", cn,"Dim.CollectionSample",
				batchMode   //added 2018-07-09
				,"(CollectionSampleSID,CollectionSample)",
				rowsLoadedPerTable ,F.nl,F.qt(50));
		
		TableInsert.load(dir+"cpt.csv", cn,"Dim.CPT",
				batchMode
				,"(CPTSID, CPTCode, CPTName, CPTDescription)",
				rowsLoadedPerTable, F.nl, F.qt(5),  F.qt(50) ,  F.qt(8000) );
		
		TableInsert.load(dir+"dosageform.csv",cn,"Dim.DosageForm",batchMode, 
				" ( DosageFormSID, DosageFormIEN, DosageForm ) ",
				rowsLoadedPerTable,  F.s, F.qt(50), F.qt(50));
		
		TableInsert.load(dir+"immunizationname.csv",cn,"Dim.ImmunizationName", batchMode, 
				 "(ImmunizationNameSID, ImmunizationName,CVXCode, MaxInSeries) " 
				,rowsLoadedPerTable  ,F.s, F.s, F.qt(50), F.qt(50));

		TableInsert.load(dir+"institution.csv", cn,"Dim.Institution",batchMode
				,"(InstitutionSID, Sta3n, InstitutionName, InstitutionCode)",
				rowsLoadedPerTable, F.nl, F.nl, F.qt(50), F.qt(50) );
		
		TableInsert.load(dir+"loinc.csv", cn,"Dim.LOINC",batchMode
				,"( LOINCSID, LOINC, Component)",
				rowsLoadedPerTable, F.nl, F.qt(50), F.qt(250) );
		
		TableInsert.load(dir+"labchemtest.csv", cn,"Dim.LabChemTest",batchMode
				,"( LabChemTestSID, LabChemTestName, CollectionSampleSID ) ",
				rowsLoadedPerTable ,F.nl, F.qt(50), F.nl);
		
		TableInsert.load(dir+"localdrug.csv",cn,"Dim.LocalDrug",batchMode,
				"(LocalDrugSID, LocalDrugIEN, Sta3n, LocalDrugNameWithDose, NationalDrugSID, "
				+ "NationalDrugNameWithDose, PharmacyOrderableItemSID )"
				,rowsLoadedPerTable 
				,F.s, F.qt(50), F.s, F.qt(100), F.s,
				F.qt(100) , F.s);
		
		TableInsert.load(dir+"location.csv",cn,"Dim.Location",batchMode," ( LocationSID,LocationName) "
				,rowsLoadedPerTable, F.s, F.qt(50) );    /// another column, DefaultProviderSID is not provided
		
		TableInsert.load(dir + "maritalstatus.csv", cn, "Dim.MaritalStatus",batchMode, "(MaritalStatusSID, MaritalStatusCode)"
				,rowsLoadedPerTable , F.s, F.qt(1) );
		
		TableInsert.load(dir+"nationaldrug.csv", cn, "Dim.NationalDrug",batchMode, 
				"( NationalDrugSID, DrugNameWithDose, DosageFormSID, InactivationDate, VUID )"
				,rowsLoadedPerTable ,
				F.s, F.qt(100), F.s, F.d, F.qt(50)); 
		
		TableInsert.load(dir + "orderstatus.csv", cn, "Dim.OrderStatus",batchMode, "(OrderStatusSID,OrderStatus)",
				rowsLoadedPerTable , F.s, F.qt(50));

		TableInsert.load(dir + "orderableitem.csv", cn, "Dim.OrderableItem",  false,
					" (OrderableItemSID, OrderableItemName, IVBaseFlag, IVAdditiveFlag) ",rowsLoadedPerTable ,
					F.s, F.qt(100), F.qt(1), F.qt(1) );
		
		TableInsert.load(dir + "pharmacyorderableitem.csv", cn, "Dim.PharmacyOrderableItem", batchMode,
				"(PharmacyOrderableItemSID, PharmacyOrderableItem, SupplyFlag)",rowsLoadedPerTable ,
				F.s, F.qt(50), F.qt(1) );   // new fields MedicationRoute, DosageForm, and PatientInstructions not in CSV

		TableInsert.load(dir+"providernarrative.csv",cn,"Dim.ProviderNarrative",batchMode,
				"( ProviderNarrativeSID, ProviderNarrative )",rowsLoadedPerTable ,F.s, F.qt(255));
		
		TableInsert.load(dir + "reaction.csv", cn, "Dim.Reaction", batchMode,"(ReactionSID,Reaction,VUID)",
				rowsLoadedPerTable , F.s, F.qt(50), F.qt(50) );
		
		TableInsert.load(dir + "sta3n.csv", cn, "Dim.Sta3n", batchMode,"( Sta3n, Sta3nName, TimeZone)",
				rowsLoadedPerTable , F.s, F.qt(50) ,F.qt(100) );

		TableInsert.load(dir+"topography.csv", cn,"Dim.Topography",batchMode
				,"(TopographySID, Topography)",rowsLoadedPerTable, F.nl, F.qt(100));		
		
		TableInsert.load(dir+"vistapackage.csv", cn,"Dim.VistaPackage",batchMode,"( VistaPackageSID, VistaPackage )"
				,rowsLoadedPerTable ,F.s, F.qt(50));	

		TableInsert.load(dir + "vitaltype.csv", cn, "Dim.VitalType",  batchMode,
			   	" ( VitalTypeSID, VitalType, VUID ) ",
			   			 rowsLoadedPerTable,
				F.s, F.qtn(50), F.qtn(50) );
	}
}

