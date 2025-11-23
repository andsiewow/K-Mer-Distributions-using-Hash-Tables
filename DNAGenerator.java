import java.util.Random;

public class DNAGenerator {

    private static final char[] BASES = {'A', 'C', 'G', 'T'};
    private static final Random RAND = new Random();

    // Generates a random DNA string of given length (no spaces)
    public static String generateDNA(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char base = BASES[RAND.nextInt(BASES.length)];
            sb.append(base);
        }

        return sb.toString();
    }
}