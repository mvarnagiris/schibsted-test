package com.schibsted.android.chatbot;

import java.util.Random;

public class TestUtils {
    public static int anInt(int limit) {
        return new Random().nextInt(limit);
    }

    public static String aString(String string) {
        return string + anInt(1000);
    }
}
