import java.util.ArrayList;

public class HashTable {
  private String input;
  private int length;
  private int size;
  private int maxPositions;
  private int closestPrime;
  private ArrayList<String> substrings;
  private ArrayList<Integer> indexes;
  private ArrayList<Integer> resolvedIndex;
  private ArrayList<String> resolvedSubstring;

  public HashTable(String input, int size) {
    this.input = input;
    this.length = input.length();
    this.size = size;
    this.substrings = new ArrayList<>();
    this.indexes = new ArrayList<>();
    this.resolvedIndex = new ArrayList<>();
    this.resolvedSubstring = new ArrayList<>();
  }

  public void splitSections() {
    maxPositions = 0;

    if (size > length) {
        return;
    }

    for (int i = 0; i <= length - size; i++) {
      String section = input.substring(i, i + size);

      if (!(substrings.contains(section))) {
        substrings.add(section);
        maxPositions++;
      }
    }
  }

  private boolean isPrime(int n) {
    if (n <= 1) {
      return false;
    } else if (n == 2) {
      return true;
    } else if (n % 2 == 0) {
      return false;
    }

    for (int i = 3; i * i <= n; i += 2) {
      if (n % i == 0) return false;
    }

    return true;
}

  public int isClosestPrime() {
    if (maxPositions == 2) {
      closestPrime = 2;
    }
    else {
      int i = maxPositions;

      if (i % 2 == 0) {
        i++;
      }

      //this is for closest GREATER prime number
      while (!isPrime(i)) { //if closest LESSER prime number, just add && i < maxPositions
        i += 2;
      }

      closestPrime = i;
    }

    return closestPrime;
  }

  public int hashFunction(String substring) {
    int base = Math.abs(substring.hashCode());
    int index = base % closestPrime;
    return index;
  }

  public void collisionResolution() {
    indexes.clear();
    resolvedIndex.clear();
    boolean[] occupied = new boolean[closestPrime];

    for (int i = 0; i < maxPositions; i++) {
        int idx = hashFunction(substrings.get(i));
        indexes.add(idx);
    }

    for (int i = 0; i < maxPositions; i++) {
        int idx = indexes.get(i);
        while (occupied[idx]) {
            idx = (idx + 1) % closestPrime;
        }
        occupied[idx] = true;
        resolvedIndex.add(idx);
    }
  }

  public int noOfOccurrences(String s) {
    int count = 0;

    for (int i = 0; i <= length - size; i++) {
      String section = input.substring(i, i + size);
      if (section.equals(s)) {
        count++;
      }
    }
    
    return count;
  }

  public ArrayList<String> getSubstrings() {
    return substrings;
  }

  public int getMaxPositions() {
    return maxPositions;
  }

  public ArrayList<Integer> getResolvedIndex() {
    return resolvedIndex;
  }

  public ArrayList<String> getResolvedSubstring() {
    return resolvedSubstring;
  }
}