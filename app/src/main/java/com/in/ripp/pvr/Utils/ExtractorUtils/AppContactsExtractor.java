package com.in.ripp.pvr.Utils.ExtractorUtils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.in.ripp.pvr.POJOs.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;


public class AppContactsExtractor {
    private Context context;
    private String NO_NAME = "EMPTY";
    private String NO_NUMBER = "EMPTY";
    private String regexParam = "[-+.^:,() ]";

    public AppContactsExtractor(Context context) {
        this.context = context;
    }

    public void extractLocalPhoneContactsIntoNewContactsRealmObject(Realm baseRealmObject) {

        baseRealmObject.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.clear(ContactObject.class);
                Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
                ContactObject contactObject = new ContactObject();
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replaceAll(regexParam, "");

                    contactObject.setNameValidity(isContactNameValid(name));
                    contactObject.setNumberValidity(isPhonenumberValid(phonenumber));
                    contactObject.setName(name.toUpperCase());
                    contactObject.setPhoneNumber(phonenumber);

                    realm.copyToRealmOrUpdate(contactObject);
                }
                cursor.close();
            }
        });

    }

    private boolean isPhonenumberValid(String phonenumber) {
        Pattern phoneNumberPattern = Pattern.compile("^[+]?[0-9]{7,13}$");
        Matcher matcher = phoneNumberPattern.matcher(phonenumber);

        if(!matcher.matches()){
            return false;
        }
        return true;
    }

    private boolean isContactNameValid(String name) {
        String[] splitName = name.split(" ");
        name = splitName[0];
        Pattern specialCharacterPatter = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = specialCharacterPatter.matcher(name);

        if(!matcher.matches()){
            return false;
        }
        return true;
    }
}
