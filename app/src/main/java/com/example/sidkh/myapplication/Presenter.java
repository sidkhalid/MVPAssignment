package com.example.sidkh.myapplication;

import android.content.Context;

class Presenter implements Interactor.OnLogInFinished {
    Context context;
    Interactor interactor;
    LogIn logIn;
    Presenter(LogIn logIn, Interactor interactor){
        this.logIn=logIn;
        this.interactor=interactor;
    }

    public void onLoginClicked(String user_name, String Password){
        try {
            interactor.AuthorizedLogin(user_name, Password, this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void onSignUp(String user_name, String Password){
        try {
            interactor.DoSignUp(user_name, Password, this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void alreadyAccount() {
        logIn.AlreadyAccount();
    }

    @Override
    public void notAvailable() {
        logIn.noAccount();
    }

    @Override
    public void InvalidDetails() {
        logIn.InvalidLogin();
    }

    @Override
    public void onSuccessLogin() {
        logIn.showLogin();
    }

    @Override
    public void accountCreated() {
        logIn.AccountCreated();
    }


}
