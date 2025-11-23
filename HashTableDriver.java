import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HashTableDriver {
  public static void main(String[] args) {

    int sampleSize = 5;
    int stringLength = 16;
    int k = 3;

    String fileName = stringLength + "Results.txt";

    try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {

      // ==========================
      //  GENERATE MASTER STRINGS
      // ==========================
      String[] strInputs = new String[sampleSize];
      for (int i = 0; i < sampleSize; i++) {
        strInputs[i] = DNAGenerator.generateDNA(stringLength);
      }

      // --- Multiplication Hash ---
      HashTable[] mulTables = new HashTable[sampleSize];
      long[] mulInstanceTimes = new long[sampleSize];

      for (int i = 0; i < sampleSize; i++) {
        mulTables[i] = new HashTable(stringLength);

        long start = System.nanoTime();
        mulTables[i].computeMul(strInputs[i], k);
        long end = System.nanoTime();

        mulInstanceTimes[i] = end - start;
        System.out.println("Mul Instance [" + (i + 1) + "] time: " + mulInstanceTimes[i] + "ns");
      }

      // --- Division Hash ---
      HashTable[] divTables = new HashTable[sampleSize];
      long[] divInstanceTimes = new long[sampleSize];

      for (int i = 0; i < sampleSize; i++) {
        divTables[i] = new HashTable(stringLength);

        long start = System.nanoTime();
        divTables[i].computeDiv(strInputs[i], k);
        long end = System.nanoTime();

        divInstanceTimes[i] = end - start;
        System.out.println("Div Instance [" + (i + 1) + "] time: " + divInstanceTimes[i] + "ns");
      }

      // ==========================
      //  AVERAGE TIME + COLLISIONS
      // ==========================
      long avgMul = 0;
      long avgDiv = 0;
      long avgCollisionsMul = 0;
      long avgCollisionsDiv = 0;

      for (int i = 0; i < sampleSize; i++) {
        avgMul += mulInstanceTimes[i];
        avgDiv += divInstanceTimes[i];
        avgCollisionsMul += mulTables[i].getCollisionCounter();
        avgCollisionsDiv += divTables[i].getCollisionCounter();
      }

      double avgMulTime = (double) avgMul / sampleSize;
      double avgDivTime = (double) avgDiv / sampleSize;

      double avgColMul = (double) avgCollisionsMul / sampleSize;
      double avgColDiv = (double) avgCollisionsDiv / sampleSize;

      System.out.println("\n===== AVERAGE RESULTS =====");
      System.out.println("Multiplication Hash avg time: " + String.format("%.3f", avgMulTime) + " ns");
      System.out.println("Division Hash avg time: " + String.format("%.3f", avgDivTime) + " ns");
      System.out.println("[MULTIPLICATION] Avg collisions: " + String.format("%.3f", avgColMul));
      System.out.println("[DIVISION] Avg collisions: " + String.format("%.3f", avgColDiv));


      // ==========================================
      // WRITE EVERYTHING TO THE OUTPUT FILE
      // ==========================================

      out.println("MasterStrings");
      for (int i = 0; i < sampleSize; i++) {
        out.println("[" + (i + 1) + "] " + strInputs[i]);
      }

      out.println("\n========================================\n");

      // Instance tables - Multiplication
      out.println("Instance tables (Multiplication Hash)");
      for (int i = 0; i < sampleSize; i++) {
        out.println("Instance [" + (i + 1) + "] /");
        printTable(mulTables[i], out);
        out.println();
      }

      out.println("========================================\n");

      // Instance tables - Division
      out.println("Instance tables (Division Hash)");
      for (int i = 0; i < sampleSize; i++) {
        out.println("Instance [" + (i + 1) + "] /");
        printTable(divTables[i], out);
        out.println();
      }

      out.println("========================================\n");

      // Collision Logs
      out.println("Collision log for all instances");

      out.println("---- Multiplication Hash ----");
      for (int i = 0; i < sampleSize; i++) {
        out.println("Instance [" + (i + 1) + "]");
        out.print(mulTables[i].getCollisionLog());
        out.println();
      }

      out.println("---- Division Hash ----");
      for (int i = 0; i < sampleSize; i++) {
        out.println("Instance [" + (i + 1) + "]");
        out.print(divTables[i].getCollisionLog());
        out.println();
      }

      out.flush();
      System.out.println("\nResults written to file: " + fileName);

    } catch (IOException e) {
      System.out.println("Error writing file: " + e.getMessage());
    }
  }

  private static void printTable(HashTable table, PrintWriter out) {
    out.println("Index | K-mer | Count");
    for (int i = 0; i < table.size; i++) {
      if (table.table[i] != null) {
        out.printf("%5d | %-5s | %d%n",
                i, table.table[i].kmer(), table.table[i].getCount());
      }
    }
    out.print("# of collisions [" + table.getCollisionCounter() + "]");
    out.println();
  }
}
