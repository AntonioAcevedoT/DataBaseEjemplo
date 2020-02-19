package com.example.databaseexample;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //referencias a los 4 botones y a los edittext de la ui de la activity
    Button btnInsertar,btnDelete,btnSearch,btnSelect;
    EditText edtName,edtDelete,edtage,edtsearch;
   //referenciar a nuetsra data manager para para llamar a los diferentes metodos segun el boton pulsado
    DataManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DataManager(this);

        btnInsertar= findViewById(R.id.buttonInsertar);
        btnDelete=findViewById(R.id.buttonBorrar);
        btnSearch=findViewById(R.id.buttonBuscar);
        btnSelect=findViewById(R.id.btn_seleccionar);

        edtage=findViewById(R.id.edt_age);
        edtDelete=findViewById(R.id.editTextBorrar);
        edtName=findViewById(R.id.edit_name);
        edtsearch=findViewById(R.id.editTextBuscar);

        btnSelect.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnInsertar.setOnClickListener(this);

    }

    public void showData(Cursor cursor){
        while (cursor.moveToNext()){
            Log.i(cursor.getString(1),cursor.getString(2));
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonInsertar:
                //aqui podemos filtrar antes del llamar al data manager la inf que metieron en el editext
                dm.insertar(edtName.getText().toString(),edtage.getText().toString());
                break;
            case R.id.buttonBorrar:
                dm.eliminar(edtDelete.getText().toString());
                break;
            case R.id.buttonBuscar:
                showData(dm.buscar(edtsearch.getText().toString()));
                break;
            case R.id.btn_seleccionar:
                showData(dm.selectAll());
                break;

        }
    }
}
