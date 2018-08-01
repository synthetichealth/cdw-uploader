package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;

public class FixVuid {
	
	
	public static void fix(Connection con, Boolean AWS) throws Exception{
		
		//Note: This code is not idempotent.   Run Once after all the updates.  Running more that once is BAD!
		
		String sql = "";
		if (AWS) {   // code for AWS CDW
			sql = "UPDATE [CDWWork].[Dim].[NationalDrug]\n" + 
					"SET [VUID] = A.[Code]\n" + 
					"FROM [OIT_Lighthouse].[app].[RXN_CONSO] AS A\n" + 
					"     JOIN [OIT_Lighthouse].[app].[RXN_CONSO] AS D\n" + 
					"     ON A.[rxcui] = D.[rxcui]\n" + 
					"WHERE D.[rxcui] = [CDWWork].[Dim].[NationalDrug].[VUID]\n" + 
					"  AND A.[sab] = 'VANDF'\n" + 
					"  AND D.[tty] in ('SCD','SBD','SCDG','SBDG','BPCK','GPCK', 'SCDF', 'SBDF');";
		}
		else {   // code for local MITRE database instance
			sql = "UPDATE [CDWwSchema].[Dim].[NationalDrug]\n" + 
					"SET [VUID] = A.[Code]\n" + 
					"FROM [OIT_Lighthouse].[app].[RXN_CONSO] AS A\n" + 
					"     JOIN [OIT_Lighthouse].[app].[RXN_CONSO] AS D\n" + 
					"     ON A.[rxcui] = D.[rxcui]\n" + 
					"WHERE D.[rxcui] = [CDWwSchema].[Dim].[NationalDrug].[VUID]\n" + 
					"  AND A.[sab] = 'VANDF'\n" + 
					"  AND D.[tty] in ('SCD','SBD','SCDG','SBDG','BPCK','GPCK', 'SCDF', 'SBDF');";
		}
		RunSql.runUpdate(con, sql);
		System.out.println("executed " + sql);
	}
	

}
