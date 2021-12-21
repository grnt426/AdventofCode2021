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

            /**
             * Solution: 6,085,575
             */
            println("Result: " + Day3().calculateLifeSupport("input/day3/input"))
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

    fun calculateLifeSupport(s: String): Long {

        // a Trie DS would be better here, but the perf won't matter with an input list of 1,000 elements
        // so a simple ArrayList is sufficient
        var oxygenCandidates = arrayListOf<CharArray>()
        var co2Candidates = arrayListOf<CharArray>()

        File(s).forEachLine {
            oxygenCandidates.add(it.toCharArray())
            co2Candidates.add(it.toCharArray())
        }

        var index = 0
        while(oxygenCandidates.size > 1 || co2Candidates.size > 1) {
            val oxygenOnes = countMajority(oxygenCandidates, index)
            val co2Ones = countMajority(co2Candidates, index)

            oxygenCandidates = filterCandidates(oxygenCandidates, index, if(oxygenOnes >= 0) '1' else '0')
            co2Candidates = filterCandidates(co2Candidates, index, if(co2Ones >= 0) '0' else '1')

            index++
        }

        return convertBinaryToDecimal(oxygenCandidates[0]) * convertBinaryToDecimal(co2Candidates[0])
    }

    private fun countMajority(candidates: ArrayList<CharArray>, countIndex: Int): Int {
        var ones = 0

        for(c in candidates) {
            if(c[countIndex] == '1') ones++
            else ones--
        }

        return ones
    }

    private fun filterCandidates(candidates: ArrayList<CharArray>, index: Int, criteria: Char): ArrayList<CharArray> {
        val kept = arrayListOf<CharArray>()

        if(candidates.size == 1) return candidates

        for(c in candidates) {
            if(c[index] == criteria)
                kept.add(c)
        }

        return kept
    }

    private fun convertBinaryToDecimal(bits: CharArray): Long {
        bits.reverse()
        var res = 0L
        for(i in bits.indices) {
            val v = if(bits[i] == '1') 1 else 0
            res += 2.0.pow(i.toDouble()).toLong() * v
        }

        return res
    }
}

