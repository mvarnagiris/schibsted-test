package com.schibsted.android.chatbot.feature.login;

import com.schibsted.android.chatbot.feature.AppUserStore;
import com.schibsted.android.chatbot.model.AppUser;

import org.junit.Before;
import org.junit.Test;

import rx.subjects.PublishSubject;

import static com.schibsted.android.chatbot.AppUserUtils.anAppUser;
import static com.schibsted.android.chatbot.RxSchedulersUtils.rxSchedulers;
import static com.schibsted.android.chatbot.VoidUtils.aTrigger;
import static com.schibsted.android.chatbot.model.AppUser.noAppUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rx.Observable.just;

public class LoginPresenterTest {

    PublishSubject<String> nameChangesSubject = PublishSubject.create();
    PublishSubject<Void> loginRequestsSubject = PublishSubject.create();
    AppUserStore appUserStore = mock(AppUserStore.class);
    LoginService loginService = mock(LoginService.class);
    LoginPresenter.View view = mock(LoginPresenter.View.class);
    LoginPresenter presenter = new LoginPresenter(appUserStore, loginService, rxSchedulers());

    @Before
    public void setUp() {
        when(appUserStore.getAppUser()).thenReturn(noAppUser);
        when(view.nameChanges()).thenReturn(nameChangesSubject);
        when(view.loginRequests()).thenReturn(loginRequestsSubject);
    }

    @Test
    public void doesNotDisplayChatIfUserIsNotLoggedIn() {
        when(appUserStore.getAppUser()).thenReturn(noAppUser);

        presenter.attach(view);

        verify(view, never()).displayChat();
    }

    @Test
    public void displaysChatIfUserIsAlreadyLoggedIn() {
        when(appUserStore.getAppUser()).thenReturn(anAppUser());

        presenter.attach(view);

        verify(view).displayChat();
    }

    @Test
    public void showsErrorWhenTryingToLoginWithEmptyName() {
        presenter.attach(view);
        changeName("");

        login();

        verify(loginService, never()).login(any());
        verify(appUserStore, never()).setAppUser(any());
        verify(view, never()).displayChat();
        verify(view).showErrorUserNameCannotBeEmpty();
    }

    @Test
    public void logsInAndStoresAppUser() {
        AppUser appUser = anAppUser();
        when(loginService.login(appUser.getName())).thenReturn(just(appUser));
        presenter.attach(view);
        changeName(appUser.getName().getFullName());

        login();

        verify(loginService).login(appUser.getName());
        verify(appUserStore).setAppUser(appUser);
        verify(view).displayChat();
    }

    void changeName(String name) {
        nameChangesSubject.onNext(name);
    }

    void login() {
        loginRequestsSubject.onNext(aTrigger());
    }
}