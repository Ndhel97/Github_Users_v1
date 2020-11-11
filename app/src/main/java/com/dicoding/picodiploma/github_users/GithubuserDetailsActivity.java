package com.dicoding.picodiploma.github_users;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GithubuserDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_GITHUBUSER = "extra_githubuser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_githubuser_details);

        ImageView avatar = findViewById(R.id.img_avatar);
        TextView username = findViewById(R.id.tv_item_username);
        TextView name = findViewById(R.id.tv_item_name);
        TextView company = findViewById(R.id.tv_item_company);
        TextView location = findViewById(R.id.tv_item_location);
        TextView repository = findViewById(R.id.tv_item_repository);
        TextView follower = findViewById(R.id.tv_item_follower);
        TextView following = findViewById(R.id.tv_item_following);

        Githubuser githubuser = getIntent().getParcelableExtra(EXTRA_GITHUBUSER);

        Glide.with(this).load(githubuser.getAvatar()).into(avatar);
        username.setText(githubuser.getUsername());
        name.setText(githubuser.getName());
        company.setText(githubuser.getCompany());
        location.setText(githubuser.getLocation());
        repository.setText(String.valueOf(githubuser.getRepository()));
        follower.setText(String.valueOf(githubuser.getFollower()));
        following.setText(String.valueOf(githubuser.getFollowing()));
    }
}