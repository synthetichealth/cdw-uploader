package org.mitre.synthea.export.cdwupload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class LoadMultipleRuns {
	
	public static void load( String parentDir, Boolean AWS, HashMap<String,Integer> rowsLoadedPerTable  ) {
		
		File[] dirs = new File(parentDir).listFiles();
		for (int i=0; i < dirs.length; i++) {
			System.out.println(dirs[i]);
			if (dirs[i].isDirectory()) {
				System.out.println("***** loading " + dirs[i].toString());
				UploadNoDelete.load(dirs[i].toString() + File.separator, AWS, rowsLoadedPerTable);
			}
		}
	}
	
	public static void loadParallel( String parentDir, Boolean AWS, HashMap<String,Integer> rowsLoadedPerTable  ) throws Exception{
		
		File[] dirs = new File(parentDir).listFiles();
		ExecutorService service = Executors.newCachedThreadPool();
		
		ArrayList<Future<String>> myFutures= new ArrayList<Future<String>>();
		
		for (int i=0; i < dirs.length; i++) {
			System.out.println(dirs[i]);
			if (dirs[i].isDirectory()) {
				String myDir = dirs[i].toString();
				System.out.println("***** loading " + myDir);
				
				// see Ken Kousen's Modern Java Recipes example 9-15
				
				Future<String> future = service.submit( new Callable<String>() {
					@Override
					public String call() throws Exception {
						Thread.sleep(1);
						UploadNoDelete.load(myDir + File.separator, AWS, rowsLoadedPerTable);
						return("done " + myDir);
					}
				});
				myFutures.add(future);
				Thread.sleep(10000);
				//UploadNoDelete.load(dirs[i].toString() + File.separator, AWS, rowsLoadedPerTable);
			}	
		}
		Boolean finished = false;
		while (!finished) {
			Boolean tmp = false;
			for (int i=myFutures.size() -1; i >=0; i--) {
				if (myFutures.get(i).isDone()) {
					myFutures.remove(i);
				}
			}
			Thread.sleep(myFutures.size()  * 10000);
			if (myFutures.isEmpty() ) {
				finished = true;
			}
			else {
				System.out.println("\n $$$$$$$$$$$  " + myFutures.size() + " remaining futures");
			}
		}
		System.out.println("********* All futures finished");
	}
	
	public static void loadOITLightouse( String parentDir, Boolean AWS, HashMap<String,Integer> rowsLoadedPerTable  ) {
		File[] dirs = new File(parentDir).listFiles();
		for (int i=0; i < dirs.length; i++) {
			System.out.println(dirs[i]);
			if (dirs[i].isDirectory()) {
				System.out.println("***** loading " + dirs[i].toString());
				UploadNoDeleteOITLighthouse.load(dirs[i].toString() + File.separator, AWS, rowsLoadedPerTable);
			}
		}
	}

	public static void main( String args[]) {
		
		if (args.length > 0) {
			Util.propFilePath = args[0];
		}
		String propFilePath = Util.propFilePath;

		Properties dbProps = new Properties();
		Boolean AWS = false;
		Connection con = null;
		long starttime = System.currentTimeMillis();
		String baseDir = ""; 
		String dbUrl = "";
		Properties conProps = new Properties();
		HashMap<String,Integer> rowsLoadedPerTable = new HashMap<String,Integer>();
		HashMap<String,Integer> rowsToLoadByTable = new HashMap<String,Integer>();
		String outfilepath = "";
		
		try {
			outfilepath = Props.getInstance().getOutfilepath();
			AWS = Props.getInstance().onAWS();
			baseDir = (String) dbProps.get("basedir");
			baseDir = Props.getInstance().getBasedir();
			System.out.println(AWS);
			con = GetDBConnection.getConCDWWorkDB();
			DeleteDimTables.delete(con);
			DeleteOtherTables.delete(con);
			
			//CountRowsToLoad.countLinesInFiles( baseDir, rowsToLoadByTable, outfilepath, false) ;
			LoadMultipleRuns.loadParallel(baseDir, AWS, rowsLoadedPerTable);
			//LoadMultipleRuns.load(baseDir, AWS, rowsLoadedPerTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			con = GetDBConnection.getConOITLighthouseDB();
			DeleteOITLighthouseTables.delete(con);
			LoadMultipleRuns.loadOITLightouse(baseDir, AWS, rowsLoadedPerTable);			
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