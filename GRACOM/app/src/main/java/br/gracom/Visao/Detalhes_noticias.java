package br.gracom.Visao;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.gracom.Persistencia.BancoControle;
import br.gracom.Persistencia.BaseDAO;
import br.gracom.R;

public class Detalhes_noticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_noticias);
        String cod = this.getIntent().getStringExtra("codigo");
        //TextView textTest = (TextView) findViewById(R.id.textTeste);

        long idSelected = getIntent().getLongExtra("ID", 0);
        long positionSelected = getIntent().getIntExtra("POSITION",0);
        //textTest.setText("hello");
        BancoControle crud = new BancoControle(getBaseContext());
        final Cursor cursor = crud.carregaDadoById(cod);
        //String titulo = cursor.getColumnName(cursor.getColumnIndexOrThrow(BaseDAO.NOTICIA_TITULO));
        //String corpo = cursor.getColumnName(cursor.getColumnIndexOrThrow(BaseDAO.NOTICIA_CORPO));
        //String data = cursor.getColumnName(cursor.getColumnIndexOrThrow(BaseDAO.NOTICIA_DATA));

        //String texto_noticia = titulo + "\n" + "Notícia::" + corpo + "\n" + "Data::" + data;
        //textTest.setText(titulo);

        ListView listView = (ListView) findViewById(R.id.listView2);

        String[] nomeCampos = new String[] {BaseDAO.NOTICIA_TITULO, BaseDAO.NOTICIA_CORPO, BaseDAO.NOTICIA_DATA};
        int[] idViews = new int[] {R.id.tituloNoticia, R.id.corpoNoticia, R.id.dataNoticia};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,
                R.layout.row, cursor, nomeCampos, idViews, 0);
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
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
