package net.katrinka.services

import spock.lang.Specification

class NotReallyATest extends Specification {
    def "it will just fail"() {
        expect:
        true == false
    }

    def "A second test"() {
        expect:
        doSomething() == true
    }

    def doSomething() {
        return true
    }
}
