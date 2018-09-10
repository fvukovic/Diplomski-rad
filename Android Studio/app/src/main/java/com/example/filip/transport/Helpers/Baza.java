package com.example.filip.transport.Helpers;

import android.app.Activity;

import com.example.database.entities.RadniNalog;
import com.example.database.entities.Radnik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Filip on 24.1.2017..
 */

public class Baza extends Activity {
    Connection connection = null;
    String className = "net.sourceforge.jtds.jdbc.Driver";
    public String android_id = "";


    public Baza() {
        System.out.print("AAAAAAAAAA");
        System.out.flush();
        System.out.print("AAAAAAAAAA");
        System.out.flush();System.out.print("AAAAAAAAAA");
        System.out.flush();

        try {
            System.out.print("BBBBBBBBBBBBBBBB");
            System.out.flush();

            /* Povezivanje na bazu sa jtdsom 1.3.0
            85.94.77.105
            DB = dajsveandroid
            User = dajsveappuser, Pass = Pa55word
             10.0.3.2 - Genymotion
                 */
            this.android_id = android_id;

            Class.forName(className).newInstance();
            System.out.print("Uspjesno spojeni na bazu");

            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://10.0.3.2;databaseName=invictus;user=admin;password=admin;");
            System.out.print("Uspjesno spojeni na bazu");
            System.out.flush();
            Statement stmt = connection.createStatement();
            ResultSet reset = stmt.executeQuery("select * from dbo.radnik");

            if (!reset.isBeforeFirst()) {
                System.out.println("nema podataka");
            } else {
                System.out.println("ima podataka");
            }
            System.out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Greska pri spajanju na bazu");
        } catch (ClassNotFoundException e) {
            System.out.print("Greska - klasa nije pronađena");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.flush();

    }

    public ResultSet IzvrsiUpit(String upit) throws SQLException {
        ResultSet reset = null;
        if (connection != null) {
            Statement stmt = connection.createStatement();
            reset = stmt.executeQuery(upit);
        }
        return reset;
    }



    public void DohvatiRadnike() throws SQLException {
        String  upit= "select * from radnik;";
        ResultSet reset = IzvrsiUpit(upit);
        Radnik.deleteAll();
        System.out.print("TU sam");
        if (reset != null) {
            while (reset.next()) {
                Radnik novi = new Radnik();
                novi.setId(reset.getInt("id_radnik"));
                novi.setImeIprezime(reset.getString("ime_i_prezime"));

                novi.setPassword(reset.getString("lozinka"));
                System.out.println("lozinka "+ reset.getString("lozinka"));
                novi.save();
            }
        }

    }
    public  void DohvatiRadneNaloge() throws  SQLException
    {
        RadniNalog.deleteAll();
        String upit = "select * from radni_nalog;";
        ResultSet reset = IzvrsiUpit(upit);
        if (reset != null) {
            while (reset.next()) {
                RadniNalog novi = new RadniNalog();
                novi.setId(reset.getInt("ID"));
                novi.setPocetak(reset.getString("pocetak"));
                novi.setStatus(reset.getInt("status"));
                novi.setKraj(reset.getString("kraj"));
                novi.setModel(reset.getString("model"));
                novi.save();
            }

        }
    }

}