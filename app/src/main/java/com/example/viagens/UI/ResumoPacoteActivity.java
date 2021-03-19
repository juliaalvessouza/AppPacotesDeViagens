package com.example.viagens.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viagens.R;
import com.example.viagens.model.Pacote;
import com.example.viagens.util.DataUtil;
import com.example.viagens.util.DiasUtil;
import com.example.viagens.util.MoedaUtil;
import com.example.viagens.util.ResourceUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.viagens.UI.PacoteActivityConstantes.CHAVE_PACOTE;

public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Resumo do Pacote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        setTitle(TITULO_APP_BAR);
        carregaPacoteRecebido();
    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            inicializaCampos(pacote);
            configuraButton(pacote);
        }
    }

    private void configuraButton(Pacote pacote) {
        Button buttonRealizaPgmento = findViewById(R.id.buttonPacoteResumoFinalizarPgmento);
        buttonRealizaPgmento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaPgmento(pacote);
            }
        });
    }

    private void vaiParaPgmento(Pacote pacote) {
        Intent intent = new Intent(ResumoPacoteActivity.this, PgmentoActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }

    private void inicializaCampos(Pacote pacote) {
        mostraLocal(pacote);
        mostraImagem(pacote);
        mostraDias(pacote);
        mostraPreco(pacote);
        mostraData(pacote);
    }

    private void mostraData(Pacote pacote) {
        TextView data = findViewById(R.id.textPacoteResumoData);
        String dataDaViagem = DataUtil.periodoDiasEmTexto(pacote.getDias());
        data.setText(dataDaViagem);
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.textPacoteResumoPreco);
        String moedaBr = MoedaUtil.formatMoedaBrasileiro(pacote.getPreco());
        preco.setText(moedaBr);
    }

    private void mostraDias(Pacote pacote) {
        TextView dias = findViewById(R.id.textPacoteResumoDias);
        String diasEmTexto = DiasUtil.formatEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.imagePacoteResumo);
        Drawable drawableDoPacote = ResourceUtil.devolveDrawable(this, pacote.getImagem());
    }

    private void mostraLocal(Pacote pacote) {
        TextView local = findViewById(R.id.textPacoteResumoLocal);
        local.setText(pacote.getLocal());
    }
}