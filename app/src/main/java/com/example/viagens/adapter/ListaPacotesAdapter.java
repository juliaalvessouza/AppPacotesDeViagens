package com.example.viagens.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viagens.R;
import com.example.viagens.model.Pacote;
import com.example.viagens.util.DiasUtil;
import com.example.viagens.util.MoedaUtil;
import com.example.viagens.util.ResourceUtil;

import java.util.List;

public class ListaPacotesAdapter extends BaseAdapter {

    private final List<Pacote> pacotes;
    private final Context context;

    public ListaPacotesAdapter(List<Pacote> pacotes, Context context) {
        this.pacotes = pacotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Pacote getItem(int position) {
        return pacotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_pacote, parent, false);
        Pacote pacote = pacotes.get(position);

        mostraLocal(viewCriada, pacote);
        mostraImagem(viewCriada, pacote);
        mostraDias(viewCriada, pacote);
        mostraPrecos(viewCriada, pacote);

        return viewCriada;
    }

    private void mostraPrecos(View viewCriada, Pacote pacote) {
        TextView preco = viewCriada.findViewById(R.id.textPreco);
        String moedaBr = MoedaUtil.formatMoedaBrasileiro(pacote.getPreco());
        preco.setText(moedaBr);
    }

    private void mostraDias(View viewCriada, Pacote pacote) {
        TextView dias = viewCriada.findViewById(R.id.textDias);
        String diasEmTexto = DiasUtil.formatEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    private void mostraImagem(View viewCriada, Pacote pacote) {
        ImageView imagem = viewCriada.findViewById(R.id.imageCidade);
        Drawable drawableImagePAcote = ResourceUtil.devolveDrawable(context, pacote.getImagem());
        imagem.setImageDrawable(drawableImagePAcote);
    }

    private void mostraLocal(View viewCriada, Pacote pacote) {
        TextView local = viewCriada.findViewById(R.id.textCidade);
        local.setText(pacote.getLocal());
    }
}
