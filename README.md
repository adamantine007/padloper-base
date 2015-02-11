PHONE BOOK
=======================

Description
----------------
It is simple WEB application based on Servlet API and AJAX technologies. Users can save phone book records to database.
An application provides four CRUD operation: create, read, update and delete. So, you can create record about person,
you can read info about some person, also you can update and delete information about person from database.
But now app realized on manual commit structure - if you want to save changes that you did, you must to commit it manual.

There are technologies that were used:
- Servlet API
- Jetty App Server (used embedded mode)
- Ajax
- HTML
- Gson library
- Maven
Also I used `json2.js` file created by [douglascrockford](https://github.com/douglascrockford/JSON-js/blob/master/json2.js) to convert JSON string in JavaScript objects.

How to run?
----------------
So, if you interest in running this application all what you need
 
What are you can?
----------------
An application has next CRUD operations: 
- add person
- select person
- update information about person
- delete person


Communication with DB based on Java Servlets + Ajax.

A client have 4 section on one HTML page: add, select, delete person and commit DB. When client commit DB, all data convert to JSON format and stored in the **data.json** file.

Unfortunately, if client didn't commit before undeploy `.war` file, changes after last commit will not save.
 
Propositions
----------------
If you have some offer to change code, or to add some feature, please contact me.
