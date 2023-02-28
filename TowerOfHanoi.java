public class TowerOfHanoi
{
    static int[][] towers;
    static int disks;
    static int step = 1;
    public static void main(String args[])
    {
        disks = 4;
        towers = new int[3][disks];
        setup();
        print();
        solve(disks, 0, 2, 1);
    }

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
    
    public static void print()
    {
        for(int i = disks; i >= 0; i--)
        {
            if(i == disks)
            {
                System.out.println("   |       |       |");
            }
            else
            {
                for(int j = 0; j < 3; j++)
                {
                    switch(towers[j][i])
                    {
                        case 0: System.out.print("   |    ");
                                break;
                        case 1: System.out.print("   -    ");
                                break;
                        case 2: System.out.print("  ---   ");
                                break;
                        case 3: System.out.print(" -----  ");
                                break;
                        case 4: System.out.print("------- ");
                                break;
                    }
                }
                System.out.println();
            }
        }
    }
}