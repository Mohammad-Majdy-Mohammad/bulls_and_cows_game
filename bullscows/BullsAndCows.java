package bullscows;

import java.util.Arrays;

public class BullsAndCows {

    protected boolean hasUniqueDigits(String code) {

        int[] frequency = new int[10];

        for (int i = 0; i < code.length(); ++i) {
            // if the number doesn't exist then its frequency should be zero
            // so when increasing its value by one it will not exceed 1
            // if it is greater than 1 the value is not unique
            if(++frequency[code.charAt(i) - '0'] > 1) {
                return false;
            }
        }
        return true;
    }



    protected String generateRandomNumber(int length) {

        long randomNumber = System.nanoTime();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(randomNumber % 10);
            randomNumber /= 10;
        }
        return code.toString();
    }


    protected String generateRandomChars(int length) {
        StringBuilder randomChars = new StringBuilder();
        // initializing the randomChar :
        // choosing random character to start with
        // int startingIndex = (int)((System.nanoTime() % 100) % 26);
        // for (int i = 0; i < length; i++) {
        //     randomChars.append((char)((startingIndex + i) % 26 + 'a'));  // next char = startingIndex + i
        // }

        for (int i = 0; i < length; i++) {
            randomChars.append((char)('a' + i));
        }

        // randomizing the randomChar :
        int swapKey = (int) (System.nanoTime() % length);
        // swapping every char at index i with the char at index (swapKey + i) % length
        for (int i = 0; i < length; i++) {
            String temp      = String.valueOf(randomChars.charAt(i));
            randomChars.replace(i, i + 1, String.valueOf(randomChars.charAt((swapKey + i) % length)));
            randomChars.replace((swapKey + i) % length,(swapKey + i) % length + 1, temp);
        }
        return randomChars.toString();
    }

}



