import Command.Command
import Command.CommandParser
import Command.CommandType
import Command.DictionaryCommandException
import spock.lang.Specification
import spock.lang.Unroll

class CommandParserTest extends Specification {

    static final CommandParser commandParser = new CommandParser()


    @Unroll
    def 'test command parser returns command object when both command and number of arguments are valid' (){
        when: 'I try to parse a a valid command'
        Command parsedCommand = commandParser.parseCommand(commandInput)

        then: 'Command object is correctly parsed'
        assert parsedCommand != null

        and: 'Command has expected type and arguments'
        parsedCommand.type == expectedCommandType
        parsedCommand.arguments == expectedArguments

        where:
        commandInput | expectedCommandType | expectedArguments
        'KEYS' | CommandType.KEYS | []
        'MEMBERS foo' | CommandType.MEMBERS | ['foo']
        'ADD foo bar' | CommandType.ADD | ['foo', 'bar']
        'REMOVE bar baz' | CommandType.REMOVE | ['bar', 'baz']
        'REMOVEALL bar' | CommandType.REMOVE_ALL | ['bar']
        'CLEAR' | CommandType.CLEAR | []
        'KEYEXISTS test' | CommandType.KEY_EXISTS | ['test']
        'MEMBEREXISTS 123 test' | CommandType.MEMBER_EXISTS | ['123', 'test']
        'ALLMEMBERS' | CommandType.ALL_MEMBERS | []
        'ITEMS' | CommandType.ITEMS | []

    }


    def 'test command parser throws exception when command type is invalid'() {
        given: "Some invalid command is provided"
        String invalidInput = "This_command is invalid"

        when: 'I try to parse an invalid command'
        commandParser.parseCommand(invalidInput)

        then: 'I encounter an invalid command exception with not a valid command'
        def e = thrown(DictionaryCommandException)
        assert e.message == 'error: This_command is not a valid command'

    }

    @Unroll
    def 'test command parser throws exception when number of command arguments are invalid'() {
        when: 'I try to parse a command with invalid number of arguments'
        commandParser.parseCommand(commandInput)

        then: 'I encounter an invalid command exception'
        def e = thrown(DictionaryCommandException)
        e.message == "error: Invalid number of arguments, please try again"

        where:
        commandInput | _
                'KEYS foo' | _
                'MEMBERS foo bar' | _
                'ADD 123' | _
                'REMOVE a bc def' | _
                'REMOVEALL hello bar' | _
                'CLEAR 123' | _
                'KEYEXISTS' | _
                'MEMBEREXISTS 123' | _
                'ALLMEMBERS foo' | _
                'ITEMS bar' | _

    }

}
