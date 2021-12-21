package com.grnt426.day4

import java.io.File

class Day4 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            /**
             * Solution: 32,844 (board id 73)
             */
            println("Result: " + Day4().determineWinner("input/day4/input"))
        }
    }

    private class Board(val id: Int) {

        private val board = ArrayList<ArrayList<Int>>(0)

        fun checkWin(num: Int): Int {

            var r = 0
            var c = 0
            found@for(row in board.indices) {
                for(col in board[row].indices) {
                    if(board[row][col] == num) {
                        board[row][col] = -1
                        r = row
                        c = col
                        break@found
                    }
                }
            }

            var hSum = 0
            var vSum = 0
            for(i in 4 downTo 0) {
                vSum += board[i][c]
                hSum += board[r][i]
            }

            var total = -1

            // A winner will have no sum on one axis
            if(vSum == -5 || hSum == -5) {
                println("Winning board $id")
                total = board.sumOf { it.sumOf { n -> if(n >= 0) n else 0 } }
            }

            return total * num
        }

        fun addRowToBoard(input: String, boards: HashMap<Int, ArrayList<Board>>) {
            val row = ArrayList<Int>(0)
            for(i in input.split(" ")) {
                if(i == "")
                    continue
                row.add(i.toInt())
                val others = boards.getOrDefault(i.toInt(), arrayListOf())
                others.add(this)
                boards.putIfAbsent(i.toInt(), others)
            }

            board.add(row)
        }
    }

    private fun determineWinner(s: String): Int {
        val boards = HashMap<Int, ArrayList<Board>>()
        val drawnNumbers = ArrayList<String>()
        var currentBoard: Board? = null
        var boardCount = 0

        File(s).forEachLine {
           if(drawnNumbers.isEmpty())
               drawnNumbers.addAll(it.split(","))
           else if(it.isEmpty()){
               currentBoard = Board(boardCount)
               boardCount++
           } else {
               currentBoard?.addRowToBoard(it, boards)
           }
        }

        var winningBoard = -1
        var toDraw = 0
        while(winningBoard < 0 && toDraw < drawnNumbers.size) {
            println("${drawnNumbers[toDraw]} has been drawn!")
            val num = drawnNumbers[toDraw].toInt()
            val candidates = boards.getOrDefault(num, emptyList())

            for(c in candidates) {
                winningBoard = c.checkWin(num)
                if(winningBoard >= 0) break
            }

            toDraw++
        }

        return winningBoard
    }
}