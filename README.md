# multi-value-dictionary


**Requirments**
-JDK version 11 (Used OpenJDK 11.0.4)
-Gradle 6.9.1  https://gradle.org/releases/

**Building and Installation**
1. Clone the Project from github
2. Navigate to the root directory and execute the  "gradle build" command

**Running the application**
To run the application, navigate to the build/libs directory and run the command "java -jar .\multi-value-dictionary-1.0-SNAPSHOT.jar"

**Project Structure**
1. src/main/groovy/Command  
    -This directory contains utilities and model objects that facilitate the parsing of command objects from standard input to be executed on the dictionary
      a. Command.groovy: a basic model object to model commands
      
      b. CommandType.groovy: An enum that maps different types of command from standar input
      
      c. CommandParser.groovy: Main utility Class for parsing commands
      
      d. DictionaryCommandException: an exception class for throwing exceptions during the running of the application
      
 2. src/main/groovy/dictionary
    --This directory contains the domain for the dictionary data structure and its utilities
      a. DictionaryService.groovy: This class handles the processing of command objects from the command parser, and
      directs the command to the proper operation to be executed in the dictionary class
      
      b. Item : a model to model key value pairs, primarily used to easily retrieve and show data in the "ITEMS" command
      
      c. MultiValueDictionary.groovy: The primary dictionary data structure, contains the underlying implementations of all operations triggered by the commands
      
  3. ApplicationRunner.groovy: Entry point to the application, provides command line access and handles any exceptions
  4. 
  5. src/test/groovy: This directory contains all unit testing classes


**Supported Commands**
-Note arguments are denoted by quoted values, each command must have the specified arguments without quotes (example: ADD foo bar) and commands are case sensitive

1. KEYS: Returns all keys in the dictionary
2. MEMBERS "key": Returns all members for "key"
3. REMOVE "key" "value": Removes the member denoted by "value" for "key"
4. REMOVEALL "key" : Removes all members for "key"
5. CLEAR: Clears the dictionary
6. KEYEXISTS "key": Returns true if "key" exists in the dictionary, or false if it does not
7. MEMBEREXISTS "key" "value" : Returns true if the specified key value pair exists in the dictionary, or false if it does not
8. ALLMEMBERS: Returns all members in the dictionary
9. ITEMS: Returns all items, or key value pairs, in the dictionary


     
