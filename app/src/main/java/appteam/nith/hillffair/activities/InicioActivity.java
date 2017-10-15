package appteam.nith.hillffair.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import appteam.nith.hillffair.R;

public class InicioActivity extends AppCompatActivity {

    Context context;
    EditText fecha_emb;
    private Calendar myCalendar;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        init();
        context=this;

        myCalendar = Calendar.getInstance();

        btn_next = (Button) findViewById(R.id.btn_preg_madre_sig);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InicioActivity.this, ThemeSelectionActivity.class);
                //intent.putExtra("estado_madre",estado_madre);
                startActivity(intent);
            }
        });

    }

    void init() {
        Log.d("hi","iniciando interfaz");

    }

}
