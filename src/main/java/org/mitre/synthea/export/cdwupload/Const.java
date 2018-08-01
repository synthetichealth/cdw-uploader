package org.mitre.synthea.export.cdwupload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Const {
	
	public static ArrayList<String> schemaListCDWWork = new ArrayList<String> (Arrays.asList( 
			"Allergy", "Appt", "BCMA", "CPRSOrder", "Chem", "Con", "Dim", "Immun", "Inpat",
			"MOCKUP", "Micro", "Mitre", "NonVAMed", "Outpat", "PatSub", "RxOut",
			"SPatient", "SStaff", "Surg", "Vital"
			));
	
	public static ArrayList<String> tableListCDWWork = new ArrayList<String> (Arrays.asList(
			"Allergy.AllergicReaction","Allergy.Allergy","Allergy.AllergyComment","Appt.Appointment","BCMA.BCMADispensedDrug",
			"BCMA.BCMAMedicationLog","CPRSOrder.CPRSOrder","CPRSOrder.OrderedItem","Chem.LabChem","Chem.LabPanel",
			"Chem.PatientLabChem","Con.Consult","Dim.Antibiotic","Dim.AppointmentStatus","Dim.AppointmentType",
			"Dim.CPT","Dim.CPTModifier","Dim.CollectionSample","Dim.DosageForm","Dim.DrugClass",
			"Dim.DrugIngredient","Dim.DrugNameWithoutDose","Dim.ICD10","Dim.ICD10DescriptionVersion","Dim.ICD9",
			"Dim.ICD9DescriptionVersion","Dim.ImmunizationName","Dim.Institution","Dim.LOINC","Dim.LabChemTest",
			"Dim.LocalDrug","Dim.Location","Dim.MaritalStatus","Dim.NationalDrug","Dim.NationalVALabCode",
			"Dim.OrderStatus","Dim.OrderableItem","Dim.Organism","Dim.PharmacyOrderableItem","Dim.ProviderNarrative",
			"Dim.Reactant","Dim.Reaction","Dim.RequestService","Dim.Sta3n","Dim.SurgeryCancelReason",
			"Dim.Topography","Dim.VistaPackage","Dim.VitalType","Immun.Immunization","Inpat.Inpatient",
			"MOCKUP.Resource","Micro.AntibioticSensitivity","Micro.AntibioticSensitivityComment","Micro.BacteriologyReports","Micro.MicroOrderedTest",
			"Micro.Microbiology","Micro.MycobacteriologyReports","Micro.Mycology","Micro.MycologyReports","Micro.Parasitology",
			"Micro.ParasitologyReports","Micro.Virology","Micro.VirologyReports","Mitre.TABLE_COLUMN_INFO","NonVAMed.NonVAMed",
			"Outpat.ProblemList","Outpat.VDiagnosis","Outpat.VProcedure","Outpat.Visit","PatSub.PatientEthnicity",
			"PatSub.PatientRace","RxOut.RxOutpat","RxOut.RxOutpatFill","RxOut.RxOutpatMedInstructions","RxOut.RxOutpatSig",
			"SPatient.AppointmentComment","SPatient.SPatient","SPatient.SPatientAddress","SPatient.SPatientPhone","SStaff.SStaff",
			"Surg.SurgeryPRE","Surg.SurgeryPrincipalAssociatedProcedure","Surg.SurgeryPrincipalCPTModifier","Surg.SurgeryProcedureDiagnosisCode","Vital.VitalSign"
			) );

	public static ArrayList<String> tableListOITLighthouse = new ArrayList<String> (Arrays.asList("app.Lookup_Patient"));
	
	
	public static HashMap<String,String> csvToTable= new HashMap();
	// keep mapping from Synthea export file csv name to Schema.TableName
	
	static {
		csvToTable.put("allergicreaction.csv","Allergy.AllergicReaction");
		csvToTable.put("allergy.csv","Allergy.Allergy");
		csvToTable.put("allergycomment.csv","Allergy.AllergyComment");
		csvToTable.put("appointment.csv","Appt.Appointment");
		csvToTable.put("cprsorder.csv","CPRSOrder.CPRSOrder");
		csvToTable.put("ordereditem.csv","CPRSOrder.OrderedItem");
		csvToTable.put("labchem.csv","Chem.LabChem");
		csvToTable.put("labpanel.csv","Chem.LabPanel");
		csvToTable.put("patientlabchem.csv","Chem.PatientLabChem");
		csvToTable.put("consult.csv","Con.Consult");
		csvToTable.put("collectionsample.csv","Dim.CollectionSample");
		
		csvToTable.put("cpt.csv","Dim.CPT");
		
		csvToTable.put("dosageform.csv","Dim.DosageForm");
		csvToTable.put("immunizationname.csv","Dim.ImmunizationName");
		csvToTable.put("institution.csv","Dim.Institution");
		csvToTable.put("loinc.csv","Dim.LOINC");
		csvToTable.put("labchemtest.csv","Dim.LabChemTest");
		csvToTable.put("localdrug.csv","Dim.LocalDrug");
		csvToTable.put("location.csv","Dim.Location");
		csvToTable.put("maritalstatus.csv","Dim.MaritalStatus");
		csvToTable.put("nationaldrug.csv","Dim.NationalDrug");
		csvToTable.put("orderstatus.csv","Dim.OrderStatus");
		csvToTable.put("orderableitem.csv","Dim.OrderableItem");
		csvToTable.put("pharmacyorderableitem.csv","Dim.PharmacyOrderableItem");
		csvToTable.put("providernarrative.csv","Dim.ProviderNarrative");
		csvToTable.put("reaction.csv","Dim.Reaction");
		csvToTable.put("sta3n.csv","Dim.Sta3n");
		csvToTable.put("topography.csv","Dim.Topography");
		csvToTable.put("vistapackage.csv","Dim.VistaPackage");
		csvToTable.put("immunization.csv","Immun.Immunization");
		csvToTable.put("inpatient.csv","Inpat.Inpatient");
		csvToTable.put("nonvamed.csv","NonVAMed.NonVAMed");
		csvToTable.put("problemlist.csv","Outpat.ProblemList");
		csvToTable.put("vdiagnosis.csv","Outpat.VDiagnosis");
		csvToTable.put("vprocedure.csv","Outpat.VProcedure");
		csvToTable.put("visit.csv","Outpat.Visit");
		csvToTable.put("patientethnicity.csv","PatSub.PatientEthnicity");
		csvToTable.put("patientrace.csv","PatSub.PatientRace");
		csvToTable.put("rxoutpatient.csv","RxOut.RxOutpat");
		csvToTable.put("spatient.csv","SPatient.SPatient");
		csvToTable.put("spatientaddress.csv","SPatient.SPatientAddress");
		csvToTable.put("spatientphone.csv","SPatient.SPatientPhone");
		csvToTable.put("sstaff.csv","SStaff.SStaff");
		
		csvToTable.put("surgeryprocedurediagnosiscode.csv", "Surg.SurgeryProcedureDiagnosisCode");
		csvToTable.put("surgerypre.csv", "Surg.SurgeryPRE");
		
		csvToTable.put("rxoutpatfill.csv","RxOut.RxOutpatFill");
		
		// OIT_Lighthouse Table names
		csvToTable.put("lookuppatient.csv","App.Lookup_Patient");
		
		

	}

}
