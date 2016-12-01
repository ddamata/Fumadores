package com.example.daniel.cleintefumador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Spinner idFumador;
    private String[] arraySpinner;

    public void  coneccion  (View view) {

        EditText ipServidor = (EditText) findViewById(R.id.IpServidor);
        Spinner idFumador = (Spinner) findViewById(R.id.IdFuamdor);
        TextView mjs = (TextView) findViewById(R.id.mjs);
        Fumador f =  new Fumador(ipServidor.getText().toString(),String.valueOf(idFumador.getSelectedItemPosition()),50008, mjs);
        f.execute();


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.arraySpinner = new String[] {
                "Tabaco", "Papel", "Fosforo"
        };
        Spinner s = (Spinner) findViewById(R.id.IdFuamdor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);




    }
}
