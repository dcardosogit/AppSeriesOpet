package br.com.opet.tmm.appseriesopet.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.opet.tmm.appseriesopet.R;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
    }

    public void inserirSerie(View v){
        Intent intent = new Intent(DashBoardActivity.this,InsereSerieActivity.class);
        startActivity(intent);
    }

    public void listarSerie(View v){
        Intent intent = new Intent(DashBoardActivity.this,ConsultaSerieActivity.class);
        startActivity(intent);
    }

    public void inserirCategoria(View v){
        Intent intent = new Intent(DashBoardActivity.this,InsereCategoriaActivity.class);
        startActivity(intent);
    }
}
