#Assignment no 3
This assignment demonstrates the basics of how to develop, deploy and test local server hosted web application

#Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

#Team Member Information

 * Mihir Patil- patil.m@husky.neu.edu
 * Vivek Shetye- shetye.v@husky.neu.edu
 * Pushkar Khedekar- khedekar.p@husky.neu.edu
 * Atul Takekar- takekar.a@husky.neu.edu
 
 #Prerequisites 
 * IntelliJ IDEA Ultimate IDE
 * Apache Tomcat 8
 * gradle
 * MySQL
 * jmeter
 
 #Installing
 A step by step series of examples that tell you have to get a development env running 

 * Install java 8 on your machine
First, update the package index.


    sudo apt-get update

Next, install Java. Specifically, this command will install the Java Runtime Environment (JRE).

    sudo apt-get install default-jre

There is another default Java installation called the JDK (Java Development Kit). The JDK is usually only needed if you are going to compile Java programs or if the software that will use Java specifically requires it.

The JDK does contain the JRE, so there are no disadvantages if you install the JDK instead of the JRE, except for the larger file size.

You can install the JDK with the following command:

    sudo apt-get install default-jdk


* Install IntelliJ IDEA Ultimate IDE from this website
``` bash
https://www.jetbrains.com/idea/
```
* Install Tomcat 8 from this website
``` bash
https://tomcat.apache.org/download-80.cgi
```
* Install gradle from this website
``` bash
https://gradle.org/
```
* Install jmeter from this website
``` bash
http://jmeter.apache.org/download_jmeter.cgi
```

#Build and Deploy instructions for web application.
* Download the application.
* Import the web app in IntelliJ IDE.
* Create schema in database with name e.g. MySchema
``` bash
create schema Assignment_2
```
*change application properties file as
``` bash
spring.datasource.url = jdbc:mysql://localhost:3306/Assignment_2
```
* Edit run configuration.
* Add local tomcat server.
* add application artifact to local server.
* run the application

#Instructions to run unit, integration test
* First, Open terminal on your project directory.
* To build and execute all tests run following commands in your terminal
``` bash
./gradlew war 
./gradlew test
```
this will all unit and integration tests

#Instructions to load test
* Open HTTPRequest.jmx file in jmeter application located in jmeter folder of the project
* Enter Number of threads to test in each thread group. Keep the number same for all thread groups.
	Thread Group--> Thread Properties
* Run each thread individually in following sequence

	1. Thread Group (Login/Register)
	1.1.After running it create file name Create_Task csv with columns (email,password,description). This file can be created by exporting 		1.2.these columns from sql table
	In thread group of create task in CSV Data Set Config attach this created file.

	2. Thread Group (Create Task)
	2.1.After running it create file name Update_Task csv with columns (email,password,taskId,updated_Desc). This file can be created by 	     exporting these columns from sql table
	2.2.In thread group of Update task in CSV Data Set Config attach this created file.

	3. Thread Group (Update Task)
	3.1.After running it create file name Add_Attachment csv with columns (email,password,taskId,file_path). This file can be created by 	     exporting some of these columns from sql table
	3.2.In thread group of Add Attachments in CSV Data Set Config attach this created file.

	4. Thread Group (Add Attachment(s))
	4.1.After running it create file name Get_Attachment csv with columns (email,password,taskId). This file can be created by 	 		exporting some of these columns from sql table
	4.2.In thread group of Get Attachments in CSV Data Set Config attach this created file.

	5. Thread Group (Get attachetments)
	5.1.After running it create file name Delete_Attachment csv with columns (email,password,taskId,attachmentId). This file can be 	created be exporting some of these columns from sql table
	5.2.In thread group of Delete Attachments in CSV Data Set Config attach this created file.

	6. Thread Group (Delete attachments)
	After running it create file name Delete_Task csv with columns (email,password,taskId). This file can be created by 	       		exporting some of these columns from sql table
	In thread group of Delete task in CSV Data Set Config attach this created file.

	7. Thread Group (Delete Task)

 
#Link to TravisCI build for the project.
``` bash
https://travis-ci.com/vivekshetye/csye6225-fall2017/builds/55708703
```
