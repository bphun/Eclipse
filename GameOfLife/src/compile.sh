#!/bin/sh

echo "Compiling LifeAsWeKnowIt"
for file in *.java
do
	javac -cp "../gridworld.jar" $file
done

echo "Running LifeAsWeKnowIt"
java LifeAsWeKnowIt

for file in *.class
do
	echo "Removing $file"
	rm $file
done

