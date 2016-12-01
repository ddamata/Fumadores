package com.example.daniel.cleintefumador;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Created by Daniel on 11/16/16.
 */

public class Fumador extends AsyncTask<Void, Void, Void> {
        String ipServidor, response,idFumador;
        int  puerto;
        TextView tResponse;

        Fumador (String ip, String id, int puerto, TextView Responce){
            ipServidor =  ip;
            idFumador = id;
            this.puerto = puerto;
            this.tResponse = Responce;

        }
    @Override
    protected Void doInBackground(Void... arg0) {

        Log.i("estatus", "entre en el metodo conetar de la clase fumador");


        Log.i("mjs", " socket " + ipServidor + " " + puerto);
        try {
            Socket sk = new Socket(ipServidor, puerto);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(sk.getInputStream()));
            PrintWriter salida = new PrintWriter(
                    new OutputStreamWriter(sk.getOutputStream()), true);
            Log.i("mjs", "enviando...");
            response = "entre en el try";
            if (sk != null && salida != null && entrada != null) {
                salida.println(idFumador + "xFumador");
                response = "Fumador Conectado";
                while (true) {
                    if (entrada.readLine() != null) {
                        //System.out.println(entrada.readLine());

                        String mensaje[] = entrada.readLine().split("x");
                        response = mensaje[0]+" "+mensaje[1]+" "+ mensaje[2];
                        Traza.insertarTraza(mensaje[0], mensaje[1], mensaje[2]);
                    }
                }
                //sk.close();

            }
        } catch (UnknownHostException e) {

            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            response = "IOException: " + e.toString();
        }
        return null;
    }

    protected void onPostExecute(Void result) {
            tResponse.setText(response);
            super.onPostExecute(result);
        }
    }


