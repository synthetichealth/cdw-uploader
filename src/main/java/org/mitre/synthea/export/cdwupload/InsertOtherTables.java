package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.util.HashMap;

public class InsertOtherTables {
	
	public static void load(Connection cn, String dir, Boolean batchMode, HashMap<String,Integer> rowsLoadedPerTable) throws Exception{	
		// pass a function to transform each String from the result set in order; F.s will single quote strings, F.d will fix the dates.
		// F.n just passes the String back
		// The size of the argument lists must match so each field gets a function.  
		// 
		if (batchMode) {
			cn.setAutoCommit(false);
		}
		TableInsert.load(dir + "surgeryprocedurediagnosiscode.csv", cn, "Surg.SurgeryProcedureDiagnosisCode",  batchMode,
			   	"( SurgeryProcedureDiagnosisCodeSID, SurgerySID, Sta3n, PrincipalCPTSID, PatientSID, "
			   	+ "SurgeryDateTime, PrincipalPostOpICD9SID ,PrincipalPostOpICD10SID, CodingCompleteFlag )", 
			   	rowsLoadedPerTable,
				F.s, F.nl, F.nl, F.nl, F.nl,
				F.d, F.nl, F.nl, F.qtn(1) );
		
		//SurgeryProcedureDiagnosisCodeSID,SurgerySID,Sta3n,PrincipalCPTSID,PatientSID,SurgeryDateTime,PrincipalPostOpICD9SID,PrincipalPostOpICD10SID,CodingCompleteFlag
		
		TableInsert.load(dir + "surgerypre.csv", cn, "Surg.SurgeryPRE",  batchMode,
			   	"( SurgerySID, VisitSID, NonORLocationSID, SurgeryCancelReasonSID, CPRSOrderSID )", rowsLoadedPerTable,
				F.s, F.nl, F.nl, F.nl, F.nl );
		
		//SurgerySID, VisitSID, NonORLocationSID, SurgeryCancelReasonSID, CPRSOrderSID
		
		TableInsert.load(dir + "allergicreaction.csv", cn, "Allergy.AllergicReaction", batchMode,
				"(AllergicReactionSID,  AllergySID,  AllergyIEN,  Sta3n,   ReactionSID)",rowsLoadedPerTable ,
				F.s, F.nl, F.qtn(50), F.nl, F.nl);

		TableInsert.load(dir + "allergy.csv", cn, "Allergy.Allergy",  batchMode,
				" ( AllergySID,  AllergyIEN,  Sta3n,  PatientSID,  AllergyType"
				+ ",  AllergicReactant,  LocalDrugSID,  DrugNameWithoutDoseSID,  DrugClassSID,  ReactantSID" 
						+ ",  DrugIngredientSID,  OriginationDateTime,  OriginatingStaffSID,  ObservedHistorical,  Mechanism"
						+ ",  VerifiedFlag,  VerificationDateTime,  VerifyingStaffSID,  EnteredInErrorFlag ) " 
						,rowsLoadedPerTable ,
				F.nl, F.qtn(50), F.nl, F.nl, F.qtn(50), 
				F.qtn(8000), F.nl, F.nl, F.nl, F.nl,   
				F.nl, F.d, F.nl, F.qtn(50), F.qtn(50), 
				F.qtn(1), F.d, F.nl, F.qtn(1));
		
		TableInsert.load(dir + "allergycomment.csv", cn, "Allergy.AllergyComment",  batchMode,
				"(AllergyCommentSID,  AllergySID,  AllergyIEN,  Sta3n,  PatientSID,"
						+ "  OriginationDateTime,  EnteringStaffSID,  AllergyComment, CommentEnteredDateTime)"
						,rowsLoadedPerTable,     // DDL has different field order!
				F.s, F.nl, F.qtn(50), F.s, F.nl, 
				F.d, F.nl, F.qtn(8000), F.d);
		
		TableInsert.load(dir + "appointment.csv", cn, "Appt.Appointment",  batchMode,
				" (AppointmentSID,  Sta3n,  PatientSID,  AppointmentDateTime,  AppointmentMadeDate," + 
					"AppointmentTypeSID,  AppointmentStatus,  VisitSID,  LocationSID,  PurposeOfVisit," + 
					"  SchedulingRequestType,  FollowUpVisitFlag,  LengthOfAppointment,  ConsultSID,  CheckInDateTime"
						+ ",  CheckOutDateTime) " ,rowsLoadedPerTable,
				F.nl, F.s, F.nl, F.d, F.d, 
				F.nl, F.qtn(50), F.nl, F.nl, F.qtn(50),
				F.qtn(50), F.qtn(1), F.m, F.nl, F.d, 
				F.d);
		
		TableInsert.load(dir + "consult.csv", cn, "Con.Consult",  batchMode,
				" ( ConsultSID,  ToRequestServiceSID ) ",rowsLoadedPerTable, F.s, F.nl);
		
		TableInsert.load(dir + "cprsorder.csv", cn, "CPRSOrder.CPRSOrder",  batchMode,
				" ( CPRSOrderSID,  Sta3n,  PatientSID,  OrderStaffSID,  EnteredByStaffSID,"
				+ "  EnteredDateTime,  OrderStatusSID,  VistaPackageSID,  OrderStartDateTime,  OrderStopDateTime"
				+ ",  PackageReference ) ",rowsLoadedPerTable,
				F.s, F.s, F.nl, F.nl, F.nl, 
				F.d, F.nl, F.nl, F.d, F.d,
				F.qtn(50));	
		
		TableInsert.load(dir + "immunization.csv", cn, "Immun.Immunization",  batchMode,
				"(ImmunizationSID,  ImmunizationIEN,  Sta3n,  PatientSID,  ImmunizationNameSID,"
						+ "  Series,  Reaction,  VisitDateTime,  ImmunizationDateTime,  OrderingStaffSID,"
						+ "  ImmunizingStaffSID,  VisitSID,  ImmunizationComments,  ImmunizationRemarks )" 
						,rowsLoadedPerTable,
				F.s, F.qtn(50), F.s, F.nl, F.nl, 
				F.qtn(50), F.qtn(50), F.d, F.d, F.nl,
				F.nl, F.nl, F.qtn(255), F.qtn(8000) );
		
		TableInsert.load(dir + "inpatient.csv", cn, "Inpat.Inpatient",  batchMode,
				" ( InpatientSID, PatientSID, AdmitDateTime ) " ,rowsLoadedPerTable, F.s, F.nl, F.d );
		
		TableInsert.load(dir + "nonvamed.csv", cn, "NonVAMed.NonVAMed",  batchMode,
				" ( NonVAMedSID,  PatientSID,  NonVAMedIEN,  Sta3n,  LocalDrugSID, "
				+ "Dosage,  MedicationRoute, Schedule,  NonVAMedStatus,  CPRSOrderSID, "
				+ "  StartDateTime,  DocumentedDateTime,  NonVAMedComments ) ",rowsLoadedPerTable,
				F.s, F.nl, F.qtn(50), F.s, F.nl, 
				F.qtn(100), F.qtn(50), F.qtn(50), F.qtn(50), F.nl,
				F.d, F.d, F.qtn(8000) );
		
		TableInsert.load(dir + "ordereditem.csv", cn, "CPRSOrder.OrderedItem",  batchMode,
				" ( OrderedItemSID, CPRSOrderSID, OrderableItemSID ) ",rowsLoadedPerTable,
				F.s, F.nl, F.nl);
		
		TableInsert.load(dir + "problemlist.csv", cn, "Outpat.ProblemList",  batchMode,
				" (ProblemListSID,  Sta3n,  ICD9SID,  ICD10SID,  PatientSID,"
				+ "  ProviderNarrativeSID,  EnteredDateTime,  OnsetDateTime,  ProblemListCondition,  RecordingProviderSID,"
				+ "  ResolvedDateTime,  SNOMEDCTConceptCode)",rowsLoadedPerTable,
				F.s, F.s, F.nl, F.nl, F.nl, 
				F.nl, F.d, F.d, F.qtn(50), F.nl,
				F.d, F.qtn(50)  );
		
		TableInsert.load(dir + "vdiagnosis.csv", cn, "Outpat.VDiagnosis",  batchMode,
				" (VDiagnosisSID,  Sta3n,  ICD9SID,  ICD10SID,  PatientSID"
				+ ",VisitSID,  VisitDateTime,  VDiagnosisDateTime,  ProviderNarrativeSID,  ProblemListSID,"
				+ "OrderingProviderSID,  EncounterProviderSID )",rowsLoadedPerTable,
				F.s, F.s, F.nl, F.nl, F.nl, 
				F.nl, F.d, F.d, F.nl, F.nl,
				F.nl, F.nl );	
		
		TableInsert.load(dir + "visit.csv", cn, "Outpat.Visit",  batchMode,
				" (VisitSID,  VisitDateTime  ,CreatedByStaffSID,  LocationSID,  PatientSID )",rowsLoadedPerTable,
				F.s, F.d, F.nl, F.nl, F.nl );	
		
		TableInsert.load(dir + "patientethnicity.csv", cn, "PatSub.PatientEthnicity",  batchMode,
				"( PatientEthnicitySID,  PatientSID,  Ethnicity ) ",rowsLoadedPerTable,
				F.s, F.nl, F.qtn(50));
		
		
		TableInsert.load(dir + "patientrace.csv", cn, "PatSub.PatientRace",  batchMode,
				" (  PatientRaceSID, PatientSID,  Race) ",rowsLoadedPerTable, F.s, F.nl, F.qtn(45) );
		
		TableInsert.load(dir + "rxoutpatient.csv", cn, "RxOut.RxOutpat",  batchMode,
				" (  RxOutpatSID,  Sta3n,  RxNumber,  IssueDate,  CancelDate,"
				+ "  FinishingDateTime,  PatientSID,  ProviderSID,  EnteredByStaffSID,  LocalDrugSID,"
				+ "  NationalDrugSID,  PharmacyOrderableItemSID,  MaxRefills,  RxStatus,  OrderedQuantity )"
				,rowsLoadedPerTable,
				F.s, F.s, F.qtn(50), F.d, F.d, 
				F.d, F.nl, F.nl, F.nl, F.nl,
				F.nl, F.nl, F.nl, F.qtn(50), F.qtn(50) );	
		
		TableInsert.load(dir + "spatient.csv", cn, "SPatient.SPatient", batchMode,
				"( PatientSID, PatientName, PatientLastName, PatientFirstName,PatientSSN,"
				+ "Age, BirthDateTime, DeceasedFlag, DeathDateTime, Gender"
				+ ",SelfIdentifiedGender, Religion, MaritalStatus, MaritalStatusSID, PatientEnteredDateTime)"
				,rowsLoadedPerTable,
				F.s, F.qtn(100), F.qtn(50), F.qtn(50), F.qtn(50), 
				F.s, F.d, F.qtn(1), F.d, F.qtn(1), 
				F.qtn(50), F.qtn(30), F.qtn(25), F.nl, F.d);
		
		TableInsert.load(dir + "spatientaddress.csv", cn, "SPatient.SPatientAddress", batchMode,
				   " ( SPatientAddressSID, PatientSID,  AddressType,  NameOfContact,  RelationshipToPatient,"
						   + "  StreetAddress1,  StreetAddress2,  StreetAddress3,  City,  State,  "
						   + "  Zip,  PostalCode,  Country,  GISMatchScore,  GISStreetSide,"
						   + "  GISPatientAddressLongitude,  GISPatientAddressLatitude,  GISFIPSCode )" 
						   ,rowsLoadedPerTable,
				F.s, F.nl, F.qtn(50), F.qtn(50), F.qtn(50), 
				F.qtn(50), F.qtn(50), F.qtn(50), F.qtn(50), F.qtn(30),
				F.qtn(50), F.qtn(50), F.qtn(100), F.nl, F.qtn(50),
				F.nl, F.nl, F.qtn(50)  );
		
		TableInsert.load(dir + "spatientphone.csv", cn, "SPatient.SPatientPhone",  batchMode,
			   	"( SPatientPhoneSID,  PatientSID,  PatientContactType,  NameOfContact,  RelationshipToPatient,"
			   	+ "  PhoneNumber,  WorkPhoneNumber,  EmailAddress ) ", rowsLoadedPerTable,
				F.s, F.nl, F.qtn(50), F.qtn(50), F.qtn(50), 
				F.qtn(50), F.qtn(50), F.qtn(50)  );
		
		TableInsert.load(dir + "sstaff.csv", cn, "SStaff.SStaff", batchMode,
				" ( StaffSID , StaffName ) ", rowsLoadedPerTable, F.s, F.qtn(100)  );	
		
		
		TableInsert.load(dir + "vprocedure.csv", cn, "Outpat.VProcedure", batchMode,
				" ( VProcedureSID, PatientSID, VisitSID, CPRSOrderSID ) ", rowsLoadedPerTable, 
				F.nl, F.nl, F.nl, F.nl  );	
		
		TableInsert.load(dir + "labchem.csv", cn, "Chem.LabChem",  batchMode,
			   	"( LabChemSID, Sta3n, LabPanelSID, LabChemTestSID, PatientSID"
			   	+ ",StaffSID, LabChemSpecimenDateTime, LabChemResultValue, LOINCSID, Units"
			   	+ ",Abnormal, RefHigh, RefLow )", rowsLoadedPerTable,
				F.nl, F.s, F.nl, F.nl, F.nl,
				F.nl, F.d, F.qtn(100), F.nl, F.qtn(50), 
				F.qtn(50), F.qtn(100), F.qtn(100)  );
		
		TableInsert.load(dir + "labpanel.csv", cn, "Chem.LabPanel",  batchMode,
			   	"( LabPanelSID, LabPanelIEN, PatientSID )", rowsLoadedPerTable,
				F.nl, F.qt(50), F.nl  );
		
		TableInsert.load(dir + "patientlabchem.csv", cn, "Chem.PatientLabChem",  batchMode,
			   	"( LabChemSID, Sta3n, LabPanelSID, PatientSID, LabChemSpecimenDateTime"
			   	+ ",LabChemCompleteDateTime, TopographySID, AccessionInstitutionSID )", rowsLoadedPerTable,
				F.s, F.s, F.nl, F.nl, F.d,
				F.d, F.nl, F.nl  );
		
		
		TableInsert.load(dir + "rxoutpatfill.csv", cn, "RxOut.RxOutpatFill",  batchMode,
			   	"( RxOutpatFillSID, RxOutpatSID, Qty, DaysSupply )", rowsLoadedPerTable,
				F.s, F.s, F.qtn(50), F.nl );
		
		TableInsert.load(dir + "vitalsign.csv", cn, "Vital.VitalSign",  batchMode,
			   	"( VitalSignSID, Sta3n, VitalSignTakenDateTime, PatientSID, VitalTypeSID,"
			   	+ " VitalResult, Systolic, Diastolic, SupplementalO2, LocationSID, "
			   	+ " StaffSID, EnteredInErrorFlag ) ",
			   			 rowsLoadedPerTable,
				F.s, F.s, F.d, F.nl, F.nl,
				F.qtn(50), F.rnd, F.rnd, F.qtn(100), F.nl
				, F.nl , F.qtn(1));
		

	}
}
