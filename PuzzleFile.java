package sklSPBN;


import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

/**
 * PuzzleFile class reads puzzles from a file, placing their integers into an array for use.
 *
 * author Seth K. Lunn
 */
public class PuzzleFile {


    /**
     * Checks to make sure input puzzle is a valid slider puzzle.
     * @param puzzleNumbers an array of puzzle numbers.
     * @return True if it is valid, false if not.
     */
    public static boolean isValid(int[] puzzleNumbers)
    {
        if (puzzleNumbers.length == 9)
        {
            // Check if duplicates exist in the array.
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
     * Reads a file of integers and places integers in an array.
     * @param fileName - The filename.
     */
    public static int[] readFromFile(String fileName)throws IOException
    {

        int puzzleNumbers [] = new int [9];
        int read = 0;

        File file = new File(fileName);

        try
        {
            Scanner input = new Scanner(file);
            while(input.hasNext())
            {
                puzzleNumbers[read] = input.nextInt();
                read++;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        if (!isValid(puzzleNumbers))
        {
            System.out.println("Invalid Puzzle.....");
            System.exit(0);
        }

        return puzzleNumbers;
    }

}
