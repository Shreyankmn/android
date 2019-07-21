package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button Done,ViewExpenses;
    private EditText Amount,SpentFor ;
    String amount,forWhat;
    DatabaseHelper mDatabaseHelper;
    final String currentDate=new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Done=(Button)findViewById(R.id.button);
        Amount=(EditText)findViewById(R.id.editText);
        SpentFor=(EditText)findViewById(R.id.editText2);
        ViewExpenses=(Button)findViewById(R.id.button2);
        mDatabaseHelper=new DatabaseHelper(this);
        final StringBuffer str=new StringBuffer();


        Done.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          amount=Amount.getText().toString();
                                          forWhat=SpentFor.getText().toString();


                                          if(amount.matches("") || forWhat.matches("")) {
                                              toastMessage("Enter data");

                                          }else {
                                              AddData(currentDate, amount, forWhat);
                                              Amount.setText("");
                                              SpentFor.setText("");
                                              openActivity2();

                                          }
                                      }
                                  }


        );
        ViewExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();

            }
        });
    }
    public void openActivity2(){
        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("Amount",amount);
        intent.putExtra("SpentFor",forWhat);

        startActivity(intent);
    }
    public void AddData(String item0,String item1,String item2){
        boolean insertData=mDatabaseHelper.addData(currentDate,amount,forWhat);
        if(insertData){
            toastMessage("Data entered successfully");
        }else{
            toastMessage("Something went wrong");
        }
    }
    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
