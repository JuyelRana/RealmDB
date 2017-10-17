package dreamycoding.com.realmdb.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import dreamycoding.com.realmdb.R;
import dreamycoding.com.realmdb.adapter.ContactAdapter;
import dreamycoding.com.realmdb.model.Contact;
import dreamycoding.com.realmdb.realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private Realm realm;
    private List<Contact> contactList;
    private ListView listView;
    private ContactAdapter adapter;
    private RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        //Realm configuration
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(configuration);

        helper = new RealmHelper(realm);


        floatingActionButton.setOnClickListener(floatingActionButtonClickListner);

        listView = (ListView) findViewById(R.id.listView);

        showContactList();
    }

    private void showContactList() {
        contactList = helper.getAllContacts();

        adapter = new ContactAdapter(getApplicationContext(), contactList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    View.OnClickListener floatingActionButtonClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            inputContact();

        }
    };

    private void inputContact() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Add Contact");
        dialog.setContentView(R.layout.dialog);

        final EditText etName = (EditText) dialog.findViewById(R.id.etName);
        final EditText etPhone = (EditText) dialog.findViewById(R.id.etPhone);
        final EditText etEmail = (EditText) dialog.findViewById(R.id.etEmail);
        final EditText etAge = (EditText) dialog.findViewById(R.id.etAge);

        Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact(etName.getText().toString(), etPhone.getText().toString(), etEmail.getText().toString(), etAge.getText().toString());

                realm.beginTransaction();
                realm.copyToRealm(contact);
                realm.commitTransaction();

                showContactList();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
