package com.dicoding.picodiploma.github_users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListGithubuserAdapter extends RecyclerView.Adapter<ListGithubuserAdapter.ListViewHolder> {
    private ArrayList<Githubuser> listGithubuser;

    public ListGithubuserAdapter(ArrayList<Githubuser> list) {
        this.listGithubuser = list;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Githubuser data);
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListGithubuserAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_githubuser, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Githubuser githubuser = listGithubuser.get(position);
        Glide.with(holder.itemView.getContext())
                .load(githubuser.getAvatar())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);
        holder.tvUsername.setText(githubuser.getUsername());
        holder.tvName.setText(githubuser.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listGithubuser.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGithubuser.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvUsername, tvName;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_user);
            tvUsername = itemView.findViewById(R.id.tv_item_username);
            tvName = itemView.findViewById(R.id.tv_item_name);
        }
    }
}
