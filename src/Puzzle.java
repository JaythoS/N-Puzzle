import java.util.*;

public class Puzzle {
    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private PriorityQueue<Node> openList;
    private HashSet<Node> closedList;
    private Node initialNode;

    ManhattanDistance manDis = new ManhattanDistance();



    public Puzzle(int[][] initial) {
        this.initialNode = new Node(initial, 0, manDis.calculateManhattanDistance(initial, goal), "");
        this.openList = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        this.closedList = new HashSet<>();
    }

    public void solve() {
        boolean solvability = this.isSolvable(initialNode.getBoard());
        if (solvability) {
            openList.add(initialNode);
            while (!openList.isEmpty()) {
                Node currentNode = openList.poll();
                closedList.add(currentNode);

                if (Arrays.deepEquals(currentNode.getBoard(), goal)) {
                    printSolution(currentNode);
                    return;
                }

                for (Node neighbor : currentNode.getNeighbors()) {
                    if (!closedList.contains(neighbor) && !openList.contains(neighbor)) {
                        neighbor.setParent(currentNode); // Ebeveyni ayarla
                        openList.add(neighbor);
                    }
                }
            }
        } else {
            System.out.println("Cannot solvable!");
        }
    }

    public String[] getAllMovesInSolution() {
        Stack<Node> path = new Stack<>();
        List<String> allMoves = new ArrayList<>();

        // Çözüm yolunu yığına ekleyin
        Node currentNode = closedList.stream().filter(node -> Arrays.deepEquals(node.getBoard(), goal)).findFirst().orElse(null);
        while (currentNode != null) {
            path.push(currentNode);
            currentNode = currentNode.getParent();
        }

        while (!path.isEmpty()) {
            Node node = path.pop();
            if (node.getMove() != null) {
                allMoves.add(node.getMove());
            }
        }

        if (!allMoves.isEmpty()) {
            allMoves.remove(0);
        }

        return allMoves.toArray(new String[0]);
    }

    private void printSolution(Node solution) {
        Stack<Node> path = new Stack<>();

        // Yolun tüm düğümlerini bir yığına ekleyin
        while (solution != null) {
            path.push(solution);
            solution = solution.getParent();
        }

        // Yolu yazdırın ve taşınan adımları gösterin
        while (!path.isEmpty()) {
            Node node = path.pop();
            node.printBoard();
            if (node.getParent() != null) {
                System.out.println("Move: " + node.getMove());
                System.out.println();
            }
        }
    }


    public boolean isSolvable(int[][] board) {
        int total = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int count = 0;
                for (int k = i; k < 3; k++) {
                    for (int l = (k == i ? j + 1 : 0); l < 3; l++) {
                        if (board[i][j] != 0 && board[k][l] != 0 && board[i][j] > board[k][l]) {
                            count++;
                        }
                    }
                }
                total += count;
            }
        }

        // total değerini 2'ye böldükten sonra kalanı kontrol ederek çift ise true, tek ise false döndürüyoruz
        return total % 2 == 0;
    }


}









