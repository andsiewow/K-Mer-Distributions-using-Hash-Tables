public class HashTable {
    Entry[] table;
    int size;

    public HashTable(int size){
        this.size = size;
        table = new Entry[size];
    }

    private int mulHash(String subString){
        HashingOne hashOne = new HashingOne(subString, size);

        int hashedString = subString.hashCode();
        int lesserPower = hashOne.isLesserPower();
        double getFraction = hashOne.getFraction();

        double product = hashedString * getFraction;
        double fraction = product - Math.floor(product);
        double product2 = lesserPower * fraction;

        return (int) Math.floor(product2);
    }

    private int divHash(String subString){
        HashingOne hashOne = new HashingOne(subString, size);

        int hashedString = subString.hashCode();

        return Math.abs(hashedString) % hashOne.isClosestPrime();
    }

    public void insertMul(String kMer) {
        int index = mulHash(kMer);
        int loopCount = 0;

        while (table[index] != null) {

            if (table[index].kmer().equals(kMer)) {
                table[index].inc();
                return;
            }

            // ✅ PRINT FIRST BEFORE MOVING
            System.out.println(
                    "Collision occurred with kMer: [" + kMer +
                            "] and table entity: [" + table[index].kmer() +
                            "] at index -> " + index
            );

            loopCount++;
            if (loopCount == size) {
                System.out.println("[ERROR] CANNOT FIND EMPTY SLOTS RETURNING NOW");
                return;
            }

            // NOW move
            index = (index + 1) % size;
        }

        table[index] = new Entry(kMer);
    }

    public void insertDiv(String kMer) {
        int index = divHash(kMer);
        int loopCount = 0;

        while (table[index] != null) {

            if (table[index].kmer().equals(kMer)) {
                table[index].inc();
                return;
            }

            // ✅ PRINT FIRST BEFORE MOVING
            System.out.println(
                    "Collision occurred with kMer: [" + kMer +
                            "] and table entity: [" + table[index].kmer() +
                            "] at index -> " + index
            );

            loopCount++;
            if (loopCount == size) {
                System.out.println("[ERROR] CANNOT FIND EMPTY SLOTS RETURNING NOW");
                return;
            }

            // NOW move
            index = (index + 1) % size;
        }

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

    public void print() {
        System.out.println("Index | K-mer | Count");
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.printf("%5d | %-5s | %d\n",
                        i, table[i].kmer(), table[i].getCount());
            }
        }
    }


}







