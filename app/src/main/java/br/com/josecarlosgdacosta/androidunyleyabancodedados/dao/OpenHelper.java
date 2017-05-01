package br.com.josecarlosgdacosta.androidunyleyabancodedados.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by josecarlos on 29/04/17.
 */

public class OpenHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "filmes.db";

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Criando a tabela 'genero'.
        db.execSQL("CREATE TABLE IF NOT EXISTS genero (" +
                "codigo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL);"
        );

        // Criando a tabela 'filme'.
        db.execSQL("CREATE TABLE IF NOT EXISTS filme (" +
                "codigo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "diretor TEXT," +
                "anoLancamento INTEGER," +
                "genero INTEGER NOT NULL);"
        );

        // Fazendo a carga inicial tabela 'genero'
        db.execSQL("INSERT INTO genero VALUES (NULL, 'Comédia');");
        db.execSQL("INSERT INTO genero VALUES (NULL, 'Suspense');");
        db.execSQL("INSERT INTO genero VALUES (NULL, 'Ação');");
        db.execSQL("INSERT INTO genero VALUES (NULL, 'Ficção científica');");
        db.execSQL("INSERT INTO genero VALUES (NULL, 'Adulto');");
        db.execSQL("INSERT INTO genero VALUES (NULL, 'Terror');");
        db.execSQL("INSERT INTO genero VALUES (NULL, 'Drama');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS genero;");
        db.execSQL("DROP TABLE IF EXISTS filme;");
        onCreate(db);
    }
}
