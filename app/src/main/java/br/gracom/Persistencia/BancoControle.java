package br.gracom.Persistencia;

import android.content.ContentValues;
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
        String[] campos =  {banco.NOTICIA_ID,banco.NOTICIA_TITULO,banco.NOTICIA_CORPO,banco.NOTICIA_DATA, banco.NOTICIA_FAVORITAR};
        String where = banco.NOTICIA_ID + "=" + id_noticia;
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TBL_NOTICIA, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor carregaDadoByStar() {
        Cursor cursor;
        String[] campos =  {banco.NOTICIA_ID,banco.NOTICIA_TITULO,banco.NOTICIA_CORPO,banco.NOTICIA_DATA};
        String where = banco.NOTICIA_FAVORITAR + "= 1";
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TBL_NOTICIA, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor carregaDadoById_Evento(String id_evento) {
        Cursor cursor;
        String[] campos =  {banco.EVENTOS_ID,banco.EVENTO_TITULO,banco.EVENTO_CORPO,banco.EVENTO_DATA};
        String where = banco.EVENTOS_ID + "=" + id_evento;
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TBL_EVENTOS, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public void carregaFavorito(String id_noticia) {
        ContentValues valores;
        String where;
        db = banco.getWritableDatabase();
        where = banco.NOTICIA_ID + "=" + id_noticia;
        valores = new ContentValues();
        valores.put(banco.NOTICIA_FAVORITAR,"1");
        db.update(banco.TBL_NOTICIA,valores,where,null);

    }
}

