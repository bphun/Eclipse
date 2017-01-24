#!/bin/sh

# for file in *.java
# do
# 	if [ $? -eq 0 ]; then
# 		javac $file
# 		echo "Compiled $file"
# 	else
# 		echo "Error compiling $file"
# 		exit 1
# 	fi
# done

echo "Compiling KlondikeLauncher.java"
javac KlondikeLauncher.java

echo "Running Klondike"
java KlondikeLauncher

for file in *.class
do
	echo "Removing $file"
	rm $file
done
