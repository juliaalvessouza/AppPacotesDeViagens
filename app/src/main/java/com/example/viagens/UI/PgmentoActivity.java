package com.example.viagens.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.viagens.R;
import com.example.viagens.model.Pacote;
import com.example.viagens.util.MoedaUtil;

import static com.example.viagens.UI.PacoteActivityConstantes.CHAVE_PACOTE;

public class PgmentoActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Pagamento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgmento);
        setTitle(TITULO_APP_BAR);

        carregaPacoteRecebido();

    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            mostraPreco(pacote);

            configuraButton(pacote);
        }
    }

    private void configuraButton(Pacote pacote) {
        Button buttonFinalizaCompra = findViewById(R.id.buttonFinalizarCompra);
        buttonFinalizaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaResumoCompra(pacote);
            }
        });
    }

    private void vaiParaResumoCompra(Pacote pacote) {
        Intent intent = new Intent(PgmentoActivity.this, ResumoCompraActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.textValor);
        String moedaBr = MoedaUtil.formatMoedaBrasileiro(pacote.getPreco());
        preco.setText(moedaBr);
    }
}