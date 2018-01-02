package net.katrinka.services

import spock.lang.Specification

class NotReallyATest extends Specification {
    def "it will just fail"() {
        expect:
        false == true
    }
}
