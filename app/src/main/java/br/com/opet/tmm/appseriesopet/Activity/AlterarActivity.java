package br.com.opet.tmm.appseriesopet.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import br.com.opet.tmm.appseriesopet.Util.BancoUtil;
import br.com.opet.tmm.appseriesopet.DAO.SerieDAO;
import br.com.opet.tmm.appseriesopet.Model.Serie;
import br.com.opet.tmm.appseriesopet.R;
import br.com.opet.tmm.appseriesopet.Util.Util;

public class AlterarActivity extends Activity {

    EditText serie;
    EditText temporadas;
    EditText episodios;
    ImageView poster;
    Button alterar;
    Button deletar;
    Cursor cursor;
    SerieDAO crud;
    String codigo;
    final int ACTIVITY_SELECT_IMAGE = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new SerieDAO(getBaseContext());

        serie = (EditText)findViewById(R.id.editText4);
        temporadas = (EditText)findViewById(R.id.editText5);
        episodios = (EditText)findViewById(R.id.editText6);
        poster = (ImageView) findViewById(R.id.imageView2);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        serie.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TITULO_SERIE)));
        temporadas.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TEMPORADAS_SERIE)));
        episodios.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.EPISODIOS_SERIE)));
        poster.setImageBitmap(Util.Base64toImage(cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.IMAGEM_SERIE))));

        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Selecione a Imagem"),ACTIVITY_SELECT_IMAGE);
            }
        });

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Serie serieObj = new Serie();
                serieObj.set_ID(Integer.parseInt(codigo));
                serieObj.setTitulo(serie.getText().toString());
                serieObj.setTemporadas(Integer.parseInt(temporadas.getText().toString()));
                serieObj.setEpisodios(Integer.parseInt(episodios.getText().toString()));
                serieObj.setImagem(Util.ImagetoBase64 (((BitmapDrawable)poster.getDrawable()).getBitmap()));
                crud.alteraRegistro(serieObj);
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ACTIVITY_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),data.getData());
                        poster.setImageBitmap(bitmap);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else if(resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(getApplicationContext(), "Cancelado.", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
