package br.com.opet.tmm.appseriesopet.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.opet.tmm.appseriesopet.Util.BancoUtil;

/**
 * Created by Diego on 04/05/2017.
 */


public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  sql = "CREATE TABLE "+BancoUtil.TABELA_CATEGORIA+"("
                + BancoUtil.ID_CATEGORIA + " integer primary key autoincrement,"
                + BancoUtil.TITULO_CATEGORIA + " text"
                +")";
        db.execSQL(sql);

        sql = "CREATE TABLE "+BancoUtil.TABELA_SERIE+"("
                + BancoUtil.ID_SERIE + " integer primary key autoincrement,"
                + BancoUtil.TITULO_SERIE + " text,"
                + BancoUtil.TEMPORADAS_SERIE + " integer,"
                + BancoUtil.EPISODIOS_SERIE + " integer,"
                + BancoUtil.IMAGEM_SERIE + " text,"
                + BancoUtil.AVALIACAO_SERIE + " double,"
                + BancoUtil.CATEGORIA_SERIE + " integer,"
                + " FOREIGN KEY (" + BancoUtil.CATEGORIA_SERIE + ") REFERENCES " + BancoUtil.TABELA_CATEGORIA + "(" + BancoUtil.ID_CATEGORIA + ")"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_CATEGORIA);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_SERIE);

        onCreate(db);
    }
}
