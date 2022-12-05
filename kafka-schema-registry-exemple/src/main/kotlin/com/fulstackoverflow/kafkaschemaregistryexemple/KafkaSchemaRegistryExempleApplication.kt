package com.fulstackoverflow.kafkaschemaregistryexemple

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaSchemaRegistryExempleApplication

fun main(args: Array<String>) {
	runApplication<KafkaSchemaRegistryExempleApplication>(*args)
}
