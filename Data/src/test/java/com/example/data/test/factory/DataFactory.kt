package com.example.data.test.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {

    fun randomString() = UUID.randomUUID().toString()

    fun randomInt() = ThreadLocalRandom.current().nextInt(0, 1000 + 1)

    fun randomLong() = randomInt().toLong()

    fun randomBoolean() = Math.random() < 0.5
}