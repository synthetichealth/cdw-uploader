package org.mitre.synthea.export.cdwupload;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoadMultipleRuns {
	
	public static void load( String parentDir, Boolean AWS ) {
		
		File[] dirs = new File(parentDir).listFiles();
		for (int i=0; i < dirs.length; i++) {
			System.out.println(dirs[i]);
			if (dirs[i].isDirectory()) {
				System.out.println("***** loading " + dirs[i].toString());
				
				UploadNoDelete.load(dirs[i].toString() //+ File.separator + "cdw" 
			+ File.separator, AWS);
			}
		}
	}
	
	public static void loadOITLightouse( String parentDir, Boolean AWS ) {
		File[] dirs = new File(parentDir).listFiles();
		for (int i=0; i < dirs.length; i++) {
			System.out.println(dirs[i]);
			if (dirs[i].isDirectory()) {
				System.out.println("***** loading " + dirs[i].toString());
				UploadNoDeleteOITLighthouse.load(dirs[i].toString() //+ File.separator + "cdw" 
			+ File.separator, AWS);
			}
		}
	}

	public static void main( String args[]) {
		String propFilePath = Util.propFilePath;
		Properties dbProps = new Properties();
		Boolean AWS = false;
		Connection con = null;
		long starttime = System.currentTimeMillis();
		String baseDir = "";  		
		String dbUrl = "";
		Properties conProps = new Properties();
		try {
			dbProps.load(new FileInputStream(propFilePath));
			AWS =Boolean.parseBoolean((String) dbProps.get("AWS"));
			baseDir = (String) dbProps.get("basedir");
			System.out.println(AWS);
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
			System.out.println(conProps);
			System.out.println(dbUrl);
			con = DriverManager.getConnection(dbUrl, conProps);
			DeleteDimTables.delete(con);
			DeleteOtherTables.delete(con);
			LoadMultipleRuns.load(baseDir, AWS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		conProps.put("database", dbProps.getProperty("oitlighthousedatabase"));
		try {
			con = DriverManager.getConnection(dbUrl, conProps);
			DeleteOITLighthouseTables.delete(con);
			LoadMultipleRuns.loadOITLightouse(baseDir, AWS);			
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
		System.out.println("\n finished LoadMultipleRuns in " + (double) (stoptime -starttime)/(60.000 * 1000.0) + "min");
	}
}