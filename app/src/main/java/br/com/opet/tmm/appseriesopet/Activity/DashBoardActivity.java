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

    public void inserir(View v){
        Intent intent = new Intent(DashBoardActivity.this,InsereActivity.class);
        startActivity(intent);
    }

    public void listar(View v){
        Intent intent = new Intent(DashBoardActivity.this,ConsultaActivity.class);
        startActivity(intent);
    }
}
