package br.com.josecarlosgdacosta.androidunyleyabancodedados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.josecarlosgdacosta.androidunyleyabancodedados.adapter.FilmeAdapter;
import br.com.josecarlosgdacosta.androidunyleyabancodedados.bean.FilmeBean;
import br.com.josecarlosgdacosta.androidunyleyabancodedados.dao.FilmeDAO;

public class ListaFilmesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FilmeAdapter filmeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FilmeDAO filmeDAO = new FilmeDAO(this);
        List<FilmeBean> listaFilmes = filmeDAO.getAll();

        this.recyclerView = (RecyclerView) findViewById(R.id.listaFilmes);
        filmeAdapter = new FilmeAdapter(listaFilmes);
        RecyclerView.LayoutManager filmeLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.recyclerView.setLayoutManager(filmeLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setAdapter(filmeAdapter);
    }
}
