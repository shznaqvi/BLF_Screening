package edu.aku.hassannaqvi.blf_screening.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import edu.aku.hassannaqvi.blf_screening.R;
import edu.aku.hassannaqvi.blf_screening.core.DatabaseHelper;
import edu.aku.hassannaqvi.blf_screening.models.FollowUps;
import edu.aku.hassannaqvi.blf_screening.ui.sections.SectionWFA01Activity;

/**
 * Created by hassan.naqvi on 8/1/2016.
 */
public class FollowupsAdapter extends RecyclerView.Adapter<FollowupsAdapter.ViewHolder> {
    Context c;
    DatabaseHelper db;
    private Context context;
    private List<FollowUps> fups = Collections.emptyList();

    // Provide a suitable constructor (depends on the kind of dataset)
    public FollowupsAdapter(List<FollowUps> fups, Context c) {
        this.fups = fups;
        this.c = c;
        Log.d("TAG:count", String.valueOf(getItemCount()));
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.followups_list_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        db = new DatabaseHelper(c);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        int childCount = 0;
        //childCount = db.getChildrenByUUID(form.get(position).get_UID());
        int photoChild = 0;
        //photoChild = db.getChildrenPhotoCheck(form.get(position).get_UID());
        int cardChild = 0;
        //cardChild = db.getChildrenCardCheck(form.get(position).get_UID());


        String iStatus = "Status  Unknown";
        int iColor = 0;
        switch (fups.get(position).getFupdonedt()) {
            case "null":
                iStatus = "Done";
                iColor = Color.GREEN;
                break;

            default:
                iStatus = "Not Done";
                iColor = Color.RED;
                break;
        }

        //holder.hhno.setText(form.get(position).getRefno() + " \t\t(" + form.get(position).getA01() + ")");
        //    holder.hhno.setText(form.get(position).getA05());
        Log.d("FollowupsAdapter", "onBindViewHolder: " + fups.get(position).getFupweek() + "/20");
        holder.fupWeek.setText(fups.get(position).getFupweek() + "/20");
        holder.chName.setText(fups.get(position).getChName());
        holder.mName.setText(" [" + fups.get(position).getmName() + "]");
        holder.mrno.setText(fups.get(position).getMrno() + " - " + fups.get(position).getStudyid());
        holder.fupdates.setText("SCHEDULED NO: " + fups.get(position).getFupdt() + " - DONE ON: " + (fups.get(position).getFupdonedt().equals("null") ? "not-done" : fups.get(position).getFupdonedt()));
        holder.istatus.setText(iStatus);
        holder.istatus.setTextColor(iColor);
        //  holder.cluster.setText(form.get(position).getA08() + " (" + form.get(position).getRefno() + ")");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SectionWFA01Activity.class);
                intent.putExtra("list", true);
                ((Activity) context).startActivityForResult(intent, 1002);
            }
        });


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return fups.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {


        public RecyclerView rv;
        public TextView chName;
        public TextView mName;
        public TextView fupWeek;
        public TextView mrno;
        public TextView fupdates;
        public TextView istatus;
        // each data item is just a string in this case

        public ViewHolder(View v) {
            super(v);
//            rv = v.findViewById(R.id.FormsList);
            fupWeek = v.findViewById(R.id.fupWeek);
            chName = v.findViewById(R.id.chname);
            mName = v.findViewById(R.id.mname);
            mrno = v.findViewById(R.id.mrno);
            fupdates = v.findViewById(R.id.fupDates);
            istatus = v.findViewById(R.id.istatus);
            context = v.getContext();
        }


    }
}