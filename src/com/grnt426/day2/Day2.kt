package com.grnt426.day2

import com.grnt426.day1.Day1
import java.io.File

class Day2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            /**
             * Solution: 1,698,735
             */
            println(Day2().findMultiplicativePosition("input/day2/input"))
        }
    }

    fun findMultiplicativePosition(s: String): Int {
        var horz = 0
        var depth = 0
        File(s).forEachLine {
            val course = it.split(" ")
            val dir = course[0]
            val mag = course[1].toInt()
            when(dir) {
                "forward" -> horz += mag
                "up" -> depth -= mag
                "down" -> depth += mag
            }
        }

        return horz * depth
    }
}