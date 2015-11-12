package com.example.franprimo.storage;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Constructores de los 4 view de la interfaz
        final EditText campoTexto = (EditText) findViewById(R.id.editText);
        Button anyadirBtn = (Button) findViewById(R.id.button);
        final Button mostrarBtn = (Button) findViewById(R.id.button2);
        final TextView mostrarMsj = (TextView) findViewById(R.id.textView);


        //Accion del boton añadir
        anyadirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declaraciones del archivo de entrada y su envoltorio
                FileOutputStream fos = null;
                BufferedWriter bw = null;

                try {
                    //Creacion del archivo donde voy a escribir el mensaje que se pasa
                    fos = openFileOutput("datosguardados.txt", MODE_PRIVATE);
                    bw = new BufferedWriter(new OutputStreamWriter(fos));

                    //Escribo en el archivo el mensaje que se ha escrito en el editText
                    bw.write(campoTexto.getText().toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        //Controlo que se pueda cerrar el BufferedWriter
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        //Accion del boton mostrar
        mostrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declaraciones
                BufferedReader br = null;
                StringBuilder result = new StringBuilder();
                try {
                    //Creo el archivo de salida o de lectura
                    FileInputStream in = openFileInput("datosguardados.txt");
                    br = new BufferedReader(new InputStreamReader(in));
                    String str;
                    while((str = br.readLine()) != null){
                        result.append(str + "\n");
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    //Controlo que si se puede que se cierre el BufferedReader
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //Muestro el mensaje guardado en la variable result en el editText seleccionado
                mostrarMsj.setText(result);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
