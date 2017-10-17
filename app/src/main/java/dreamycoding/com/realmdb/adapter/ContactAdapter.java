package dreamycoding.com.realmdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dreamycoding.com.realmdb.R;
import dreamycoding.com.realmdb.model.Contact;

/**
 * Created by Juyel on 10/17/2017.
 */

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contactList;
    private LayoutInflater inflater;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row, null);
        }

        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        TextView txtPhone = (TextView) convertView.findViewById(R.id.txtPhone);
        TextView txtEmail = (TextView) convertView.findViewById(R.id.txtEmail);
        TextView txtAge = (TextView) convertView.findViewById(R.id.txtAge);

        txtName.setText(contactList.get(position).getName());
        txtPhone.setText(contactList.get(position).getPhone());
        txtEmail.setText(contactList.get(position).getEmail());
        txtAge.setText(contactList.get(position).getAge());
        return convertView;
    }
}
