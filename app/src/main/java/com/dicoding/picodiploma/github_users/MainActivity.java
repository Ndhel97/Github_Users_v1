package com.dicoding.picodiploma.github_users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvGithubuser;
    private ArrayList<Githubuser> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvGithubuser = findViewById(R.id.rv_githubuser);
        rvGithubuser.setHasFixedSize(true);

        getGithubuserList();
        showRecyclerList();
    }

    private void getGithubuserList() {
        String myJSONstr = loadJSONFromAsset("githubuser.json");
        try {
            JSONObject rootJsonObject = new JSONObject(myJSONstr);
            JSONArray githubuserJsonArray = rootJsonObject.getJSONArray("users");
            for (int i = 0; i < githubuserJsonArray.length(); i++) {
                Githubuser aGithubuser = new Githubuser();
                JSONObject jsonObject = githubuserJsonArray.getJSONObject(i);
                aGithubuser.setUsername(jsonObject.getString("username"));
                aGithubuser.setName(jsonObject.getString("name"));
                aGithubuser.setAvatar(getResources().getIdentifier(jsonObject.getString("avatar"), "drawable", "com.dicoding.picodiploma.github_users"));
                aGithubuser.setCompany(jsonObject.getString("company"));
                aGithubuser.setLocation(jsonObject.getString("location"));
                aGithubuser.setRepository(jsonObject.getInt("repository"));
                aGithubuser.setFollower(jsonObject.getInt("follower"));
                aGithubuser.setFollowing(jsonObject.getInt("following"));
                list.add(aGithubuser);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset(String fileName) {
        String json;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void showRecyclerList() {
        rvGithubuser.setLayoutManager(new LinearLayoutManager(this));
        ListGithubuserAdapter ListGithubuserAdapter = new ListGithubuserAdapter(list);
        rvGithubuser.setAdapter(ListGithubuserAdapter);
        ListGithubuserAdapter.setOnItemClickCallback(new ListGithubuserAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Githubuser data) {
                moveToDetail(data);
            }
        });
    }

    private void moveToDetail(Githubuser data) {
        Intent moveWithObject = new Intent(MainActivity.this, GithubuserDetailsActivity.class);
        moveWithObject.putExtra(GithubuserDetailsActivity.EXTRA_GITHUBUSER, data);
        startActivity(moveWithObject);
    }
}