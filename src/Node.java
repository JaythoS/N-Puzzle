import java.util.ArrayList;
import java.util.List;

public class Node {
    private int[][] board;
    private Node parent;
    private int g;
    private int h;

    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    private String move;

    ManhattanDistance manDis = new ManhattanDistance();

    public Node(int[][] board, int g, int h, String move) {
        this.board = board;
        this.g = g;
        this.h = h; // Hesaplama burada yapılmalı
        this.move = move;
    }
    public List<Node> getNeighbors() {
        List<Node> neighbors = new ArrayList<>();
        int[] zeroPos = findZero();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right
        String[] moves = {"UP", "DOWN", "LEFT", "RIGHT"};

        for (int i = 0; i < 4; i++) {
            int newX = zeroPos[0] + directions[i][0];
            int newY = zeroPos[1] + directions[i][1];

            if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                int[][] newBoard = copyBoard(board);
                newBoard[zeroPos[0]][zeroPos[1]] = newBoard[newX][newY];
                newBoard[newX][newY] = 0;
                int newG = g + 1;
                int newH = manDis.calculateManhattanDistance(newBoard, goal);
                Node neighbor = new Node(newBoard, newG, newH, moves[i]);
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public String getMove() {
        return move;
    }

    private int[] findZero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    public int getF() {
        return g + h;
    }

    public int[][] getBoard() {
        return board;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void printBoard() {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }


}