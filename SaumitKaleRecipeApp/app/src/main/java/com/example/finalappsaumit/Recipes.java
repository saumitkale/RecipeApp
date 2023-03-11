package com.example.finalappsaumit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Recipes extends AppCompatActivity {

    RequestQueue rq;
    ArrayList<RecipeInfo> info;
    EditText searchText;
    Button searchButton;
    RecipeInfo recipeInfo;
    RecipeAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    String foodurl;

    String dishurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        searchText = findViewById(R.id.recipeSearcheditText);
        searchButton = findViewById(R.id.recipeSearchButton);
        recyclerView = findViewById(R.id.recipeRecycler);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getRecipes(searchText.getText().toString());
            }
        });
    }

    public void getRecipes(String str) {

        info = new ArrayList<>();
        rq = Volley.newRequestQueue(Recipes.this);
        rq.getCache().clear();
        foodurl = "https://api.spoonacular.com/recipes/complexSearch?apiKey=bfa0441fabe847f4a0222f25dc747542&query="+str+"&addRecipeNutrition=true&addRecipeInformation=true&number=10";
        RequestQueue queue = Volley.newRequestQueue(Recipes.this);

        JsonObjectRequest recipes = new JsonObjectRequest(Request.Method.GET, foodurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("results");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject results = data.getJSONObject(i);
                        JSONObject volume = results.getJSONObject("nutrition");
                        String title = results.optString("title");
                        String readyMins = results.optString("readyInMinutes");
                        String servings = results.optString("sourceUrl");
                        String image = results.optString("image");
                        String summary = results.optString("healthScore");

                        recipeInfo = new RecipeInfo(title, readyMins, summary, image, servings);
                        info.add(recipeInfo);
                        adapter = new RecipeAdapter(info, Recipes.this);
                        linearLayoutManager = new LinearLayoutManager(Recipes.this, RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Recipes.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(recipes);
    }

}