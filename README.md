#Big file sorter

##Overview
Application sorts all lines in a big file, assuming file size is big enough that can not be fully loaded into the  
memory. Required memory size should not depend of file size. 

In addition its required to develop file generator, that generates file with a given number of lines and given
maximum line length.

###Assumption
Every line of a given file can be fully loaded into memory.
                                                                                                  

##Technology and algorithm overview 
In this assignment I used Guice: a lightweight DI framework from google. 

Starting class is com.kabanov.app.Application that combines required configurations and starts 
DataSorter.
###Algorithm
DataSorter reads N lines from file into memory. N is selected in the way that N lines of the file 
are guaranteed to fit into memory. Read lines are sorts internally and writes to some temporary storage. 
Process repeats until all the lines are read from source file.

After we have files, that are sorted internally, we take any two of the files, and merge them line by line 
into third file, considering sorting order. 
Process repeats until only one file left. 

###Choosing N
It has been decided not to calculate the maximum number of simultaneously loaded lines in runtime.
The constant number is used to fit the requirement that required amount of memory
should not depend on filesize. 
Value N is read from app.properties file
 
##How to run file sorter
Application can be started with a single command: gradlew runApplication -q --console=plain

According to gradle specification, in order to pass program params to main method please use --args="param1 param2..."

####note
flags: "-q --console=plain" hides gradle build status from console that interferes with user input

###input parameters
Application can be started with two parameters: filepath to source file, and filepath to destination file. 

####example of start application
gradlew runApplication -q --console=plain --args="source.txt destination.txt"

##How to run file generator
Generator can be started with a single command: gradlew runGenerator -q --console=plain

####note
since generator is used for testing only - validation of input parameters has been omitted

###input parameters 
Generator receives three parameters:
 - path to generated file
 - number of lines to generate
 - max line length  
