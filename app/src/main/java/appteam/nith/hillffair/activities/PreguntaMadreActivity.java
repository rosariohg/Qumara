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

public class PreguntaMadreActivity extends AppCompatActivity {

    Context context;
    EditText fecha_emb;
    private Calendar myCalendar;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_madre);
        init();
        context=this;

        myCalendar = Calendar.getInstance();

        btn_next = (Button) findViewById(R.id.btn_preg_madre_sig);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreguntaMadreActivity.this, PreguntaGestanteDosActivity.class);
                //intent.putExtra("estado_madre",estado_madre);
                startActivity(intent);
            }
        });


        fecha_emb = (EditText) findViewById(R.id.preg_fecha_bb_nacido);
        fecha_emb.setKeyListener(null);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        fecha_emb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PreguntaMadreActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        fecha_emb.setText(sdf.format(myCalendar.getTime()));
    }

    void init() {
        Log.d("hi","iniciando interfaz");

    }

}
