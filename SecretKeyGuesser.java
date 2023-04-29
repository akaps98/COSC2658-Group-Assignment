public class SecretKeyGuesser {
    public void start() {
        SecretKey key = new SecretKey();
        String str = "ITMRMRRITMRITMRT";
        int idx1 = 0;
        int idx2 = str.length() - 1;
        boolean isFront = true;
        int checkingIdx;
        int match = key.guess(str);
        System.out.println("Guessing... " + str);

        while (match != -1 && match != 16) {
            if(isFront){
                checkingIdx = idx1;
            }else{
                checkingIdx = idx2;
            }

            char[] curr = str.toCharArray();
            int newMatch;
            // Check if the program changed a character that already matches the correct key.
            toNextChar(curr, checkingIdx); // 2
            String s;
            s = String.valueOf(curr);
            System.out.println("Guessing... " + s);
            newMatch = key.guess(s);
            if (newMatch > match) {
                match = newMatch;
                str = s;
            } else if(newMatch == match) {
                toNextChar(curr, checkingIdx);
                s = String.valueOf(curr);
                newMatch = key.guess(s);
                System.out.println("Guessing... " + s);
                if(newMatch == match) {
                    // Since there are only 4 characters available in order to create a key
                    // The character in the current index must match the one in the secret key
                    // When it moves once more to a next char
                    toNextChar(curr, checkingIdx); // 3
                    s = String.valueOf(curr);
                    System.out.println("Guessing... " + s);
                    newMatch++;
                    if(newMatch == 16){
                        key.guess(s);
                    }
                }
                str = s;
                match = newMatch;
            }
            if(isFront){
                idx1++;
                isFront = false;
            }else {
                idx2--;
                isFront = true;
            }
        }
        System.out.println("I found the secret key. It is " + str);
    }

    static void toNextChar(char[] curr, int idx) {
        if (order(curr[idx]) < 3) {
            curr[idx] = charOf(order(curr[idx]) + 1);
        } else {
            curr[idx] = 'R';
        }
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
