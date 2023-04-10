public class Way2 {
    public void start() {
        SecretKey key = new SecretKey();
        String str = "RMITTIRMTRTIRITM";
        int[] arr = key.guess(str);
        do {
            if(arr[0] != 16){
                str = next(str, arr);
                if (str.length() == 16) {
                    System.out.println("Guessing... " + str);
                }
                arr = key.guess(str);
            }
        } while (arr[0] != -1 && arr[0] != 16);
        if (str.length() == 16) {
            System.out.println("I found the secret key. It is " + str);
        }
    }

    static char toNextChar(char c){
        switch (c) {
            case 'R' -> c = 'M';
            case 'M' -> c = 'I';
            case 'I' -> c = 'T';
            case 'T' -> c = 'R';
        }

        return c;
    }

    // return the next value in 'RMIT' order, that is
    // R < M < I < T
    // Divide length 16 string into 4 characters array and change one character in each part.
    // Skip if the character matches the correct key
    public String next(String current, int[] arr) {
        char[] curr = current.toCharArray();
        for (int i = curr.length/4 - 1; i >= 0; i--) {
            if(arr[i + 1] == 1){
                continue;
            }
            curr[i] = toNextChar(curr[i]);
            break;
        }
        for (int i = (curr.length/4 + 4) - 1; i >= curr.length/4 - 1; i--) {
            if(arr[i + 1] == 1){
                continue;
            }
            curr[i] = toNextChar(curr[i]);
            break;
        }
        for (int i = (curr.length/4 + 8) - 1; i >= (curr.length/4 + 4) - 1; i--) {
            if(arr[i + 1] == 1){
                continue;
            }
            curr[i] = toNextChar(curr[i]);
            break;
        }
        for (int i = (curr.length/4 + 12) - 1; i >= (curr.length/4 + 8) - 1; i--) {
            if(arr[i + 1] == 1){
                continue;
            }
            curr[i] = toNextChar(curr[i]);
            break;
        }
        return String.valueOf(curr);
    }

}
