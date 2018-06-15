package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class runSql {
	
	public static void runUpdate(Connection con, String sql) throws Exception {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			int resultCode = stmt.executeUpdate(sql);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw (e);
		}
		finally {
			try {
				stmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

