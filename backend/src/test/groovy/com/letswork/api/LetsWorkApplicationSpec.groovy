package com.letswork.api

import spock.lang.Specification

import org.springframework.context.ApplicationContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired

@SpringBootTest
class LetsWorkApplicationSpec extends Specification {

    @Autowired
    private ApplicationContext context

    def 'Should load Spring context'() {
        expect:
        context != null
    }
}
