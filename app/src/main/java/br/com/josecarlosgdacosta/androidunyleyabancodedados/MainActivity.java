package br.com.josecarlosgdacosta.androidunyleyabancodedados;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.josecarlosgdacosta.androidunyleyabancodedados.bean.FilmeBean;
import br.com.josecarlosgdacosta.androidunyleyabancodedados.bean.GeneroBean;
import br.com.josecarlosgdacosta.androidunyleyabancodedados.dao.FilmeDAO;
import br.com.josecarlosgdacosta.androidunyleyabancodedados.dao.GeneroDAO;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener{

    EditText eTitulo;
    EditText eDiretor;
    EditText eAno;
    Spinner sGenero;
    Button bBtnCadastrar;

    int generoToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.eTitulo = (EditText) findViewById(R.id.titulo_filme);
        this.eTitulo.addTextChangedListener(this);
        this.eDiretor = (EditText) findViewById(R.id.diretor_filme);
        this.eAno = (EditText) findViewById(R.id.ano_filme);
        this.sGenero = (Spinner) findViewById(R.id.genero_filme);
        this.bBtnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        this.bBtnCadastrar.setEnabled(false);

        /* Carregando a lista de gêneros do banco de dados. */
        GeneroDAO generoDAO = new GeneroDAO(this);
        List<GeneroBean> generoList = generoDAO.getAllGeneros();

        final ArrayList<Integer> arrCodigoGeneros = new ArrayList<Integer>();
        ArrayList<CharSequence> arrNomeGeneros = new ArrayList<CharSequence>();

        for(GeneroBean genero : generoList) {
            arrCodigoGeneros.add(genero.getCodigo());
            arrNomeGeneros.add(genero.getNome());
        }

        ArrayAdapter<CharSequence> generoSpinnerAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, arrNomeGeneros);
        generoSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.sGenero.setAdapter(generoSpinnerAdapter);
        this.sGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                generoToSave = arrCodigoGeneros.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bBtnCadastrar.setOnClickListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            bBtnCadastrar.setEnabled(true);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnCadastrar:

                String titulo = this.eTitulo.getText().toString();
                String diretor = this.eDiretor.getText().toString();
                int ano = Integer.parseInt(this.eAno.getText().toString());
                int genero = this.generoToSave;

                FilmeBean filme = new FilmeBean();
                filme.setTitulo(titulo);
                filme.setDiretor(diretor);
                filme.setAno(ano);
                filme.setGenero(genero);

                FilmeDAO filmeDAO = new FilmeDAO(this);
                if (filmeDAO.insert(filme)) {
                    Toast.makeText(getApplicationContext(), "Filme cadastrado com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Houve um problema ao cadastrar o filme.", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.linkVerFilmesCadastrados:

                Intent intent = new Intent(MainActivity.this, ListaFilmesActivity.class );
                startActivity(intent);
                break;
        }
    }
}
