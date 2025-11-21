public class HashTable {
    Entry[] table;
    int size;

    public HashTable(int size){
        this.size = size;
        table = new Entry[size];
    }

    private double mulHash(String subString){
        HashingOne hashOne = new HashingOne(subString, size);

        int hashedString = subString.hashCode();
        int lesserPower = hashOne.isLesserPower();
        double getFraction = hashOne.getFraction();

        double product = hashedString * getFraction;
        double fraction = product - Math.floor(product);
        double product2 = lesserPower * fraction;

        return Math.floor(product2);
    }

    private int divHash(String subString){
        HashingOne hashOne = new HashingOne(subString, size);

        int hashedString = subString.hashCode();

        return hashedString % hashOne.isClosestPrime();
    }

    public void insertMul(String kMer) {
        int index = mulHash(kMer);
        int loopCount = 0;

        while (table[index] != null) {
            if (table[index].kmer().equals(kMer)) { //if kmer being inserted already exists inc instead of doing anything
                table[index].inc();
                return;
            }
            if (loopCount == size) {
                System.out.println("[ERROR] CANNOT FIND EMPTY SLOTS RETURNING NOW");
            }
            loopCount++;
            index = index + 1 % size; //this means it was a collision, increment then % is for the wrapping
            System.out.println("Collision occurred with kMer: [" + kMer + "] and table entity:  [" + table[index].kmer() + "] resolving hash to -> " + index);
        }

        System.out.print("insert found a new spot inserting " + kMer + "@ hash location -> " + index);
        table[index] = new Entry(kMer);
    }

    public void insertDiv(String kMer) {
        int index = divHash(kMer);
        int loopCount = 0;

        while (table[index] != null) {
            if (table[index].kmer().equals(kMer)) { //if kmer being inserted already exists inc instead of doing anything
                table[index].inc();
                return;
            }
            if (loopCount == size) {
                System.out.println("[ERROR] CANNOT FIND EMPTY SLOTS RETURNING NOW");
            }
            loopCount++;
            index = index + 1 % size; //this means it was a collision, increment then % is for the wrapping
            System.out.println("Collision occurred with kMer: [" + kMer + "] and table entity:  [" + table[index].kmer() + "] resolving hash to -> " + index);
        }

        System.out.print("insert found a new spot inserting " + kMer + "@ hash location -> " + index);
        table[index] = new Entry(kMer);
    }


    public void computeMul(String dna, int k) {
        for (int i = 0; i <= dna.length() - k; i++) {
            String kmer = dna.substring(i, i + k);
            insertMul(kmer);
        }
    }
    public void computeDiv(String dna, int k) {
        for (int i = 0; i <= dna.length() - k; i++) {
            String kmer = dna.substring(i, i + k);
            insertDiv(kmer);
        }
    }


}







