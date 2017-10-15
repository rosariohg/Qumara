package appteam.nith.hillffair.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.adapters.LeaderBoardAdapter;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.models.LeaderBoardModel;
import appteam.nith.hillffair.utilities.APIINTERFACE;
import appteam.nith.hillffair.utilities.Utils;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ;
    private ArrayList<LeaderBoardUserModel> users;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPref sharedPref = new SharedPref(this);
        setTheme(sharedPref.getThemeId());
        setContentView(R.layout.activity_leader_board);


        recyclerView = (RecyclerView) findViewById(R.id.leader_recycler);
        progressBar = (ProgressBar) findViewById(R.id.leader_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.leader_toolbar);
        toolbar.setTitle("LeaderBoard");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("NITH Hillffair Quiz");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getLeaderBoard("1");

    }

    public void getLeaderBoard(String from) {
        APIINTERFACE mAPI = Utils.getRetrofitService();
        Call<LeaderBoardModel> mService = mAPI.getLeaderBoard(from);
        mService.enqueue(new Callback<LeaderBoardModel>() {
            @Override
            public void onResponse(Call<LeaderBoardModel> call, Response<LeaderBoardModel> response) {
                if(response!=null&&response.isSuccess()){
                    if(response.body().getUsers()!=null){
                        users = response.body().getUsers();
                        adapter = new LeaderBoardAdapter(users, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(), "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<LeaderBoardModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
                   progressBar.setVisibility(View.GONE);
            }
        });


    }


    public class LeaderBoardUserModel {

        @SerializedName("name")
        private String name;
        @SerializedName("score")
        private int score;
        @SerializedName("rollno")
        private String rollNo;
        @SerializedName("sets")
        private int sets;

        public LeaderBoardUserModel(String name, int score, String rollNo, int sets) {
            this.name = name;
            this.score = score;
            this.rollNo = rollNo;
            this.sets = sets;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getRollNo() {
            return rollNo;
        }

        public void setRollNo(String rollNo) {
            this.rollNo = rollNo;
        }

        public int getSets() {
            return sets;
        }

        public void setSets(int sets) {
            this.sets = sets;
        }
    }


}
