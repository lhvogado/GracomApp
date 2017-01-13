package br.gracom.Visao;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import br.gracom.Persistencia.BancoControle;
import br.gracom.Persistencia.BaseDAO;
import br.gracom.R;

import static br.gracom.R.drawable.logo_gracom;


public class Detalhes_noticias extends AppCompatActivity {
    long idSelected;
    long positionSelected;
    String cod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_noticias);
        cod = this.getIntent().getStringExtra("codigo");

        idSelected = getIntent().getLongExtra("ID", 0);
        positionSelected = getIntent().getIntExtra("POSITION",0);

        BancoControle crud = new BancoControle(getBaseContext());
        final Cursor cursor = crud.carregaDadoById(cod);

        ListView listView = (ListView) findViewById(R.id.listView2);
        ImageView imageView1;



        String[] nomeCampos = new String[] {BaseDAO.NOTICIA_TITULO, BaseDAO.NOTICIA_CORPO, BaseDAO.NOTICIA_DATA, BaseDAO.NOTICIA_FAVORITAR};
        int[] idViews = new int[] {R.id.titulo, R.id.corpo, R.id.data, R.id.star};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,
                R.layout.detalhes, cursor, nomeCampos, idViews, 0);
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(adaptador);
    }
    public void btnBack_Click(View v){
        //insere dados no banco de dados
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("codigo", codigo);
        //intent.putExtra("cpf",cpf);
        startActivity(intent);
    }
    public void BntFavoritar(View v){
        BancoControle crud = new BancoControle(getBaseContext());
        final Cursor cursor = crud.carregaDadoById(cod);
        crud.carregaFavorito(cod);
        Toast.makeText(getApplicationContext(),"Adicionado aos favoritos",Toast.LENGTH_LONG).show();
    }
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
