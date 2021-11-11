import spock.lang.Specification

class exampletest extends Specification{

    def 'test' (){
        given:
        def x =2

        when:
        x = x+2

        then:
        x == 4
    }
}