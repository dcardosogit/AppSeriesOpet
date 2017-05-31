package br.com.opet.tmm.appseriesopet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ConsultaActivity extends Activity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {CriaBanco.ID, CriaBanco.TITULO,CriaBanco.EPISODIOS};
        int[] idViews = new int[] {R.id.idSerie, R.id.nomeSerie,R.id.tempSerie};

        /*
         * Questão 12: O que é um SimpleCursorAdapter? Existem outros tipos de Cursores?
         * */
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.series_layout,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        /*
         * Questão 13: Explique o parâmetro da função setOnItemCLickListener. Que tipo de parametro é este?
         * */
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /*
             * Questão 14: Explique os parâmetros da função onItemClicl
             * */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                Intent intent = new Intent(ConsultaActivity.this, AlterarActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }
}
