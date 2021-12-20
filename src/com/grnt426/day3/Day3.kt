package com.grnt426.day3

import java.io.File
import kotlin.math.pow

class Day3 {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            /**
             * Solution: 2,250,414
             */
            println("Result: " + Day3().calculatePower("input/day3/input"))
        }
    }

    fun calculatePower(s: String): Long {
        var gamma = 0L
        var epsilon = 0L

        var bitPattern = IntArray(0)

        File(s).forEachLine {
            val bits = it.toCharArray()

            // we want the bits in little endian for easier computation later
            bits.reverse()

            if(bitPattern.isEmpty())
                bitPattern = IntArray(bits.size)

            // More zeros means a negative number, and more ones means a positive number
            // This will correspond to Gamma. Epsilon is the opposite
            for(i in bits.indices) {
                bitPattern[i] += if(bits[i].digitToInt() == 1) 1 else -1
            }
        }

        println("Size of bit pattern ${bitPattern.size}")
        bitPattern.forEach { print("${it}, ") }
        println()

        for(i in bitPattern.indices) {
            val g = if(bitPattern[i] > 0) 1 else 0
            val e = if(g == 1) 0 else 1
            gamma += 2.0.pow(i.toDouble()).toLong() * g
            epsilon += 2.0.pow(i.toDouble()).toLong() * e
        }

        return gamma * epsilon
    }
}