package appteam.nith.hillffair.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.models.Calendario;

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioAdapter.ViewHolder> {

    private List<Calendario> mDataset; // es una lista de eventos
    private OnItemClickListener mListener;


    public CalendarioAdapter(List<Calendario> dataset) {
        mDataset = dataset;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public CalendarioAdapter(ArrayList<Calendario> dataset, OnItemClickListener listener) {
        mDataset = dataset;
        mListener = listener;
    }


    @Override
    public CalendarioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_calendario,parent,false);

        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CalendarioAdapter.ViewHolder holder, int position) {
        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView mEvento;
        private TextView mFecha;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mEvento = (TextView) itemView.findViewById(R.id.cvCalendarioEvento);
            mFecha = (TextView) itemView.findViewById(R.id.cvCalendarioFecha);
        }

        public void bind(final Calendario calendario) {
            mEvento.setText(calendario.getTipo()+": "+calendario.getNombre());
            mFecha.setText(calendario.getFecha());

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(getAdapterPosition(), calendario);
                }
            });
        }

        public void setSelected(boolean selected) {
            //if(selected)
            //    mName.setTextColor(Color.YELLOW);
            //else
            //    mName.setTextColor(Color.WHITE);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, Calendario evento);
    }
}
