package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;

public class DeleteOtherTables {
	
	public static void delete(Connection cn) throws Exception{			
		TableDelete.delete( cn,  "Allergy.AllergicReaction" , "" );
		TableDelete.delete(cn,"Allergy.Allergy" , "" );
		TableDelete.delete(  cn,"Allergy.AllergyComment", "" );
		TableDelete.delete(cn, "Appt.Appointment" , "" );
		TableDelete.delete(cn, "Con.Consult" , "" );
		TableDelete.delete( cn, "CPRSOrder.CPRSOrder" , "" );
		TableDelete.delete(cn, "Immun.Immunization" , "" );  		
		TableDelete.delete( cn, "Inpat.Inpatient" , "" );
		TableDelete.delete( cn, "NonVAMed.NonVAMed" , "" );
		TableDelete.delete(  cn, "Outpat.ProblemList" , "" );
		TableDelete.delete(  cn, "Outpat.VDiagnosis" , "" );	
		TableDelete.delete( cn,  "Outpat.Visit" , "" );
		TableDelete.delete( cn, "Patsub.PatientEthnicity" , "" );
		TableDelete.delete( cn, "Patsub.PatientRace" , "" );
		TableDelete.delete( cn, "RxOut.RxOutpat" , "" );
		TableDelete.delete( cn, "SPatient.SPatient" , "" );
		TableDelete.delete( cn, "SPatient.SPatientAddress"  , "" );
		TableDelete.delete( cn, "SPatient.SPatientPhone" , "" );
		TableDelete.delete( cn, "SStaff.SStaff" , "" );	
		
		TableDelete.delete( cn,"CPRSOrder.OrderedItem", "" );	
		cn.commit( );
	}
}

