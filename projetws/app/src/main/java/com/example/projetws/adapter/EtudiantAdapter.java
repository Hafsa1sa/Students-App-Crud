package com.example.projetws.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetws.EtudiantListActivity;
import com.example.projetws.R;
import com.example.projetws.beans.Etudiant;

import java.util.List;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.ViewHolder> {

    private List<Etudiant> etudiants;
    private Context context;

    public EtudiantAdapter(List<Etudiant> etudiants, Context context) {
        this.etudiants = etudiants;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_etudiant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Etudiant etudiant = etudiants.get(position);
        holder.nomTextView.setText(etudiant.getNom());
        holder.prenomTextView.setText(etudiant.getPrenom());
        holder.villeTextView.setText(etudiant.getVille());
        holder.sexeTextView.setText(etudiant.getSexe());

        // Logique pour le bouton Modifier
        holder.buttonModifier.setOnClickListener(v -> {
            // Appel à la méthode de l'activité pour modifier l'étudiant
            ((EtudiantListActivity) context).showUpdateEtudiantDialog(etudiant);
        });

        // Logique pour le bouton Supprimer
        holder.buttonSupprimer.setOnClickListener(v -> {
            // Afficher une alerte pour confirmer la suppression
            ((EtudiantListActivity) context).showDeleteDialog(etudiant, position);
        });
    }

    @Override
    public int getItemCount() {
        return etudiants.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomTextView;
        TextView prenomTextView;
        TextView villeTextView;
        TextView sexeTextView;
        Button buttonModifier;
        Button buttonSupprimer;

        ViewHolder(View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.textViewNom);
            prenomTextView = itemView.findViewById(R.id.textViewPrenom);
            villeTextView = itemView.findViewById(R.id.textViewVille);
            sexeTextView = itemView.findViewById(R.id.textViewSexe);
            buttonModifier = itemView.findViewById(R.id.buttonModifier);
            buttonSupprimer = itemView.findViewById(R.id.buttonSupprimer);
        }
    }
}
