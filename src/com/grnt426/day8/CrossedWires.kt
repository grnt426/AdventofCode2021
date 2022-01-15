package com.grnt426.day8

import java.io.File

class CrossedWires {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            /**
             * Solution: 554
             */
            println("Part 1 Result: ${CrossedWires().findSimpleDigits("input/day8/input")}")

            println("Part 2 Result: ${CrossedWires().findAllDigits("input/day8/input")}")
        }
    }

    private fun findAllDigits(file: String): Int {
        var sum = 0

        File(file).forEachLine {
            val digits = arrayOf("", "", "", "", "", "", "", "", "", "", "")
            val data = it.split(" | ")

            deduceDigits(data[0].split(" "), digits)

            val outputVals = data[1].split(" ")
            var mag = 1
            var value = 0
            var digit = 0

            for(o in outputVals) {
                when(o.length) {
                    2 -> digit = 1
                    3 -> digit = 7
                    4 -> digit = 4
                    7 -> digit = 8
                }

                value += digit * mag
                mag *= 10
            }

            sum += value
        }

        return sum
    }

    private fun deduceDigits(uniqueDigits: List<String>, mapping: Array<String>) {
        
    }

    private fun findSimpleDigits(file: String): Int {
        var total = 0

        File(file).forEachLine {
            val data = it.split(" | ")
            val outputVals = data[1].split(" ")

            for(o in outputVals) {
                when(o.length) {
                    2 -> total++
                    3 -> total++
                    4 -> total++
                    7 -> total++
                }
            }
        }

        return total
    }
}