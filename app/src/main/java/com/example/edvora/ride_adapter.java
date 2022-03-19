package com.example.edvora;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.edvora.Ride;

import java.util.List;

public class ride_adapter extends RecyclerView.Adapter<ride_adapter.CustomViewHolder> {

    private List<Ride> rides;
    private Context mContext;
    private ride_adapter.OnItemClickListener mListener;

    public ride_adapter(){}

    public ride_adapter(Context mContext, List<Ride> mRides){
        this.rides = mRides;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(ride_adapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {

        void itemClicked(int position);
        void addPhotoClicked(int position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txt_city, txt_state, txt_id, txt_origin_station,txt_station_path,txt_date,txt_distance;
        private ImageView img;


        public CustomViewHolder(View view, final ride_adapter.OnItemClickListener listener) {
            super(view);
            txt_city = (TextView) view.findViewById(R.id.txt_city);
            txt_state = (TextView) view.findViewById(R.id.txt_state);
            txt_id = (TextView) view.findViewById(R.id.txt_id);
            txt_origin_station = (TextView) view.findViewById(R.id.txt_origin_station);
            txt_station_path = (TextView) view.findViewById(R.id.txt_station_path);
            txt_date = (TextView) view.findViewById(R.id.txt_date);
            txt_distance = (TextView) view.findViewById(R.id.txt_distance);
            img = view.findViewById(R.id.img);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.itemClicked(position);
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }

    public ride_adapter(List<Ride> rides) {
        this.rides = rides;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ride, parent, false);

        return new CustomViewHolder(itemView,mListener);
    }



    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Ride ride = rides.get(position);




        List <String> sNumbers = ride.getStation_path();

        int size = sNumbers.size();
        int [] numbers = new int [size];
        for(int i=0; i<size; i++) {
            numbers[i] = Integer.parseInt(sNumbers.get(i));
        }

        int myNumber = 62;

        int distance = Math.abs(numbers[0] - myNumber);
        int idx = 0;

        for(int c = 1; c < numbers.length; c++){
            int cdistance = Math.abs(numbers[c] - myNumber);
            if(cdistance < distance){
                idx = c;
                distance = cdistance;
            }
        }

        int myDistance = Math.abs(myNumber - numbers[idx]);


        holder.txt_city.setText(ride.getCity());
        holder.txt_state.setText(ride.getState());
        holder.txt_id.setText(String.valueOf(ride.getId()));
        holder.txt_origin_station.setText(String.valueOf(ride.getOrigin_station_code()));
        holder.txt_station_path.setText(ride.getStation_path().toString());
        holder.txt_date.setText(ride.getDate());
        holder.txt_distance.setText(String.valueOf(myDistance));
        if(ride.getMap_url().equals("default")){
            holder.img.setImageResource(R.drawable.default_image);
        }else{
            Glide.with(mContext).load(ride.getMap_url()).into(holder.img);
            /*Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(location.getImageURL()).getContent());
                holder.img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            //holder.img.setImageResource(location.getImageURL());
        }

    }

    @Override
    public int getItemCount() {
        return rides.size();
    }


    public static int[] StringArrToIntArr(String[] s) {
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = Integer.parseInt(s[i]);
        }
        return result;
    }
}