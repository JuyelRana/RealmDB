package dreamycoding.com.realmdb.model;

import io.realm.RealmObject;

/**
 * Created by Juyel on 10/17/2017.
 */

public class Contact extends RealmObject {
    private String name;
    private String phone;
    private String email;
    private String age;

    public Contact(String name, String phone, String email, String age) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
