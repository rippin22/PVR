package com.in.ripp.pvr.Views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.in.ripp.pvr.POJOs.ContactObject;
import com.in.ripp.pvr.R;
import com.in.ripp.pvr.Utils.ExtractorUtils.AppContactsExtractor;
import com.in.ripp.pvr.Utils.ExtractorUtils.FacebookFriendsExtractor;
import com.in.ripp.pvr.Utils.ExtractorUtils.TwitterFollowersExtractor;
import com.in.ripp.pvr.Utils.PermissionsHandlerUtil;
import com.in.ripp.pvr.Utils.RealmController;
import com.in.ripp.pvr.databinding.ActivityMainBinding;

import io.realm.RealmQuery;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    public static final int RequestPermissionCode  = 1 ;
    public ActivityMainBinding binding;
    public AppContactsExtractor appContactsExtractor;
    public FacebookFriendsExtractor facebookFriendsListExtractor;
    public TwitterFollowersExtractor twitterFollowersExtractor;

    private RealmController realmController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionsHandlerUtil.EnableRuntimePermission(this);
        initObjects();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(this);
    }

    private void initObjects() {
        realmController = new RealmController(getApplicationContext());
        appContactsExtractor = new AppContactsExtractor(getApplicationContext());

    }


    public void initPVRMainButtonClick(View view){
        appContactsExtractor.extractLocalPhoneContactsIntoNewContactsRealmObject(realmController.getRealmInstance());
        facebookFriendsListExtractor.extractAllFreindsNamesFromFacebookIntoFacebookRealmObject();
        twitterFollowersExtractor.extracteAllFollowersNamesFromTwitterIntoTwitterRealmObject();
        queryRealm();
    }

    private void queryRealm() {
        RealmQuery<ContactObject> realmQueryNameValidityFalse = realmController.getRealmInstance().where(ContactObject.class).equalTo("nameValidity", false);
        RealmQuery<ContactObject> realmQuerynameValidityTrue = realmController.getRealmInstance().where(ContactObject.class).equalTo("nameValidity", true);
        RealmQuery<ContactObject> realmQueryNumberValidityFalse = realmController.getRealmInstance().where(ContactObject.class).equalTo("numberValidity", false);
        RealmQuery<ContactObject> realmQueryNumberValidityTrue = realmController.getRealmInstance().where(ContactObject.class).equalTo("numberValidity", true);

        RealmResults<ContactObject> resultsNameFalse = realmQueryNameValidityFalse.findAll();
        RealmResults<ContactObject> resultsNameTrue = realmQuerynameValidityTrue.findAll();
        RealmResults<ContactObject> resultsNumberFalse = realmQueryNumberValidityFalse.findAll();
        RealmResults<ContactObject> resultsNumberTrue = realmQueryNumberValidityTrue.findAll();

        Log.d("rip", "queryRealm: "+resultsNameFalse.size());
        Log.d("rip", "queryRealm: "+resultsNameTrue.size());
        Log.d("rip", "queryRealm: "+resultsNumberFalse.size());
        Log.d("rip", "queryRealm: "+resultsNumberTrue.size());

    }
}
