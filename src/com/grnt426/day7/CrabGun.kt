package com.grnt426.day7

import java.io.File
import kotlin.math.abs

class CrabGun {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            /**
             * Example Solution: 37
             */
            println("Part 1 Example Result: ${CrabGun().alignCrabs("input/day7/example")}")

            /**
             * Solution: 347,011
             */
            println("Part 1 Result: ${CrabGun().alignCrabs("input/day7/input")}")

            /**
             * Solution:
             */
//            println("Part 2 Result: ${CrabGun().alignCrabs("input/day7/input")}")
        }
    }

    fun alignCrabs(s: String): Int {
        var answer = Int.MAX_VALUE
        val crabs = ArrayList<Int>(1000)

        File(s).forEachLine {
            it.split(",").forEach{
                c -> crabs.add(c.toInt())
            }
        }

        crabs.sort()
        val median = crabs.size / 2

        // check downwards from the median
        var candidate = Int.MAX_VALUE - 1

        var index = median
        while(candidate <= answer) {
            answer = candidate
            candidate = calculateFuel(index, crabs)
            index--
        }

        // this is stupid lazy, but just check upwards
        index = median
        while(candidate <= answer) {
            answer = candidate
            candidate = calculateFuel(index, crabs)
            index++
        }

        return answer
    }

    private fun calculateFuel(index: Int, crabs: ArrayList<Int>): Int {
        var fuel = 0
        val convergence = crabs[index]
        for(c in crabs)
            fuel += abs(c - convergence)
        return fuel
    }
}