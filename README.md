# ng4-contacts-server

This is a Spring REST CRUD backend to an Embedded Derby database.

This is an Eclipse / Maven project.

See https://github.com/zouabimourad/angular2-spring for pagination and initial Spring backend.

See https://github.com/RedFroggy/angular-spring-hmac for Security.

I modified the code to implement CRUD and added Search functionality and used Derby for persistence.

The model is for Contact vs Person.

A SQL script is included to create a Derby database.  See the script in the sql folder.

Modify the persistence.xml to reflect where the database resides.

Tested using Java 8 / Tomcat 8.5

Using Eclipse, Right click on the project, Debug As ... select your Java server, click Finish

To test the REST API ... http://localhost:8080/ng5-contacts-server/api/contact

You should see JSON formated data.

Proceed to the README of the ng5-contacts for an Angluar 5 frontend.


