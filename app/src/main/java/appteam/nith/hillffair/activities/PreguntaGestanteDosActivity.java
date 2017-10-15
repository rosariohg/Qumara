package appteam.nith.hillffair.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import appteam.nith.hillffair.R;

public class PreguntaGestanteDosActivity extends AppCompatActivity {

    Context context;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_gestante_dos);
        context=this;

        btn_next = (Button) findViewById(R.id.btn_preg_ges_dos_sig);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreguntaGestanteDosActivity.this, HomeActivity.class);
                //intent.putExtra("estado_madre",estado_madre);
                startActivity(intent);
            }
        });


    }




}
