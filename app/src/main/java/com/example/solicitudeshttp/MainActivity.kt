package com.example.solicitudeshttp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.Throws

class MainActivity : AppCompatActivity(), CompletadoListener {
    override fun descargaCompleta(resultado: String) {
        Log.d("descargaCompleta", resultado)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bValidarRed = findViewById<Button>(R.id.bValidarRed)
        val bSolicitudHTTP = findViewById<Button>(R.id.bSolicitudHTTP)
        val bVolley = findViewById<Button>(R.id.bVolley)

        bValidarRed.setOnClickListener {
            //Código para validar red
            if (Network.hayRed(this)){
                Toast.makeText(this, "Si hay red", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "No hay una conexión a internet", Toast.LENGTH_LONG).show()
            }
        }
        bSolicitudHTTP.setOnClickListener {
            if (Network.hayRed(this)){
                //Log.d("bSolicitudOnClick", descargarDatos("http://www.google.com"))
                DescargaURL(this).execute("http://www.google.com")
            } else {
                Toast.makeText(this, "No hay una conexión a internet", Toast.LENGTH_LONG).show()
            }
        }
        bVolley.setOnClickListener {
            if (Network.hayRed(this)){
                //Log.d("bSolicitudOnClick", descargarDatos("http://www.google.com"))
                solicitudHTTPVolley("http://www.google.com")
            } else {
                Toast.makeText(this, "No hay una conexión a internet", Toast.LENGTH_LONG).show()
            }
        }
    }
    //Método para Volley
    private fun solicitudHTTPVolley(url:String){
        val queue = Volley.newRequestQueue(this)

        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String>{
            response ->
            try {
                Log.d("solicitudHTTPVolley", response)
            }catch (e: Exception){
                //
            }
        }, Response.ErrorListener {  })
        queue.add(solicitud)
    }

}