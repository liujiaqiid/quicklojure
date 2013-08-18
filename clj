#!/bin/sh
# run clojure source or start repl
# with .classpath if exists

# quicklojure -- focusing on speedy start of clojure
# Evan Liu (hmisty)
# 2013.08.18

# reference: http://en.wikibooks.org/wiki/Clojure_Programming/Getting_Started

RLWRAP=rlwrap
BREAKCHARS="(){}[],^%$#@\"\";:''|\\"
COMPLETIONS=$HOME/.clj_completions

JAVA=/usr/bin/java
JPARAM="-d32 -client -XX:+TieredCompilation -XX:TieredStopAtLevel=1" #for 64bit jvm

CLJ_LIB=$HOME/Library/quicklojure/lib
CLASSPATH=`find $CLJ_LIB | xargs | sed 's/ /:/g'`

if [ -f .classpath ]; then
	CLASSPATH=$CLASSPATH:`cat .classpath`
fi

if [ $# -eq 0 ]; then 
	exec $RLWRAP --remember -c -b "$BREAKCHARS" $JAVA $JPARAM -cp "$CLASSPATH" clojure.main
else
	exec $JAVA $JPARAM -cp "$CLASSPATH" clojure.main "$1" -- "$@"
fi

