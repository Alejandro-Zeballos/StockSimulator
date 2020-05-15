package utils;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static int generateRandomNumber(int base, int top) {
        int randomNumber;
        ThreadLocalRandom threadRandom = ThreadLocalRandom.current();
        randomNumber = threadRandom.nextInt(base, top);

        return randomNumber;
    }
}
