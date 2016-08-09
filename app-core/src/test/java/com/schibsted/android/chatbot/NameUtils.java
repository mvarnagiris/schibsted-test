package com.schibsted.android.chatbot;

import com.schibsted.android.chatbot.model.Name;

import static com.schibsted.android.chatbot.TestUtils.aString;

public class NameUtils {
    public static Name aName() {
        return new Name(aString("Name"));
    }
}
