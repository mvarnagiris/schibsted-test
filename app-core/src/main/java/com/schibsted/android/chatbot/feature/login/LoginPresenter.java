package com.schibsted.android.chatbot.feature.login;

import com.mvcoding.mvp.Presenter;
import com.schibsted.android.chatbot.RxSchedulers;
import com.schibsted.android.chatbot.feature.AppUserStore;
import com.schibsted.android.chatbot.model.Name;

import rx.Observable;

public final class LoginPresenter extends Presenter<LoginPresenter.View> {
    private final AppUserStore appUserStore;
    private final LoginService loginService;
    private final RxSchedulers schedulers;

    public LoginPresenter(AppUserStore appUserStore, LoginService loginService, RxSchedulers schedulers) {
        this.appUserStore = appUserStore;
        this.loginService = loginService;
        this.schedulers = schedulers;
    }

    @Override
    protected void onViewAttached(View view) {
        super.onViewAttached(view);

        if (appUserStore.getAppUser().isLoggedIn()) {
            view.displayChat();
        } else {
            unsubscribeOnDetach(view.loginRequests()
                    .withLatestFrom(view.nameChanges(), (aVoid, name) -> new Name(name))
                    .doOnNext(name -> validate(view, name))
                    .filter(Name::isNotEmpty)
                    .observeOn(schedulers.io())
                    .switchMap(loginService::login)
                    .doOnNext(appUserStore::setAppUser)
                    .observeOn(schedulers.main())
                    .subscribe(appUser -> view.displayChat()));
        }
    }

    private void validate(View view, Name name) {
        if (name.isEmpty()) view.showErrorUserNameCannotBeEmpty();
    }

    public interface View extends Presenter.View {
        Observable<String> nameChanges();
        Observable<Void> loginRequests();
        void showErrorUserNameCannotBeEmpty();
        void displayChat();
    }
}
