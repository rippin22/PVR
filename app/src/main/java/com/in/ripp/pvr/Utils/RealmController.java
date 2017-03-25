package com.in.ripp.pvr.Utils;


import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmController {

    public RealmController(Context context) {
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    //Singleton
    public Realm getRealmInstance(){
        if(Realm.getDefaultInstance() != null){
            return Realm.getDefaultInstance();
        }
        return null;
    }
}
