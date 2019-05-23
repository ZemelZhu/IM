package com.im.zemel.util;

import java.util.Random;

public class RandomUntil {
    public static int getRandom() {
        Random random = new Random();
            int nextInt = random.nextInt(999999999);
            int abs = Math.abs(nextInt);
            return abs;
    }
}
