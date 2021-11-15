import Command.DictionaryCommandException
import dictionary.Item
import dictionary.MultiValueDictionary
import spock.lang.Specification
import spock.lang.Unroll

class MultiValueDictionaryTest extends Specification{

    def 'test get all keys' (){
        given:
        Set<String> memberSet = ['bar', 'bang'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': memberSet, 'baz':memberSet])

        when: 'I retrieve all keys'
        Set<String> keys = dictionary.getKeys()

        then: 'I get the expected keys'
        assert keys.containsAll(['foo', 'baz'])
    }

    def 'test get members' (){
        given:
        Set<String> fooSet = ['bar', 'bang'].toSet()
        Set<String> bazSet = ['bang2'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': fooSet, 'baz':bazSet])


        when: 'I retrieve members for key foo'
        Set<String> members = dictionary.getMembers('foo')

        then: 'I get the expected keys for foo'
        assert members.containsAll(['bar', 'bang'])
        assert !members.contains('bang2')
    }

    def 'test add member' (){
        given:
        MultiValueDictionary dictionary = new MultiValueDictionary(
                values: new HashMap<String, Set<String>>()
        )

        when: 'I add foo bar to the dictionary'
        dictionary.addItem('foo', 'bar')

        then: 'dictionary now contains foo bar'
        assert dictionary.values.get('foo') != null
        assert dictionary.values.get('foo').contains('bar')

    }

    def 'test add member when member for key already exists' (){
        given:
        Set<String> set = ['bar'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': set])

        when: 'I add foo bar to the dictionary'
        dictionary.addItem('foo', 'bar')

        then: 'exception is thrown'
        def e = thrown(DictionaryCommandException)
        assert e.message == "member already exists for key"

    }

    def 'test remove member'(){
        given:
        Set<String> set = ['bar','baz'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': set])

        when: 'I remove foo bar from the dictionary'
        dictionary.removeItem('foo', 'bar')

        then: 'Foo bar is removed'
        assert !dictionary.values.get('foo').contains('bar')


    }

    @Unroll
    def 'test remove member with exception'(){
        given:
        MultiValueDictionary dictionary = inputDictionary

        when: 'I try to remove foo bar from the dictionary'
        dictionary.removeItem('foo', 'bar')

        then: 'exception is thrown'
        def e = thrown(DictionaryCommandException)
        assert e.message == expectedException

        where:
        inputDictionary | expectedException
        new MultiValueDictionary(values: new HashMap<String, Set<String>>()) | 'key does not exist'
        new MultiValueDictionary(values: ['foo': ['baz, bang'].toSet()]) | 'member does not exist'

    }

    def 'test remove all members for key'(){
        given:
        Set<String> fooSet = ['bar', 'bang'].toSet()
        Set<String> bazSet = ['bang2'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': fooSet, 'baz':bazSet])

        when: 'I remove all values for foo from the dictionary'
        dictionary.removeAll('foo')

        then: 'Foo values are removed and baz items remain'
        assert !dictionary.values.get('foo')
        assert dictionary.values.get('baz')
    }

    def 'test remove all members when key does not exist'(){
        given:
        Set<String> fooSet = ['bar', 'bang'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': fooSet])

        when: 'I remove all values for baz from the dictionary'
        dictionary.removeAll('baz')

        then: 'Exception is thrown'
        def e = thrown(DictionaryCommandException)
        e.message == "key does not exist"


    }

    def 'test clear'(){
        given:
        Set<String> set = ['bar','baz'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': set])

        when: 'I clear the dictionary'
        dictionary.clear()

        then: 'Dictionary is empty'
        assert dictionary.values.isEmpty()

    }

    def 'test key exists'(){
        given:
        Set<String> set = ['bar'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': set])

        when: 'I try to retrieve if keys exist'
        boolean fooExists = dictionary.keyExists('foo')
        boolean bazExists = dictionary.keyExists('baz')

        then: 'Expected values are returned'
        assert fooExists
        assert !bazExists
    }

    def 'test member exists' (){
        given:
        Set<String> set = ['bar'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': set])

        when: 'I try to retrieve if members exist'
        boolean fooBarExists = dictionary.memberExists('foo', 'bar')
        boolean fooBangExists = dictionary.memberExists('foo', 'bang')

        then: 'expected values are returned'
        assert fooBarExists
        assert !fooBangExists
    }

    def 'test get all members'(){
        given:
        Set<String> fooSet = ['bar', 'bang'].toSet()
        Set<String> bazSet = ['bang2'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': fooSet, 'baz':bazSet])

        when: 'I retrieve all members from the dictionary'
        List<String> allMembers = dictionary.getAllMembers()

        then: 'Expected values are returned'
        assert allMembers.containsAll('bar', 'bang','bang2')

    }

    def 'test get all items'(){
        given:
        Set<String> fooSet = ['bar', 'bang'].toSet()
        Set<String> bazSet = ['zoom'].toSet()
        MultiValueDictionary dictionary = new MultiValueDictionary(values: ['foo': fooSet, 'baz':bazSet])

        when: 'I retrieve all items from the dictionary'
        List<Item> allItems = dictionary.getAllItems()

        then: 'Expected values are returned'
        assert allItems.size() == 3

        assert allItems[0].key == 'foo'
        assert allItems[0].value == 'bar'

        assert allItems[1].key == 'foo'
        assert allItems[1].value == 'bang'

        assert allItems[2].key == 'baz'
        assert allItems[2].value == 'zoom'
    }

}
