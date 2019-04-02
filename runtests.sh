#!/bin/bash

SOURCE_DIRS=(
    "src/template"
    )

for JAVA_PACKAGE in "${SOURCE_DIRS[*]}"
do
    echo "Compiling .java files in ${JAVA_PACKAGE}"
    javac -d bin "${JAVA_PACKAGE}/"*.java
done

echo "done"

# compile all tests file
javac -d bin -sourcepath src -cp .:lib/junit-platform-console-standalone-1.4.1.jar src/test/*.java

echo "Running tests"

# run all tests from package "test"
java -jar lib/junit-platform-console-standalone-1.4.1.jar --cp bin/ -p test  | egrep '✘|✔'

# add more tests cases below
