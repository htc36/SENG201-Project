#!/bin/bash

echo "Compiling .java files"
javac -d bin -cp . \
	src/unit/*.java \
	src/consumable/*.java \
	src/crew/*.java \
	src/game/*.java \
	src/outpost/*.java \
	src/random_events/*.java \
	src/planet/*.java \
	src/gui/*.java

echo "done"

if [[ "$1" == "--test" ]]
then
# compile all tests file
javac -d bin -sourcepath src -cp .:lib/junit-platform-console-standalone-1.4.1.jar src/test/*.java

echo "Running tests"
# run all tests from package "test"
java -jar lib/junit-platform-console-standalone-1.4.1.jar --cp bin/ -p test  | egrep '✘|✔'
fi

# add more tests cases below
