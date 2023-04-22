# TestProjectIbibo
This Project is setup with the help of Maven.
All the requiered dependencies are stored in pom.xml file
in this project I have used Hybrid framework under Page object model Design Pattern.
under src/testjava there is pompages package which have separate classes for the each of the pages of the webpage
those classes contains object and themethods for the respective webpages.
all the locators have been stored in the Config.properties file which is available in src/test/resources folder.
all the testcases scripts are stored in "testscripts" package under src/test/java folder.
in this project the data has been extracted from the excelsheets with the help of Apache POI library.
the excelsheets are stored in "ExcelSheets" folder under src/test/resources folder.
all the methods requier to exract data from the excelsheets are added in the ExcelReader class in excelAndreports package.
all the basic utility methods are stored in FuncLibrary class in "frameworkutility" package.
the properties file setup is created in FuncUtilLibrary class in "frameworkutility" package.
the initialization or set up of the tests will start with "BaseTest" class in the "testscripts" package.
The project is integrated with testNG so the project will run with help of TestNG.xml file.
please select appropriate testscript in the testNG.xml file to any testcase script.
