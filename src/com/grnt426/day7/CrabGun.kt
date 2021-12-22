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
            println("Part 1 Example Result: ${CrabGun().alignCrabs("input/day7/example", CrabGun::calculateFuel)}")

            /**
             * Solution: 347,011
             */
            println("Part 1 Result: ${CrabGun().alignCrabs("input/day7/input", CrabGun::calculateFuel)}")

            /**
             * Example Solution: 168
             */
            println("Part 2 Example Result: ${CrabGun().alignCrabs("input/day7/example", CrabGun::calculateExpensiveFuel)}")

            /**
             * Solution: 98,363,777
             *
             * Previous attempts: 109,799,140 (too high)
             */
            println("Part 2 Result: ${CrabGun().alignCrabs("input/day7/input", CrabGun::calculateExpensiveFuel)}")
        }
    }

    fun alignCrabs(s: String, calcFunc: (CrabGun, Int, ArrayList<Int>) -> Int): Int {
        var answer = Int.MAX_VALUE
        val crabs = ArrayList<Int>(1000)

        File(s).forEachLine {
            it.split(",").forEach{
                c -> crabs.add(c.toInt())
            }
        }

        crabs.sort()

        for(i in 0 until crabs.last()) {
            val fuel = calcFunc(this, i, crabs)
            if(fuel < answer) answer = fuel
        }

        return answer
    }

    private fun calculateFuel(convergence: Int, crabs: ArrayList<Int>): Int {
        var fuel = 0
        for(c in crabs)
            fuel += abs(c - convergence)
        return fuel
    }

    private fun calculateExpensiveFuel(convergence: Int, crabs: ArrayList<Int>): Int {
        var fuel = 0
        for(c in crabs) {
            val delta = abs(c - convergence)
            fuel += delta * (delta + 1) / 2
        }

        return fuel
    }
}