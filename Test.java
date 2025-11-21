

public class Test {
    public static void main (String[] args){
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = "hello";
        String s4 = "Philip";
        int i1 = Math.abs(s1.hashCode());
        int i2 = Math.abs(s2.hashCode());
        int i3 = Math.abs(s3.hashCode());
        int i4 = Math.abs(s4.hashCode());
        System.out.println(s1 + " " + i1);
        System.out.println(s2 + " " + i2);
        System.out.println(s3 + " " + i3);
        System.out.println(s4 + " " + i4);
    }
}
