package br.com.opet.tmm.appseriesopet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class AlterarActivity extends Activity {

    EditText serie;
    EditText temporadas;
    EditText episodios;
    ImageView poster;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;
    final int ACTIVITY_SELECT_IMAGE = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        serie = (EditText)findViewById(R.id.editText4);
        temporadas = (EditText)findViewById(R.id.editText5);
        episodios = (EditText)findViewById(R.id.editText6);
        poster = (ImageView) findViewById(R.id.imageView2);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        serie.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        temporadas.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TEMPORADAS)));
        episodios.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EPISODIOS)));
        poster.setImageBitmap(Util.Base64toImage(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.IMAGEM))));

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
