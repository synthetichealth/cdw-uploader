package org.mitre.synthea.export.cdwupload;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
public class UploadNoDelete {
	
public static void load(String baseDir, Boolean AWS) {
		String propFilePath = "/Users/garygryan/db.properties";
		Properties dbProps = new Properties();
		Connection con = null;
		long starttime = System.currentTimeMillis();
		String dbUrl = "";
		Properties conProps = new Properties();
		try {	
			dbProps.load(new FileInputStream(propFilePath));
			if (AWS) {
				dbUrl = (String) dbProps.get("awsdburl");
				conProps.put("user", dbProps.get("awsuser"));
				conProps.put("password", dbProps.get("awspassword"));
				conProps.put("database", dbProps.get("awsdatabase"));
			}
			else {
				dbUrl = (String) dbProps.get("mitredburl");
				conProps.put("user", dbProps.get("mitreuser"));
				conProps.put("password", dbProps.get("mitrepassword"));
				conProps.put("database", dbProps.get("mitredatabase"));
			}
			con = DriverManager.getConnection(dbUrl, conProps);
			InsertDimTables.load(con, baseDir, true);
			InsertOtherTables.load(con, baseDir, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (con.isValid(1000)) {
					con.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		long stoptime = System.currentTimeMillis();
		System.out.println(" finished in " + (double) (stoptime -starttime)/(60.000 * 1000.0) + "min");
	}
}

