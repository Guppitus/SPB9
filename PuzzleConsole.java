package sklSPBN;

import java.util.HashSet;
import java.util.Scanner;

/**
 * PuzzleConsole class scans the console input and returns the input as an array of integers
 * for PuzzleSolve class to implement a new starting puzzle board.
 *
 * author: Seth K Lunn
 */


public class PuzzleConsole {

    /**
     * Checks to make sure input puzzle is a valid slider puzzle.
     * @param puzzleNumbers an array of puzzle numbers.
     * @return True if it is valid, false if not.
     */
    public static boolean isValid(int[] puzzleNumbers)
    {
        if (puzzleNumbers.length == 9)
        {
            // Check if duplicates exist in the input.
            HashSet<Integer> uniqueCheck = new HashSet<>();

            for(int i = 0; i < puzzleNumbers.length; i++)
            {
                if (uniqueCheck.contains(puzzleNumbers[i]) || puzzleNumbers[i] > (puzzleNumbers.length -1))
                {
                    return false;
                }
                uniqueCheck.add(puzzleNumbers[i]);
            }
            return true;
        }
        return false;
    }
    /**
     * Retrieves a user's input from the console
     * and returns the input as an integer array of puzzle numbers.
     * @return array of integers.
     */
    public static int[] readConsole()
    {
        System.out.println("enter puzzle numbers in array format(ex: 123456780):");

        Scanner s = new Scanner(System.in);

        String input = (s.nextLine());

        int[] consolePuzzleNumbers = new int[9];
        for(int i = 0; i < input.length(); i++)
        {
            consolePuzzleNumbers[i] = Integer.parseInt(Character.toString(input.charAt(i)));
        }
        if (!isValid(consolePuzzleNumbers))
        {
            System.out.println("Invalid Puzzle!");
            System.exit(0);
        }
        return consolePuzzleNumbers;
    }

}

