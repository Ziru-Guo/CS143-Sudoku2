import java.util.*;
import java.io.*;

public class MySudokuBoard
{
   private int[][] board; // 0 for empty spaces

   public MySudokuBoard(String fileName)
   {
      board = new int[9][9];
      loadFile(fileName);
   }

   private void loadFile(String fileName)
   {
      try
      {
         Scanner input = new Scanner(new File(fileName));
         for (int r = 0; r < board.length; r++)
         {
            String line = input.nextLine();
            for (int c = 0; c < board[r].length; c++)
            {
               char ch = line.charAt(c);
               int spot = 0;
               if (ch == '.')
               {
                  spot = 0;
               }
               else if (ch >= '1' && ch <= '9')
               {
                  spot = ch - '0';
               }
               else
               {
                  spot = -1;
               }
               board[r][c] = spot;
            }
         }
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Error: File not found");
      }
   }

   private boolean isValidRow(int r)
   {
      Set<Integer> seen = new HashSet<>();
      for (int c = 0; c < 9; c++)
      {
         int val = board[r][c];
         if (val < 0 || val > 9)
         {
            return false;
         }
         if (val != 0)
         {
            if (!seen.add(val))
            {
               return false;
            }
         }
      }
      return true;
   }

   private boolean isValidCol(int c)
   {
      Set<Integer> seen = new HashSet<>();
      for (int r = 0; r < 9; r++)
      {
         int val = board[r][c];
         if (val < 0 || val > 9)
         {
            return false;
         }
         if (val != 0)
         {
            if (!seen.add(val))
            {
               return false;
            }
         }
      }
      return true;
   }

   private int[][] getMiniSquare(int spot)
   {
      int[][] mini = new int[3][3];
      int startRow = ((spot - 1) / 3) * 3;
      int startCol = ((spot - 1) % 3) * 3;

      for (int r = 0; r < 3; r++)
      {
         for (int c = 0; c < 3; c++)
         {
            mini[r][c] = board[startRow + r][startCol + c];
         }
      }
      return mini;
   }

   private boolean isValidMiniSquare(int spot)
   {
      int[][] mini = getMiniSquare(spot);
      Set<Integer> seen = new HashSet<>();

      for (int r = 0; r < 3; r++)
      {
         for (int c = 0; c < 3; c++)
         {
            int val = mini[r][c];
            if (val < 0 || val > 9)
            {
               return false;
            }
            if (val != 0)
            {
               if (!seen.add(val))
               {
                  return false;
               }
            }
         }
      }
      return true;
   }

   public boolean isValid()
   {
      for (int r = 0; r < 9; r++)
      {
         if (!isValidRow(r))
         {
            return false;
         }
      }

      for (int c = 0; c < 9; c++)
      {
         if (!isValidCol(c))
         {
            return false;
         }
      }

      for (int box = 1; box <= 9; box++)
      {
         if (!isValidMiniSquare(box))
         {
            return false;
         }
      }

      return true;
   }

   public boolean isSolved()
   {
      if (!isValid())
      {
         return false;
      }

      Map<Integer, Integer> counts = new HashMap<>();
      for (int r = 0; r < 9; r++)
      {
         for (int c = 0; c < 9; c++)
         {
            int val = board[r][c];
            if (val == 0)
            {
               return false;
            }
            counts.put(val, counts.getOrDefault(val, 0) + 1);
         }
      }

      for (int i = 1; i <= 9; i++)
      {
         if (counts.getOrDefault(i, 0) != 9)
         {
            return false;
         }
      }

      return true;
   }

   public String toString()
   {
      String result = "";
      for (int r = 0; r < board.length; r++)
      {
         for (int c = 0; c < board[r].length; c++)
         {
            if (c == 0)
            {
               result += " ";
            }

            if (board[r][c] == 0)
            {
               result += "-";
            }
            else
            {
               result += board[r][c];
            }

            if (c < board[r].length - 1)
            {
               if ((c + 1) % 3 == 0)
               {
                  result += " | ";
               }
               else
               {
                  result += "   ";
               }
            }
         }
         result += "\n";

         if (r < board.length - 1)
         {
            if ((r + 1) % 3 == 0)
            {
               result += "–––––––––––|–––––––––––|––––––––––\n";
            }
         }
      }
      return result;
   }
}