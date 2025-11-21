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

}
