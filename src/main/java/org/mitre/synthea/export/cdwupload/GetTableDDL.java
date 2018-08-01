package org.mitre.synthea.export.cdwupload;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Properties;

public class GetTableDDL {
	
	// this dumps information about the current database tables and their fields to
	// a text file.   Running a "diff" on the text files from different days lets 
	// you see if anything has changed.
	
	public static void main( String args[]) {
		String propFilePath = Util.propFilePath;
		Properties dbProps = new Properties();
		Boolean AWS = false;
		Connection con = null;
		long starttime = System.currentTimeMillis();
		String baseDir = "";  
		String dbUrl = "";
		String outfilepath = "";
		Properties conProps = new Properties();
		try {
			dbProps.load(new FileInputStream(propFilePath));
			//dbProps.getProperty("dbUrl");
			AWS =Boolean.parseBoolean((String) dbProps.get("AWS"));
			baseDir = (String) dbProps.get("basedir");
			outfilepath = (String) dbProps.get("outfilepath");
			System.out.println("outfilepath=" + outfilepath);
			System.out.println(AWS);
			if (AWS) {
				dbUrl = (String) dbProps.get("awsdburl");
				conProps.put("user", dbProps.get("awsuser"));
				conProps.put("password", dbProps.get("awspassword"));
				conProps.put("database", dbProps.get("awsdatabase"));
				conProps.put("oitlighthousedatabase",dbProps.get("oitlighthousedatabase"));
			}
			else {
				dbUrl = (String) dbProps.get("mitredburl");
				conProps.put("user", dbProps.get("mitreuser"));
				conProps.put("password", dbProps.get("mitrepassword"));
				conProps.put("database", dbProps.get("mitredatabase"));
				conProps.put("oitlighthousedatabase",dbProps.get("oitlighthousedatabase"));

			}
			System.out.println(conProps);
			System.out.println(dbUrl);
			con = DriverManager.getConnection(dbUrl, conProps);

			DatabaseMetaData myDBMD = con.getMetaData();
			
			ResultSet rs = myDBMD.getTables(null, null, null, new String[] {"TABLE"});
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			System.out.println("columnCount=" + columnCount);
			
			for (int j=1; j <= columnCount; j++) {
				System.out.print( rsmd.getColumnName(j) + " ") ;
			}
			System.out.println();

			while (rs.next() ) {
				for (int i=1; i<= columnCount; i++) {	
					if (i>1) System.out.print(",");
					System.out.print(rs.getString(i));
				}
				System.out.println( );
			}
			
			String storedProcSql = "select definition from sys.objects o join sys.sql_modules m on m.object_id = o.object_id\n" + 
					"where o.type = 'V' AND is_ms_shipped=0 ";
			//storedProcSql = "select * from Sys.objects where type = 'p'";
			//storedProcSql = "select definition from sys.objects o join sys.sql_modules m on m.object_id = o.object_id where o.type = 'V' ";
			//runSql.runQuery(con, storedProcSql);
			
			String tableDDLQuery = "SELECT \n" + 
					"  OBJECT_NAME(major_id) TableName,(select name from sys.columns c where c.object_id=major_id and c.column_id=minor_id) ColumnName,*\n" + 
					"FROM \n" + 
					"  sys.extended_properties\n" + 
					"WHERE \n" + 
					"  name='MS_Description' AND minor_id>0 and class=1";
			tableDDLQuery = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS";
			BufferedWriter out = new BufferedWriter(new FileWriter(outfilepath +
					 System.currentTimeMillis() + "_fieldList.csv" ));
			RunSql.runQuery(con, tableDDLQuery, out, true);
			con.close();
			conProps.put("database", dbProps.get("oitlighthousedatabase"));
			con = DriverManager.getConnection(dbUrl, conProps);
			Thread.sleep(4000);
			RunSql.runQuery(con, tableDDLQuery, out, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long stoptime = System.currentTimeMillis();
		System.out.println("\n finished GetTableDDL in " + (double) (stoptime -starttime)/(60.000 * 1000.0) + "min");	
	}
}
