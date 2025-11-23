public class HashTable {
    Entry[] table;
    int size;
    private int CollisionCounter;
   // private StringBuilder CollisionLog;

    public HashTable(int size){
        this.size = size;
        table = new Entry[size];
        this.CollisionCounter = 0;
     //   CollisionLog = new StringBuilder();
    }

    private int mulHash(String subString){
        HashHelper hashOne = new HashHelper(subString, size);

        int hashedString = subString.hashCode();
        int lesserPower = hashOne.isLesserPower();
        double getFraction = hashOne.getFraction();

        double product = hashedString * getFraction;
        double fraction = product - Math.floor(product);
        double product2 = lesserPower * fraction;

        return (int) Math.floor(product2);
    }

    private int divHash(String subString){
        HashHelper hashOne = new HashHelper(subString, size);

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

       //     CollisionLog.append("Collision occurred with kMer: [").append(kMer).append("] and table entity: [").append(table[index].kmer()).append("] at index -> ").append(index).append("\n");
            CollisionCounter++;

            loopCount++;
            if (loopCount == size) {
                System.out.println("[ERROR] CANNOT FIND EMPTY SLOTS RETURNING NOW");
                return;
            }
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
         //   CollisionLog.append("Collision occurred with kMer: [").append(kMer).append("] and table entity: [").append(table[index].kmer()).append("] at index -> ").append(index).append("\n");
            CollisionCounter++;

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


    public void computeMul(String dna, int k){
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


    public void printCollisions(){
        System.out.print("# of collisions [" + this.CollisionCounter + "]");
    }

    public int getCollisionCounter(){
        return this.CollisionCounter;
    }
/*
    public String getCollisionLog(){
        return this.CollisionLog.toString();
    }
 */
}







