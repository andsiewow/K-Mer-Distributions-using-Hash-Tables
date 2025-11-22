import java.util.Scanner;
import java.util.ArrayList;

public class HashTableDriver {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter DNA String: ");
    String dna = sc.nextLine().trim();

    System.out.print("Enter k (substring size): ");
    int k = sc.nextInt();

    int tableSize = dna.length();   // required by project

    System.out.println("\n-----------------------------------------");
    System.out.println("            HASH TABLE (DIV)             ");
    System.out.println("-----------------------------------------");

    HashTable htDiv = new HashTable(tableSize);
    htDiv.computeDiv(dna, k);
    htDiv.print();

    System.out.println("\n-----------------------------------------");
    System.out.println("            HASH TABLE (MUL)             ");
    System.out.println("-----------------------------------------");

    HashTable htMul = new HashTable(tableSize);
    htMul.computeMul(dna, k);
    htMul.print();
  }
}
  