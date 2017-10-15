package layout;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.adapters.CalendarioAdapter;
import appteam.nith.hillffair.models.Calendario;

public class calendarioFragment extends Fragment {

    private RecyclerView rvCalendario;
    private CalendarioAdapter mCalendarioAdapter;

    public calendarioFragment() {
        // Required empty public constructor
    }


    public static calendarioFragment newInstance() {
        calendarioFragment fragment = new calendarioFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendario, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("--------------------", "VIEW CREATED--------------");
        Log.d("--------------------", String.valueOf(Calendario.EVENTOS.size()));

        Calendario evento1 = new Calendario();
        evento1.setFecha("30/10");
        evento1.setTipo("Cita");
        evento1.setNombre("Cita para despistaje y prevención");
        Calendario.EVENTOS.add(evento1);

        Calendario evento2 = new Calendario();
        evento2.setFecha("10/11");
        evento2.setTipo("Recordatorio");
        evento2.setNombre("Comer alimentos ricos en hierro");
        Calendario.EVENTOS.add(evento2);

        Calendario evento3 = new Calendario();
        evento3.setFecha("15/11");
        evento3.setTipo("Cita");
        evento3.setNombre("Ir a la posta para la talla del niño");
        Calendario.EVENTOS.add(evento3);

        Calendario evento4 = new Calendario();
        evento4.setFecha("15/11");
        evento4.setTipo("Recordatorio");
        evento4.setNombre("Toma tus suplementos");
        Calendario.EVENTOS.add(evento4);

        Log.d("--------------------", String.valueOf(Calendario.EVENTOS.size()));

        Activity activity = this.getActivity();

        rvCalendario = (RecyclerView) activity.findViewById(R.id.rvCalendario);
        //rvCalendario.setHasFixedSize(true);
        mCalendarioAdapter = new CalendarioAdapter(Calendario.EVENTOS);
        rvCalendario.setAdapter(mCalendarioAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        ((CalendarioAdapter) mCalendarioAdapter).setOnItemClickListener(new CalendarioAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int pos, Calendario evento) {
                // si se seleccion un evento se hace algo?
            }
        });
    }
}
