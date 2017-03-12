#!/bin/sh

echo "Compiling Platformer.java"
javac PlatformerLauncher.java

echo "Running Platformer"
java PlatformerLauncher

for file in *.class
do
	echo "Removing $file"
	rm $file
done
