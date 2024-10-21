package com.example.projetws;

import static android.content.ContentValues.TAG;

import android.content.Intent;  // Import pour Intent
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetws.beans.Etudiant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class addEtudiant extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextText;
    private EditText editTextText2;
    private Spinner spinner;
    private RadioButton radioButtonHomme;
    private RadioButton radioButtonFemme;
    private Button button;
    private RequestQueue requestQueue;
    private String insertUrl = "http://10.0.2.2/projet/ws/createEtudiant.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        // Initialisation de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialisation des vues
        editTextText = findViewById(R.id.editTextText);
        editTextText2 = findViewById(R.id.editTextText2);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);
        radioButtonHomme = findViewById(R.id.radioButtonHomme);
        radioButtonFemme = findViewById(R.id.radioButtonFemme);

        // Définir l'écouteur de clic
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            // Créer une nouvelle requête
            requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest request = new StringRequest(Request.Method.POST, insertUrl,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Raw response: " + response); // Afficher la réponse brute

                            // Vérifiez si la réponse commence par un crochet pour déterminer si c'est un tableau JSON
                            if (response.startsWith("[")) {
                                // La réponse est un tableau JSON
                                Type type = new TypeToken<Collection<Etudiant>>(){}.getType();
                                Collection<Etudiant> etudiants = new Gson().fromJson(response, type);

                                // Itérez sur les étudiants pour afficher leurs informations
                                for (Etudiant e : etudiants) {
                                    Log.d(TAG, e.toString());
                                }
                            } else {
                                // La réponse n'est pas un tableau, traitez-la comme un message de succès ou d'erreur
                                Log.d(TAG, "Message: " + response);

                                // Afficher un message de succès ou d'erreur dans un Toast
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }

                            // Une fois l'ajout réussi, naviguer vers EtudiantListActivity
                            Intent intent = new Intent(addEtudiant.this, EtudiantListActivity.class);
                            startActivity(intent);
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(addEtudiant.this, "Erreur lors de l'ajout de l'étudiant : " + error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    String sexe = radioButtonHomme.isChecked() ? "homme" : "femme";

                    // Préparer les paramètres à envoyer
                    HashMap<String, String> params = new HashMap<>();
                    params.put("nom", editTextText.getText().toString().trim());
                    params.put("prenom", editTextText2.getText().toString().trim());
                    params.put("ville", spinner.getSelectedItem().toString().trim());
                    params.put("sexe", sexe);
                    return params;
                }
            };

            // Ajouter la requête à la file d'attente
            requestQueue.add(request);
        }

    }
}
