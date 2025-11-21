import java.util.Scanner;
import java.util.ArrayList;

public class HashTableDriver {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter string: ");
    String input = sc.nextLine();

    System.out.print("Enter size of substring: ");
    int size = sc.nextInt();

    HashingOne ht = new HashingOne(input, size);

    ht.splitSections();
    ht.isClosestPrime();
    ht.collisionResolution();
    int m = ht.getMaxPositions();
    ArrayList<String> substrings = ht.getSubstrings();
    ArrayList<Integer> resolved = ht.getResolvedIndex();

    System.out.println();
    System.out.println("-----------------------------------------");
    System.out.println("               HASH TABLE                ");
    System.out.println("-----------------------------------------");
    System.out.println("Index     Substring     No. of Occurrences");

    for (int i = 0; i < m; i++) {
      System.out.println("  " + resolved.get(i) + "         " + substrings.get(i) + "              " + ht.noOfOccurrences(substrings.get(i)));
    } 

    System.out.println();
  }
}
  