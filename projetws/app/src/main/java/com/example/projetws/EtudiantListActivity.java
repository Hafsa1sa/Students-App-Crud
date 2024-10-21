package com.example.projetws;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetws.adapter.EtudiantAdapter;
import com.example.projetws.beans.Etudiant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtudiantListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EtudiantAdapter etudiantAdapter;
    private RequestQueue requestQueue;
    private String loadUrl = "http://10.0.2.2/projet/ws/loadEtudiant.php";
    private String deleteUrl = "http://10.0.2.2/projet/ws/deleteEtudiant.php"; // URL pour supprimer un étudiant
    private String updateUrl = "http://10.0.2.2/projet/ws/updateEtudiant.php"; // URL pour mettre à jour un étudiant
    private List<Etudiant> etudiantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_list);

        recyclerView = findViewById(R.id.recyclerViewEtudiants);
        etudiantList = new ArrayList<>();

        // Initialiser RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        etudiantAdapter = new EtudiantAdapter(etudiantList, this); // Passer le contexte à l'adaptateur
        recyclerView.setAdapter(etudiantAdapter);

        // Initialiser Volley RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        // Envoyer la requête pour charger les étudiants
        loadEtudiantsFromServer();
    }

    private void loadEtudiantsFromServer() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("loadEtudiant", "Réponse brute du serveur : " + response);

                        try {
                            // Tenter de convertir en JSON
                            if (response.startsWith("{")) {
                                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                                if (jsonObject.has("error")) {
                                    Toast.makeText(EtudiantListActivity.this, jsonObject.get("error").getAsString(), Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }

                            // Convertir la réponse JSON en une liste d'objets Etudiant
                            Type listType = new TypeToken<Collection<Etudiant>>(){}.getType();
                            Collection<Etudiant> etudiants = new Gson().fromJson(response, listType);

                            if (etudiants != null && !etudiants.isEmpty()) {
                                etudiantList.clear();
                                etudiantList.addAll(etudiants);
                                etudiantAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(EtudiantListActivity.this, "Aucun étudiant trouvé.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e("loadEtudiant", "Erreur lors de la conversion JSON : " + e.getMessage());
                            Toast.makeText(EtudiantListActivity.this, "Erreur de format de réponse : " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EtudiantListActivity.this, "Erreur lors du chargement des étudiants : " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        // Ajouter la requête à la queue
        requestQueue.add(stringRequest);
    }

    // Méthode pour afficher une boîte de dialogue de confirmation pour la suppression
    public void showDeleteDialog(Etudiant etudiant, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Supprimer l'étudiant")
                .setMessage("Voulez-vous vraiment supprimer cet étudiant ?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    deleteEtudiant(etudiant.getId(), position);
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    // Méthode pour supprimer un étudiant
    private void deleteEtudiant(int etudiantId, int position) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, deleteUrl,
                response -> {
                    Log.d("deleteEtudiant", "Réponse du serveur : " + response);
                    // Suppression réussie
                    etudiantList.remove(position);
                    etudiantAdapter.notifyItemRemoved(position);
                    Toast.makeText(EtudiantListActivity.this, "Étudiant supprimé.", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    Toast.makeText(EtudiantListActivity.this, "Erreur lors de la suppression : " + error.getMessage(), Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(etudiantId)); // Passer l'ID de l'étudiant à supprimer
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    // Méthode pour afficher la boîte de dialogue de mise à jour
    public void showUpdateEtudiantDialog(Etudiant etudiant) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_update_etudiant, null);
        builder.setView(dialogView);

        EditText editTextNom = dialogView.findViewById(R.id.editTextNom);
        EditText editTextPrenom = dialogView.findViewById(R.id.editTextPrenom);
        EditText editTextVille = dialogView.findViewById(R.id.editTextVille);
        EditText editTextSexe = dialogView.findViewById(R.id.editTextSexe);

        // Remplir les champs avec les informations actuelles de l'étudiant
        editTextNom.setText(etudiant.getNom());
        editTextPrenom.setText(etudiant.getPrenom());
        editTextVille.setText(etudiant.getVille());
        editTextSexe.setText(etudiant.getSexe());

        builder.setTitle("Modifier l'étudiant")
                .setPositiveButton("Mettre à jour", (dialog, which) -> {
                    updateEtudiant(etudiant.getId(), editTextNom.getText().toString(), editTextPrenom.getText().toString(),
                            editTextVille.getText().toString(), editTextSexe.getText().toString());
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    // Méthode pour mettre à jour un étudiant
    private void updateEtudiant(int etudiantId, String nom, String prenom, String ville, String sexe) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, updateUrl,
                response -> {
                    Log.d("updateEtudiant", "Réponse du serveur : " + response);
                    // Mettre à jour l'étudiant dans la liste
                    for (Etudiant etudiant : etudiantList) {
                        if (etudiant.getId() == etudiantId) {
                            etudiant.setNom(nom);
                            etudiant.setPrenom(prenom);
                            etudiant.setVille(ville);
                            etudiant.setSexe(sexe);
                            break;
                        }
                    }
                    etudiantAdapter.notifyDataSetChanged();
                    Toast.makeText(EtudiantListActivity.this, "Étudiant mis à jour.", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    Toast.makeText(EtudiantListActivity.this, "Erreur lors de la mise à jour : " + error.getMessage(), Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(etudiantId)); // Passer l'ID de l'étudiant à mettre à jour
                params.put("nom", nom);
                params.put("prenom", prenom);
                params.put("ville", ville);
                params.put("sexe", sexe);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
