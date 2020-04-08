package gr.hua.android.it21608.assignment1;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends MainActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        TableLayout tableLayout = findViewById(R.id.TableLayout);
        myDB = new DatabaseHelper(this);
        String searchQuery = getIntent().getExtras().getString("searchField"); // getting the String from the searchfield
        Cursor results = myDB.fnameQuery(searchQuery);

        if (results.getCount() == 0)
            //if the DB is empty pop up message
            Toast.makeText(SecondActivity.this, "DB is empty", Toast.LENGTH_LONG).show();
        else{
            while (results.moveToNext()) {

                TableRow tableRow1 = new TableRow(this   );
                //Set the layout parameters associated with this view. These supply parameters to the parent of this view specifying how it should be arranged.
                tableRow1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));

                TextView field1 = new TextView(this);
                field1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                field1.setBackgroundColor(Color.GRAY);
                field1.setPadding(70, 20, 10, 20);
                field1.setText(results.getString(0));

                TextView field2 = new TextView(this);
                field2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                field2.setBackgroundColor(Color.GRAY);
                field2.setPadding(70, 20, 10, 20);
                field2.setText(results.getString(1));

                TextView field3 = new TextView(this);
                field3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                field3.setBackgroundColor(Color.GRAY);
                field3.setPadding(70, 20, 10, 20);
                field3.setText(results.getString(2));

                TextView field4 = new TextView(this);
                field4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                field4.setBackgroundColor(Color.GRAY);
                field4.setPadding(70, 20, 10, 20);
                field4.setText(results.getString(3));

                tableRow1.addView(field1);
                tableRow1.addView(field2);
                tableRow1.addView(field3);
                tableRow1.addView(field4);

                tableLayout.addView(tableRow1);




            }

        }

    }




}
