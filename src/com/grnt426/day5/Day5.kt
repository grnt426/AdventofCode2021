package com.grnt426.day5

import java.io.File

class Day5 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            /**
             * Solution: 5
             */
//            println("Part 1 Example Result: " + Day5().findDangerousOverlap("input/day5/example"))

            /**
             * Solution: 6687
             *
             * Previous guesses: 18916 (too high), 7232 (too high)
             */
            println("Part 1 Result: " + Day5().findDangerousOverlap("input/day5/input"))
        }
    }

    class Coord(startData: List<String>, endData: List<String>) {

        val start = IntArray(2)
        val end = IntArray(2)


        init {
            start[0] = startData[0].toInt()
            start[1] = startData[1].toInt()
            end[0] = endData[0].toInt()
            end[1] = endData[1].toInt()
        }
    }

    private fun findDangerousOverlap(s: String): Int {
        val markedSpots = HashSet<String>()
        val dangerousSpots = HashSet<String>()
        var nonAxial = 0

        File(s).forEachLine {
            val pair = it.split(" -> ")
            val coord = Coord(pair[0].split(","), pair[1].split(","))

            var start = 0
            var end = 0
            var staticAxis = 1
            var doNothing = false


            when {
                coord.start[0] != coord.end[0] && coord.start[1] != coord.end[1] -> {
                    doNothing = true
                    nonAxial++
                }
                coord.start[0] < coord.end[0] -> {
                    start = coord.start[0]
                    end = coord.end[0]
                }
                coord.start[0] > coord.end[0] -> {
                    start = coord.end[0]
                    end = coord.start[0]
                }
                coord.start[1] < coord.end[1] -> {
                    start = coord.start[1]
                    end = coord.end[1]
                    staticAxis = 0
                }
                coord.start[1] > coord.end[1] -> {
                    start = coord.end[1]
                    end = coord.start[1]
                    staticAxis = 0
                }
                else -> {
                    println("ERROR! Something went wrong in processing")
                }
            }

            if(!doNothing) {
                for(i in start..end) {
                    val x = if(staticAxis == 0) coord.start[0] else i
                    val y = if(staticAxis == 1) coord.start[1] else i
                    if(!markedSpots.add("$x,$y")) dangerousSpots.add("$x,$y")
                }
            }
        }

        println("Non-Axial Lines $nonAxial")

        return dangerousSpots.size
    }
}
