# parkinglot
The program was developed on ubuntu 14.04. 

System Requirements:
---------------------------

JDK 1.8

Maven > 2

Junit 3.8.1

log4j 1.2.17

Executing the program:
---------------------------

(1)By making use of your own file:

	Place the input file at src/main/resources, with name input_file.txt
	mvn clean install
	java -jar target/parkinglot-jar-with-dependencies.jar input_file.txt
	
(2)By running shell script

	sh execute.sh
		
