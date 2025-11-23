import java.util.ArrayList;

public class HashHelper {
  private String input;
  private int length;
  private int size;
  private int maxPositions;
  private int closestPrime;
  private ArrayList<String> substrings;
  private ArrayList<Integer> indexes;
  private ArrayList<Integer> resolvedIndex;
  private ArrayList<String> resolvedSubstring;
  private final double CONSTANT_FRACTION =  0.61803398875; //The most used fractional portion for the multiplication method

  public HashHelper(String input, int size) {
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
    if (maxPositions <= 2) {
      closestPrime = 2;
      return closestPrime;
    }

    int i = maxPositions;

    if (i % 2 == 0) {
      i--;
    }

    while (i >= 2 && !isPrime(i)) {
      i -= 2;
    }

    if (i < 2) {
      closestPrime = 2;
    } else {
      closestPrime = i;
    }

    return closestPrime;
  }

  public int isLesserPower() {
      int lesserPower = 2;

      while (lesserPower < maxPositions) {
          lesserPower *= 2;
      }

      return lesserPower;
  }


  /**
   * This function uses the Division Method
   * @param substring
   * @return an unchecked index
   */
  private int hashFunctionOne(String substring) { //-- I changed it to a private class since its an in class helper function
    int base = Math.abs(substring.hashCode());
    return base % closestPrime; //simplfied it to one line of return --Philip
  }

  public void collisionResolution() {
    indexes.clear();
    resolvedIndex.clear();
    boolean[] occupied = new boolean[closestPrime];

    for (int i = 0; i < maxPositions; i++) {
        int idx = hashFunctionOne(substrings.get(i));
        indexes.add(idx);
    }

    for (int i = 0; i < maxPositions; i++) {
        int idx = indexes.get(i);
        while (occupied[idx]) {
            idx = (idx + 1) % closestPrime; //This logic is for wrapping around the index using %
        }
        occupied[idx] = true;
        resolvedIndex.add(idx);
    }
  }
  public double getFraction() {
      return CONSTANT_FRACTION;
  }
}