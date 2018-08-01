package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;

public class DeleteOITLighthouseTables {
	
	public static void delete(Connection cn) throws Exception{			
//		TableDelete.delete(cn, "App.Lookup_Patient", " where patientSID > 1 ");
		TableDelete.delete(cn, "App.Lookup_Patient", "");
		cn.commit();
	}

}
