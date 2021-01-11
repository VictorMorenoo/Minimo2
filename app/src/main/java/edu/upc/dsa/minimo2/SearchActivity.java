package edu.upc.dsa.minimo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity
{
    APIInterface APIInterface;
    EditText Uname;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        APIInterface = APIClient.getClient().create(APIInterface.class);
        Uname = (EditText) findViewById(R.id.TextUser); //Lo que escribe el usuario
        sharedPref = getApplicationContext().getSharedPreferences("Examen_Min2", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    public void sendUser(View view){
        String username = Uname.getText().toString();

        if (username.equals(""))
            Toast.makeText(getApplicationContext(), "Debe escribir un nombre", Toast.LENGTH_LONG).show();

        else {
            Call<User> userCall = APIInterface.getUserInfo(username);
            userCall.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, Response<User> response)
                {
                    User userr = response.body();
                    Call<List<User>> followersCall = APIInterface.getRepos(username);
                    progressBar.setVisibility(View.VISIBLE);
                    followersCall.enqueue(new Callback<List<User>>()
                    {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response)
                        {

                            userr.setUserRepositories(response.body());
                            UserInstance.getInstance().setUser(userr);

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error, intentelo de nuevo", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onFailure(Call<User> call, Throwable t)
                {
                    Toast.makeText(getApplicationContext(), "Error, intentelo de nuevo", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
