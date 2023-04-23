public class SecretKey {
    private String correctKey;
    private int counter;

    public SecretKey() {
        // for the real test, your program will not know this
        correctKey = "RRRRRRRRRMITRMIT";
        counter = 0;
    }

    // IDK if we can modify return data type
    // But it basically does the same thing with the provided one.
    // One thing different is that it returns array of integers
    // Which implies a character in certain position is same as the one in the correct key.
    public int[] guess(String guessedKey) {
        counter++;
        int[] isMatched = new int[correctKey.length() + 1];
        // validation
        if (guessedKey.length() != correctKey.length()) {
            isMatched[0] = -1;
            return isMatched;
        }
        int matched = 0;
        for (int i = 0; i < guessedKey.length(); i++) {
            char c = guessedKey.charAt(i);
            if (c != 'R' && c != 'M' && c != 'I' && c != 'T') {
                isMatched[0] = -1;
                return isMatched;
            }
            if (c == correctKey.charAt(i)) {
                matched++;
                // If c is the same, make an element in an array 1
                // To indicate that the c matches a char in this position.
                isMatched[i + 1] = 1;
            }
        }
        if (matched == correctKey.length()) {
            System.out.println("Number of guesses: " + counter);
        }
        isMatched[0] = matched;
        return isMatched;
    }

    public static void main(String[] args) {
        // new way1().start();
    }
}
