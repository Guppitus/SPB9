package sklSPBN;

import java.util.Arrays;
import java.util.HashSet;

/**
 * PuzzleBoard class handles the construction and handling
 * of the current slider puzzle board.
 *
 * author: Seth K Lunn
 */


class PuzzleBoard
{

    /** The array of numbers representing the puzzles board.
     *  currently set for 3x3
     */
    public int[] puzzleNumbers = new int[9];

    /** The blank tile position in puzzle that can be moved */
    public int blankTile;

    /**
     * The size of the board (N)by(N)
     */
    public int n;

    /**
     * The
     */
    public String lastMove;

    /** The puzzle board before the current puzzle board. */
    private PuzzleBoard boardBefore;

    /** g in f(n) = g(n) + h(n)
     * Number of moves to goal
     */
    private int g;

    /** h in f(n) = g(n) + h(n)
     * heuristic value calculated using heuristics.java
     */
    private int h;

    /**
     * The puzzle board constructor which holds array of numbers, position of blank tile,
     * the size of the puzzle board, reference to the board before the current puzzle board,
     * number of moves to goal g(n) and heuristic value h(n)
     * @param numbers array of integers in the slider puzzle board.
     */
    public PuzzleBoard(int[] numbers)
    {
        puzzleNumbers = numbers;
        blankTile = findIndex(numbers, 0);

        //cast into int, because sqrt will always return whole number in this instance.
        n = (int)Math.sqrt(numbers.length);

        //default starting board marked as "start"
        lastMove = "Start";

        boardBefore = null;
        g = 0;
        h = PuzzleHeuristics.calcHeuristic(puzzleNumbers, n);
        repOK();
    }

    /**
     * This puzzle board constructor is used to create a new puzzle board based on
     * the board before with a new blank index. Used with PuzzleMove.java
     * @param previous The board before the current puzzle board.
     * @param blankSpace The new blank tile position.
     * @param move the description of move performed as a String.
     */
    public PuzzleBoard(PuzzleBoard previous, int blankSpace, String move)
    {
        puzzleNumbers = Arrays.copyOf(previous.puzzleNumbers, previous.puzzleNumbers.length);

        //reposition blank tile position for new board using
        //blank tile index changed from PuzzleMove.java
        puzzleNumbers[previous.blankTile] = puzzleNumbers[blankSpace];
        puzzleNumbers[blankSpace] = 0;
        blankTile = blankSpace;

        n = (int)Math.sqrt(previous.puzzleNumbers.length);
        lastMove = move;
        boardBefore = previous;
        g = previous.g + 1;
        h = PuzzleHeuristics.calcHeuristic(puzzleNumbers, n);
        repOK();
    }

    /**
     * This method returns the g(n) of f(n) = g(n) + h(n).
     * Its the number of steps taken.
     * @return int: g(n) of puzzle board.
     */
    public int g()
    {
        return g;
    }

    /**
     * This method returns the h(n) of f(n) = g(n) + h(n).
     * Its the heuristic value calculated from heuristics.java
     * @return int: h(n) of puzzle board.
     */
    public int h()
    {
        return h;
    }

    /**
     * The f(n) or total cost to get to current board.
     * achieved by g(n) + h(n)
     * @return int: The cost to get to the current board.
     */
    public int f()
    {
        return g() + h();
    }

    /**
     * finds the index of a specified value from the array of numbers.
     * @param numbers A puzzle board puzzleNumbers.
     * @param value The value in the puzzleNumbers to retrieve the index for.
     * @return int: The index of the tile being searched for.
     */
    public static int findIndex(int[] numbers, int value)
    {
        for (int i = 0; i < numbers.length; i++)
        {
            if (numbers[i] == value) return i;
        }
        return -1;
    }

    /**
     * checks if board is solved.
     * @return true if solved, false if not.
     */
    public boolean isItSolved()
    {
        int[] board = puzzleNumbers;
        for (int i = 1; i < board.length - 1; i++)
        {
            if (board[i - 1] > board[i])
            {
                return false;
            }
        }
        return (board[0] == 1);
    }

    /**
     * creates a string of all the swapped boards.
     * @return String: The puzzle board steps.
     */
    public String showSwaps()
    {
        StringBuilder output = new StringBuilder();

        // add boardBefore swap step until starting board.
        if (boardBefore != null)
        {
            output.append(boardBefore.showSwaps());
        }
        output.append(this.toString());
        return output.toString();
    }

    /**
     * creates a string of steps and number of swaps to solved board.
     * @return String: the output string.
     */
    public String finishedOutput()
    {
        String output = "\n";
        output = output + (this.showSwaps());

        output = output + ("\n\nTakes " + g + " swaps to solve this puzzle.\n");
        return output;
    }

    /**
     * creates a string of the puzzle board and
     * includes the move to get to current board.
     * @return String: the puzzle board.
     */
    public String toString() {
        int[] board = puzzleNumbers;
        String output = "\n";
        if(this.lastMove.equals("Start"))
        {
            output = output + "\nStarting Board.";
        }
        else
        {
            output = output + "\nSwap " + this.lastMove + ".";
        }
        for(int i = 0; i < board.length; i++)
        {
            if ( i % this.n == 0 )
            {
                output = output + "\n";
            }
            output = output + " " + String.format("%d", board[i]);
        }
        return output;
    }

    /**
     * check representation
     */
    private void repOK()
    {

        assert (blankTile >= 0 && blankTile <= (n*n)-1);

        assert (lastMove == "start" || lastMove == "up"
                || lastMove == "down" || lastMove == "left"
                || lastMove == "right");

        assert (n == 2 || n == 3);
        assert (g >= 0);
        assert (g <= 50);
        assert (h >= 0);
        assert (h <= 72);

    }

}