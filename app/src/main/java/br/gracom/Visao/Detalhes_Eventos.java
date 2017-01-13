package br.gracom.Visao;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.gracom.Persistencia.BancoControle;
import br.gracom.Persistencia.BaseDAO;
import br.gracom.R;

/**
 * Created by luiz henrique on 16/12/2016.
 */
public class Detalhes_Eventos extends AppCompatActivity {
    ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_eventos);
        String cod = this.getIntent().getStringExtra("codigo");
        long idSelected = getIntent().getLongExtra("ID", 0);
        long positionSelected = getIntent().getIntExtra("POSITION",0);

        BancoControle crud = new BancoControle(getBaseContext());
        final Cursor cursor = crud.carregaDadoById_Evento(cod);

        String[] nomeCampos = new String[] {BaseDAO.EVENTO_TITULO, BaseDAO.EVENTO_CORPO, BaseDAO.EVENTO_DATA};
        int[] idViews = new int[] {R.id.tituloNoticia, R.id.corpoNoticia, R.id.dataNoticia};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,
                R.layout.row, cursor, nomeCampos, idViews, 0);
        ls = (ListView)findViewById(R.id.detalhes_Eventos);
        ls.setAdapter(adaptador);

    }
    public void btnBack_Click(View v){
        Intent intent = new Intent(this, Eventos.class);
        startActivity(intent);
    }
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
