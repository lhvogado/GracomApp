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
                Intent intent = new Intent(Eventos.this, Detalhes_Eventos.class);
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
    public void btnInserir_Click(View v){
        //insere dados no banco de dados
        database.execSQL("INSERT INTO eventos (titulo_evento, corpo_evento, data_evento) VALUES " +
                "('Promoção de natal', 'A GRACOM está iniciando um novo curso de web voltado para o desenvolvimento de sites e também de games.', '16/12/2016')");
        database.execSQL("INSERT INTO eventos (titulo_evento, corpo_evento, data_evento) VALUES " +
                "('Minicurso de natal', 'Minicurso de natal nos dias 15 e 16 de dezembro na sala G2. Além do Minicurso, também terão aulas extras os alunos que faltaram.', '14/12/2016')");
        database.execSQL("INSERT INTO eventos (titulo_evento, corpo_evento, data_evento) VALUES " +
                "('Atenção alunos', 'Entre os dias 24 de dezembro e 01 de janeiro não haverá aulas na gracom teresina, durante a semana haverá workshops e campeonatos abertos ao públicos, partice!.', '13/12/2016')");
        database.execSQL("INSERT INTO eventos (titulo_evento, corpo_evento, data_evento) VALUES " +
                "('Hollywood Premiada', 'A Gracom Teresina e Goiânia levam você para HOLLYWOOD! Só quem é aluno Gracom tem a chance de ganhar essa super viagem para Los Angeles com direito a visitar a maior escola....', '14/12/2016')");



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
