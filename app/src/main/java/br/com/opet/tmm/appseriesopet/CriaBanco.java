package br.com.opet.tmm.appseriesopet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diego on 04/05/2017.
 */


/*
* Questão 1: Qual a finalidade do SQLiteOpenHelper?
* */
public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "series";
    public static final String ID = "_id";
    public static final String TITULO = "titulo";
    public static final String TEMPORADAS = "temporadas";
    public static final String EPISODIOS = "episodios";
    public static final String IMAGEM = "thumb";
    public static final int VERSAO = 3;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    /*
    * Questão 2: O que é a classe SQLiteDatabase?
    * Questão 3: Qual a finalidade da função execSQL?
    * Questão 4: O que são as funções onCreate e onUpgrade? Elas pertencem a qual classe?
    * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + TITULO + " text,"
                + TEMPORADAS + " integer,"
                + EPISODIOS + " integer,"
                + IMAGEM + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
