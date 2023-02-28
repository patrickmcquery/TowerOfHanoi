/*
 * Recursive Tower of Hanoi solver for any number of disks.
 * Patrick McQuery
 */
import java.util.Scanner;

public class TowerOfHanoi
{
    static int[][] towers;
    static int disks;
    static int step = 1;
    public static void main(String args[])
    {
        System.out.println("How many disks would you like to solve for? (I would recommend between 1 and 12)");
        Scanner console = new Scanner(System.in);
        disks = console.nextInt();
        towers = new int[3][disks];
        setup();
        print();
        solve(disks, 0, 2, 1);
    }
    /*
     * The starting value of "n" is the number of disks, "from" is the tower that the disk is on currently,
     * "to" is the tower that it will be moved to, and "temp" is the other tower that will not be used for the 
     * current placement but is needed for subsequent moves.
     */
    public static void solve(int n, int from, int to, int temp)
    {
        if (n == 0) 
        {
            return;
        }
        solve(n - 1, from, temp, to);
        place(n, to);
        System.out.println("Step " + step);
        step++;
        print();
        solve(n - 1, temp, to, from);
    }
    /*
     * Place searches for the specified disk (n) and places it on the "to" tower
     */
    public static void place(int n, int to)
    {
        for(int i = disks - 1; i >= 0; i--)
        {
            for(int j = 0; j < 3; j++)
            {
                if(towers[j][i] == n)
                {
                    for(int y = 0; y < disks; y++)
                    {
                        if(towers[to][y] == 0)
                        {
                            towers[to][y] = towers[j][i];
                            towers[j][i] = 0;
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void setup()
    {
        for(int i = 0; i < disks; i++)
        {
            towers[0][i] = disks - i;
        }
    }

    /*
     * I added an ASCII printout to help visually represent what is happening.
     * I use a total of 3 methods to print each individual part to help break
     * the problem up into smaller chunks.
     */
    public static void print()
    {
        for(int i = disks; i >= 0; i--)
        {
            printLayer(i);
        }
    }
    public static void printLayer(int layer)
    {
        if(layer == disks)
        {
            String segment = "";
            for(int i = 1; i <= 2 * disks - 1; i++)
            {
                if(i == ((2 * disks - 1) / 2) + 1)
                {
                    segment += "|";
                }
                else
                {
                    segment += " ";
                }
            }
            System.out.print(segment + " " + segment + " " + segment);
        }
        else
        {
            for(int i = 0; i < 3; i++)
            {
                printSegment(layer, i);
            }
        }
        System.out.println();
    }
    public static void printSegment(int layer, int tower)
    {
        String segment = "";
        int lengthOfSegment = 2 * disks - 1;
        if(towers[tower][layer] == 0)
        {
            for(int i = 1; i <= lengthOfSegment; i++)
            {
                if(i == (lengthOfSegment / 2) + 1)
                {
                    segment += "|";
                }
                else
                {
                    segment += " ";
                }
            }
        }
        else
        {
            int lengthOfDisk = (2 * towers[tower][layer]) - 1;
            int start = (lengthOfSegment / 2 + 1) - (lengthOfDisk / 2);
            for(int i = 1; i <= lengthOfSegment; i++)
            {
                if(i >= start && i < start + lengthOfDisk)
                {
                    segment += "-";
                }
                else
                {
                    segment += " ";
                }
            }
        }
        
        System.out.print(segment + " ");
    }
}