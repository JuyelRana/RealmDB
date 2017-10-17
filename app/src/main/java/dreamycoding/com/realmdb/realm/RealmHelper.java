package dreamycoding.com.realmdb.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import dreamycoding.com.realmdb.model.Contact;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Juyel on 10/17/2017.
 */

public class RealmHelper {
    private Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }


    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(Contact.class);
        realm.commitTransaction();
    }

    //add contact
    public void addContact(final Contact contact) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Contact con = realm.copyToRealm(contact);
            }
        });
    }

    //retrive contact
    public List<Contact> getAllContacts() {

        List<Contact> contactList = new ArrayList<>();
        RealmResults<Contact> realmResults = realm.where(Contact.class).findAll();

        for (Contact contact : realmResults) {
            contactList.add(new Contact(contact.getName(), contact.getPhone(), contact.getEmail(), contact.getAge()));
        }

        return contactList;
    }

}
