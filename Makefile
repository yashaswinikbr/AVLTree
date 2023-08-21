JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

jar: $(classes)
	javac avltree.java

clean :
	rm -f *.class output_file.txt