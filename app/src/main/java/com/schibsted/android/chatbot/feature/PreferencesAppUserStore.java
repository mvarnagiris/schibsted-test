package com.schibsted.android.chatbot.feature;

import android.content.Context;

import com.schibsted.android.chatbot.model.AppUser;
import com.schibsted.android.chatbot.model.Name;

import java.util.concurrent.atomic.AtomicReference;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.schibsted.android.chatbot.model.Name.noName;

public class PreferencesAppUserStore implements AppUserStore {
    private static final String KEY_NAME = "KEY_NAME";

    private final Context context;

    private AtomicReference<AppUser> appUserReference = new AtomicReference<>();

    public PreferencesAppUserStore(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public AppUser getAppUser() {
        AppUser appUser = appUserReference.get();
        if (appUser != null) return appUser;

        appUser = createAppUserFromPreferences();
        appUserReference.set(appUser);
        return appUser;
    }

    @Override
    public void setAppUser(AppUser appUser) {
        appUserReference.set(appUser);
        saveAppUserToPreferences(appUser);
    }

    private AppUser createAppUserFromPreferences() {
        String name = getDefaultSharedPreferences(context).getString(KEY_NAME, null);
        return new AppUser(name == null ? noName : new Name(name));
    }

    private void saveAppUserToPreferences(AppUser appUser) {
        getDefaultSharedPreferences(context).edit().putString(KEY_NAME, appUser.getName().getFullName()).apply();
    }
}
