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
            println("Part 1 Example Result: ${LanternFish().numericallySimulateFishGrowth("input/day6/example", 80)}")

            /**
             * Solution: 361169
             */
            println("Part 1 Result: ${LanternFish().numericallySimulateFishGrowth("input/day6/input", 80)}")

            /**
             * Solution: 1,634,946,868,992
             */
            println("Part 2 Result: ${LanternFish().numericallySimulateFishGrowth("input/day6/input", 256)}")
        }
    }

    fun simulateFishGrowth(s: String, days: Int): Long {
        val population = PriorityQueue<Int>(if(days == 80) 100_000 else 2_000_000_000)
        var total = 0L

        // get the starting population
        File(s).forEachLine {
            val pop = it.split(",")

            // we don't want to store on what day the fish will populate, not its age until it populates, so
            // we need to add 1 for the initial fish
            pop.forEach{
                p ->
                run {
                    population.add(p.toInt() + 1)
                    total++
                }
            }
        }

        for(d in 1..days) {
            while(d == population.peek()) {
                population.poll()

                // We need to add one more, as fish will not populate until the day *after* their last
                population.add(d + 9)
                population.add(d + 7)
                total++
            }
        }

        return total
    }

    fun numericallySimulateFishGrowth(s: String, days: Int): Long {
        val population = longArrayOf(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L)
        val windowSize = population.size - 1

        // get the starting population
        File(s).forEachLine {
            val pop = it.split(",")

            // now we don't want to add one to the population because we are zero-indexing the for loop
            pop.forEach{
                p -> population[p.toInt()]++
            }
        }

        for(d in 0 until days) {
            population[(d + 7) % windowSize] += population[d % windowSize]
            population[(d + 9) % windowSize] += population[d % windowSize]
            population[d % windowSize] = 0
        }

        return population.sum()
    }
}