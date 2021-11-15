package Command

/*
-Utility class class for parsing commands from standard input to apply to the multivalue dictonary
-This class provides input validation for command types and arguments and provides and provides the command object
to the multi value dictionary object
 */
class CommandParser {

   static Command parseCommand(String input){
        int expectedArguments
        List<String> arguments = input.split(" ")
        String actionString = arguments.first()
        CommandType type

        switch(actionString){
            case "KEYS":
                type = CommandType.KEYS
                expectedArguments = 1
                break
            case "MEMBERS":
                type = CommandType.MEMBERS
                expectedArguments = 2
                break
            case "ADD":
                type = CommandType.ADD
                expectedArguments = 3
                break;
            case "REMOVE":
                type = CommandType.REMOVE
                expectedArguments = 3
                break;
            case "REMOVEALL":
                type = CommandType.REMOVE_ALL
                expectedArguments = 2
                break;
            case "CLEAR" :
                type = CommandType.CLEAR
                expectedArguments = 1
                break;
            case "KEYEXISTS":
                type = CommandType.KEY_EXISTS;
                expectedArguments = 2
                break;
            case "MEMBEREXISTS" :
                type = CommandType.MEMBER_EXISTS;
                expectedArguments = 3
                break;
            case "ALLMEMBERS":
                type = CommandType.ALL_MEMBERS
                expectedArguments = 1
                break;
            case "ITEMS":
                type = CommandType.ITEMS;
                expectedArguments = 1
                break;
            default:
                throw new DictionaryCommandException("error: $actionString is not a valid command")
        }

       if(arguments.size() != expectedArguments){
           throw new DictionaryCommandException("error: Invalid number of arguments, please try again")
       }

        return new Command(type: type, arguments: arguments.tail())
    }
}
