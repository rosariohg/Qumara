package appteam.nith.hillffair.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.application.SharedPref;

public class ThemeSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private int themeVal;
    ImageView madre, gestante, soltera, wonderwoman, flash, captain;
    Context context;
    boolean settings_call=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_selection);
        init();
        context=this;

        settings_call=getIntent().getBooleanExtra("settings_call",false);

        setRoundImage(madre,R.drawable.madre1);
        setRoundImage(gestante,R.drawable.madre2);
        setRoundImage(soltera,R.drawable.madre3);
        //setRoundImage(wonderwoman,R.drawable.wonderwoman_btn);
        //setRoundImage(captain,R.drawable.captain);
        //setRoundImage(flash,R.drawable.flash);

    }

    void init() {
        madre = (ImageView) findViewById(R.id.madre);
        gestante = (ImageView) findViewById(R.id.gestante);
        soltera = (ImageView) findViewById(R.id.soltera);
        //wonderwoman = (ImageView) findViewById(R.id.wonderwoman);
        //flash = (ImageView) findViewById(R.id.flash);
        //captain = (ImageView) findViewById(R.id.captain);
        madre.setOnClickListener(this);
        gestante.setOnClickListener(this);
        soltera.setOnClickListener(this);
        //wonderwoman.setOnClickListener(this);
        //flash.setOnClickListener(this);
        //captain.setOnClickListener(this);

    }

    void setRoundImage(ImageView view,int id){

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),id);
        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCornerRadius(2.0f);
        roundedBitmapDrawable.setCircular(true);
        view.setImageDrawable(roundedBitmapDrawable);

    }

    @Override
    public void onClick(View view) {
        String estado_madre = "";
        switch (view.getId()) {
            case R.id.gestante:
                estado_madre = "gestante";
                break;
            case R.id.soltera:
                estado_madre = "soltera";
                break;
            case R.id.madre:
                estado_madre = "madre";
                break;
        }

        themeVal = R.style.madre;

        //savetoSharedPref();

        /*if(settings_call==false)
        startActivity(new Intent(ThemeSelectionActivity.this,LoginActivity.class));
        else
        startActivity(new Intent(ThemeSelectionActivity.this,SettingsActivity.class));*/


        if (estado_madre == "gestante"){
            Intent intent = new Intent(ThemeSelectionActivity.this, PreguntaGestanteActivity.class);
            intent.putExtra("estado_madre",estado_madre);
            startActivity(intent);

        }else if (estado_madre == "soltera" ){
            Intent intent = new Intent(ThemeSelectionActivity.this, PreguntaGestanteActivity.class);
            intent.putExtra("estado_madre",estado_madre);
            startActivity(intent);
        }else{ // estado_madre == "madre"
            Intent intent = new Intent(ThemeSelectionActivity.this, PreguntaMadreActivity.class);
            intent.putExtra("estado_madre",estado_madre);
            startActivity(intent);
        }

        finish();
    }
    
    void savetoSharedPref(){
        SharedPref sharedPref = new SharedPref(context);
        sharedPref.setThemeId(themeVal);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(settings_call){
            Intent in=new Intent(ThemeSelectionActivity.this,SettingsActivity.class);
            overridePendingTransition(0,0);
            startActivity(in);
            finish();
        }

    }

}
