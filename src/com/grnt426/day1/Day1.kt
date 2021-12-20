package com.grnt426.day1

import java.io.File

/**
 * https://adventofcode.com/2021/day/1
 */
class Day1 {

    companion object {
        @JvmStatic fun main(args: Array<String>) {

            /**
             * Solution: 1583
             */
            println(Day1().simpleIncreasingDelta("input/day1/input"))

            /**
             * Solution: 1627
             */
            println(Day1().tripleMeasurementWindow("input/day1/input"))
        }
    }

    fun simpleIncreasingDelta(fileName: String): Int {
        var prev = -1
        var increases = 0
        File(fileName).forEachLine {
            val n = it.toInt()
            if(prev > -1 && n > prev) increases++
            prev = n
        }

        return increases
    }

    fun tripleMeasurementWindow(fileName: String): Int {
        var increases = 0
        var index = 0
        val window = intArrayOf(0, 0, 0, 0)

        File(fileName).forEachLine {
            val n = it.toInt()
            window[index % 4] = n

            if(index > 0)
                window[(index-1) % 4] += n

            if(index > 1) {
                window[(index - 2) % 4] += n
                if(index > 2) {
                    if(window[(index - 2) % 4] > window[(index - 3) % 4]) increases++
                }
            }

            index++
        }

        return increases
    }
}