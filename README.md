# jetty-examples
Steps - we are creating both the server (java) and the client (javascript) in the same directory.  You can run them on the same host (use localhost as the URL).
<ol><li>
  Clone this repo and move into the jetty-examples working directory.
  
  ```
  git clone https://github.com/UCSB-CS-RACELab/jetty-examples.git
  cd jetty-examples
  ```
  
</li><li>
Download/save off the Jetty server 
<br>from here: http://repo1.maven.org/maven2/org/eclipse/jetty/aggregate/jetty-all/
//choose the most recent version 9.4.18 for example:
http://repo1.maven.org/maven2/org/eclipse/jetty/aggregate/jetty-all/9.4.18.v20190429/
<br>click on and download the jetty-all uber jar file: jetty-all-9.4.18.v20190429-uber.jar
<br>Save it in the local working directory as jetty.jar, e.g.:

```
mv ~/Desktop/jetty-all-9.4.18.v20190429-uber.jar jetty.jar  //change downloaded filename if needed
```

</li><li>
Download simple-json v1.1.1 (json-simple-1.1.1.jar) from here: https://code.google.com/archive/p/json-simple/downloads
<br>Save it in the local working directory and rename it to be called json-simple.jar

```
mv ~/Desktop/json-simple-1.1.1.jar ./json-simple.jar     //change the downloaded filename if needed
```
  
</li><li>
Download GSON+JSON (java deserialization to JSON objects):
<br>https://search.maven.org/artifact/com.google.code.gson/gson/2.8.5/jar
<br>Click on most recent version (2.8.5) via drop down menu
<br>Click on Downloads on top right, select jar, move the downloaded jar to here:

```
mv ~/Desktop/gson-2.8.5.jar ./gson.jar     //change the downloaded filename if needed
```

</li><li>
Build the java files in this directory (open a console and move into the working directory jetty-examples).

```
export CLASSPATH=.:./gson.jar:./jetty-all.jar:./json-simple.jar
javac *.java
```
  
</li><li>
Run the server (in a SEPARATE console/window). It runs forever (until you kill it (steps below)) so you need it to run in a separate window.  You only interoperate with it via HTTP requests from another window/console/machine.
  
```
export CLASSPATH=.:./gson.jar:./jetty-all.jar:./json-simple.jar
java App
```

Leave it running, but when you are ready to turn it off, use ctrl-c to kill/stop it
</li><li>
7) On the javascript/html side (using jquery) -- still in your local working directory
<br>Download jquery production copy from http://jquery.com/download/

```
  cp ~/Desktop/jquery-3.4.1.min.js .
```
</li><li>
Open chrome (do not use any other browser!)
  <br>Download/install CORS extension (allows localhost testing)
https://chrome.google.com/webstore/detail/allow-control-allow-origi/nlfbmbojpeacfghkpbjhddihlkkiljbi?hl=en-US
<br>Turn on debugging window: View->Developer->JavaScript Console
<br>Select File->Open and navigate to to your working directory (where test.html and jqfns.js are) and select test.html
<br>Click the buttons to run each of the javascript functions (2 for POST and 1 for GET).
<br>The server will output information in its window when it receives the POSTs and GETs.
</li><li>You can also use curl to test the server -- note the capitalization in surName (must be exactly this):

```
curl --request POST http://localhost:8080/test/SaveName -d 'name=XXX&surName=YYY'
curl --request GET http://localhost:8080/test/GetName
```
  
  </li><li>
If you change the jqfns.js or test.html files, reload/refresh the test.html window
<br>If you change the java files, re-compile them with javac and stop/start the App again using the steps above.
<br>If you get classes/symbols not found -- check that you have the classpath set correctly (see above)
<br>If you add libraries, put the jar files in the working directory and add them to the end of the CLASSPATH line above after a colon.
</li></ol>

Files
<ul><li>App.java - the java program (with main in it) that starts the simple Java HTTP server (jetty)
</li><li>Jetty.java - the jetty server where you register your java classes for each POST/GET and the route at which each lives
</li><li>GetName.java - The class that implements a GET HTTP request (pass in an "id" or nothing, see jqfns.js for a javascript client example, when t
he GetButton
</li><li>SaveName.java - The class that implements a POST HTTP request when you press the PostButton.  You pass it a key/value pair and it uses it to set name and surName
</li><li>SaveName.java - Same as SaveName only it gets invoked when you press the PostArrayButton.  You pass it a json array and it prints it out.
</li></ul>

LICENSE: Modified BSD, UCSB Copyright (see file LICENSE)

