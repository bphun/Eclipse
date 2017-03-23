#!/bin/sh

echo "Compiling Platformer.java"
javac MovingObjectsGameLauncher.java

echo "Running Platformer"
java MovingObjectsGameLauncher

for file in *.class
do
	echo "Removing $file"
	rm $file
done


