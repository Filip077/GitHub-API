package com.example.korisnik.githubapi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.korisnik.githubapi.Model.User;
import com.example.korisnik.githubapi.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Korisnik on 24.02.2018.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {
        private List<User> users;
        private Context context;

    public ReposAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repos_row, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user =users.get(position);
        holder.name.setText(user.getName());

    }



    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name ;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.repo_name);
        }
    }


}
