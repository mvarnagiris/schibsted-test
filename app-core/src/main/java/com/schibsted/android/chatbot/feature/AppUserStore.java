package com.schibsted.android.chatbot.feature;

import com.schibsted.android.chatbot.model.AppUser;

public interface AppUserStore {
    AppUser getAppUser();
    void setAppUser(AppUser appUser);
}
