package com.example.sidkh.myapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.widget.Toast;

public class Interactor implements Runnable{
    AppDatabase db;
    String userName;
    String Password;
    int operation;
    boolean status;
    Presenter presenter;
    Context context;
    @Override
    public void run() {
        if(operation==0){
            User u = db.dao().getUser(userName, Password);
            //Toast.makeText(context.getApplicationContext(), ""+u.name+u.Password, Toast.LENGTH_LONG).show();
            if(u!=null){
                status=true;
                //Toast.makeText(context.getApplicationContext(), ""+u.name+u.Password, Toast.LENGTH_LONG).show();
            }
            else{
                String s = db.dao().getUserStatus(userName);
                if(s!=null)
                    operation=2;
                else
                    status=false;
            }

        }
        else if(operation==1){
            String s = db.dao().getUserStatus(userName);
            if(s!=null)
                status=false;
            else
            {
                User u =new User();
                u.name=userName;
                u.Password=Password;
                db.dao().Insert(u);
                status = true;
            }
        }
    }

    interface OnLogInFinished{
        void alreadyAccount();
        void notAvailable();
        void InvalidDetails();
        void onSuccessLogin();
        void accountCreated();
    }
    Interactor(Context c){
        context=c;
        //Toast.makeText(c.getApplicationContext(), "invalid username or password", Toast.LENGTH_LONG).show();
        db = Room.databaseBuilder(c.getApplicationContext(), AppDatabase.class, "database-name.db").build();
    }

    void AuthorizedLogin(String u, String p, Presenter prr) throws InterruptedException {
        presenter=prr;
        userName=u;
        Password=p;
        operation=0;
        Thread t = new Thread(this);
        t.start();
        t.join();
        if(status)
            presenter.onSuccessLogin();
        else if(operation==2)
            presenter.InvalidDetails();
        else
            presenter.notAvailable();
    }

    void DoSignUp(String u, String p, Presenter prr) throws InterruptedException {
        presenter=prr;
        userName=u;
        Password=p;
        operation=1;
        Thread t = new Thread(this);
        t.start();
        t.join();
        if(status)
            presenter.accountCreated();
        else
            presenter.alreadyAccount();
    }

}
