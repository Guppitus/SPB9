package sklSPBN;

/**
 * PuzzleMove class handles calculating the movement of the
 * blank tile within the puzzle board. The movement methods
 * account for the size of the board being operated on.
 *
 * author: Seth K Lunn
 */
class PuzzleMove
{

    /**
     * moves the blank tile to the right, as long as the
     * blank tile can be moved to the right.
     * @param board The puzzle board being swapped on.
     * @return null: if move is invalid, the new puzzle board if valid,
     *         the new index of swap tile, and the string of move performed.
     */
    public static PuzzleBoard right(PuzzleBoard board)
    {
        if (board.blankTile % board.n < (board.n-1))
        {
            return new PuzzleBoard(board, board.blankTile + 1, "right");
        }
        return null;
    }

    /**
     * moves the blank tile up, as long as the blank tile
     * can be moved up.
     * @param board The puzzle board being swapped on.
     * @return null: if move is invalid, the new puzzle board if valid,
     *         the new index of swap tile, and the string of move performed.
     */
    public static PuzzleBoard up(PuzzleBoard board)
    {
        if (board.blankTile > board.n)
        {
            return new PuzzleBoard(board, board.blankTile - board.n, "up");
        }
        return null;
    }

    /**
     * moves the blank tile to the left, as long as the
     * blank tile can be moved to the left.
     * @param board The puzzle board being swapped on.
     * @return null: if move is invalid, the new puzzle board if valid,
     *         the new index of swap tile, and the string of move performed.
     */
    public static PuzzleBoard left(PuzzleBoard board)
    {
        if (board.blankTile % board.n > 0)
        {
            return new PuzzleBoard(board, board.blankTile - 1,"left");
        }
        return null;
    }

    /**
     * moves the blank tile down, as long as the blank tile
     * can be moved down.
     * @param board the puzzle board being swapped on
     * @return null: if move is invalid, the new puzzle board if valid,
     *         the new index of swap tile, and the string of move performed.
     */
    public static PuzzleBoard down(PuzzleBoard board)
    {
        if (board.blankTile < (board.n*board.n)-board.n)
        {
            return new PuzzleBoard(board, board.blankTile + board.n, "down");
        }
        return null;
    }
}
