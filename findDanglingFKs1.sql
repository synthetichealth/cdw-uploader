select count(*) from Allergy.AllergicReaction where ReactionSID not in ( select  ReactionSID from  Dim.Reaction ) 
select count(*) from Allergy.AllergicReaction where Sta3n not in ( select Sta3n from  Dim.Sta3n )
select count(*) from Allergy.Allergy where DrugClassSID not in ( select  DrugClassSID from  Dim.DrugClass )
select count(*) from Allergy.Allergy where DrugClassSID is not NULL and DrugClassSID not in ( select  DrugClassSID from  Dim.DrugClass )
select count(*) from Allergy.Allergy where DrugIngredientSID not in ( select  DrugIngredientSID from  Dim.DrugIngredient )
select count(*) from Allergy.Allergy where DrugIngredientSID is not NULL and DrugIngredientSID not in ( select  DrugIngredientSID from  Dim.DrugIngredient )
select count(*) from Allergy.Allergy where DrugNameWithoutDoseSID not in ( select  DrugNameWithoutDoseSID from  Dim.DrugNameWithoutDose )
select count(*) from Allergy.Allergy where DrugNameWithoutDoseSID is not NULL and DrugNameWithoutDoseSID not in ( select  DrugNameWithoutDoseSID from  Dim.DrugNameWithoutDose )
select count(*) from Allergy.Allergy where LocalDrugSID not in ( select  LocalDrugSID from  Dim.LocalDrug )
select count(*) from Allergy.Allergy where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Allergy.Allergy where ReactantSID not in ( select  ReactantSID from  Dim.Reactant )
select count(*) from Allergy.Allergy where ReactantSID is not NULL and ReactantSID not in ( select  ReactantSID from  Dim.Reactant )
select count(*) from Allergy.Allergy where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Allergy.AllergyComment where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Allergy.AllergyComment where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Micro.AntibioticSensitivity where MicrobiologySID not in ( select  MicrobiologySID from  Micro.Microbiology )
select count(*) from Micro.AntibioticSensitivity where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Micro.AntibioticSensitivity where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Micro.AntibioticSensitivityComment where MicrobiologySID not in ( select  MicrobiologySID from  Micro.Microbiology )
select count(*) from Micro.AntibioticSensitivityComment where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Micro.AntibioticSensitivityComment where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Appt.Appointment where AppointmentTypeSID not in ( select  AppointmentTypeSID from  Dim.AppointmentType )
select count(*) from Appt.Appointment where AppointmentTypeSID is not NULL and AppointmentTypeSID not in ( select  AppointmentTypeSID from  Dim.AppointmentType )
select count(*) from Appt.Appointment where ConsultSID not in ( select  ConsultSID from  Con.Consult )
select count(*) from Appt.Appointment where LocationSID not in ( select  LocationSID from  Dim.Location )
select count(*) from Appt.Appointment where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Appt.Appointment where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Appt.Appointment where VisitSID not in ( select  VisitSID from  Outpat.Visit )
select count(*) from Micro.BacteriologyReports where MicrobiologySID not in ( select  MicrobiologySID from  Micro.Microbiology )
select count(*) from Micro.BacteriologyReports where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Micro.BacteriologyReports where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from BCMA.BCMADispensedDrug where BCMAMedicationLogSID not in ( select  BCMAMedicationLogSID from  BCMA.BCMAMedicationLog )
select count(*) from BCMA.BCMADispensedDrug where LocalDrugSID not in ( select  LocalDrugSID from  Dim.LocalDrug )
select count(*) from BCMA.BCMADispensedDrug where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from CPRSOrder.CPRSOrder where OrderStatusSID not in ( select  OrderStatusSID from  Dim.OrderStatus )
select count(*) from CPRSOrder.CPRSOrder where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from CPRSOrder.CPRSOrder where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from CPRSOrder.CPRSOrder where VistaPackageSID not in ( select  VistaPackageSID from  Dim.VistaPackage )
select count(*) from Immun.Immunization where ImmunizationNameSID not in ( select  ImmunizationNameSID from  Dim.ImmunizationName )
select count(*) from Immun.Immunization where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Immun.Immunization where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Immun.Immunization where VisitSID not in ( select  VisitSID from  Outpat.Visit )
select count(*) from Inpat.Inpatient where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Dim.Institution where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Chem.LabChem where LabChemTestSID not in ( select  LabChemTestSID from  Dim.LabChemTest )
select count(*) from Chem.LabChem where LabPanelSID not in ( select  LabPanelSID from  Chem.LabPanel )
select count(*) from Chem.LabChem where LOINCSID not in ( select  LOINCSID from  Dim.LOINC )
select count(*) from Chem.LabChem where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Dim.LabChemTest where CollectionSampleSID not in ( select  CollectionSampleSID from  Dim.CollectionSample )
select count(*) from Chem.LabPanel where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Dim.LocalDrug where NationalDrugSID not in ( select  NationalDrugSID from  Dim.NationalDrug )
select count(*) from Dim.LocalDrug where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Micro.Microbiology where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Micro.Microbiology where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Micro.Microbiology where TopographySID not in ( select  TopographySID from  Dim.Topography )
select count(*) from Micro.MicroOrderedTest where CPRSOrderSID not in ( select  CPRSOrderSID from  CPRSOrder.CPRSOrder )
select count(*) from Micro.MicroOrderedTest where MicrobiologySID not in ( select  MicrobiologySID from  Micro.Microbiology )
select count(*) from Micro.MicroOrderedTest where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Micro.MycobacteriologyReports where MicrobiologySID not in ( select  MicrobiologySID from  Micro.Microbiology )
select count(*) from Micro.MycobacteriologyReports where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Micro.MycobacteriologyReports where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Micro.Mycology where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Micro.Mycology where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Micro.MycologyReports where MicrobiologySID not in ( select  MicrobiologySID from  Micro.Microbiology )
select count(*) from Micro.MycologyReports where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Dim.NationalDrug where DosageFormSID not in ( select  DosageFormSID from  Dim.DosageForm )
select count(*) from NonVAMed.NonVAMed where CPRSOrderSID not in ( select  CPRSOrderSID from  CPRSOrder.CPRSOrder )
select count(*) from NonVAMed.NonVAMed where LocalDrugSID not in ( select  LocalDrugSID from  Dim.LocalDrug )
select count(*) from NonVAMed.NonVAMed where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from NonVAMed.NonVAMed where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from CPRSOrder.OrderedItem where CPRSOrderSID not in ( select  CPRSOrderSID from  CPRSOrder.CPRSOrder )
select count(*) from CPRSOrder.OrderedItem where OrderableItemSID not in ( select  OrderableItemSID from  Dim.OrderableItem )
select count(*) from Micro.Parasitology where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Micro.Parasitology where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Micro.ParasitologyReports where MicrobiologySID not in ( select  MicrobiologySID from  Micro.Microbiology )
select count(*) from Micro.ParasitologyReports where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Patsub.PatientEthnicity where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Chem.PatientLabChem where LabPanelSID not in ( select  LabPanelSID from  Chem.LabPanel )
select count(*) from Chem.PatientLabChem where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Chem.PatientLabChem where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Chem.PatientLabChem where TopographySID not in ( select  TopographySID from  Dim.Topography )
select count(*) from Patsub.PatientRace where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Outpat.ProblemList where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Outpat.ProblemList where ProviderNarrativeSID not in ( select  ProviderNarrativeSID from  Dim.ProviderNarrative )
select count(*) from Outpat.ProblemList where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from RxOut.RxOutpat where LocalDrugSID not in ( select  LocalDrugSID from  Dim.LocalDrug )
select count(*) from RxOut.RxOutpat where NationalDrugSID not in ( select  NationalDrugSID from  Dim.NationalDrug )
select count(*) from RxOut.RxOutpat where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from RxOut.RxOutpat where PharmacyOrderableItemSID not in ( select  PharmacyOrderableItemSID from  Dim.PharmacyOrderableItem )
select count(*) from RxOut.RxOutpat where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from SPatient.SPatient where MaritalStatusSID not in ( select  MaritalStatusSID from  Dim.MaritalStatus )
select count(*) from SPatient.SPatientAddress where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from SPatient.SPatientPhone where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Surg.SurgeryPRE where CPRSOrderSID not in ( select  CPRSOrderSID from  CPRSOrder.CPRSOrder )
select count(*) from Surg.SurgeryPRE where VisitSID not in ( select  VisitSID from  Outpat.Visit )
select count(*) from Surg.SurgeryPrincipalAssociatedProcedure where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Surg.SurgeryPrincipalAssociatedProcedure where SurgeryProcedureDiagnosisCodeSID not in ( select  SurgeryProcedureDiagnosisCodeSID from  Surg.SurgeryProcedureDiagnosisCode )
select count(*) from Surg.SurgeryPrincipalCPTModifier where CPTModifierSID not in ( select  CPTModifierSID from  Dim.CPTModifier )
select count(*) from Surg.SurgeryPrincipalCPTModifier where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Surg.SurgeryPrincipalCPTModifier where SurgeryProcedureDiagnosisCodeSID not in ( select  SurgeryProcedureDiagnosisCodeSID from  Surg.SurgeryProcedureDiagnosisCode )
select count(*) from Surg.SurgeryProcedureDiagnosisCode where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Surg.SurgeryProcedureDiagnosisCode where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Surg.SurgeryProcedureDiagnosisCode where SurgerySID not in ( select  SurgerySID from  Surg.SurgeryPRE )
select count(*) from Outpat.VDiagnosis where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Outpat.VDiagnosis where ProblemListSID not in ( select  ProblemListSID from  Outpat.ProblemList )
select count(*) from Outpat.VDiagnosis where ProviderNarrativeSID not in ( select  ProviderNarrativeSID from  Dim.ProviderNarrative )
select count(*) from Outpat.VDiagnosis where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Outpat.VDiagnosis where VisitSID not in ( select  VisitSID from  Outpat.Visit )
select count(*) from Micro.Virology where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Micro.Virology where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Micro.VirologyReports where MicrobiologySID not in ( select  MicrobiologySID from  Micro.Microbiology )
select count(*) from Micro.VirologyReports where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Outpat.Visit where LocationSID not in ( select  LocationSID from  Dim.Location )
select count(*) from Outpat.Visit where LocationSID is not NULL and LocationSID not in ( select  LocationSID from  Dim.Location )
select count(*) from Outpat.Visit where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Vital.VitalSign where LocationSID not in ( select  LocationSID from  Dim.Location )
select count(*) from Vital.VitalSign where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Vital.VitalSign where Sta3n not in ( select  Sta3n from  Dim.Sta3n )
select count(*) from Vital.VitalSign where StaffSID not in ( select  StaffSID from  SStaff.SStaff )
select count(*) from Vital.VitalSign where VitalTypeSID not in ( select  VitalTypeSID from  Dim.VitalType )
select count(*) from Outpat.VProcedure where CPRSOrderSID not in ( select  CPRSOrderSID from  CPRSOrder.CPRSOrder )
select count(*) from Outpat.VProcedure where PatientSID not in ( select  PatientSID from  SPatient.SPatient )
select count(*) from Outpat.VProcedure where VisitSID not in ( select  VisitSID from  Outpat.Visit )