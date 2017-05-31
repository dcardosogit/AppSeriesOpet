package br.com.opet.tmm.appseriesopet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Diego on 04/05/2017.
 */

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDado(Serie serie){
        ContentValues valores;
        long resultado;

        /*Questão 5: Qual a diferença entre getWritableDatabase e getReadableDatabase?*/
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, serie.getTitulo());
        valores.put(CriaBanco.TEMPORADAS, serie.getTemporadas());
        valores.put(CriaBanco.EPISODIOS, serie.getEpisodios());

        /*
         * Questão 6: Expique os parâmetros da função insert do SQLiteDatabase.
         * */
        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO,banco.EPISODIOS};
        db = banco.getReadableDatabase();
        /*
         * Questão 7: Expique os parâmetros da função query do SQLiteDatabase. O que ela retorna?
         * */
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        /*
         * Questão 8: O que é um cursor no contexto co Android?
         * */
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO,banco.TEMPORADAS,banco.EPISODIOS};
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(Serie serie){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID + "=" + serie.get_ID();

        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, serie.getTitulo());
        valores.put(CriaBanco.TEMPORADAS, serie.getTemporadas());
        valores.put(CriaBanco.EPISODIOS, serie.getEpisodios());

        /*
         * Questão 9: Expique os parâmetros da função update do SQLiteDatabase.
         * */
        db.update(CriaBanco.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        /*
         * Questão 10: Expique os parâmetros da função delete do SQLiteDatabase.
         * */
        db.delete(CriaBanco.TABELA,where,null);
        db.close();
    }
}
