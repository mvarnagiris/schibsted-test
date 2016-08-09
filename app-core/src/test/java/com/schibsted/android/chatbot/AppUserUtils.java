package com.schibsted.android.chatbot;

import com.schibsted.android.chatbot.model.AppUser;

import static com.schibsted.android.chatbot.NameUtils.aName;

public class AppUserUtils {
    public static AppUser anAppUser() {
        return new AppUser(aName());
    }
}
