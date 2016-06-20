package com.frantic.simplecrud;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database db;
    TextView textView;
    StringBuffer buffer;
    ArrayAdapter<String> stringArrayAdapter;
    ListView listView;
    List<String> stringList;
    MyAdapter adapter;
    List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new Database(this);
        listView = (ListView) findViewById(R.id.lister);
        stringList = new ArrayList<>();
        studentList = new ArrayList<>();


        getValue();

        adapter = new MyAdapter(this,R.layout.list_item,studentList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = studentList.get(position);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("name",student.getName());
                intent.putExtra("address",student.getAddress());
                intent.putExtra("faculty",student.getFaculty());
                intent.putExtra("phone",student.getPhone());
                intent.putExtra("email",student.getEmail());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence[] sequences ={"Update","Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Actions");
                alert.setItems(sequences, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Student student = studentList.get(position);
                        if (which==0){
                            Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                            intent.putExtra("id",student.getId());
                            intent.putExtra("name",student.getName());
                            intent.putExtra("address",student.getAddress());
                            intent.putExtra("faculty",student.getFaculty());
                            intent.putExtra("phone",student.getPhone());
                            intent.putExtra("email",student.getEmail());
                            startActivityForResult(intent,2);
                        }
                        if (which==1){
                            db.deleteRecord(student);
                            studentList.clear();
                            getValue();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                //alert.setMessage("today");
                alert.create();
                alert.show();
                return true;
            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence[] sequences ={"Update","Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Actions");
                alert.setItems(sequences, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Student student = studentList.get(position);
                        if (which==0){
                            Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                            intent.putExtra("id",student.getId());
                            intent.putExtra("name",student.getName());
                            intent.putExtra("address",student.getAddress());
                            intent.putExtra("faculty",student.getFaculty());
                            intent.putExtra("phone",student.getPhone());
                            intent.putExtra("email",student.getEmail());
                            startActivityForResult(intent,2);
                        }
                        if (which==1){
                            db.deleteRecord(student);
                            studentList.clear();
                            getValue();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                //alert.setMessage("today");
                alert.create();
                alert.show();
            }
        });*/


    }

    private void getValue() {
        for (int i = 0; i < db.getAllStudents().size(); i++) {
            stringList.add(db.getAllStudents().get(i).getName());
            Student student = new Student();
            student.setId(db.getAllStudents().get(i).getId());
            student.setName(db.getAllStudents().get(i).getName());
            student.setAddress(db.getAllStudents().get(i).getAddress());
            student.setFaculty(db.getAllStudents().get(i).getFaculty());
            student.setPhone(db.getAllStudents().get(i).getPhone());
            student.setEmail(db.getAllStudents().get(i).getEmail());
            studentList.add(student);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id==R.id.action_add){
            Intent intent = new Intent(this,AddActivity.class);
            startActivityForResult(intent,1);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==1){
                Log.d("data",data.getStringExtra("name"));
                Student student = new Student();
                student.setName(data.getStringExtra("name"));
                student.setAddress(data.getStringExtra("address"));
                student.setFaculty(data.getStringExtra("faculty"));
                student.setPhone(data.getStringExtra("phone"));
                student.setEmail(data.getStringExtra("email"));
                db.addStudent(student);

                studentList.clear();
                getValue();
                adapter.notifyDataSetChanged();


            }
        }
        if (requestCode==2){
            if (resultCode==2){
                studentList.clear();
                getValue();
                adapter.notifyDataSetChanged();
            }
        }
    }
}
