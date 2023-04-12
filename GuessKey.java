public class GuessKey {
    public void start() {
        SecretKey2 key = new SecretKey2();
        String str = "MITRMITRMITRMITR";
        int endIdx = str.length();
        int match = key.guess(str);
        while (match != -1 && match != 16) {
            char[] curr = str.toCharArray();
            String s;
            int newMatch;
            if (order(curr[endIdx - 1]) < 3) {
                // increase this one and stop
                curr[endIdx - 1] = charOf(order(curr[endIdx - 1]) + 1);
            } else {
                curr[endIdx - 1] = 'R';
            }
            s = String.valueOf(curr);
            System.out.println("Guessing... " + s);
            newMatch = key.guess(s);
            // Check if the program changed a character that already matches the correct key.
            if (match > newMatch) {
                if (order(curr[endIdx - 1]) > 0) {
                    // Decrease this one and stop
                    curr[endIdx - 1] = charOf(order(curr[endIdx - 1]) - 1);
                } else {
                    curr[endIdx - 1] = 'T';
                }
                str = String.valueOf(curr);
                endIdx--;
            }
            // Check if the program got a right character
            else if (match < newMatch) {
                endIdx--;
                match = newMatch;
                str = s;
            }
            // When the changed character doesn't match the one from the correct key
            else {
                str = s;
            }
        }
        System.out.println("I found the secret key. It is " + str);
    }

    static int order(char c) {
        if (c == 'R') {
            return 0;
        } else if (c == 'M') {
            return 1;
        } else if (c == 'I') {
            return 2;
        }
        return 3;
    }

    static char charOf(int order) {
        if (order == 0) {
            return 'R';
        } else if (order == 1) {
            return 'M';
        } else if (order == 2) {
            return 'I';
        }
        return 'T';
    }
}
