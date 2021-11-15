import Command.Command
import Command.CommandType
import dictionary.DictionaryService
import dictionary.MultiValueDictionary
import spock.lang.Specification

class DictionaryServiceTest extends Specification {

    MultiValueDictionary mockDictionary = Mock()
    final DictionaryService dictionaryService = new DictionaryService()

    def 'test process KEYS command' (){
        given:
        Command command = new Command(type:CommandType.KEYS, arguments: [])

        when: 'I pass in a KEYS command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls keys method'
        1 * mockDictionary.getKeys() >> ['foo']

    }

    def 'test process MEMBERS command' (){
        given:
        Command command = new Command(type:CommandType.MEMBERS, arguments: ['foo'])
        String arg = command.arguments[0]

        when: 'I pass in a members command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls members method'
        1 * mockDictionary.getMembers(arg) >> ['bar']
    }

    def 'test process ADD command' (){
        given:
        Command command = new Command(type:CommandType.ADD, arguments: ['foo', 'bar'])
        String arg1 = command.arguments[0]
        String arg2 = command.arguments[1]

        when: 'I pass in a ADD command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls keys method'
        1 * mockDictionary.addItem(arg1, arg2)

    }

    def 'test process REMOVE command' (){
        given:
        Command command = new Command(type:CommandType.REMOVE, arguments: ['foo', 'bar'])
        String arg1 = command.arguments[0]
        String arg2 = command.arguments[1]

        when: 'I pass in a REMOVE command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls remove method'
        1 * mockDictionary.removeItem(arg1, arg2)

    }

    def 'test process REMOVEALL command' (){
        given:
        Command command = new Command(type:CommandType.REMOVE_ALL, arguments: ['foo'])
        String arg = command.arguments[0]

        when: 'I pass in a REMOVEALL command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls removeAll method'
        1 * mockDictionary.removeAll(arg)

    }


    def 'test process CLEAR command' (){
        given:
        Command command = new Command(type:CommandType.CLEAR, arguments: [])

        when: 'I pass in a CLEAR command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls clear method'
        1 * mockDictionary.clear()

    }

    def 'test process KEYEXISTS command' (){
        given:
        Command command = new Command(type:CommandType.KEY_EXISTS, arguments: ['foo'])
        String arg = command.arguments[0]

        when: 'I pass in a KEYEXISTS command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls removeAll method'
        1 * mockDictionary.keyExists(arg) >> true

    }

    def 'test process MEMBEREXISTS command' (){
        given:
        Command command = new Command(type:CommandType.MEMBER_EXISTS, arguments: ['foo'])
        String arg1 = command.arguments[0]
        String arg2 = command.arguments[1]

        when: 'I pass in a MEMBEREXISTS command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls memberExists method'
        1 * mockDictionary.memberExists(arg1, arg2) >> true

    }


    def 'test process ALLMEMBERS command' (){
        given:
        Command command = new Command(type:CommandType.ALL_MEMBERS, arguments: [])

        when: 'I pass in a ALLMEMBERS command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls clear method'
        1 * mockDictionary.getAllMembers() >> ['foo']
    }



    def 'test process ITEMS command' (){
        given:
        Command command = new Command(type:CommandType.ITEMS, arguments: [])

        when: 'I pass in a ITEMS command'
        dictionaryService.processDictionaryCommand(command, mockDictionary)

        then: 'Dictionary object calls clear method'
        1 * mockDictionary.getAllItems() >> ['foo: bar']

    }
}
