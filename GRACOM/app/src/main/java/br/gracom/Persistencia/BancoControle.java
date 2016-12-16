package br.gracom.Persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by luiz henrique on 03/12/2016.
 */
public class BancoControle {
    private SQLiteDatabase db;
    private BaseDAO banco;

    public BancoControle(Context context){
        banco = new BaseDAO(context);
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.NOTICIA_ID,banco.NOTICIA_TITULO,banco.NOTICIA_CORPO,banco.NOTICIA_DATA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TBL_NOTICIA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor carregaDados_Eventos(){
        Cursor cursor;
        String[] campos =  {banco.EVENTOS_ID,banco.EVENTO_TITULO,banco.EVENTO_CORPO,banco.EVENTO_DATA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TBL_EVENTOS, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor carregaDadoById(String id_noticia) {
        Cursor cursor;
        //O problema é o _id
        String[] campos =  {banco.NOTICIA_ID,banco.NOTICIA_TITULO,banco.NOTICIA_CORPO,banco.NOTICIA_DATA};
        String where = banco.NOTICIA_ID + "=" + id_noticia;
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TBL_NOTICIA, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}

