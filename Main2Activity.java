package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

public class Main2Activity extends AppCompatActivity {
    private TextView Display;
    private ListView mListView;
    String getValue1,getValue2;
    /*SQLiteDatabase mydatabase = openOrCreateDatabase("Money",MODE_PRIVATE,null);*/
     DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Display=(TextView)findViewById(R.id.Display);

        mDatabaseHelper=new DatabaseHelper(this);
        populateView();

        /*Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getInstance().format(calendar.getTime());*/

        String currentDate=new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());


       /* getValue1=getIntent().getExtras().getString("Amount");
        getValue2=getIntent().getExtras().getString("SpentFor");

        Display.setText(" "+currentDate+"\n\n"+"  Paid:"+getValue1+"\n"+"  For:"+getValue2);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.expenses,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"Back",Toast.LENGTH_SHORT);
                openAcitivity1();
                return  true;
            case R.id.app_bar_search:
                Toast.makeText(this,"Search For",Toast.LENGTH_SHORT);

                return  true;
                default:return super.onOptionsItemSelected(item);
        }

    }

    private void openAcitivity1() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void populateView(){
        Cursor data = mDatabaseHelper.getData();
        ListView listView=(ListView)findViewById(R.id.list1);
        ArrayList<String> theList=new ArrayList<>();

        if(data.getCount()==0){
           // showMessage("Error","!!Nothing found");
            return;
        }
        //StringBuffer buffer=new StringBuffer();
        while (data.moveToNext()){
            /*buffer.append("Date: "+data.getString(0)+"\n");
            buffer.append("Amount: "+data.getString(1)+"\n");
            buffer.append("SpentFor: "+data.getString(2)+"\n\n");*/
            theList.add(data.getString(0));
            theList.add(data.getString(1));
            theList.add(data.getString(2));
            ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
            listView.setAdapter(listAdapter);


        }
        //showMessage("Data",buffer.toString());
    }

    /*public void showMessage(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }*/

}
