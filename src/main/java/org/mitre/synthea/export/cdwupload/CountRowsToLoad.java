package org.mitre.synthea.export.cdwupload;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class CountRowsToLoad {
	
	
	
	public static int countRowsInFile(String fullPath) {
		// count the number of rows in the file (including header)
		int count = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {
			String line;
			while ((line = br.readLine()) != null) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(count);
	}
	
	public static void countLinesInFiles( String parentDir, HashMap<String,Integer> rowsToLoadPerTable,
			String outfilepath, Boolean writeToFile) {
		File[] dirs = new File(parentDir).listFiles();
		
		for (int i=0; i < dirs.length; i++) {
			System.out.println(dirs[i]);
			
			if (dirs[i].isDirectory()) {
				System.out.println("***** loading " + dirs[i].toString());
				File[] csvFiles = new File(dirs[i].toString() ).listFiles( (File dir, String name ) -> name.endsWith(".csv"));
				
				for (int j=0; j < csvFiles.length; j++) {
					String fullPath = csvFiles[j].toString();
					int count = countRowsInFile(fullPath);
					String[] fileNameParts = fullPath.split(File.separator);
					String filename = fileNameParts[fileNameParts.length -1];
					String tableName = Const.csvToTable.get(filename);
					if (tableName == null) {
						tableName = fullPath;
						System.out.println("**** missing map entry " + filename);
					}
					// always subtract one for header line
					Util.updateLoadedRecordCount(rowsToLoadPerTable ,  tableName, count - 1);
				}
				
				if(writeToFile) {
					List<String> myKeys = new ArrayList<String>(rowsToLoadPerTable.keySet());
					Collections.sort(myKeys);
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(outfilepath +
								System.currentTimeMillis() + "_toLoadSummary.csv" ));
				
						for (int j=0; j < myKeys.size(); j++) {
							System.out.println( myKeys.get(j) + ", " + rowsToLoadPerTable.get(myKeys.get(j)));
							out.write( myKeys.get(j) + ", " + rowsToLoadPerTable.get(myKeys.get(j)) + "\n");
						}
						out.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
	}
	
	public static void main(String[] args) {
		
		String parentDir = Props.getInstance().getBasedir();
		String outfilepath = Props.getInstance().getOutfilepath();
		HashMap<String,Integer> rowsToLoadByTable = new HashMap<String,Integer>();
		
		try {	
			countLinesInFiles(parentDir, rowsToLoadByTable, outfilepath, false);
			System.out.println("in CountRowToLoad outfilepath="+ outfilepath);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	


}

