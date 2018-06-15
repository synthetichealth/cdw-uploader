package org.mitre.synthea.export.cdwupload;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVtoArrayList {
	//ArrayList<String> row = new ArrayList<String>();
	
	// make each row an ArrayList of String; return all the rows as an ArrayList of rows
	public static ArrayList<ArrayList<String>> CSVToArrayList(String fullPath) {
		ArrayList<String> row = new ArrayList<String>();
		ArrayList<ArrayList<String>> myList = new ArrayList<ArrayList<String>>();
		try ( BufferedReader br = new BufferedReader( new FileReader(fullPath))   ) {
			String line;
			while ( (line = br.readLine()) != null ) {
				if (line.endsWith(",")) {
					line = line + "''";     //boundary condition of line with nothing after comma
				}			
				String fields[] = line.split(",",-1);  // pass in -1 to get all the trailing empty values
				row =  new ArrayList<String>( Arrays.asList( line.split(",") ));
				myList.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return( myList);
	}
}

