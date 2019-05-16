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
On the javascript/html side (using jquery) -- still in your local working directory
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

You can also try using Python for the client.  There is a simple python client in the python_client directory.
<br>With the server running, go to another console window on the same machine and run the python client:

```
cd python_client
pip install requests //if you haven't done this already
python client.py
```

You should see this on the server console/window:

```
GET request, ID: foo
POST (incoming key/value String pairs): 
name:name1 surname:name2
```

Check out client.py to see how to issue GETs and POSTs from python.

  
  </li><li>
If you change the jqfns.js or test.html files, reload/refresh the test.html window
<br>If you change the java files, re-compile them with javac and stop/start the App again using the steps above.
<br>If you get classes/symbols not found -- check that you have the classpath set correctly (see above)
<br>If you add libraries, put the jar files in the working directory and add them to the end of the CLASSPATH line above after a colon.
</li></ol>

## Making the above work on two different machines (server and client)

For this example, we are doing the same thing as above only running the client (curl, test.html+jqfns.js) on one machine and the server (java App) on another.  The clients will talk over the network to the server.  

<ol><li>
Open a console/window and ssh into csil-24.cs.ucsb.edu 

```
mkdir cs48
cd cs48
//clone the repo and download the dependencies
git clone https://github.com/UCSB-CS-RACELab/jetty-examples.git
cd jetty-examples
wget http://repo1.maven.org/maven2/org/eclipse/jetty/aggregate/jetty-all/9.4.18.v20190429/jetty-all-9.4.18.v20190429-uber.jar -O jetty-all.jar
wget https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/json-simple/json-simple-1.1.1.jar -O json-simple.jar
wget https://search.maven.org/remotecontent?filepath=com/google/code/gson/gson/2.8.5/gson-2.8.5.jar -O gson.jar
```

</li><li>Build the repo and run the server

```
cd
cd cs48
cd jetty-examples
export CLASSPATH=.:./gson.jar:./jetty-all.jar:./json-simple.jarjavac *.java
java App

//you should see some lines and these as the last 2 lines (the last value 358ms is time, so that may change):
2019-05-16 08:06:33.922:INFO:oejs.AbstractConnector:main: Started ServerConnector@4f8e5cde{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
2019-05-16 08:06:33.923:INFO:oejs.Server:main: Started @358ms


//if you instead see this as the final line:
2019-05-16 08:13:07.751:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@45c8e616{/test,null,AVAILABLE}
//e.g. {/test,null,AVAILABLE} then the port is already in use and you need to choose another and restart:

Steps to kill/reset the port/restart:
//kill the server (ctrl-C)
//edit App.java and change 8080 (final Jetty jetty = new Jetty(8080);) to some number between 8100 and 8999.  
//Then rebuild (javac *.java) 
//And start the server again (java App)
//Repeat until you see the final line saying ... Server:main: Started ...
```

</li><li>Leave the server running. 
<br>Open another window and ssh into a DIFFERENT csil machine (e.g. csil-10.cs.ucsb.edu)

<br>Run a client (e.g. curl in this case):

```
curl --request POST http://csil-24.cs.ucsb.edu:8080/test/SaveName -d 'name=XXX&surName=YYY'
//you should see this on the client (nothing, a space)
//you should see this on the server
//you should see this on the server console/window:
POST (incoming key/value String pairs): 
name:XXX surname:YYY


curl --request GET http://csil-24.cs.ucsb.edu:8080/test/GetName
//you should see this on the client:
{"prop1":"property1val","prop2":"property2val"}
//you should see this on the server console/window:
GET request, ID: NONE
```

</li><li>Next you can test that a different client works.  There is a sample javascript client in this repo.  Here are the steps to test it.  Make sure that your server is running and note the name of the server and the port (e.g. csil-24.cs.ucsb.edu:8080, change the port to the one you set in App.java if you changed it).

<br>On your laptop (make sure you are connected to the Internet), clone the repo and cd into the jetty-examples directory. Edit the jqfns.js file and change localhost:8080 everywhere (3 places) to your server and port: csil-24.cs.ucsb.edu:8080

<br>Then open test.html (File->Open) in a chrome browser on your laptop.  Navigate to the jetty-examples directory and open test.html. Make sure that you have completed step 8  in the first part (adding the CORS chrome extension to your browser).

<br>Click on ClickMe:GetButton
<br>You should this in your browser:

```
prop1:property1val
prop2:property2val
```

<br>And you should this in your server console:

```
GET request, ID: testID
```


<br>Click on ClickMe:PostButton
<br>You should see this on the server console/window (and nothing on you browser/client window):

```
POST (incoming key/value String pairs): 
name:XXX surname:YYY
```

</li><li>Try a client in a different language: Python
<br>With the server running, go to another console window on the same machine and run the python client. Edit client.py and change localhost:8080 in two places to the name of the server (e.g. csil-24.cs.ucsb.edu:8080).

```
cd python_client
pip install requests //if you haven't done this already
python client.py
```

You should see this on the server console/window:

```
GET request, ID: foo
POST (incoming key/value String pairs): 
name:name1 surname:name2
```

Check out client.py to see how to issue GETs and POSTs from python.


</li><li>Assuming the above worked.  You can now try implementing your client with React or some other framework and have it post/get to your server.  To to this, change the URL/IP that react uses (developement server) to be the URL/IP of your server (csil-24.cs.ucsb.edu:8080 in the steps above.  Change 8080 to the port you set if you changed the port).

<br>There is a SaveArray example/button that posts an array of json data in jqfns.js for you to check out if needed.
</li></ol>

## Files
<ul><li>App.java - the java program (with main in it) that starts the simple Java HTTP server (jetty)
</li><li>Jetty.java - the jetty server where you register your java classes for each POST/GET and the route at which each lives
</li><li>GetName.java - The class that implements a GET HTTP request (pass in an "id" or nothing, see jqfns.js for a javascript client example, when t
he GetButton
</li><li>SaveName.java - The class that implements a POST HTTP request when you press the PostButton.  You pass it a key/value pair and it uses it to set name and surName
</li><li>SaveName.java - Same as SaveName only it gets invoked when you press the PostArrayButton.  You pass it a json array and it prints it out.
</li></ul>

## LICENSE: Modified BSD, UCSB Copyright (see file LICENSE)

