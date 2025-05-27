package pl.xsware

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class XsWareDbServiceApplication

fun main(args: Array<String>) {
	runApplication<XsWareDbServiceApplication>(*args)
}
