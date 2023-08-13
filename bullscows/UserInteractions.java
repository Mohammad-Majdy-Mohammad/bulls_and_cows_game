package bullscows;

import java.util.Scanner;

public class UserInteractions {


    protected static boolean validateSolution(String solution, int length) {
        if (length != solution.length()) {
            System.out.println("error, wrong length");
            return true;
        } else if (!solution.matches("\\w+")) {
            System.out.println("error");
            return true;
        }

        return false;
    }
    
    protected static boolean validateCodeLength(String length, String possibleSymbols) {

        if (!length.matches("\\d+")) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", length);
            return true;
        } else if (!possibleSymbols.matches("\\d+")) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", possibleSymbols);
            return true;
        } else if (Integer.parseInt(length) > Integer.parseInt(possibleSymbols)) {
            System.out.printf("Error: it's not possible to generate a code with a length of %s with %s unique symbols.\n"
                    , length, possibleSymbols);
            return true;
        } else if (Integer.parseInt(length) > 36 || Integer.parseInt(possibleSymbols) > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return true;
        } else if (Integer.parseInt(length) <= 0 || Integer.parseInt(possibleSymbols) <= 0) {
            System.out.println("error, zero or negative length!");
            return true;
        }

        return false;
    }


    protected static void getUserInput() {

        BullsAndCows play = new BullsAndCows();
        Scanner scan      = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length:");
        String lengthStr = scan.nextLine();
        if (validateCodeLength(lengthStr, "36")) {
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        String possibleSymbolsStr = scan.nextLine();
        if (validateCodeLength(lengthStr, possibleSymbolsStr)) {
            return;
        }

        int length = Integer.parseInt(lengthStr);
        int possibleSymbols = Integer.parseInt(possibleSymbolsStr);

        int charLength   = Math.max(length - 10, 0);
        int digitsLength = Math.min(length, 10);

        if (possibleSymbols > 10) {
            System.out.printf("The secret is prepared: %s (0-9, a-%c).\n",
                    "*".repeat(length), (char)(possibleSymbols - 11 + 'a'));
        } else {
            System.out.printf("The secret is prepared: %s (0-9).\n", "*".repeat(length));
        }

        System.out.println("Okay, let's start a game!");

        boolean isUnique = false;

        while (!isUnique) {


            StringBuilder code = new StringBuilder(play.generateRandomNumber(digitsLength));
            isUnique    = play.hasUniqueDigits(code.toString());

            if (charLength > 0) {
                code.append(play.generateRandomChars(charLength));
            }


            if (isUnique) {

                boolean hasBeenGuessed = false;
                int turn = 1;
                while (!hasBeenGuessed) {

                    System.out.printf("Turn %d:\n", turn++);

                    String input = scan.nextLine();
                    if(validateSolution(input, length)) {
                        return;
                    }

                    if (input.contentEquals(code)) {
                        System.out.printf("Grade: " + length + (length > 1 ? " bulls\n" : " bull\n") +
                                "Congratulations! You guessed the secret code.", length);

                        hasBeenGuessed = true;

                    } else {
                        int bulls = 0;
                        int cows  = 0;
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                if (code.charAt(i) == input.charAt(j)) {
                                    if (i == j) {
                                        bulls++;
                                    } else {
                                        cows++;
                                    }
                                }
                            }
                        }

                        System.out.println("Grade: " + bulls + (bulls != 1 ? " bulls " : " bull ") + cows + (cows != 1 ? " cows" : " cow"));
                    }
                }
            }

        }

    }
}
