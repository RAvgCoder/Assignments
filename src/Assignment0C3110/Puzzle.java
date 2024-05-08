package Assignment0C3110;


import java.io.File;
import java.util.*;

enum MovementOffset {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    final int X;
    final int Y;

    MovementOffset(int x, int y) {
        this.X = x;
        this.Y = y;
    }
}

public class Puzzle {
    static final MovementOffset[] movementOffset = new MovementOffset[]{
            MovementOffset.UP, MovementOffset.LEFT,
            MovementOffset.DOWN, MovementOffset.RIGHT
    };
    final static int PUZZLE_SIZE = 3;
    static HashSet<Puzzle> visited_states = new HashSet<>();
    static boolean PRINT = false;
    int hash_code;

    String TO_STRING;
    int[][] grid;
    int[] zero_cord;
    int stack_depth;

    Puzzle(int[][] grid, int[] zero_cord) {
        this.grid = grid;
        this.zero_cord = zero_cord;
        this.stack_depth = 0;
    }

    public static void main(String[] args) {
        ArrayList<Puzzle> puzzles = read_from_file();

        StringBuilder result = new StringBuilder(999999999);

        System.out.println("Puzzle_Size :" + puzzles.size());

        long startTime = System.currentTimeMillis();

        int i = 0;
        for (Puzzle p : puzzles) {
            i++;
            Puzzle origin = new Puzzle(
                    new int[][]{
                            {1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 0},
                    },
                    new int[]{2, 2}
            );

            origin.generate_to_string();
            p.generate_to_string();

            int reachability = check_reachability(origin, p);

            result.append(i)
                    .append(") RESULT: ")
                    .append((reachability == -1) ? "unreachable" : reachability)
                    .append(", ")
                    .append("\n");

//            System.out.println(i+") RESULT: "+((reachability == -1) ? "unreachable" : reachability) + ", ");
            visited_states = new HashSet<>();
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println(result);
        System.out.println("Elapsed time sec:" + elapsedTime / 1000.0);
    }

    private static int check_reachability(Puzzle origin, final Puzzle result) {
        final Queue<Puzzle> states = new LinkedList<>(List.of(origin));

        while (!states.isEmpty()) {
            Puzzle curr = states.remove();

            if (PRINT) {
                System.out.println("------------ Curr Depth:" + curr.stack_depth + " ------------");
                System.out.println(curr);
                System.out.println("------------------------------\n\n");
            }

            if (curr.equals(result)) {
                return curr.stack_depth;
            } else if (visited_states.contains(curr)) {
                continue;
            } else visited_states.add(curr);

            int i = 0;
            for (MovementOffset m_offset : movementOffset) {
                i++;
                int[] new_zero_pos = curr.can_move(m_offset);

                if (new_zero_pos != null) {
                    Puzzle copy = copy(curr, new_zero_pos);

                    // Swap pos
                    copy.grid[curr.zero_cord[0]][curr.zero_cord[1]] =
                            copy.grid[new_zero_pos[0]][new_zero_pos[1]];
                    copy.grid[new_zero_pos[0]][new_zero_pos[1]] = 0;

                    copy.generate_to_string();

                    if (PRINT) {
                        System.out.println("------------ Curr " + i + " Depth:" + copy.stack_depth + "------------");
                        System.out.println(copy);
                        System.out.println("------------------------------\n\n");
                    }

                    states.add(copy);
                }

            }
        }

        return -1;
    }

    private static ArrayList<Puzzle> read_from_file() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("puzzles.txt"))) {

            int[][] grid = new int[PUZZLE_SIZE][PUZZLE_SIZE];
            int[] zero_cord = new int[2];

            while (scanner.hasNextLine()) {
                for (int i = 0; i < PUZZLE_SIZE; i++) {
                    int[] line = Arrays.stream(scanner.nextLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    if (line[0] == 0) zero_cord = new int[]{i, 0};
                    else if (line[1] == 0) zero_cord = new int[]{i, 1};
                    else if (line[2] == 0) zero_cord = new int[]{i, 2};

                    grid[i] = line;
                }

                puzzles.add(new Puzzle(grid, zero_cord));

                // Read blank line
                scanner.nextLine();
                grid = new int[PUZZLE_SIZE][PUZZLE_SIZE];
            }
        } catch (Exception e) {
            System.out.print("Cannot open puzzles.txt");
            e.printStackTrace();
        }

        return puzzles;
    }

    private static Puzzle copy(Puzzle puzzle, int[] new_zero) {
        int[][] copy_grid = new int[][]{
                puzzle.grid[0].clone(),
                puzzle.grid[1].clone(),
                puzzle.grid[2].clone(),
        };

        Puzzle p = new Puzzle(copy_grid, new_zero);
        p.stack_depth = puzzle.stack_depth + 1;
        return p;
    }

    private void generate_to_string() {
        TO_STRING = Arrays.toString(zero_cord) +
                "\n" +
                Arrays.toString(grid[0]) +
                "\n" +
                Arrays.toString(grid[1]) +
                "\n" +
                Arrays.toString(grid[2]) +
                "\n";

        hash_code = TO_STRING.hashCode();
    }

    private int[] can_move(MovementOffset movementOffset) {
        int[] offset = {
                zero_cord[0] + movementOffset.X,
                zero_cord[1] + movementOffset.Y
        };

        // Check if within bounds
        if (offset[0] < 0 || offset[0] > 2  // x
                || offset[1] < 0 || offset[1] > 2) // y
        {
            return null;
        }
        return offset;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Puzzle other) {
//            if (!Arrays.equals(grid[0], other.grid[0])) return false;
//            else if (!Arrays.equals(grid[1], other.grid[1])) return false;
//            else return (Arrays.equals(grid[2], other.grid[2]));

            return this.TO_STRING.equals(other.TO_STRING);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash_code;
    }

    @Override
    public String toString() {
        return TO_STRING;
    }
}
