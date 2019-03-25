#!/bin/bash

javac -d bin -sourcepath src -cp .:lib/junit-platform-console-standalone-1.4.1.jar src/test/*.java

java -jar lib/junit-platform-console-standalone-1.4.1.jar --cp bin/ -p test 
