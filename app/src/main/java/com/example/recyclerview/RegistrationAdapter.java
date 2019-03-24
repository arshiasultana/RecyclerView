package com.example.recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationAdapter.RegistrationViewHolder> {

  //creating member variables
   private Context mContext;
   private Cursor mCursor;



//cursor gets the values out of database
    public RegistrationAdapter(Context context, Cursor cursor){

        mContext = context;
        mCursor = cursor;

    }

    public class RegistrationViewHolder extends RecyclerView.ViewHolder{


        public TextView nameText;
        public TextView courseText;
        public TextView yearText;



        public RegistrationViewHolder(View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textview_name);
            courseText = itemView.findViewById(R.id.textview_course);
            yearText = itemView.findViewById(R.id.textview_year);


        }
    }

    @Override
    public RegistrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.registrationlist, parent, false);
        return new RegistrationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RegistrationViewHolder holder, int position) {
    //data is displayed

        if(!mCursor.moveToPosition(position)){
            return;

        }

        String name= mCursor.getString(mCursor.getColumnIndex(RegistrationContract.RegistrationEntry.COLUMN_NAME));
        String course= mCursor.getString(mCursor.getColumnIndex(RegistrationContract.RegistrationEntry.COLUMN_COURSE));
        int year = mCursor.getInt(mCursor.getColumnIndex(RegistrationContract.RegistrationEntry.COLUMN_YEAR));

        long id = mCursor.getLong(mCursor.getColumnIndex(RegistrationContract.RegistrationEntry._ID));

        holder.nameText.setText("Name:" +name);
        holder.courseText.setText("Course:" + course);
        holder.yearText.setText(String.valueOf("YEAR:" +year ));
        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if( mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
