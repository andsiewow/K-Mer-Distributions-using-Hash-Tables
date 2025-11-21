public class Entry {
    private final String kMer;
    private int count;

    public Entry(String kMer){
        this.kMer = kMer;
        this.count =  1; //It means there is already at least one instance if it activated this constructor is called
    }

    public String kmer(){
        return kMer;
    }

    public int getCount(){
        return count;
    }

    public void inc(){
        this.count++;
    }
}
