package Assignment0C3110;


import java.io.*;
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
    final static int PUZZLE_SIZE = 3;

    // ----------- Instance var ----------- \\
    // Normal
    private final int[] zero_cord; // Loc of zero
    private final int[][] grid;
    // Caching purposes
    private final int hash_code;
    private final String TO_STRING;
    private int num_of_moves_taken; // num of moves taken to reach this state
    // ----------- Instance var END ----------- \\

    private Puzzle(int[][] grid, int[] zero_cord) {
        this.grid = grid;
        this.zero_cord = zero_cord;
        this.num_of_moves_taken = 0;
        this.TO_STRING = Arrays.toString(zero_cord) +
                "\n" +
                Arrays.toString(grid[0]) +
                "\n" +
                Arrays.toString(grid[1]) +
                "\n" +
                Arrays.toString(grid[2]) +
                "\n";

        this.hash_code = TO_STRING.hashCode();
    }

    public static void main(String[] args) {
        ArrayList<Puzzle> puzzles = read_input(false);

        // Original Configuration
        Puzzle original_configuration = new Puzzle(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0},
                },
                new int[]{2, 2}
        );

        HashMap<Puzzle, Integer> reachable_states =
                generate_reachable_configurations(original_configuration);

        write_to_file(puzzles, reachable_states);
    }

    private static HashMap<Puzzle, Integer> generate_reachable_configurations(Puzzle original_configuration) {
        final Queue<Puzzle> configuration_queue = new LinkedList<>(List.of(original_configuration));
        final MovementOffset[] movement_offset = new MovementOffset[]{
                MovementOffset.UP, MovementOffset.LEFT,
                MovementOffset.DOWN, MovementOffset.RIGHT
        };

        // Stores a configuration and num of steps taken to get there
        final HashMap<Puzzle, Integer> visited_configurations = new HashMap<>();

        while (!configuration_queue.isEmpty()) {
            Puzzle curr = configuration_queue.remove();

            // Check for repetitions to old configurations
            if (visited_configurations.containsKey(curr)) {
                continue;
            } else visited_configurations.put(curr, curr.num_of_moves_taken);

            // Move to all 4 possible offsets
            for (MovementOffset m_offset : movement_offset) {
                // If an offset is possible - not out of bounds, then create one
                curr.can_move(m_offset)
                        .ifPresent(new_zero_pos -> configuration_queue.add(copy(curr, new_zero_pos)));
            }
        }

        return visited_configurations;
    }


    private static void write_to_file(ArrayList<Puzzle> puzzles, HashMap<Puzzle, Integer> reachable_states) {
        String filename = "prog_output.txt";
        try {
            // Create a FileWriter with the specified file path
            // Redirect System.out to the FileWriter
            PrintWriter printWriter = new PrintWriter(new FileWriter(filename));

            for (Puzzle p : puzzles) {
                Integer count = reachable_states.get(p);
                String output = String.valueOf((count == null) ? "unreachable" : count);
                System.out.println(output);
                printWriter.println(output);
            }

            // Close the writer
            printWriter.close();
        } catch (IOException e) {
            System.err.println("Error" + e);
        }
    }

    private static ArrayList<Puzzle> read_input(boolean read_from_file) {
        ArrayList<Puzzle> puzzles = new ArrayList<>();

        Scanner scanner;

        if (read_from_file) {
            try {
                scanner = new Scanner(new File("puzzles.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("Can not open file puzzles.txt");
                throw new RuntimeException(e);
            }
        } else {
            scanner = new Scanner(System.in);
        }

        int[][] grid = new int[PUZZLE_SIZE][PUZZLE_SIZE];
        int[] zero_cord = new int[2];

        for (int j = 0; j < 1000; j++) {
            for (int i = 0; i < PUZZLE_SIZE; i++) {
                int[] line = Arrays.stream(scanner.nextLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                // Find zero positions
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

        return puzzles;
    }

    private static Puzzle copy(Puzzle curr, int[] new_zero_pos) {
        int[][] copy_grid = new int[][]{
                curr.grid[0].clone(),
                curr.grid[1].clone(),
                curr.grid[2].clone(),
        };

        // Swap zero positions
        copy_grid[curr.zero_cord[0]][curr.zero_cord[1]] =
                copy_grid[new_zero_pos[0]][new_zero_pos[1]];
        copy_grid[new_zero_pos[0]][new_zero_pos[1]] = 0;


        Puzzle p = new Puzzle(copy_grid, new_zero_pos);
        p.num_of_moves_taken = curr.num_of_moves_taken + 1;
        return p;
    }


    /**
     * Checks if the puzzle can move the zero_cord to a given offset
     *
     * @param movementOffset Offset given
     * @return New offset if one exists
     */
    private Optional<int[]> can_move(MovementOffset movementOffset) {
        int[] offset = {
                zero_cord[0] + movementOffset.X,
                zero_cord[1] + movementOffset.Y
        };

        // Check if within bounds
        if (offset[0] < 0 || offset[0] > 2  // x
                || offset[1] < 0 || offset[1] > 2) // y
        {
            return Optional.empty();
        } else return Optional.of(offset);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Puzzle other) {
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
