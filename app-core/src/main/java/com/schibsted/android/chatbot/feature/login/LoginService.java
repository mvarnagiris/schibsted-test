package com.schibsted.android.chatbot.feature.login;

import com.schibsted.android.chatbot.model.AppUser;
import com.schibsted.android.chatbot.model.Name;

import rx.Observable;

public interface LoginService {
    Observable<AppUser> login(Name name);
}
