package com.frantic.simplecrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    EditText name,address,faculty,phone,email;
    Button btn;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name = (EditText) findViewById(R.id.ed_add_name);
        address = (EditText) findViewById(R.id.ed_add_address);
        faculty = (EditText) findViewById(R.id.ed_add_faculty);
        phone = (EditText) findViewById(R.id.ed_add_phone);
        email = (EditText) findViewById(R.id.ed_add_email);
        btn= (Button) findViewById(R.id.btn_add);
        db = new Database(this);

        final Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        address.setText(intent.getStringExtra("address"));
        faculty.setText(intent.getStringExtra("faculty"));
        phone.setText(intent.getStringExtra("phone"));
        email.setText(intent.getStringExtra("email"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setId(intent.getIntExtra("id",0));
                student.setName(name.getText().toString());
                student.setAddress(address.getText().toString());
                student.setFaculty(faculty.getText().toString());
                student.setPhone(phone.getText().toString());
                student.setEmail(email.getText().toString());

                db.updateRecord(student);

                setResult(2);
                finish();
            }
        });
    }
}
