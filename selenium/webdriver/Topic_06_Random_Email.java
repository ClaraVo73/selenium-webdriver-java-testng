package webdriver;


import java.util.Random;

public class Topic_06_Random_Email {
   public static void main(String[] args){
       //untilities
       //Data type: Class/interface / collection/ String/ Float
       Random rand = new Random();
       System.out.print(rand.nextFloat());
       System.out.print(rand.nextDouble());
       System.out.print(rand.nextInt());
       System.out.print(rand.nextInt(999999));
       System.out.print("automation" + rand.nextFloat() + "@gmail.com");
       System.out.print(rand.nextLong());

   }
}
