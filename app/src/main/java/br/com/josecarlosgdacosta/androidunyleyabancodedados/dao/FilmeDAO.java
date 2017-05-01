package br.com.josecarlosgdacosta.androidunyleyabancodedados.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.josecarlosgdacosta.androidunyleyabancodedados.bean.FilmeBean;

/**
 * Created by josecarlos on 30/04/17.
 */

public class FilmeDAO {

    Context context;
    SQLiteDatabase db;
    public static final String TABLE = "filme";

    public FilmeDAO(Context context) {

        this.context = context;

        OpenHelper openHelper = new OpenHelper(this.context);

        this.db = openHelper.getWritableDatabase();


    }

    public List<FilmeBean> getAll() {

        Cursor cursor = this.db.rawQuery("SELECT f.*, g.nome FROM " + TABLE + " f INNER JOIN genero g WHERE f.codigo = g.codigo ", null);
        List<FilmeBean> filmeList = new ArrayList<FilmeBean>();

        if (cursor.moveToFirst()) {

            do {

                FilmeBean filme = new FilmeBean();
                filme.setCodigo(Integer.parseInt(cursor.getString(0)));
                filme.setTitulo(cursor.getString(1));
                filme.setDiretor(cursor.getString(2));
                filme.setAno(Integer.parseInt(cursor.getString(3)));
                //filme.setGenero(Integer.parseInt(cursor.getString(4)));
                filme.setNomeGenero(cursor.getString(5));

                filmeList.add(filme);

            } while (cursor.moveToNext());
        }

        return filmeList;

    }

    public boolean insert(FilmeBean filme) {

        ContentValues values = new ContentValues();
        values.put("codigo", (byte[]) null);
        values.put("titulo", filme.getTitulo());
        values.put("diretor", filme.getDiretor());
        values.put("anoLancamento", filme.getAno());
        values.put("genero", filme.getGenero());

        long newRegister = this.db.insert(TABLE, null, values);
        if (newRegister != -1) {
            return true;
        }

        return false;
    }
}
