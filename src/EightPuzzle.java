
import java.util.Arrays;
// A program that partially implements the 8 puzzle.
public class EightPuzzle {
   // The main method is the entry point where the program starts execution.
   public static void main(String[] args) {
      // Create a board instance and add it to the frame

      // Make the frame visible
      // StdDraw setup
      // -----------------------------------------------------------------------
      // set the size of the canvas (the drawing area) in pixels
      StdDraw.setCanvasSize(500, 500);
      // set the range of both x and y values for the drawing canvas
      StdDraw.setScale(0.5, 3.5);
      // enable double buffering to animate moving the tiles on the board
      StdDraw.enableDoubleBuffering();

      Board board = new Board();
      ManhattanDistance manDis = new ManhattanDistance();

      int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
      int[][] initial = board.getNumbers();

      Node node = new Node(initial, 0, manDis.calculateManhattanDistance(initial, goal), "");

      Puzzle puzzle = new Puzzle(initial);

      drawBoard(board);

      Thread solveThread = new Thread(() -> {
         puzzle.solve();
         String[] solutionMoves = puzzle.getAllMovesInSolution();
         executeMoves(board, solutionMoves);

      });
      solveThread.start();

   }

   private static void drawBoard(Board board) {
      board.draw();
      StdDraw.show();
      StdDraw.pause(100); // 100 ms
   }

   static void executeMoves(Board board, String[] moveList) {
      for (String move : moveList) {
         switch (move) {
            case "RIGHT":
               board.moveRight();
               StdDraw.pause(50); // 50 ms
               break;
            case "LEFT":
               board.moveLeft();
               StdDraw.pause(50); // 50 ms
               break;
            case "UP":
               board.moveUp();
               StdDraw.pause(50); // 50 ms
               break;
            case "DOWN":
               board.moveDown();
               StdDraw.pause(50); // 50 ms
               break;
            default:
               System.out.println("Invalid move");
         }
         drawBoard(board);
      }
   }

}



