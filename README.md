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
So, if you interest in running this application, all what you need - clone this project, open with some development tool
like Intellij IDEA or Eclipse, then plug in all `.jar` files from `/lib` folder and run main method from `Launcher.java`.
An application works using Jetty App Server in embedded mode, so you do not need to build any `.war` files and then deploy it.
I use this way cause it's fast to do some modification and you don't spent your time.It's very helpful went you study and just develop without deploy.

