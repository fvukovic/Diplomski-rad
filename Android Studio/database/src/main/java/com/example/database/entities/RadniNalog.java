package com.example.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Filip on 5.2.2017..
 */
@Table(database = MainDatabase.class)
public class RadniNalog  extends BaseModel{
    @PrimaryKey
    @Column
    int id;
    @Column
    String pocetak;
    @Column
    String kraj;
    @Column
    String roba;
    @Column
    String datum;
    @Column
    String model;
    @Column
    int status;


    public RadniNalog(int id, String pocetak, String kraj, String roba, String datum, String model, int status) {
        this.id = id;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.roba = roba;
        this.datum = datum;
        this.model = model;
        this.status = status;
    }

    public RadniNalog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getRoba() {
        return roba;
    }

    public void setRoba(String roba) {
        this.roba = roba;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static List<RadniNalog> getAll()
    {
        List<RadniNalog> sviNalozi;
        sviNalozi= new Select().from(RadniNalog.class).queryList();
        return sviNalozi;
    }
    public static void deleteAll()
    {
        new Delete().from(RadniNalog.class).execute();
    }
}
