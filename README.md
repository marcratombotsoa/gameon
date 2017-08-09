# gameon
Game Card Application Utilities

## Prerequisites
 - Java 8
 - Maven 3.x

## Functional Summary
Standalone console application which generates a card game collection and reads from the standard input to apply an action on the collection of cards. Available commands are the following:
 - shuffle: Shuffle the cards and prints to the standard output
 - sort: Sort the cards by different types of crieria: symbol, color or number
 - pick: Get a card randomly from the collection
 
## Setup
 - Clone or download the repository
 - Go to the project folder and run the following maven command:
 	$ mvn clean install
 - Run the executable jar file:
 	$ java -jar target/cardgame-1.0.0-SNAPSHOT.jar
 

