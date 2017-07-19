package net.katrinka.services

import spock.lang.Specification

class OrderServicesTest extends Specification {
    def svc = new OrderServices()

    def "default service should return a string"() {
        expect:
        svc.defaultService() != null
    }

    def "testing service content"() {
        expect:
        svc.secondService() == "The sky above the port was the color of a television tuned to a dead channel"
    }
}