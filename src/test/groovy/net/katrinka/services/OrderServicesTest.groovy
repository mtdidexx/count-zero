package net.katrinka.services

import spock.lang.Specification

class OrderServicesTest extends Specification {
    def svc = new OrderServices()

    def "default service should return a string"() {
        expect:
        svc.defaultService() != null
    }
}