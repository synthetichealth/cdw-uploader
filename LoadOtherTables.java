package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.util.function.Function;

public class LoadOtherTables {
	

	
		
	public static void load(Connection cn, String dir, Boolean batchMode) throws Exception {	
			
		if (batchMode) {
			cn.setAutoCommit(false);
		}
		
		
		TableBatch.load(dir + "allergicreaction.csv", cn, "Allergy.AllergicReaction", batchMode,
				"(AllergicReactionSID,  AllergySID,  AllergyIEN,  Sta3n,   ReactionSID)",
				F.s, F.s, F.s, F.s, F.s);
		
		TableBatch.load(dir + "allergy.csv", cn, "Allergy.Allergy",  batchMode,
				" ( AllergySID,  AllergyIEN,  Sta3n,  PatientSID,  AllergyType"
				+ ",  AllergicReactant,  LocalDrugSID,  DrugNameWithoutDoseSID,  DrugClassSID,  ReactantSID" 
						+ ",  DrugIngredientSID,  OriginationDateTime,  OriginatingStaffSID,  ObservedHistorical,  Mechanism"
						+ ",  VerifiedFlag,  VerificationDateTime,  VerifyingStaffSID,  EnteredInErrorFlag ) " ,
				F.s, F.s, F.s, F.s, F.s, 
				F.s, F.s, F.s, F.s, F.s,
				F.s, F.d, F.s, F.s, F.s, 
				F.d, F.s, F.s, F.s);
		
		TableBatch.load(dir + "allergycomment.csv", cn, "Allergy.AllergyComment",  batchMode,
				"(AllergyCommentSID,  AllergySID,  AllergyIEN,  Sta3n,  PatientSID,"
						+ "  OriginationDateTime,  EnteringStaffSID,  AllergyComment, CommentEnteredDateTime)" ,
				F.s, F.s, F.s, F.s, F.s, 
				F.d, F.s, F.s, F.d);
		
		TableBatch.load(dir + "appointment.csv", cn, "Appt.Appointment",  batchMode,
				" (AppointmentSID,  Sta3n,  PatientSID,  AppointmentDateTime,  AppointmentMadeDate," + 
					"AppointmentTypeSID,  AppointmentStatus,  VisitSID,  LocationSID,  PurposeOfVisit," + 
					"  SchedulingRequestType,  FollowUpVisitFlag,  LengthOfAppointment,  ConsultSID,  CheckInDateTime"
						+ ",  CheckOutDateTime) " ,
				F.s, F.s, F.s, F.d, F.d, 
				F.s, F.s, F.s, F.s, F.s,
				F.s, F.d, F.s, F.s, F.d, 
				F.d);
		
		TableBatch.load(dir + "consult.csv", cn, "Con.Consult",  batchMode,
				" ( ConsultSID,  ToRequestServiceSID ) " ,
				F.s, F.s );
				
		TableBatch.load(dir + "cprsorder.csv", cn, "CPRSOrder.CPRSOrder",  batchMode,
				" ( CPRSOrderSID,  Sta3n,  PatientSID,  OrderStaffSID,  EnteredByStaffSID,"
				+ "  EnteredDateTime,  OrderStatusSID,  VistaPackageSID,  OrderStartDateTime,  OrderStopDateTime"
				+ ",  PackageReference ) ",
				F.s, F.s, F.s, F.s, F.s, 
				F.d, F.s, F.s, F.d, F.d,
				F.s);	
				
		TableBatch.load(dir + "immunization.csv", cn, "Immun.Immunization",  batchMode,
				"(ImmunizationSID,  ImmunizationIEN,  Sta3n,  PatientSID,  ImmunizationNameSID,"
						+ "  Series,  Reaction,  VisitDateTime,  ImmunizationDateTime,  OrderingStaffSID,"
						+ "  ImmunizingStaffSID,  VisitSID,  ImmunizationComments,  ImmunizationRemarks )" ,
				F.s, F.s, F.s, F.s, F.s, 
				F.s, F.s, F.d, F.d, F.s,
				F.s, F.s, F.s, F.s );
				
		TableBatch.load(dir + "inpatient.csv", cn, "Inpat.Inpatient",  batchMode,
				" (InpatientSID,PatientSID,AdmitDateTime) " ,
				F.s, F.s, F.d );
				
		TableBatch.load(dir + "nonvamed.csv", cn, "NonVAMed.NonVAMed",  batchMode,
				" ( NonVAMedSID,  PatientSID,  NonVAMedIEN,  Sta3n,  LocalDrugSID, "
				+ "Dosage,  MedicationRoute, Schedule,  NonVAMedStatus,  CPRSOrderSID, "
				+ "  StartDateTime,  DocumentedDateTime,  NonVAMedComments ) ",
				F.s, F.s, F.s, F.s, F.s, 
				F.s, F.s, F.s, F.s, F.s,
				F.d, F.d, F.s );
				
		TableBatch.load(dir + "problemlist.csv", cn, "Outpat.ProblemList",  batchMode,
				" (ProblemListSID,  Sta3n,  ICD9SID,  ICD10SID,  PatientSID,"
				+ "  ProviderNarrativeSID,  EnteredDateTime,  OnsetDateTime,  ProblemListCondition,  RecordingProviderSID,"
				+ "  ResolvedDateTime,  SNOMEDCTConceptCode)",
				F.s, F.s, F.s, F.s, F.s, 
				F.s, F.d, F.d, F.s, F.s,
				F.s, F.s  );
				
		TableBatch.load(dir + "vdiagnosis.csv", cn, "Outpat.VDiagnosis",  batchMode,
				" (VDiagnosisSID,  Sta3n,  ICD9SID,  ICD10SID,  PatientSID"
				+ ",VisitSID,  VisitDateTime,  VDiagnosisDateTime,  ProviderNarrativeSID,  ProblemListSID,"
						+ "OrderingProviderSID,  EncounterProviderSID )",
				F.s, F.s, F.s, F.s, F.s, 
				F.s, F.d, F.d, F.s, F.s,
				F.s, F.s );	
				
		TableBatch.load(dir + "visit.csv", cn, "Outpat.Visit",  batchMode,
				" (VisitSID,  VisitDateTime  ,CreatedByStaffSID,  LocationSID,  PatientSID )",
				F.s, F.d, F.s, F.s, F.s );	

		TableBatch.load(dir + "patientethnicity.csv", cn, "Patsub.PatientEthnicity",  batchMode,
				"( PatientEthnicitySID,  PatientSID,  Ethnicity ) ",
				F.s, F.s, F.s );
				
		TableBatch.load(dir + "patientrace.csv", cn, "Patsub.PatientRace",  batchMode,
				" (  PatientRaceSID, PatientSID,  Race) ",
				F.s, F.s, F.s );
				
		TableBatch.load(dir + "rxoutpatient.csv", cn, "RxOut.RxOutpat",  batchMode,
				" (  RxOutpatSID,  Sta3n,  RxNumber,  IssueDate,  CancelDate,"
				+ "  FinishingDateTime,  PatientSID,  ProviderSID,  EnteredByStaffSID,  LocalDrugSID,"
				+ "  NationalDrugSID,  PharmacyOrderableItemSID,  MaxRefills,  RxStatus,  OrderedQuantity )",
				F.s, F.s, F.s, F.d, F.d, 
				F.d, F.s, F.s, F.s, F.s,
				F.s, F.s, F.s, F.s, F.s );
				
		TableBatch.load(dir + "spatient.csv", cn, "SPatient.SPatient", batchMode,
				"( PatientSID,PatientName,PatientLastName,PatientFirstName,"
				+ "PatientSSN,Age,BirthDateTime,DeceasedFlag,DeathDateTime,Gender"
						+ ",SelfIdentifiedGender,Religion,MaritalStatus,"
						+ "MaritalStatusSID,PatientEnteredDateTime)",
				F.s, F.s, F.s, F.s, F.s, F.s, F.d, F.s, F.d, F.s, F.s, F.s, F.s, F.s, F.d);
				
		TableBatch.load(dir + "spatientaddress.csv", cn, "SPatient.SPatientAddress",  batchMode,
				   " ( SPatientAddressSID, PatientSID,  AddressType,  NameOfContact,  RelationshipToPatient,"
						   + "  StreetAddress1,  StreetAddress2,  StreetAddress3,  City,  State,  "
						   + "  Zip,  PostalCode,  Country,  GISMatchScore,  GISStreetSide,"
						   + "  GISPatientAddressLongitude,  GISPatientAddressLatitude,  GISFIPSCode )" ,
				F.s, F.s, F.s, F.s, F.s, 
				F.s, F.s, F.s, F.s, F.s,
				F.s, F.s, F.s, F.s, F.s,
				F.s, F.s, F.s  );
				
		TableBatch.load(dir + "spatientphone.csv", cn, "SPatient.SPatientPhone",  batchMode,
			   	"( SPatientPhoneSID,  PatientSID,  PatientContactType,  NameOfContact,  RelationshipToPatient,"
			   	+ "  PhoneNumber,  WorkPhoneNumber,  EmailAddress ) "    ,
				F.s, F.s, F.s, F.s, F.s, 
				F.s, F.s, F.s  );
		
		TableBatch.load(dir + "sstaff.csv", cn, "SStaff.SStaff",  batchMode,
				" ( StaffSID , StaffName ) "  ,
				F.s, F.s  );		

	}
}
