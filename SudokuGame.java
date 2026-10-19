public class SudokuGame
{
   public static void main(String[] args)
   {
      SudokuBoard gameBoard = new SudokuBoard("data1.sdk");
      System.out.println(gameBoard);

      System.out.println("Is Valid: " + gameBoard.isValid());
      System.out.println("Is Solved: " + gameBoard.isSolved());
   }
}