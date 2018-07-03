package org.mitre.synthea.export.cdwupload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class UploadCDW {
	
	public static void main(String args[]) {
		String propFilePath = Util.propFilePath;
		Properties dbProps = new Properties();
		Connection con = null;
		String baseDir = "";
		Boolean AWS = false;
		String dbUrl = "";
		Properties conProps = new Properties();
		long starttime = System.currentTimeMillis();
		
		try {
			dbProps.load(new FileInputStream(propFilePath));
			baseDir = (String) dbProps.get("basedir");
			AWS =Boolean.parseBoolean((String) dbProps.get("AWS"));
			dbProps.getProperty("dbUrl");
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
			DeleteDimTables.delete(con);
			InsertDimTables.load(con, baseDir, true);
			System.out.println(dbUrl);			
			DeleteOtherTables.delete(con);
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
		// load tables in the OIT_Lightouse DB
		conProps.put("database", dbProps.getProperty("oitlighthousedatabase"));
		try {
			con = DriverManager.getConnection(dbUrl, conProps);
			LoadOITLighthouseTables.load(con, baseDir, true);
			FixVuid.fix(con,AWS );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
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


