package utils;

import java.util.Random;

public class TestUtils {
    public static String getRandomString()
    {
        Random random = new Random();
        int randomInt = random.nextInt(1000000);
        return Integer.toString(randomInt);
    }
}
