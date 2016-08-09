package com.schibsted.android.chatbot.model;

import com.schibsted.android.chatbot.StringUtils;

public final class Name {
    public static final Name noName = new Name("");

    private final String fullName;

    public Name(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(fullName);
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;

        Name name = (Name) o;

        return fullName.equals(name.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public String toString() {
        return "Name{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
