#!/bin/sh
BASEDIR="$(dirname "$0")"
LIBRARY="$BASEDIR/lib"
USERDIR="$BASEDIR"
JAR="$BASEDIR/lib/Der Kaefer.jar"

echo "BASEDIR:" $BASEDIR
echo "LIBRARY:" $LIBRARY
echo "USERDIR:" $USERDIR
echo "JAR:" $JAR


java -Djava.library.path="$LIBRARY" -Duser.dir="$USERDIR" -jar "$JAR"
