package gr.hua.android.it21608.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Sources : android documentation , java documentation, youtube link : https://www.youtube.com/watch?v=cp2rL3sAFmI (SQLite) , E-class videos , stack overflow


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editFname;
    EditText editLname;
    EditText editAge;
    EditText editSearch;
    Button buttonInsert;
    Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //call the contrustor so that the DB is created
        myDB = new DatabaseHelper((this));

        editFname = findViewById(R.id.editText1);
        editLname = findViewById(R.id.editText2);
        editAge = findViewById(R.id.editText3);
        editSearch = findViewById(R.id.editText4);
        buttonInsert = findViewById(R.id.button1);
        buttonSearch = findViewById(R.id.button2);

        //Drop Table
        //myDB.dropTable();

        //inserting the data from the edit texts
        addDataToDB();

        //search the database and depends on the searchfield
        searchDataFromDB();
    }

    //insert the data to the DB
    public void addDataToDB() {
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editFname.getText().toString().matches("") || editLname.getText().toString().matches("") || editAge.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Fill in all the credentials please", Toast.LENGTH_LONG).show();
                } else {
                    boolean isInserted = myDB.insertData(editFname.getText().toString(), editLname.getText().toString(), Integer.parseInt(editAge.getText().toString()));
                    if (isInserted = true)
                        Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this, "Data inserted unsuccessfully", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void searchDataFromDB() {

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editSearch.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "You did not enter something to search", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("searchField", editSearch.getText().toString()); //in order to pass the value from MainActivity in the searchEditText to SecondActivity
                    editFname.getText().clear();
                    editLname.getText().clear();
                    editAge.getText().clear();
                    editSearch.getText().clear();
                    startActivity(intent); //starting the 2nd activity
                }
            }
        });
    }

}
