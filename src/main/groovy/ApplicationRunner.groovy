import Command.Command
import Command.CommandParser
import Command.DictionaryCommandException
import dictionary.DictionaryService
import dictionary.MultiValueDictionary

class ApplicationRunner {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in)

        String inputString

        Command command

        MultiValueDictionary dictionary = new MultiValueDictionary(
                values: new HashMap<String, Set<String>>()
        )

        do {
            try {
                print('>')
                inputString = scanner.nextLine()
                command = CommandParser.parseCommand(inputString)
                DictionaryService.processDictionaryCommand(command, dictionary)
            }
            catch (DictionaryCommandException e) {
                println("ERROR, ${e.message}")
            }
        }
        while (!inputString.startsWith("q"))
    }
}
