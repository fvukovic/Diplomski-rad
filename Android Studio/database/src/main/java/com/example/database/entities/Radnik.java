package com.example.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by Helena on 12.11.2016..
 */
@Table(database = MainDatabase.class)
public class Radnik extends BaseModel  {

    @Column
    @PrimaryKey
    int id;
    @Column
    String ImeIprezime;
    @Column
    String Password;

    public Radnik(int id, String ime, String password) {
        this.id = id;
        this.ImeIprezime = ime;
        this.Password = password;
    }

    public Radnik() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getImeIprezime() {
        return ImeIprezime;
    }

    public void setImeIprezime(String imeIprezime) {
        ImeIprezime = imeIprezime;
    }

    public static List<Radnik> getAll(){
        List<Radnik> ponudaList;
        ponudaList= new Select().from(Radnik.class).queryList();
        return ponudaList;
    }
    public static void deleteAll()
    {
        new Delete().from(Radnik.class).execute();
    }

}
