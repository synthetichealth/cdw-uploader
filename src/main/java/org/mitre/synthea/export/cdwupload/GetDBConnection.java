package org.mitre.synthea.export.cdwupload;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

public class GetDBConnection {
	
	
	public static Connection getConCDWWorkDB() {
		return(getConDefaultCDWWork(""));
	}
	public static Connection getConOITLighthouseDB() {
		return(getConDefaultCDWWork("oitlighthousedatabase"));
	}
	
	public static Connection getConDefaultCDWWork(String databaseName) {
		//by default, we use the CDWWork database, otherwise use OITLighthouse
		// when you pass "oitlighthousedatabase" as databaseName
		String propFilePath = Util.propFilePath;
		Properties dbProps = new Properties();
		Boolean AWS = false;    //overridden by prop file value
		Connection con = null;
		String dbUrl = "";
		Properties conProps = new Properties();
		try {
			dbProps.load(new FileInputStream(propFilePath));
			AWS =Boolean.parseBoolean((String) dbProps.get("AWS"));
			System.out.println(AWS);
			if (AWS) {
				dbUrl = (String) dbProps.get("awsdburl");
				conProps.put("user", dbProps.get("awsuser"));
				conProps.put("password", dbProps.get("awspassword"));
				conProps.put("database", dbProps.get("awsdatabase"));
				conProps.put("oitlighthousedatabase",dbProps.get("oitlighthousedatabase"));
				//by default, we use the CDWWork database, otherwise use OITLighthouse if you ask
				if (databaseName.toLowerCase().equals("oitlighthousedatabase")) {
					conProps.put("database",dbProps.get("oitlighthousedatabase"));
				}
			}
			else {
				dbUrl = (String) dbProps.get("mitredburl");
				conProps.put("user", dbProps.get("mitreuser"));
				conProps.put("password", dbProps.get("mitrepassword"));
				conProps.put("database", dbProps.get("mitredatabase"));
				conProps.put("oitlighthousedatabase",dbProps.get("oitlighthousedatabase"));
				//by default, we use the CDWWork database, otherwise use OITLighthouse if you ask
				if (databaseName.toLowerCase().equals("oitlighthousedatabase")) {
					conProps.put("database",dbProps.get("oitlighthousedatabase"));
				}
			}
			System.out.println(dbUrl);
			con = DriverManager.getConnection(dbUrl, conProps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(con);	
	}
}
