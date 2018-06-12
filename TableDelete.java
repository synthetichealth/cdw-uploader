package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.sql.Statement;

public class TableDelete {

	public static void delete(Connection con, String tableName) throws Exception {
		Statement stmt = null;
		
		try {
			con.setAutoCommit(true);
			stmt = con.createStatement();
			// get rid of old data; can't have duplicate primary key failure
			String truncateTableSql = "delete from " + tableName + " ";
			System.out.println(truncateTableSql);
			int resultCode = stmt.executeUpdate(truncateTableSql);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
