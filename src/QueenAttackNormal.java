import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class QueenAttackNormal {
    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        System.out.println("loading");
        int count = 0;
        HashMap<Integer , HashSet<Integer>> board = createBoard(n, k, r_q, c_q, obstacles);
        System.out.println("start");
        count = transverse(n, r_q - 1, c_q - 1, board);

        return count;
    }

    private static int transverse(int size, int qr, int qc, HashMap<Integer , HashSet<Integer>> board) {

        int top = 0;
        int left = 0;
        int right = 0;
        int bottom = 0;
        int topLeft = 0;
        int topRight = 0;
        int bottomLeft = 0;
        int bottomRight = 0;

        boolean hasPassedRightObstacle = false;
        boolean hasPassedBottomObstacle = false;
        boolean hasPassedBottomLeftObstacle = false;
        boolean hasPassedBottomRightObstacle = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //Row , Column
                if (isTop(i, j, qr, qc)) {
                    top += 1;
                    if (isObstacle(i, j, board)) {
                        top = 0;
                    }
                }
                if (isLeft(i, j, qr, qc)) {
                    left += 1;
                    if (isObstacle(i, j, board)) {
                        left = 0;
                    }
                }
                if (!hasPassedRightObstacle && isRight(i, j, qr, qc)) {
                    right += 1;
                    if (isObstacle(i, j, board)) {
                        right -= 1;
                        hasPassedRightObstacle = true;

                    }
                }

                if (!hasPassedBottomObstacle && isBottom(i, j, qr, qc)) {
                    bottom += 1;
                    if (isObstacle(i, j, board)) {
                        bottom -= 1;
                        hasPassedBottomObstacle = true;
                    }
                }
                //Diagonal

                if (isTopLeft(i, j, qr, qc)) {
                    topLeft += 1;
                    if (isObstacle(i, j, board)) {
                        topLeft = 0;
                    }
                }
                if (isTopRight(i, j, qr, qc)) {
                    topRight += 1;
                    if (isObstacle(i, j, board)) {
                        topRight = 0;
                    }
                }
                if (!hasPassedBottomLeftObstacle && isBottomLeft(i, j, qr, qc)) {
                    bottomLeft += 1;
                    if (isObstacle(i, j, board)) {
                        bottomLeft -= 1;
                        hasPassedBottomLeftObstacle = true;
                    }
                }
                if (!hasPassedBottomRightObstacle && isBottomRight(i, j, qr, qc)) {
                    bottomRight += 1;
                    if (isObstacle(i, j, board)) {
                        bottomRight -= 1;
                        hasPassedBottomRightObstacle = true;
                    }
                }
                if (hasPassedBottomObstacle && hasPassedBottomLeftObstacle && hasPassedBottomRightObstacle) break;

            }
            if (hasPassedBottomObstacle && hasPassedBottomLeftObstacle && hasPassedBottomRightObstacle) break;

        }
        return top + left + right + bottom + topLeft + topRight + bottomLeft + bottomRight;
    }

    private static boolean isObstacle(int i, int j, HashMap<Integer , HashSet<Integer>> board) {
        if (board.containsKey(i) && board.get(i).contains(j)){
            return true;
        }
        return false;
    }

    private static boolean isTop(int i, int j, int qr, int qc) {
        if (i < qr) {
            return j == qc;
        }
        return false;
    }

    private static boolean isLeft(int i, int j, int qr, int qc) {
        if (j < qc) {
            return i == qr;
        }
        return false;
    }

    private static boolean isRight(int i, int j, int qr, int qc) {
        if (j > qc) {
            return i == qr;
        }
        return false;
    }

    private static boolean isBottom(int i, int j, int qr, int qc) {
        if (i > qr) {
            return j == qc;
        }
        return false;
    }

    private static boolean isTopLeft(int i, int j, int qr, int qc) {
        int di = qr - i;
        int dc = qc - j;
        if (i < qr && j < qc) {
            return di == dc;
        }
        return false;
    }

    private static boolean isTopRight(int i, int j, int qr, int qc) {
        int di = qr - i;
        int dc = j - qc;
        if (i < qr && j > qc) {
            return di == dc;
        }
        return false;
    }

    private static boolean isBottomLeft(int i, int j, int qr, int qc) {
        int di = i - qr;
        int dc = qc - j;
        if (i > qr && j < qc) {
            return di == dc;
        }
        return false;
    }

    private static boolean isBottomRight(int i, int j, int qr, int qc) {
        int di = i - qr;
        int dc = j - qc;
        if (i > qr && j > qc) {
            return di == dc;
        }
        return false;
    }

    private static HashMap<Integer , HashSet<Integer>> createBoard(int size, int k, int rq, int cq, List<List<Integer>> obstacles) {
        HashMap<Integer , HashSet<Integer>> obs = new HashMap<>();
        for (int i = 0 ; i < obstacles.size() ; i++) {
            int row = obstacles.get(i).get(0)-1;
            int col = obstacles.get(i).get(1)-1;
            if (obs.containsKey(row)) {
                obs.get(row).add(col);
            }else {
                obs.put(row , new HashSet<>());
                obs.get(row).add(col);
            }
        }
        return obs;
    }
}
