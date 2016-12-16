package br.gracom.Visao;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.gracom.Persistencia.BancoControle;
import br.gracom.Persistencia.BaseDAO;
import br.gracom.R;

public class Eventos extends Activity {


    private SQLiteDatabase database;

    ListView listView2;
    BaseDAO helper;

    /** Chamado quando a Activity é exeutada pela primeira vez. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos2);
        listView2 = (ListView) findViewById(R.id.listaEventos);
        //cria instância da classe BaseDAO, responsável pela criação do Banco de Dados
        helper = new BaseDAO(this);
        //executa rotinas internas para abrir/utilizar o banco de dados
        database = helper.getWritableDatabase();

        BancoControle crud = new BancoControle(getBaseContext());

        final Cursor cursor = crud.carregaDados_Eventos();

        String[] nomeCampos = new String[] {BaseDAO.EVENTO_TITULO, BaseDAO.EVENTO_CORPO, BaseDAO.EVENTO_DATA};
        int[] idViews = new int[] {R.id.tituloNoticia, R.id.corpoNoticia, R.id.dataNoticia};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,
                R.layout.row, cursor, nomeCampos, idViews, 0);
        listView2 = (ListView)findViewById(R.id.listaEventos);
        listView2.setAdapter(adaptador);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BaseDAO.EVENTOS_ID));
                Intent intent = new Intent(Eventos.this, Detalhes_noticias.class);
                //intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
    public void btnEventos(View v){
        Intent intent = new Intent(this, Eventos.class);
        startActivity(intent);
        onDestroy();
    }
    public void btnInserir_Click(View v){
        //insere dados no banco de dados
        database.execSQL("INSERT INTO eventos (titulo_evento, corpo_evento, data_evento) VALUES " +
                "('Promoção de natal', 'A GRACOM está iniciando um novo curso de web voltado para o desenvolvimento de sites e também de games.', '14/12/2016')");
        //database.execSQL("INSERT INTO agenda (nome, endereco, telefone) VALUES " +
        //        "('Projeto Teste', 'Av. Brasil, 500', '(12)3456-78901')");

        Toast.makeText(this, "Registros inseridos com sucesso", Toast.LENGTH_SHORT).show();
        //String cpf = this.getIntent().getStringExtra("cpf");
        //String codigo = "telaNovaPlaca";
        Intent intent = new Intent(this, Eventos.class);
        //intent.putExtra("codigo", codigo);
        //intent.putExtra("cpf",cpf);
        startActivity(intent);
    }

    //método executado quando o usuário clica no botão voltar do aparelho
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        database.close();
    }

}
