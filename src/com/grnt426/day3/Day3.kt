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

        var bitPattern = IntArray(0)

        // a Trie DS would be better here, but the perf won't matter with an input list of 1,000 elements
        // so a simple ArrayList is sufficient
        var oxygenCandidates = arrayListOf<CharArray>()
        var co2Candidates = arrayListOf<CharArray>()

        File(s).forEachLine {

            // keep in order as comparisons are easier later against the original strings
            val bits = it.toCharArray()
            oxygenCandidates.add(it.toCharArray())
            co2Candidates.add(it.toCharArray())

            if(bitPattern.isEmpty())
                bitPattern = IntArray(bits.size)

            // More zeros means a negative number, and more ones means a positive number
            // This will correspond to Gamma. Epsilon is the opposite
            for(i in bits.indices) {
                bitPattern[i] += if(bits[i].digitToInt() == 1) 1 else -1
            }
        }

        for(i in bitPattern.indices) {
            var newOxygenCandidates: ArrayList<CharArray>
            var newCo2Candidates: ArrayList<CharArray>
            if(bitPattern[i] >= 0) {
                newOxygenCandidates = filterCandidates(oxygenCandidates, i, '1')
                newCo2Candidates = filterCandidates(co2Candidates, i, '0')
            }
            else {
                newOxygenCandidates = filterCandidates(oxygenCandidates, i, '0')
                newCo2Candidates = filterCandidates(co2Candidates, i, '1')
            }

            oxygenCandidates = newOxygenCandidates
            co2Candidates = newCo2Candidates

            if(oxygenCandidates.size == 1 && co2Candidates.size == 1) break
        }

        return convertBinaryToDecimal(oxygenCandidates[0]) * convertBinaryToDecimal(co2Candidates[0])
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

