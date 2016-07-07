package com.arktech.waqasansari.thescholarsinn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Linta Ansari on 6/27/2016.
 */
public class AdapterResult extends RecyclerView.Adapter<AdapterResult.ResultViewHolder> {
    List<ClassResult> classResults;

    public AdapterResult(List<ClassResult> classResults){
        this.classResults = classResults;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_top_10, parent, false);

        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        ClassResult result = classResults.get(position);

        holder.txtRank.setText(String.valueOf(position+1));
        holder.txtStudentName.setText(result.getName());
        holder.txtClass.setText("Class: " + result.get_class());
        holder.txtID.setText("ID: " + result.getID());
        holder.txtPercentage.setText("Percentage: " + String.valueOf(result.getPercentage()));
    }

    @Override
    public int getItemCount() {
        return classResults.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView txtRank;
        TextView txtStudentName;
        TextView txtClass;
        TextView txtID;
        TextView txtPercentage;

        public ResultViewHolder(View itemView) {
            super(itemView);
            txtRank = (TextView) itemView.findViewById(R.id.txtRank);
            txtStudentName = (TextView) itemView.findViewById(R.id.txtName);
            txtClass = (TextView) itemView.findViewById(R.id.txtClass);
            txtID = (TextView) itemView.findViewById(R.id.txtId);
            txtPercentage = (TextView) itemView.findViewById(R.id.txtPercentage);
        }
    }
}
