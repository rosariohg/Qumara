package appteam.nith.hillffair.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.Manifest;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import appteam.nith.hillffair.Notification.NotificationActivity;
import appteam.nith.hillffair.R;
import appteam.nith.hillffair.adapters.HomeAdapter;
import appteam.nith.hillffair.application.SharedPref;
import appteam.nith.hillffair.fragments.ProfileTab2;
import appteam.nith.hillffair.models.Calendario;
import appteam.nith.hillffair.models.ProfileDataModel;
import appteam.nith.hillffair.models.main_screen_model;
import appteam.nith.hillffair.utilities.APIINTERFACE;
import appteam.nith.hillffair.utilities.RecyclerItemClickListener;
import appteam.nith.hillffair.utilities.Utils;
import layout.calendarioFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //private RecyclerView recyclerView;
    //private HomeAdapter adapter;
    private SharedPref pref;
    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 100;
    private static String[] PERMISSIONS_PHONECALL = {Manifest.permission.CALL_PHONE};
    final String number[] = {"9882654141", "9882852966"};
    int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref= new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initCollapsingToolbar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(pref.getUserName()==null)
        profileBasicInfo(pref.getUserId());


        // obtenemos DATOS del servidor
        // probar datos compartidos para solo jalarlos una vez o sqlite


        // empezamos cambiando RECOMENDACION

        /*cvRecomendacion = (CardView)findViewById(R.id.cvRecomendacion);
        cvRecomendacionTitulo = (TextView)findViewById(R.id.cvRecomendacionTitulo);
        cvRecomendacionTexto = (TextView)findViewById(R.id.cvRecomendacionTexto);
        cvRecomendacionBtn = (Button)findViewById(R.id.cvRecomendacionBtn);

        cvRecomendacionTitulo.setText("HOLA TITULO");
        cvRecomendacionTexto.setText("Tu recomendacion es q comas alimentos con hierro");
        cvRecomendacionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Redireccion a un activity con toda la recomendacion
            }
        });*/


        // agregamos el calendario
        if (savedInstanceState == null) {

            calendarioFragment fragment = new calendarioFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.framelayout_calendario, fragment)
                    .commit();
        }


        // configuramos graficas por SEMANA
        LineChart chart = (LineChart) findViewById(R.id.chart_1);

        final HashMap<Integer, String> numMap = new HashMap<>();
        numMap.put(1, "09/10");
        numMap.put(2, "10/10");
        numMap.put(3, "11/10");
        numMap.put(4, "12/10");
        numMap.put(5, "13/10");
        numMap.put(6, "14/10");
        numMap.put(7, "15/10");

        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(1, 2));
        entries.add(new Entry(2, 3));
        entries.add(new Entry(3, 6));
        entries.add(new Entry(4, 1));
        entries.add(new Entry(5, 4));
        entries.add(new Entry(6, 2));
        entries.add(new Entry(7, 5));

        LineDataSet dataSet = new LineDataSet(entries, "Semana"); // add entries to dataset
        dataSet.setColor(R.color.color_accent);
        //dataSet.setValueTextColor(); // styling, ...

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return numMap.get((int)value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh


        // configuramos graficas por ANIO
        BarChart chart2 = (BarChart) findViewById(R.id.chart_2);

        final HashMap<Integer, String> numMap2 = new HashMap<>();
        numMap2.put(1, "Enero");
        numMap2.put(2, "Febrero");
        numMap2.put(3, "Marzo");
        numMap2.put(4, "Abril");
        numMap2.put(5, "Mayo");
        numMap2.put(6, "Junio");
        numMap2.put(7, "Julio");
        numMap2.put(8, "Agosto");
        numMap2.put(9, "Septiembre");
        numMap2.put(10, "Octubre");
        numMap2.put(11, "Noviembre");
        numMap2.put(12, "Diciembre");

        List<BarEntry> entries2 = new ArrayList<BarEntry>();
        entries2.add(new BarEntry(1, 2));
        entries2.add(new BarEntry(2, 13));
        entries2.add(new BarEntry(3, 16));
        entries2.add(new BarEntry(4, 11));
        entries2.add(new BarEntry(5, 14));
        entries2.add(new BarEntry(6, 12));
        entries2.add(new BarEntry(7, 21));
        entries2.add(new BarEntry(8, 15));
        entries2.add(new BarEntry(9, 18));
        entries2.add(new BarEntry(10, 5));
        entries2.add(new BarEntry(11, 0));
        entries2.add(new BarEntry(12, 0));

        BarDataSet dataSet2 = new BarDataSet(entries2, "Mes"); // add entries to dataset
        dataSet2.setColor(R.color.color_accent);
        //dataSet.setValueTextColor(); // styling, ...

        XAxis xAxis2 = chart2.getXAxis();
        xAxis2.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return numMap2.get((int)value);
            }
        });
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);

        BarData barData = new BarData(dataSet2);
        chart2.setData(barData);
        chart2.invalidate(); // refresh



