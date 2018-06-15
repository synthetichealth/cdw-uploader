package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Function;

import java.util.function.Supplier;

public class Table {
	
	
	public static void load(String fullPath, Connection con, String tableName, String fieldList, Function<String,String> ...fList ) 
					throws Exception {

	String insertSql1  = "";
	int loaded = 0;
	try {
		ArrayList<ArrayList<String>> rowList = (ArrayList<ArrayList<String>>) CSVtoArrayList
				.CSVToArrayList(fullPath);
		Statement stmt = con.createStatement();
		// get rid of old data; can't have duplicate primary key failure
		String truncateTableSql = "delete from " + tableName + " ";	
		System.out.println(truncateTableSql);
		int resultCode = stmt.executeUpdate(truncateTableSql);
		System.out.println(" inserting " + (rowList.size() - 1) + " rows into " + tableName);
		
		
		// skip the header row
		for (int i = 1, j=0; i < rowList.size(); i++,j=0) { // skip first row
			ArrayList<String> row = rowList.get(i);
			StringBuffer insertSql2 = new StringBuffer("insert into "+ tableName + fieldList + " values (");
			int limit = fList.length;
			insertSql2.append( fList[0].apply(row.get(j++)) ); //apply the functions to each of the parameters in order
			for (int k=1; k < fList.length; k++) {
				insertSql2.append( Util.COMMA + fList[k].apply(row.get(j++)) );
			}
		   insertSql2.append( " )" );
		   String finalSql = insertSql2.toString();
			try {
				//System.out.println(finalSql);
				int resultCode2 = stmt.executeUpdate(finalSql);
				loaded++;
			}
			catch (Exception ex) {
				System.out.println(finalSql);
				ex.printStackTrace();
			}
			if (i%1000==0) {
				System.out.print((i/1000 ) + "k,");
				if (i%10000==0) System.out.println();
			}

		}
		con.commit();
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		System.out.println("  loaded " + loaded + " rows");
	}
}
	

}
