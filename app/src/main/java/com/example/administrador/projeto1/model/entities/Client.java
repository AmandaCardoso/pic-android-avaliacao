package com.example.administrador.projeto1.model.entities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.projeto1.model.persistence.ClientContract;
import com.example.administrador.projeto1.model.persistence.MemoryClientRepository;
import com.example.administrador.projeto1.model.persistence.SQLiteClientRepositiry;

import java.io.Serializable;
import java.util.List;

public class Client implements Parcelable {
    private Integer id;
    private String name;
    private Integer age;
    private ClientAddress address;
    private String phone;

    public Client(){
        super();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ClientAddress getAddress() {
        return address;
    }

    public void setAddress(ClientAddress address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void save() {
        SQLiteClientRepositiry.getInstance().save(this);
    }

    public void delete() {
        SQLiteClientRepositiry.getInstance().delete(this);
    }

    public static List<Client> getAll() {
        return SQLiteClientRepositiry.getInstance().getAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (!getId().equals(client.getId())) return false;
        if (!getName().equals(client.getName())) return false;
        if (!getAge().equals(client.getAge())) return false;
        if (!getAddress().equals(client.getAddress())) return false;
        return getPhone().equals(client.getPhone());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAge().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getPhone().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getName() + "\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id == null ? -1 : id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(phone == null ? "" : phone);
        dest.writeInt(age == null ? -1 : age);
        dest.writeParcelable(address, flags);
    }

    public void readToParcel(Parcel in) {
        int partialId = in.readInt();
        id = partialId == -1 ? null : partialId;
        name = in.readString();
        phone = in.readString();
        int partialAge = in.readInt();
        age = partialAge == -1 ? null : partialAge;
        address = in.readParcelable(ClientAddress.class.getClassLoader());
    }
    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    protected Client(Parcel in){
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.phone = in.readString();
        this.address = in.readParcelable(ClientAddress.class.getClassLoader());
    }

    public static ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();
        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.PHONE, client.getPhone());
        values.put(ClientContract.CEP, client.getAddress().getCep());
        values.put(ClientContract.TIPODELOGRADOURO, client.getAddress().getTipoDeLogradouro());
        values.put(ClientContract.LOGRADOURO, client.getAddress().getLogradouro());
        values.put(ClientContract.BAIRRO, client.getAddress().getBairro());
        values.put(ClientContract.CIDADE, client.getAddress().getCidade());
        values.put(ClientContract.ESTADO, client.getAddress().getEstado());
        return values;
    }

}
