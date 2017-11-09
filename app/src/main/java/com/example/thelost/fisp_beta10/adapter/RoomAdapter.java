package com.example.thelost.fisp_beta10.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thelost.fisp_beta10.R;
import com.example.thelost.fisp_beta10.bean.RoomBean;
import com.example.thelost.fisp_beta10.data.FispContract;
import com.example.thelost.fisp_beta10.utils.ImageUtils;

import java.util.List;

/**
 * Created by Personal Pc on 10/23/2017.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {

    private static OnViewClickListener mOnViewClick;
    private Context mContext;

    private List<RoomBean> roomData;

    private Cursor cursor;



    public RoomAdapter(Context context, List<RoomBean> roomBeanList, Cursor cursor,OnViewClickListener mOnViewClick ){
        this.mContext=context;

        this.roomData=roomBeanList;

        this.mOnViewClick=mOnViewClick;


        this.cursor=cursor;



    }



    public interface OnViewClickListener{
        void onViewClicked();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.view_room_recycler,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        cursor.moveToPosition(position);

        RoomBean mRoom=roomData.get(position);

        byte[] blob =cursor.getBlob(cursor.getColumnIndex(FispContract.FispTableRoom.COLUMN_IMAGE_FIRST));

        Bitmap bmp= BitmapFactory.decodeByteArray(blob,0,blob.length);

        holder.mCost.setText(mRoom.getCost());
        holder.mFor.setText(mRoom.getLookingFor());
        holder.mCost.setText(mRoom.getCost());
        holder.imgView.setImageBitmap(ImageUtils.getImage(blob));


    }

    @Override
    public int getItemCount() {
        return roomData.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mInformation,mLocation,mFor,mCost,mMoreInformation;

         ImageView imgView;


        public MyViewHolder(View itemView) {
            super(itemView);
            mInformation=(TextView)itemView.findViewById(R.id.tv_shortInformation);
            mFor=(TextView)itemView.findViewById(R.id.rcy_tv_for);
            mCost=(TextView)itemView.findViewById(R.id.rcy_tv_cost);
            mMoreInformation=(TextView)itemView.findViewById(R.id.rcy_tv_facility);
            imgView = (ImageView) itemView.findViewById(R.id.imageView);

            mMoreInformation.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnViewClick.onViewClicked();
        }
    }
}
