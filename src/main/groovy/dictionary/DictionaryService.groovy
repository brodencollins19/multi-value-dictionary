package dictionary

import Command.Command
import Command.CommandType

class DictionaryService {

    /*
        This method is responsible for ingesting commands from standard input and applying the commands
        to the specified dictionary instance
     */
    static void processDictionaryCommand(Command command, MultiValueDictionary dictionary){
        switch (command.type) {
            case CommandType.KEYS:
                Set<String> keys = dictionary.getKeys()
                printCollection(keys)
                break;
            case CommandType.MEMBERS:
                Set<String> members = dictionary.getMembers(command.arguments[0])
                printCollection(members)
                break;
            case CommandType.ADD:
                dictionary.addItem(command.arguments[0], command.arguments[1]);
                println(") Added")
                break;
            case CommandType.REMOVE:
                dictionary.removeItem(command.arguments[0], command.arguments[1]);
                println(") Removed")
                break;
            case CommandType.REMOVE_ALL:
                dictionary.removeAll(command.arguments[0])
                println(") Removed")
                break;
            case CommandType.CLEAR:
                dictionary.clear()
                println(") Cleared")
                break;
            case CommandType.KEY_EXISTS:
                boolean keyExists = dictionary.keyExists(command.arguments[0])
                println(") $keyExists")
                break;
            case CommandType.MEMBER_EXISTS:
                boolean memberExists = dictionary.memberExists(command.arguments[0], command.arguments[1])
                println(") $memberExists")
                break;
            case CommandType.ALL_MEMBERS:
                List<String> allMembers = dictionary.getAllMembers()
                printCollection(allMembers)
                break;
            case CommandType.ITEMS:
                Set<Item> allItems = dictionary.getAllItems()
                printCollection(allItems)
                break;
        }
    }


    static private void printCollection(def collection) {
        int index = 0
        if (collection.isEmpty()) {
            println("(empty set)")
        } else {
            collection.each { item ->
                String itemString = item.toString()
                println("${++index}) $itemString")
            }
        }
    }
}
