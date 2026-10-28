
public class SudokuSolverEngine {

   public static void main(String[] args) {
      // Here I have called my class `MySudokuBoard` if you named your class
      // differently, modify the line below to use your own class name
      MySudokuBoard board = new MySudokuBoard("boards/valid-complete.sdk");
      System.out.println("Initial board");
      System.out.println(board);
      System.out.println();
      
      if (!board.isValid()){
         System.out.println("The board cannot be solved.");
      } else if (board.isSolved()) {
         System.out.println("The board is already solved.");
      } else {
         long startTime = System.currentTimeMillis();
         if (board.solve()) {
            System.out.println("Solving board... SOLVED!");
            System.out.println(board);
         } else {
            System.out.println("No solution exists.");
         }
      }

      System.out.print("Solving board...");
      long start = System.currentTimeMillis();
      board.solve();
      long stop = System.currentTimeMillis();
      System.out.printf("SOLVED in %.3f seconds.\n", ((stop-start)/1000.0));
      System.out.println();
      System.out.println(board);
      
   }
}
