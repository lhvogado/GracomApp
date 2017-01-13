package br.gracom.Persistencia;

/**
 * Created by luiz henrique on 27/11/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Classe responsável pela criação do Banco de Dados e tabelas
public class BaseDAO extends SQLiteOpenHelper {

    public static final String TBL_NOTICIA = "noticias";
    public static final String NOTICIA_ID = "_id";
    public static final String NOTICIA_TITULO = "titulo";
    public static final String NOTICIA_CORPO = "corpo";
    public static final String NOTICIA_DATA = "data";
    public static final String NOTICIA_FAVORITAR = "star";
    public static final String NOTICIA_FOTO = "foto";


    public static final String TBL_EVENTOS = "eventos";
    public static final String EVENTOS_ID = "_id";
    public static final String EVENTO_TITULO = "titulo_evento";
    public static final String EVENTO_CORPO = "corpo_evento";
    public static final String EVENTO_DATA = "data_evento";
    //public static final String EVENTO_FAVORITAR = "star_evento";
    //public static final String EVENTO_FOTO = "foto_evento";

    private static final String DATABASE_NAME = "noticias.db";
    private static final int DATABASE_VERSION = 14;

    //Estrutura da tabela Agenda (sql statement)
    private static final String CREATE_NOTICIA = "create table " +
            TBL_NOTICIA + "( " + NOTICIA_ID       + " integer primary key autoincrement, " +
            NOTICIA_TITULO     + " text not null, " +
            NOTICIA_CORPO + " text not null, " +
            NOTICIA_FAVORITAR + " text not null, " +
            NOTICIA_FOTO + " blob, " +
            NOTICIA_DATA + " text not null);";

    private static final String CREATE_EVENTO = "create table " +
            TBL_EVENTOS + "( " + EVENTOS_ID       + " integer primary key autoincrement, " +
            EVENTO_TITULO     + " text not null, " +
            EVENTO_CORPO + " text not null, " +
            //EVENTO_FAVORITAR + " text not null, " +
            //EVENTO_FOTO + " blob, " +
            EVENTO_DATA + " text not null);";

    public BaseDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //Criação da tabela
        database.execSQL(CREATE_NOTICIA);
        database.execSQL(CREATE_EVENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Caso seja necessário mudar a estrutura da tabela
        //deverá primeiro excluir a tabela e depois recriá-l
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NOTICIA);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_EVENTOS);
        onCreate(db);
    }
}