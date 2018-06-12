package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;

public class LoadOITLighthouseTables {
	
	
	public static void load(Connection cn, String dir, Boolean  batchMode ) throws Exception{	
		// pass a function to transform each String from the result set in order; F.s will single quote strings, F.d will fix the dates.
		// F.n just passes the String back
		// The size of the argument lists must match so each field gets a function. 
	
	if (batchMode) {
		cn.setAutoCommit(false);
	}
	
	TableDelete.delete(cn, "App.Lookup_Patient");
	TableInsert.load(dir + "lookuppatient.csv", cn, "App.Lookup_Patient",  batchMode,
			" ( PatientSID, Sta3n, PatientIEN, PatientICN, PatientFullICN,"
			+ "  PatientName,  TestPatient ) ",
			F.s, F.s, F.s, F.s, F.s
			, F.s, F.s);
	}
}
