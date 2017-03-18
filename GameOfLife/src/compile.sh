#!/bin/sh

echo "Compiling LifeAsWeKnowIt"
javac LifeAsWeKnowIt.java 

echo "Running LifeAsWeKnowIt"  
java LifeAsWeKnowIt

for file in *.class
do
	echo "Removing $file"
	rm $file
done

