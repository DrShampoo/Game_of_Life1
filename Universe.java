package life;

import java.util.Random;

public class Universe {
    public char[][] map;

   public void setUniverse(int n) {
       map = new char[n][n];
       Random random = new Random();
       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               if (random.nextBoolean()) {
                   map[i][j] = 'O';
               } else {
                   map[i][j] = ' ';
               }
           }
       }
   }

   public int alive(){
       int count = 0;
       for (char[] chars : map) {
           for (int j = 0; j < map[0].length; j++) {
               if (chars[j] == 'O') {
                   count++;
               }
           }
       }
       return count;
   }

   public void printMatrix(int generate) {
       System.out.println("Generation: " + generate);
       System.out.println("Alive: " + alive());
       for (char[] chars : map) {
           for (int j = 0; j < map[0].length; j++) {
               System.out.print(chars[j]);
           }
           System.out.println();
       }
   }
}
