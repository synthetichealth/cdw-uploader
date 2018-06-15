# cdw-uploader

This code is designed to upload synthetic data produced by Synthea into a subset of SQLServer database 
tables from the Veteran's Adminstration (VA) Corporate Data Warehouse (CDW).  The data is exported from
Synthea as comma separated values (CSV) files with column headers.   

The code uses a properties file (db.properties) to supply parameters for the database connection and
location of input files.  

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
