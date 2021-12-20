package com.grnt426.day2

import java.io.File

class Day2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            /**
             * Solution: 1,698,735
             */
            println(Day2().findMultiplicativePosition("input/day2/input"))

            /**
             * Solution: 1,594,785,890
             */
            println(Day2().findPositionWithAim("input/day2/input"))
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

    fun findPositionWithAim(s: String): Int {
        var horz = 0
        var aim = 0
        var depth = 0
        File(s).forEachLine {
            val course = it.split(" ")
            val dir = course[0]
            val mag = course[1].toInt()
            when(dir) {
                "forward" -> {
                    horz += mag
                    depth += aim * mag
                }
                "up" -> aim -= mag
                "down" -> aim += mag
            }
        }

        return horz * depth
    }
}