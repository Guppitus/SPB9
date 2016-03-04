package sklSPBN;

/**
 * PuzzleHeuristics class handles the calculation of h(n) for the purpose of
 * solving the slider puzzle through the implementation of a*.
 * f(n) = g(n) + h(n)
 *
 * author: Seth K Lunn
 */
public class PuzzleHeuristics
{
    /**
     * sums the total positions each tile is away
     * from there solved position.
     * @param n the size of a board NxN.
     * @param position the position of the tile.
     * @param value the value of the tile.
     * @return int: the sum'd total distance between the tiles and where they belong.
     */
    public static int taxiCab(int n, int position, int value)
    {
        //default no heuristic
        int h = 0;
        h = h + Math.abs((position/n)-((value-1)/n));
        h = h + Math.abs((position%n)-((value-1)%n));
        return h;
    }

    /**
     * calculates the heuristic for f(n)=g(n)+h(n)
     * @param puzzleNumbers A puzzle board's puzzle numbers.
     * @param n the size of a board NxN
     * @return int: The heuristic for the current puzzle(taxicab atm).
     */
    public static int calcHeuristic(int[] puzzleNumbers, int n)
    {
        int heuristic = 0;

        for(int i = 0; i < puzzleNumbers.length; i++)
        {
            //ignore blank tile space
            if (puzzleNumbers[i] != 0)
            {
                heuristic = heuristic + taxiCab(n, i, puzzleNumbers[i]);
            }
        }
        return heuristic;
    }
}
