#!/bin/sh

# for file in *.java
# do
#       if [ $? -eq 0 ]; then
#               javac $file
#               echo "Compiled $file"
#       else
#               echo "Error compiling $file"
#               exit 1
#       fi
# done

echo "Compiling DiepLauncher.java"
javac MovingObjectsGameLauncher.java

echo "Running Diep"
java MovingObjectsGameLauncher

for file in *.class
do
        echo "Removing $file"
        rm $file
done
