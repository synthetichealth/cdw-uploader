# cdw-uploader

This code is designed to upload synthetic data produced by Synthea into a subset of SQLServer database 
tables from the Veteran's Adminstration (VA) Corporate Data Warehouse (CDW).  The data is exported from
Synthea as comma separated values (CSV) files with column headers.   

The code uses a properties file (db.properties) to supply parameters for the database connection and
location of input files; this can be passed in as a command-line parameter. (see sample script runLoad.sh).

#### Installation
You need to download the Microsoft JDBC drivers for SQlServer; they are at
```
    https://docs.microsoft.com/en-us/sql/connect/jdbc/using-the-jdbc-driver?view=sql-server-2017
```

Building the project requires Java 1.8 or above.

```
git clone https://github.com/synthetichealth/cdw-uploader

cd cdw-uploader-master

./gradlew build check test
```

You need to add the database connection information to the file db.properties, such as username, password, database url.
Once configured, you can invoke the loader using the following command
```
java -cp "~:/Users/myname/Downloads/SQLServerDrivers/sqljdbc_6.4/enu/mssql-jdbc-6.4.0.jre8.jar" org.mitre.synthea.export.cdwupload.LoadMultipleRuns ~/db.properties
```

# License

Copyright 2018 The MITRE Corporation

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
