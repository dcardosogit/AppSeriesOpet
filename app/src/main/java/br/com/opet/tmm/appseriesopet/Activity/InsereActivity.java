package br.com.opet.tmm.appseriesopet.Activity;

import android.app.Activity;
import android.content.Intent;
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

import br.com.opet.tmm.appseriesopet.DAO.SerieDAO;
import br.com.opet.tmm.appseriesopet.Model.Serie;
import br.com.opet.tmm.appseriesopet.R;
import br.com.opet.tmm.appseriesopet.Util.Util;

public class InsereActivity extends Activity {
    final int ACTIVITY_SELECT_IMAGE = 1234;
    ImageView imageView;
    String base64Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere);
        base64Image = null;
        Button botao = (Button)findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Selecione a Imagem"),ACTIVITY_SELECT_IMAGE);
            }
        });

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SerieDAO crud = new SerieDAO(getBaseContext());
                EditText titulo = (EditText)findViewById(R.id.editText);
                EditText temporadas = (EditText)findViewById((R.id.editText2));
                EditText episodios = (EditText)findViewById(R.id.editText3);
                String tituloString = titulo.getText().toString();
                int temporadasInt = Integer.parseInt(temporadas.getText().toString());
                int episodiosInt =  Integer.parseInt(episodios.getText().toString());
                String imagemString = Util.ImagetoBase64 (((BitmapDrawable)imageView.getDrawable()).getBitmap());
                String resultado;

                Serie serie = new Serie(tituloString,temporadasInt,episodiosInt);
                serie.setImagem(imagemString);
                resultado = crud.insereDado(serie);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(InsereActivity.this,ConsultaActivity.class);
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
                        imageView.setImageBitmap(bitmap);
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
