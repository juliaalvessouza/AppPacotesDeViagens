package com.example.viagens.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viagens.R;
import com.example.viagens.model.Pacote;
import com.example.viagens.util.DataUtil;
import com.example.viagens.util.MoedaUtil;
import com.example.viagens.util.ResourceUtil;

import java.io.Serializable;
import java.math.BigDecimal;

import static com.example.viagens.UI.PacoteActivityConstantes.CHAVE_PACOTE;

public class ResumoCompraActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Resumo da Compra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);
        setTitle(TITULO_APP_BAR);
        carregaPacoteRecebido();
    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);
            inicializaCampos(pacote);
        }
    }

    private void inicializaCampos(Pacote pacote) {
        mostraLocal(pacote);
        mostraImagem(pacote);
        mostraData(pacote);
        mostraPreco(pacote);
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.textresumocompravalor);
        String moedaBr = MoedaUtil.formatMoedaBrasileiro(pacote.getPreco());
        preco.setText(moedaBr);
    }

    private void mostraData(Pacote pacote) {
        TextView data = findViewById(R.id.textresumocompradata);
        String diasEmTexto = DataUtil.periodoDiasEmTexto(pacote.getDias());
        data.setText(diasEmTexto);
    }

    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.imageresumocompra);
        Drawable drawableDoPacote = ResourceUtil
                .devolveDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawableDoPacote);
    }

    private void mostraLocal(Pacote pacote) {
        TextView local = findViewById(R.id.textresumocompracidade);
        local.setText(pacote.getLocal());
    }
}