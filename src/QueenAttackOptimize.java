import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class QueenAttackOptimize {
    private static int OBSTACLES = -1;

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {

        int count = 0;
        HashMap<Integer, HashSet<Integer>> board = createBoard(n, k, r_q, c_q, obstacles);

        count = transverse(n, r_q - 1, c_q - 1, board);

        return count;
    }

    private static int transverse(int size, int qr, int qc, HashMap<Integer, HashSet<Integer>> board) {

        int top = 0;
        int left = 0;
        int right = 0;
        int bottom = 0;
        int topLeft = 0;
        int topRight = 0;
        int bottomLeft = 0;
        int bottomRight = 0;


        for (int i = 0; i < qr; i++) {
            //top
            top += 1;
            if (isObstacle(i, qc, board)) {
                top = 0;
            }
        }

        for (int i = qr + 1; i < size; i++) {
            //bottom
            if (qr != size - 1)
                bottom += 1;
            if (isObstacle(i, qc, board)) {
                bottom -= 1;
                break;
            }
        }

        for (int j = 0; j < qc; j++) {
            //left
            left += 1;
            if (isObstacle(qr, j, board)) {
                left = 0;
            }
        }

        for (int j = qc + 1; j < size; j++) {
            //right
            if (qc != size - 1)
                right += 1;
            if (isObstacle(qr, j, board)) {
                right -= 1;
                break;
            }
        }

        int topLeftStartRow = qr - 1;
        for (int i = qc - 1; i >= 0; i--) {
            //top left
            if (topLeftStartRow < 0) break;

            topLeft += 1;
            if (isObstacle(topLeftStartRow, i, board)) {
                topLeft -= 1;
                break;
            }

            topLeftStartRow--;

        }

        int topRightStartRow = qr - 1;
        for (int i = qc + 1; i < size; i++) {
            //top right
            if (topRightStartRow < 0) break;

            topRight += 1;
            if (isObstacle(topRightStartRow, i, board)) {
                topRight -= 1;
                break;
            }

            topRightStartRow--;
        }

        int bottomLeftStartRow = qr + 1;
        for (int i = qc - 1; i >= 0; i--) {
            //bottom left
            if (bottomLeftStartRow >= size) break;

            bottomLeft += 1;
            if (isObstacle(bottomLeftStartRow, i, board)) {
                bottomLeft -= 1;
                break;
            }

            bottomLeftStartRow++;
        }

        int bottomRightStartRow = qr + 1;
        for (int i = qc + 1; i <size; i++) {
            //bottom right
            if (bottomRightStartRow >= size) break;

            bottomRight += 1;
            if (isObstacle(bottomRightStartRow, i, board)) {
                bottomRight -= 1;
                break;
            }

            bottomRightStartRow++;
        }




        return top + left + right + bottom + topLeft + topRight + bottomLeft + bottomRight;
    }

    private static boolean isObstacle(int i, int j, HashMap<Integer, HashSet<Integer>> board) {
        if (board.containsKey(i) && board.get(i).contains(j)) {
            return true;
        }
        return false;
    }


    private static HashMap<Integer, HashSet<Integer>> createBoard(int size, int k, int rq, int cq, List<List<Integer>> obstacles) {
        HashMap<Integer, HashSet<Integer>> obs = new HashMap<>();
        for (int i = 0; i < obstacles.size(); i++) {
            int row = obstacles.get(i).get(0) - 1;
            int col = obstacles.get(i).get(1) - 1;
            if (obs.containsKey(row)) {
                obs.get(row).add(col);
            } else {
                obs.put(row, new HashSet<>());
                obs.get(row).add(col);
            }
        }
        return obs;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);


        int k = Integer.parseInt(firstMultipleInput[1]);


        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                obstacles.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = queensAttack(n, k, r_q, c_q, obstacles);

        System.out.println(result);

    }
}

