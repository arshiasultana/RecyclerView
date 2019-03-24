package com.example.recyclerview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //creating member variables

    private EditText mName;
    private RegistrationAdapter mAdapter;
    private EditText mCourse;
    private TextView mYear,selectYear;
    private int myear = 0;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //adding database
        RegistrationDBHelper dbHelper = new RegistrationDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();



        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RegistrationAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        //adding delete button:
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            //deleting
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                removeItem((long) viewHolder.itemView.getTag());
            }

            //attaching to recycleview
        }).attachToRecyclerView(recyclerView);

        mName = findViewById(R.id.name);
        mCourse=findViewById(R.id.course);
        mYear = findViewById(R.id.year);
        selectYear=findViewById(R.id.select_year);

        Button buttonIncrease = findViewById(R.id.button_increase);
        Button buttonDecrease = findViewById(R.id.button_decrease);
        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonUpdate = findViewById(R.id.button_update);


//setting on click listeners for buttons
        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase();
            }
        });


        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

    }


   //method to increase the year
    private void increase() {

        myear++;
        mYear.setText(String.valueOf(myear));

    }
  //method to decrease the year
    private void decrease(){
        if(myear > 0){
        myear--;
        mYear.setText(String.valueOf(myear));

    }}
 //method to add anew student
    private void add(){

        //check the content

        if(mName.getText().toString().trim().length() == 0 || myear == 0) {
            return;
        }
        String Name = mName.getText().toString();
        String Course = mCourse.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(RegistrationContract.RegistrationEntry.COLUMN_NAME, Name);
        cv.put(RegistrationContract.RegistrationEntry.COLUMN_COURSE, Course);
        cv.put(RegistrationContract.RegistrationEntry.COLUMN_YEAR, myear );

        mDatabase.insert(RegistrationContract.RegistrationEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());
        mName.getText().clear();
        mCourse.getText().clear();
    }




    //creating method for removing item

    private void removeItem(long id) {
        mDatabase.delete(RegistrationContract.RegistrationEntry.TABLE_NAME,
                RegistrationContract.RegistrationEntry._ID + "=" + id,null);
        mAdapter.swapCursor(getAllItems());
    }

//method to get all the items
    private Cursor getAllItems(){
        return  mDatabase.query(
                RegistrationContract.RegistrationEntry.TABLE_NAME,
                null,
                 null,
                 null,
                null,
                null,
                RegistrationContract.RegistrationEntry.COLUMN_YEAR + " DESC"
        );
    }

}
