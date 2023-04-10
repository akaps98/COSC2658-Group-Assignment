public class GuessKey {
    public void start() {
        SecretKey2 key = new SecretKey2();
        String str = "MMITRMITRMITRMTT";
        int match = key.guess(str);
        do {
            if (match != 16) {
                str = next(str, match, str.length(), key);
                match = key.guess(str);
            }
        } while (match != -1 && match != 16);
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

    // return the next value in 'RMIT' order, that is
    // R < M < I < T
    // Recursively go through the method until it matches the correct key
    public String next(String current, int match, int length, SecretKey2 key) {
        char[] curr = current.toCharArray();
        String s = String.valueOf(curr);
        int newMatch;
        if (length > 0 && match != 16) {
            if (order(curr[length - 1]) < 3) {
                // increase this one and stop
                curr[length - 1] = charOf(order(curr[length - 1]) + 1);
            } else {
                curr[length - 1] = 'R';
            }
            s = String.valueOf(curr);
            System.out.println("Guessing... " + s);
            newMatch = key.guess(s);

            // Check if the program changed a right character that doesn't need to be changed.
            if (match > newMatch) {
                if (order(curr[length - 1]) > 0) {
                    // Decrease this one and stop
                    curr[length - 1] = charOf(order(curr[length - 1]) - 1);
                } else {
                    curr[length - 1] = 'T';
                }
                s = String.valueOf(curr);
                s = next(s, match, length - 1, key);
            }
            // Check if the program got a right character
            else if (match < newMatch) {
                s = next(s, newMatch, length - 1, key);
            }
            // When the changed character doesn't match the one from the correct key
            else {
                s = next(s, newMatch, length, key);
            }
        }
        return s;
    }
}
