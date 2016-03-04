package sklSPBN;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * PuzzleSolve class sets up puzzle board and implements an a* search using
 * a priority heap that contains possible board moves to solve a slider puzzle.
 *
 * author: Seth K Lunn
 */


public class PuzzleSolve
{

    /** the starting board position. */
    public PuzzleBoard startingBoard;

    /** the current board position */
    public PuzzleBoard currentBoard;
    /**
     * creates a puzzle board for this puzzle class.
     * @param puzzleNumbers the array of puzzle number values.
     */
    public PuzzleSolve(int[] puzzleNumbers)
    {
        startingBoard = new PuzzleBoard(puzzleNumbers);
        currentBoard = startingBoard;
    }

    /** Priority queue priority heap. */
    public PriorityQueue<PuzzleBoard> pq = new PriorityQueue<>(11, new Comparator<PuzzleBoard>()
    {
        /** compares two boards f(n) */
        public int compare(PuzzleBoard one, PuzzleBoard two)
        {
            return one.f() - two.f();
        }
    });

    /** HashSet holds unique boards already explored. */
    public HashSet<PuzzleBoard> explored = new HashSet<>();

    /**
     * puts new possible board in priority queue (heap).
     * @param nextBoard the next puzzle board.
     */
    private void putInPriorityQueue(PuzzleBoard nextBoard)
    {
        //as long as board move is allowed && new board is unique(not already contained in hash set)
        //add to priority queue.
        if((nextBoard != null) && (!explored.contains(nextBoard))) pq.add(nextBoard);
    }

    /**
     * Solves the starting puzzle by adding boards to the Priority Queue
     * and comparing boards f(n) to find the best path to a solved board.
     */
    public void solveThePuzzle()
    {
        // add starting board to Priority Queue.
        pq.add(startingBoard);

        // while Priority Queue is not empty:
        while(!pq.isEmpty())
        {
            // Retrieve the board with best f(n).
            currentBoard = pq.poll();

            // Check to see if the currentBoard is solved.
            if (currentBoard.isItSolved())
            {
                //use PuzzleBoard toString to print steps and list number of swaps.
                System.out.println(currentBoard.finishedOutput());
                return;
            }

            // Add this currentBoard to the explored HashSet so we don't revisit it.
            explored.add(currentBoard);

            // Expand the frontier by adding moves to the Priority Queue.
            putInPriorityQueue(PuzzleMove.up(currentBoard));
            putInPriorityQueue(PuzzleMove.down(currentBoard));
            putInPriorityQueue(PuzzleMove.left(currentBoard));
            putInPriorityQueue(PuzzleMove.right(currentBoard));
        }
    }

    /**
     * checks to see if puzzle board numbers are solvable.
     * This algorithm was taken from GeeksforGeeks.org and modified to java from c++.
     * @return True if it can be solved, or False if cannot be solved.
     */
    public boolean canBeSolved()
    {
        int inverseCount = 0;
        int[] test = currentBoard.puzzleNumbers;

        for(int i = 0; i < test.length - 1; i++)
        {
            for (int j = i + 1; j < test.length; j++)
            {
                if (test[i] > test[j])
                {
                    inverseCount++;
                }
            }
            if(test[i] == 0 && i % 2 == 1)
            {
                inverseCount++;
            }
        }
        return (inverseCount % 2 == 0);
    }

    public static void main(String[] args) throws IOException {


        PuzzleSolve consolePuzzle;
        PuzzleSolve filePuzzle;
        // Retrieve console input from PuzzleConsole.java
        consolePuzzle = new PuzzleSolve(PuzzleConsole.readConsole());

        //retrieve file input from PuzzleFile.java
        filePuzzle = new PuzzleSolve(PuzzleFile.readFromFile("puzzle.txt"));

        // Is the puzzle solvable?
        if (!consolePuzzle.canBeSolved() || !filePuzzle.canBeSolved())
        {
            System.out.printf("the puzzle board:%s  cannot be solved.", consolePuzzle.currentBoard.toString());
            System.exit(0);
        }
        consolePuzzle.solveThePuzzle();
        filePuzzle.solveThePuzzle();
    }

}
