package no.usn.kvisli.listedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import no.usn.kvisli.listedemo.Aktivitetsversjon.MainActivity;

/**
 * Created by Jon on 30.01.2018.
 */

public class KommuneListAdapter
        extends RecyclerView.Adapter<KommuneListAdapter.KommuneViewHolder> {

    private ArrayList<Kommune> kommuneListen;
    private LayoutInflater mInflater;

    // Konstruktør
    public KommuneListAdapter(Context context, ArrayList<Kommune> kommuneListe) {
        mInflater = LayoutInflater.from(context);
        this.kommuneListen = kommuneListe;
    }

    // Indre ViewHolder-klasse som holder View-objekter for en rad
    class KommuneViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView kommunenrView, kommuneNavnView, folketallView, fylkeView;

        final KommuneListAdapter komAdapter;

        public KommuneViewHolder(View itemView, KommuneListAdapter adapter) {
            super(itemView);
            kommunenrView   = (TextView) itemView.findViewById(R.id.txtKommunenr);
            kommuneNavnView = (TextView) itemView.findViewById(R.id.txtKommuneNavn);
            folketallView   = (TextView) itemView.findViewById(R.id.txtFolketall);
            fylkeView       = (TextView) itemView.findViewById(R.id.txtFylke);
            this.komAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int nowPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList.
            Kommune kommunen = kommuneListen.get(nowPosition);
            MainActivity.visSnackBar(view, kommunen.kommuneNavn);
        } // End of onClick()
    }  // End of class KommuneViewHolder


    @Override
    public KommuneListAdapter.KommuneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.kommune_list_item, parent, false);
        return new KommuneViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(KommuneListAdapter.KommuneViewHolder holder, int position) {
        Kommune denneKommunen = kommuneListen.get(position);
        holder.kommuneNavnView.setText(denneKommunen.kommuneNavn);
        holder.kommunenrView.setText("K.nr: " + String.valueOf(denneKommunen.kommuneNummer));
        holder.folketallView.setText("Folketall: " + String.valueOf(denneKommunen.folkeTall));
        holder.fylkeView.setText(denneKommunen.fylke + " fylke");
    }

    @Override
    public int getItemCount() {
        return kommuneListen.size();
    }
}
