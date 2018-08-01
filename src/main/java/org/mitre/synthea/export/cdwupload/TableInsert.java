package org.mitre.synthea.export.cdwupload;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class TableInsert {

	public static int load(String fullPath, Connection con, String tableName, Boolean batchMode, String fieldList,
			HashMap<String,Integer> rowsLoadedPerTable,
			Function<String, String>... fList) throws Exception {

		String insertSql1 = "";
		int loaded = 0;
		Statement stmt = null;
		try {
			ArrayList<ArrayList<String>> rowList = (ArrayList<ArrayList<String>>) CSVtoArrayList
					.CSVToArrayList(fullPath);
			stmt = con.createStatement();
			//System.out.print("loading " + tableName + " ");

			// skip the header row
			// i indexes the row; j indexes the column for the current row
			for (int i = 1, j = 0; i < rowList.size(); i++, j = 0) { // skip first row
				ArrayList<String> row = rowList.get(i);
				StringBuffer insertSql2 = new StringBuffer("insert into " + tableName + fieldList + " values (");
				int limit = fList.length;
				insertSql2.append(fList[0].apply(row.get(j++))); // apply the functions to each of the parameters in
																	// order
				for (int k = 1; k < fList.length; k++) {
					insertSql2.append(Util.COMMA + fList[k].apply(row.get(j++)));
				}
				insertSql2.append(" )");
				String finalSql = insertSql2.toString();
				loaded++;
				try {
					if (batchMode) {
						stmt.addBatch(finalSql);
					} else {
						int resultCode2 = stmt.executeUpdate(finalSql);
					}
					//loaded++;
				} catch (Exception ex) {
					System.out.println(finalSql);
					ex.printStackTrace();
				}
				int printEveryN = 1000;
				if (i % printEveryN == 0) {
					//System.out.print( ((i / printEveryN)*10) + "k,");
					if (i % (printEveryN * 20)  == 0)
						System.out.println();

					try {
						if (batchMode) {
							stmt.executeBatch();
						}
						con.commit();
					} catch (Exception exp) {
						exp.printStackTrace();
					}
				}
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("loaded " + loaded + " rows into " + tableName);
			try {
				if (batchMode) {
					stmt.executeBatch();
				}
				con.commit();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		Util.updateLoadedRecordCount(rowsLoadedPerTable ,  tableName, loaded);
		
		return(loaded);
	}
}
