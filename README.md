# jetty-examples
Steps
<ol><li>
Download/save off the Jetty server 
<br>from here: http://repo1.maven.org/maven2/org/eclipse/jetty/aggregate/jetty-all/
//choose the most recent version 9.4.18 for example:
http://repo1.maven.org/maven2/org/eclipse/jetty/aggregate/jetty-all/9.4.18.v20190429/
<br>click on and download the jetty-all uber jar file: jetty-all-9.4.18.v20190429-uber.jar
<br>Save it in the local working directory as jetty.jar, e.g.:
<br>mv ~/Desktop/jetty-all-9.4.18.v20190429-uber.jar jetty.jar  //change downloaded filename if needed
</li><li>
Download simple-json from here: https://code.google.com/archive/p/json-simple/downloads
<br>Save it in the local working directory as json-simple.jar
</li><li>
Download GSON+JSON (java deserialization to JSON objects):
<br>https://search.maven.org/artifact/com.google.code.gson/gson/2.8.5/jar
<br>Click on most recent version (2.8.5) via drop down menu
<br>Click on Downloads on top right, select jar, move the downloaded jar to here:
<br>mv ~/Desktop/gson-2.8.5.jar ./gson.jar     //change the downloaded filename if needed
</li><li>
Build the java files in this directory.
<br>export CLASSPATH=.:./gson.jar:./jetty-all.jar:./json-simple.jar
<br>javac *.java
</li><li>
Run the server (in its own window).
<br>export CLASSPATH=.:./gson.jar:./jetty-all.jar:./json-simple.jar
<br>java App
<br>//leave it running, but when you are ready, use ctrl-c to kill/stop it
</li><li>
Open chrome (do not use any other browser!)
<br>Turn on debugging window: View->Developer->JavaScript Console
<br>Select File->Open and navigate to to your working directory (where test.html and jqfns.js are) and select test.html
<br>Click the buttons to run each of the javascript functions (2 for POST and 1 for GET).
<br>The server will output information in its window when it receives the POSTs and GETs.
</li><li>
<br>If you change the jqfns.js file, reload/refresh the test.html window
<br>If you change the java files, re-compile them with javac and stop/start the App again using the steps above.
<br>If you get classes/symbols not found -- check that you have the classpath set right
<br>If you add libraries, put the jar files in the working directory and. add them to the end of the CLASSPATH line above after a colon.
</li><ol>

Files
<ul><li>App.java - the java program (with main in it) that starts the simple Java HTTP server (jetty)
</li><li>Jetty.java - the jetty server where you register your java classes for each POST/GET and the route at which each lives
</li><li>GetName.java - The class that implements a GET HTTP request (pass in an "id" or nothing, see jqfns.js for a javascript client example, when t
he GetButton
</li><li>SaveName.java - The class that implements a POST HTTP request when you press the PostButton.  You pass it a key/value pair and it uses it to set name and surName
</li><li>SaveName.java - Same as SaveName only it gets invoked when you press the PostArrayButton.  You pass it a json array and it prints it out.
</li></ul>

LICENSE: Modified BSD, UCSB Copyright (see file LICENSE)

