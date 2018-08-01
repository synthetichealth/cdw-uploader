package org.mitre.synthea.export.cdwupload;

import java.io.FileInputStream;
import java.util.Properties;

public class Props {
	
	private static Props singleton = null;
	protected Properties dbProps = null;
	
	static {
		singleton = getInstance();
	}
	
	protected Props() {
		String propFilePath = Util.propFilePath;
		dbProps = new Properties();
		try {
			dbProps.load(new FileInputStream(propFilePath));
			Boolean AWS =Boolean.parseBoolean((String) dbProps.get("AWS"));
			if (AWS) {
				dbProps.put("dbUrl",  (String) dbProps.get("awsdburl") );
				dbProps.put("user", dbProps.get("awsuser"));
				dbProps.put("password", dbProps.get("awspassword"));
				dbProps.put("database", dbProps.get("awsdatabase"));
			}
			else {
				dbProps.put("dbUrl", (String) dbProps.get("mitredburl"));
				dbProps.put("user", dbProps.get("mitreuser"));
				dbProps.put("password", dbProps.get("mitrepassword"));
				dbProps.put("database", dbProps.get("mitredatabase"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Props getInstance() {
		if (singleton == null) {
			singleton = new Props();
			return(singleton);
		}
		else {
			return(singleton);
		}
	}
	
	public Boolean onAWS () {
		String aws = (String) dbProps.get("AWS");
		return(Boolean.parseBoolean(aws));
	}
	public String getDatabase () {
		String val = (String) dbProps.get("database");
		return(val);
	}
	public String getBasedir () {
		String val = (String) dbProps.get("basedir");
		return(val);
	}
	public String getOutfilepath () {
		String val = (String) dbProps.get("outfilepath");
		return(val);
	}
	public String getInfilepath () {
		String val = (String) dbProps.get("infilepath");
		return(val);
	}
	public String getCDWWorkDDL() {
		String val = (String) dbProps.get("cdwworkddl");
		return(val);
	}
	
	public String getQueriesToWriteFilename() {
		String val = (String) dbProps.get("queriestowritefilename");
		return(val);
	}

}


