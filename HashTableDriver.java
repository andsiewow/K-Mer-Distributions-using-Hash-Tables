import java.util.Scanner;
import java.util.ArrayList;

public class HashTableDriver {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter string: ");
    String input = sc.nextLine();

    System.out.print("Enter size of substring: ");
    int size = sc.nextInt(); //this is input k

    System.out.println();
    System.out.println("-----------------------------------------");
    System.out.println("               HASH TABLE                ");
    System.out.println("-----------------------------------------");
    System.out.println("Index     Substring     No. of Occurrences");

    System.out.println();
  }
}
  