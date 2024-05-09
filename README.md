## SWIG

SWIG generates the interface between the Java and C code. 

To run it, go to a directory with a SWIG config file, and use the command in the comment at the top of it. 

For example, for NTRIP:
1. Go to _[project dir]/app/src/main/cpp/ntrip_
2. Run `swig -java -package com.signalquest.sdk.ntrip -outdir ../../java/com/signalquest/sdk/ntrip ntrip.i`. 

## Tests

We're starting with tests to verify the Java/C bridge is implemented correctly. 

To run these tests with _Android Studio_:
1. Open one of the androidTest files (e.g. _app/src/androidTest/com/signalquest/sdk/ntrip/NtripTest.java_)
2. Right-click inside the desired test block
3. Choose the appropriate _Run_ command for the test

## Building using Android Studio

1. Choose the _release_ Build Variant (show option using *Build* -> *Select Build Variant...*)
2. *Build* -> *Rebuild Project*
3. Copy the _signalquest-release.aar_ file to the example app project
   1. From \[this project dir\]/signalquest/build/outputs/aar/signalquest-release.aar
   2. To \[example app project dir\]/app/libs/signalquest-release.aar
4. Choose *File* -> *Invalidate Caches...*, then press *Invalidate and Restart*
5. Wait for the example app to reindex

