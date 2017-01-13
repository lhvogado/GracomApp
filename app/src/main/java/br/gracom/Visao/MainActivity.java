package br.gracom.Visao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import br.gracom.Persistencia.BancoControle;
import br.gracom.Persistencia.BaseDAO;
import br.gracom.R;

public class MainActivity extends Activity {
    private SQLiteDatabase database;

    ListView listView;
    BaseDAO helper;

    /** Chamado quando a Activity é exeutada pela primeira vez. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView1);
        //cria instância da classe BaseDAO, responsável pela criação do Banco de Dados
        helper = new BaseDAO(this);
        //executa rotinas internas para abrir/utilizar o banco de dados
        database = helper.getWritableDatabase();

        BancoControle crud = new BancoControle(getBaseContext());

        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {BaseDAO.NOTICIA_TITULO, BaseDAO.NOTICIA_CORPO, BaseDAO.NOTICIA_DATA};
        int[] idViews = new int[] {R.id.tituloNoticia, R.id.corpoNoticia, R.id.dataNoticia};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,
                R.layout.row, cursor, nomeCampos, idViews, 0);
        listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BaseDAO.NOTICIA_ID));
                Intent intent = new Intent(MainActivity.this, Detalhes_noticias.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
    public void btnEventos(View v){
        Intent intent = new Intent(this, Eventos.class);
        startActivity(intent);
    }
    public void btnFavoritos(View v){
        Intent intent = new Intent(this, Favoritos.class);
        startActivity(intent);
    }
       public void btnInserir_Click(View v){
            //insere dados no banco de dados
           database.execSQL("INSERT INTO noticias (titulo, corpo, data, star) VALUES " +
                   "('Curso de webdesing', 'A GRACOM está iniciando um novo curso de web voltado para o desenvolvimento de sites e também de games.', '16/12/2016', '0')");
           database.execSQL("INSERT INTO noticias (titulo, corpo, data, star) VALUES " +
                   "('Filme de aluno da gracom', 'O nosso aluno Paulo Eduardo está gravando um filme bem criativo na sua cidade, Dermeval Lobão, confira o trailer.', '13/12/2016', '0')");
           database.execSQL("INSERT INTO noticias (titulo, corpo, data, star) VALUES " +
                   "('Professores aprovados', 'A Gracom tem o orgulho de Parabenizar os nossos professores Anne Fortes, Ivan Magalhães e Antônio Carlos pela conquista.', '11/12/2016', '0')");
           database.execSQL("INSERT INTO noticias (titulo, corpo, data, star) VALUES " +
                   "('Curso de webdesing', 'A GRACOM está iniciando um novo curso de web voltado para o desenvolvimento de sites e também de games.', '11/12/2016', '0')");

           Toast.makeText(this, "Registros inseridos com sucesso", Toast.LENGTH_SHORT).show();
           //String cpf = this.getIntent().getStringExtra("cpf");
           //String codigo = "telaNovaPlaca";
           Intent intent = new Intent(this, MainActivity.class);
           //intent.putExtra("codigo", codigo);
           //intent.putExtra("cpf",cpf);
           startActivity(intent);
        }

    //método executado quando o usuário clica no botão voltar do aparelho
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //deleta registros inseridos, simplesmente para limpar essa base que é de teste
        //database.execSQL("DELETE FROM NOTICIAS");
        //fecha a conexão com o Banco de dados
        database.close();
    }
/*
    <TextView
    android:id="@+id/idNoticia"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textAppearance="?android:attr/textAppearanceMedium"
    android:text="Nome"
    android:layout_alignParentLeft="true"/>
    */
}