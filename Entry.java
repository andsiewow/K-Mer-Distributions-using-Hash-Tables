public class Entry {
    private final String kMer;
    private final int hashKey;
    private int count;

    public Entry(String kMer, int hashKey){
        this.kMer = kMer;
        this.hashKey = hashKey;
        this.count =  1; //It means there is already at least one instance if it activated this constructor is called
    }

    public int getHashKey() {
        return hashKey;
    }

    public String getkMer(){
        return kMer;
    }

    public int getCount(){
        return count;
    }

    public void inc(){
        this.count++;
    }
}
