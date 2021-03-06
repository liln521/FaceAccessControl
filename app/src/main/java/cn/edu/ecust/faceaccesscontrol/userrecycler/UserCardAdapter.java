package cn.edu.ecust.faceaccesscontrol.userrecycler;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.edu.ecust.faceaccesscontrol.R;
import cn.edu.ecust.faceaccesscontrol.activity.AdminUserDetialActivity;

/**
 * Created by CommissarMa on 2017/5/3.
 */

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {

    private Context mContext;
    private List<UserCard> mUserCardList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView faceImage;
        TextView userNo;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            faceImage=(ImageView)view.findViewById(R.id.usercarditem_imageview_face);
            userNo=(TextView)view.findViewById(R.id.usercarditem_textview_userno);
        }
    }

    public UserCardAdapter(List<UserCard> userCardList){
        mUserCardList=userCardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.usercard_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                UserCard userCard=mUserCardList.get(position);
                //Toast.makeText(v.getContext(),userCard.getStringUserNo(),Toast.LENGTH_SHORT).show();
                Intent intentAdminUserDetialActivity=new Intent(v.getContext(), AdminUserDetialActivity.class);
                intentAdminUserDetialActivity.putExtra("userNo",userCard.getStringUserNo());
                mContext.startActivity(intentAdminUserDetialActivity);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserCard userCard=mUserCardList.get(position);
        holder.userNo.setText(userCard.getStringUserNo());
        Glide.with(mContext).load(userCard.getStringUserFacePath()).into(holder.faceImage);
    }

    @Override
    public int getItemCount() {
        return mUserCardList.size();
    }
}
