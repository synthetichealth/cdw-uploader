package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.util.function.Function;

public class DeleteDimTables {
	
	public static void delete(Connection cn) throws Exception{			
		TableDelete.delete( cn, " Dim.Reaction ");
		TableDelete.delete(cn," Dim.DosageForm ");
		TableDelete.delete(  cn," Dim.ImmunizationName " );
		TableDelete.delete(cn," Dim.LocalDrug " );
		TableDelete.delete(cn," Dim.Location ");
		TableDelete.delete( cn, " Dim.MaritalStatus " );
		TableDelete.delete(cn, " Dim.NationalDrug " );  
		TableDelete.delete( cn, " Dim.OrderStatus " );
		TableDelete.delete( cn, " Dim.ProviderNarrative " );
		TableDelete.delete(  cn, " Dim.Sta3n " );
		TableDelete.delete(  cn, " Dim.VistaPackage " );	
		TableDelete.delete( cn, " Dim.PharmacyOrderableItem " );
		cn.commit();
	}
}

