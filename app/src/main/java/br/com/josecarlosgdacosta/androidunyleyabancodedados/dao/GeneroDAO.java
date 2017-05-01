package br.com.josecarlosgdacosta.androidunyleyabancodedados.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.josecarlosgdacosta.androidunyleyabancodedados.bean.GeneroBean;

/**
 * Created by josecarlos on 30/04/17.
 */

public class GeneroDAO {

    Context context;
    SQLiteDatabase db;
    public static final String TABLE = "genero";

    public GeneroDAO(Context context) {

        this.context = context;

        OpenHelper openHelper = new OpenHelper(this.context);

        this.db = openHelper.getWritableDatabase();

    }

    public List<GeneroBean> getAllGeneros() {

        Cursor cursor = this.db.rawQuery("SELECT * FROM " + TABLE, null);
        List<GeneroBean> generoList = new ArrayList<GeneroBean>();

        if (cursor.moveToFirst()) {
            do {

                GeneroBean genero = new GeneroBean();
                genero.setCodigo(Integer.parseInt(cursor.getString(0)));
                genero.setNome(cursor.getString(1));

                generoList.add(genero);

            } while (cursor.moveToNext());
        }

        return generoList;
    }
}
