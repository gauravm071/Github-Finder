package com.example.githubfinder;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {
    List<UserRepo> listOfrepo= new ArrayList<>();
    RepoAdapterInterface repoAdapterInterface;
    public RepoAdapter(RepoAdapterInterface repoAdapterInterface,List<UserRepo> listOfrepo) {
        this.listOfrepo = listOfrepo;
        this.repoAdapterInterface=repoAdapterInterface;
    }

    @NonNull
    @Override
    public RepoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.repository,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RepoAdapter.ViewHolder holder, int position) {
        holder.name.setText(listOfrepo.get(position).getName());
        holder.desc.setText(listOfrepo.get(position).getDescription());
        holder.language.setText(listOfrepo.get(position).getLanguage());
    }

    @Override
    public int getItemCount() {
        return listOfrepo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, desc, language;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    repoAdapterInterface.onClick(getAdapterPosition());
                }
            });
            name= itemView.findViewById(R.id.repoName);
            desc= itemView.findViewById(R.id.repoDesc);
            language= itemView.findViewById(R.id.repoLang);
        }
    }
}
