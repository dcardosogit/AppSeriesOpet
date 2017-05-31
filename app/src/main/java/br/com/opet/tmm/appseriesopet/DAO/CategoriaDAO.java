package br.com.opet.tmm.appseriesopet.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.opet.tmm.appseriesopet.Util.BancoUtil;
import br.com.opet.tmm.appseriesopet.Model.Categoria;
import br.com.opet.tmm.appseriesopet.Factory.DatabaseFactory;

/**
 * Created by Diego on 04/05/2017.
 */

public class CategoriaDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public CategoriaDAO(Context context){
        banco = new DatabaseFactory(context);
    }

    public String insereDado(Categoria categoria){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_CATEGORIA, categoria.getCategoria());

        resultado = db.insert(BancoUtil.TABELA_CATEGORIA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {BancoUtil.ID_CATEGORIA,BancoUtil.TITULO_CATEGORIA};
        db = banco.getReadableDatabase();

        cursor = db.query(BancoUtil.TABELA_CATEGORIA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {BancoUtil.ID_CATEGORIA,BancoUtil.TITULO_CATEGORIA};
        String where = BancoUtil.ID_CATEGORIA + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(BancoUtil.TABELA_CATEGORIA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(Categoria categoria){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.ID_CATEGORIA + "=" + categoria.get_ID();

        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_CATEGORIA, categoria.getCategoria());

        db.update(BancoUtil.TABELA_CATEGORIA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_CATEGORIA + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_CATEGORIA,where,null);
        db.close();
    }
}
