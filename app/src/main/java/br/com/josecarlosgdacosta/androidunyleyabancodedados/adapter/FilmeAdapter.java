package br.com.josecarlosgdacosta.androidunyleyabancodedados.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.josecarlosgdacosta.androidunyleyabancodedados.R;
import br.com.josecarlosgdacosta.androidunyleyabancodedados.bean.FilmeBean;

/**
 * Created by josecarlos on 30/04/17.
 */

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.DetalheFilme>{

    List<FilmeBean> filmesList = new ArrayList<FilmeBean>();

    public class DetalheFilme extends RecyclerView.ViewHolder{

        TextView tDtlTitulo;
        TextView tDtlGenero;
        TextView tDtlDiretor;
        TextView tDtlAno;

        public DetalheFilme(View itemView) {
            super(itemView);

            tDtlTitulo = (TextView) itemView.findViewById(R.id.dtl_titulo);
            tDtlGenero = (TextView) itemView.findViewById(R.id.dtl_genero);
            tDtlDiretor = (TextView) itemView.findViewById(R.id.dtl_diretor);
            tDtlAno = (TextView) itemView.findViewById(R.id.dtl_ano);

        }
    }

    public FilmeAdapter(List<FilmeBean> filmesList) {
        this.filmesList = filmesList;
    }

    @Override
    public DetalheFilme onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_filme_detalhe, parent, false);
        return new DetalheFilme(itemView);
    }

    @Override
    public void onBindViewHolder(DetalheFilme holder, int position) {
        FilmeBean filme = this.filmesList.get(position);
        holder.tDtlTitulo.setText(filme.getTitulo());
        //holder.tDtlGenero.setText(String.valueOf(filme.getGenero()));
        holder.tDtlGenero.setText(filme.getNomeGenero());
        holder.tDtlDiretor.setText("Direção: " + filme.getDiretor());
        holder.tDtlAno.setText(String.valueOf(filme.getAno()));
    }

    @Override
    public int getItemCount() {
        return this.filmesList.size();
    }

}
