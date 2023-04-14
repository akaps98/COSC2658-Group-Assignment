/**
 * This class demonstrate the linear searching method to guess the correct key
 * 
 * @author Nguyen Anh Duy
 * @version 1
 * @since 12 - 04 - 2023
 */

public class DuySecretKeyGuesser {
    public void start() {
        // Linear searching approach key guessing
        SecretKey key = new SecretKey();
        String str = "RRRRRRRRRRRRRRRR";
        System.out.println("Guessing... " + str); // This is our begining input string

        int currentGuess = key.guess(str); // Variable that stores the current number of correct keys
        System.out.println("Number of correct keys: " + currentGuess); // This is our initial number of correct keys

        int afterGuess; // Variable that stores the new number of correct keys each time we change the input string
        int checkingIndex = 0; // Starting index to check

        // Iterating through all the index in the provided string
        while (currentGuess != 16) {
            char[] curr = str.toCharArray();
            for (int i = 0; i < 4; i++)  { // Iterate at most 3 times in either order from these 4 letters: R -> M -> I -> T

                if (curr[checkingIndex] == charOf(i)) { // Only changes the other 3 letters
                    continue;
                }

                curr[checkingIndex] = charOf(i); // Change the current character at this index to another character (in the order: R -> M -> I -> T)
                String checkStr = String.valueOf(curr); // Get the String value from the array
                System.out.println("Guessing... " + checkStr); 

                afterGuess = key.guess(checkStr); // Get the new number of correct keys after the input string is changed
                System.out.println("Number of correct keys: " + afterGuess);

                if (afterGuess > currentGuess) { // Case 1: If the number of correct keys increase --> update the string with the new character at this index
                    System.out.println("Number of correct keys has increased! --> change to new character at position " + String.valueOf(checkingIndex + 1));
                    currentGuess++;
                    str = checkStr;
                    break;
                }
                if (afterGuess < currentGuess) { // Case 2: If the number of correct keys decrease --> do not update the string and keep the current character at this index
                    System.out.println("Number of correct keys has decreased! --> keep the old character and proceeding to next index");
                    break;
                }
                System.out.println("Number of correct keys is the same! --> let's try a different character");
                // else, if the number of correct keys remain the same, change the keys until case 1 or case 2 appear
            }
            checkingIndex++; // check the next index
        }
        System.out.println("I found the secret key. It is " + str);
    }

    /**
     * Return the corresponding order of the character 
     * R -> 0, M -> 1, I -> 2, T -> 3 
     * 
     */ 

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

    /**
     * Return the corresponding character based on the index 
     * 0 -> R, 1 -> M, 2 -> I, other numbers (prefered 3) -> T 
     * 
     */ 
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
