package com.frantic.simplecrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText name,address,faculty,phone,email;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);Intent intent=new Intent();
        name = (EditText) findViewById(R.id.ed_add_name);
        address = (EditText) findViewById(R.id.ed_add_address);
        faculty = (EditText) findViewById(R.id.ed_add_faculty);
        phone = (EditText) findViewById(R.id.ed_add_phone);
        email = (EditText) findViewById(R.id.ed_add_email);
        btn= (Button) findViewById(R.id.btn_add);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("address",address.getText().toString());
                intent.putExtra("faculty",faculty.getText().toString());
                intent.putExtra("phone",phone.getText().toString());
                intent.putExtra("email",email.getText().toString());
                setResult(1,intent);
                finish();
            }
        });

    }

}
