package com.schibsted.android.chatbot.model;

import static com.schibsted.android.chatbot.model.Name.noName;

public final class AppUser {
    public static final AppUser noAppUser = new AppUser(noName);

    private final Name name;

    public AppUser(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public boolean isLoggedIn() {
        return !equals(noAppUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;

        AppUser appUser = (AppUser) o;

        return name.equals(appUser.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "name=" + name +
                '}';
    }
}
