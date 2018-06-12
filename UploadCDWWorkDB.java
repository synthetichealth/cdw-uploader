package org.mitre.synthea.export.cdwupload;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class UploadCDWWorkDB {

	public static void main(String args[]) {
		// https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
		Properties conProps = new Properties();
		
		Boolean AWS = false;
		String dbUrl = "";
		if (AWS) {
			dbUrl = "";
			conProps.put("user","");
			conProps.put("password", "");
			conProps.put("database","");
		}
		else {
			dbUrl = "";
			conProps.put("user", "");
			conProps.put("password", "");
			conProps.put("database", "");
		}
		Connection con = null;
		String baseDir = "/Users/ggryan/Downloads/synthea-cdw_exporter/output/cdw/";
		long starttime = System.currentTimeMillis();
		
		try {
			con = DriverManager.getConnection(dbUrl, conProps);
			con.setAutoCommit(false);
			Boolean batchMode = true;
			LoadDimTables.load(con, baseDir, true);
			LoadDim_PharmacyOrderableItem.load(con, baseDir, true );  // need to fix this
			LoadOtherTables.load(con, baseDir, true);
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
		
		// load tables in the OIT_Lightouse DB
		conProps.put("database", "");
		try {
			con = DriverManager.getConnection(dbUrl, conProps);
			LoadOITLighthouseTables.load(con, baseDir, true);
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
