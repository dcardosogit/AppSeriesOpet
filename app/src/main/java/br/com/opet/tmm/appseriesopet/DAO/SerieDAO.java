package br.com.opet.tmm.appseriesopet.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.opet.tmm.appseriesopet.Util.BancoUtil;
import br.com.opet.tmm.appseriesopet.Factory.DatabaseFactory;
import br.com.opet.tmm.appseriesopet.Model.Serie;

/**
 * Created by Diego on 04/05/2017.
 */

public class SerieDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public SerieDAO(Context context){
        banco = new DatabaseFactory(context);
    }

    public String insereDado(Serie serie){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_SERIE, serie.getTitulo());
        valores.put(BancoUtil.TEMPORADAS_SERIE, serie.getTemporadas());
        valores.put(BancoUtil.EPISODIOS_SERIE, serie.getEpisodios());
        valores.put(BancoUtil.IMAGEM_SERIE, serie.getImagem());

        resultado = db.insert(BancoUtil.TABELA_SERIE, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {BancoUtil.ID_SERIE,BancoUtil.TITULO_SERIE,BancoUtil.EPISODIOS_SERIE};
        db = banco.getReadableDatabase();

        cursor = db.query(BancoUtil.TABELA_SERIE, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {BancoUtil.ID_SERIE,BancoUtil.TITULO_SERIE,BancoUtil.TEMPORADAS_SERIE,BancoUtil.EPISODIOS_SERIE,BancoUtil.IMAGEM_SERIE};
        String where = BancoUtil.ID_SERIE + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(BancoUtil.TABELA_SERIE,campos,where, null, null, null, null, null);

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

        where = BancoUtil.ID_SERIE + "=" + serie.get_ID();

        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_SERIE, serie.getTitulo());
        valores.put(BancoUtil.TEMPORADAS_SERIE, serie.getTemporadas());
        valores.put(BancoUtil.EPISODIOS_SERIE, serie.getEpisodios());
        valores.put(BancoUtil.IMAGEM_SERIE, serie.getImagem());

        db.update(BancoUtil.TABELA_SERIE,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_SERIE + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_SERIE,where,null);
        db.close();
    }
}
