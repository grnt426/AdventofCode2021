package com.grnt426.day6

import java.io.File
import java.util.*

class LanternFish {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            /**
             * Example Solution: 5934
             */
            println("Part 1 Example Result: ${LanternFish().simulateFishGrowth("input/day6/example")}")

            /**
             * Example Solution: 361169
             */
            println("Part 1 Result: ${LanternFish().simulateFishGrowth("input/day6/input")}")
        }
    }

    fun simulateFishGrowth(s: String): Int {
        val population = PriorityQueue<Int>(100_000)

        // get the starting population
        File(s).forEachLine {
            val pop = it.split(",")

            // we don't want to store on what day the fish will populate, not its age until it populates, so
            // we need to add 1 for the initial fish
            pop.forEach{p -> population.add(p.toInt() + 1)}
        }

        for(d in 1..80) {
            while(d == population.peek()) {
                population.poll()

                // We need to add one more, as fish will not populate until the day *after* their last
                population.add(d + 9)
                population.add(d + 7)
            }
        }

        return population.size
    }
}