/*        recyclerView = (RecyclerView) findViewById(R.id.list);
        ArrayList<main_screen_model> list=new ArrayList<>();
        list.add(new main_screen_model(R.drawable.swords_crossed,"Battle Day",R.color.battleday));
        list.add(new main_screen_model(R.drawable.quiz,"Quiz",R.color.quiz));
        list.add(new main_screen_model(R.drawable.sponsor,"Sponsors",R.color.sponsor));
        list.add(new main_screen_model(R.drawable.news_feed,"NewsFeed",R.color.newsFeed));
        list.add(new main_screen_model(R.drawable.core,"Core Team",R.color.coreTeam));
        list.add(new main_screen_model(R.drawable.clubs,"Clubs",R.color.club));
        list.add(new main_screen_model(R.drawable.about,"About Hill'ffair",R.color.about));
        list.add(new main_screen_model(R.drawable.contributors,"Contributors",R.color.contributors));

        adapter = new HomeAdapter(list, this);
        GridLayoutManager staggeredGridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(HomeActivity.this, LinearLayoutManager.VERTICAL, Color.BLACK));

        staggeredGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return 1;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 6) {
                    startActivity(new Intent(HomeActivity.this,aboutHillffairActivity.class));
                }
                else if(position==0){
                    startActivity(new Intent(HomeActivity.this,BattleDayActivity.class));
                }
                else if (position==3){
                    startActivity(new Intent(HomeActivity.this, NewsfeedActivity.class));
                }
                else if (position==1){
                    startActivity(new Intent(HomeActivity.this,QuizActivity.class));
                }
                else if(position==2){
                    startActivity(new Intent(HomeActivity.this,SponsorActivity.class));
                }
                else if(position==4){
                    startActivity(new Intent(HomeActivity.this,CoreTeamActivity.class));
                }
                else if(position==5){
                    startActivity(new Intent(HomeActivity.this, EventActivity.class));
                }
                else if(position==7){
                    startActivity(new Intent(HomeActivity.this, ContributorsActivity.class));
                }
            }
        }));*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return true;
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }

    private void profileBasicInfo(String id){

        APIINTERFACE mAPI = Utils.getRetrofitService();
        Call<ProfileDataModel> mService = mAPI.profileBasicInfo(id);
        mService.enqueue(new Callback<ProfileDataModel>() {
            @Override
            public void onResponse(Call<ProfileDataModel> call, Response<ProfileDataModel> response) {
                if(response!=null&&response.isSuccess()){
                    if(response.body().isSuccess()){
                        ProfileTab2.ProfileBasicDetailModel model=response.body().getProfileInfo();
                        if(model!=null){
                            pref.setUserName(model.getName());
                            if(model.getRollno()==null)
                               pref.setRollNo("");
                            else
                                pref.setRollNo(model.getRollno());
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<ProfileDataModel> call, Throwable t) {
                t.printStackTrace();
               }
        });


    }
    private void call(int i) {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_PHONE_CALL);
        } else {
            //Open call function
            String phone = number[i];
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+91" + phone));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_PHONE_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                call(a);
            } else {
                Toast.makeText(this, "Sorry!!! Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
