package edu.upc.dsa.minimo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    User userr;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView fullName = findViewById(R.id.textFullName);
        TextView followers = findViewById(R.id.textFollowers);
        TextView repos = findViewById(R.id.textRepositories);
        TextView following = findViewById(R.id.textFollowing);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerRepo);

        userr = UserInstance.getInstance().getUser();
        Picasso.with(getApplicationContext()).load(userr.getAvatar_url()).into((ImageView) findViewById(R.id.imageView));
        fullName.setText(userr.getName());
        repos.setText("Repositories: " + userr.getPublic_repos());
        following.setText("Following: " + userr.getFollowing());
        followers.setText("Followers: " + userr.getFollowers());

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<User> input = userr.getUserRepositories();
        mAdapter = new RecyclerAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }

}