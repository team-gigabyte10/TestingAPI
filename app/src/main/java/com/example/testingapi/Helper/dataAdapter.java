package com.example.testingapi.Helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testingapi.DistrictList;
import com.example.testingapi.Model.DataModel;
import com.example.testingapi.R;

import java.util.List;

public class dataAdapter extends RecyclerView.Adapter <dataAdapter.dataHolder>{

    DistrictList districtList;
    List<DataModel> all_district_data;

    public dataAdapter(DistrictList districtList, List<DataModel> all_district_data) {
        this.districtList = districtList;
        this.all_district_data = all_district_data;
    }

    @NonNull
    @Override
    public dataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new dataHolder(LayoutInflater.from(districtList).inflate(R.layout.dis_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull dataHolder holder, int position) {

        holder.u_name.setText(all_district_data.get(position).getName());
        holder.u_code.setText(all_district_data.get(position).getCode());
    }

    @Override
    public int getItemCount() {
        return all_district_data.size();
    }

    class dataHolder extends RecyclerView.ViewHolder {
        TextView u_name, u_code;
           public dataHolder(@NonNull View itemView) {
               super(itemView);
               u_name = itemView.findViewById(R.id.name);
               u_code = itemView.findViewById(R.id.code);
           }
       }
}
