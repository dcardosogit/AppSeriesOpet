package br.com.opet.tmm.appseriesopet.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.opet.tmm.appseriesopet.Util.BancoUtil;
import br.com.opet.tmm.appseriesopet.DAO.SerieDAO;
import br.com.opet.tmm.appseriesopet.R;

public class ConsultaSerieActivity extends Activity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_serie);

        SerieDAO crud = new SerieDAO(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {BancoUtil.ID_SERIE, BancoUtil.TITULO_SERIE,BancoUtil.EPISODIOS_SERIE};
        int[] idViews = new int[] {R.id.idSerie, R.id.nomeSerie,R.id.tempSerie};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.series_layout,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /*
             * Questão 14: Explique os parâmetros da função onItemClicl
             * */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.ID_SERIE));
                Intent intent = new Intent(ConsultaSerieActivity.this, AlterarSerieActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }
}
