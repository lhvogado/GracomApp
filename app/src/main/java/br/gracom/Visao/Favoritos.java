package br.gracom.Visao;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.gracom.Persistencia.BancoControle;
import br.gracom.Persistencia.BaseDAO;
import br.gracom.R;

public class Favoritos extends AppCompatActivity {
    private SQLiteDatabase database;

    ListView listView3;
    BaseDAO helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        listView3 = (ListView) findViewById(R.id.listaFAVORITOS);
        //cria instância da classe BaseDAO, responsável pela criação do Banco de Dados
        helper = new BaseDAO(this);
        //executa rotinas internas para abrir/utilizar o banco de dados
        database = helper.getWritableDatabase();

        BancoControle crud = new BancoControle(getBaseContext());

        final Cursor cursor = crud.carregaDadoByStar();

        String[] nomeCampos = new String[] {BaseDAO.NOTICIA_TITULO, BaseDAO.NOTICIA_CORPO, BaseDAO.NOTICIA_DATA};
        int[] idViews = new int[] {R.id.tituloNoticia, R.id.corpoNoticia, R.id.dataNoticia};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,
                R.layout.row, cursor, nomeCampos, idViews, 0);
        listView3 = (ListView)findViewById(R.id.listaFAVORITOS);
        listView3.setAdapter(adaptador);

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BaseDAO.NOTICIA_ID));
                Intent intent = new Intent(Favoritos.this, Detalhes_noticias.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
    public void btnHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void btnEventos(View v){
        Intent intent = new Intent(this, Eventos.class);
        startActivity(intent);
    }
    public void btnFavoritos(View v){
        Intent intent = new Intent(this, Favoritos.class);
        startActivity(intent);
    }

}
