package com.example.test_fpop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
     LinkedList<Funko> funkos;
     ListView listView;
     EditText nameInput;
     EditText numInput;
     EditText typeInput;
     EditText fandomInput;
     EditText onInput;
     EditText ultInput;
     EditText priceInput;
     Cursor mCursor;
     Button insertButton;
     Button deleteButton;

    View.OnClickListener insertListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            String nIn = nameInput.getText().toString();
            int numIn = Integer.parseInt(numInput.getText().toString());
            String tIn = typeInput.getText().toString();
            String fIn = fandomInput.getText().toString();
            String oIn = onInput.getText().toString();
            String uIn = ultInput.getText().toString();
            Double pIn = Double.parseDouble(priceInput.getText().toString());
            Funko f = new Funko(nIn, numIn, tIn, fIn, oIn, uIn, pIn);
            addToList(f);
        }
    };

    View.OnClickListener deleteListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            String nIn = nameInput.getText().toString();
            int numIn = Integer.parseInt(numInput.getText().toString());
            String tIn = typeInput.getText().toString();
            String fIn = fandomInput.getText().toString();
            String oIn = onInput.getText().toString();
            String uIn = ultInput.getText().toString();
            Double pIn = Double.parseDouble(priceInput.getText().toString());
            Funko f = new Funko(nIn, numIn, tIn, fIn, oIn, uIn, pIn);
            deleteFromList(f);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        nameInput = findViewById(R.id.nameET);
        numInput = findViewById(R.id.numET);
        typeInput = findViewById(R.id.typeET);
        fandomInput = findViewById(R.id.fandomET);
        onInput = findViewById(R.id.onET);
        ultInput = findViewById(R.id.ultimateET);
        priceInput = findViewById(R.id.priceET);
        insertButton = findViewById(R.id.insertButton);
        deleteButton = findViewById(R.id.deleteButton);
        updatelistUI();
        insertButton.setOnClickListener(insertListener);
        deleteButton.setOnClickListener(deleteListener);
    }
    public void addToList(Funko f){
        if(funkos == null){
            funkos =  new LinkedList<>();
        }
        funkos.add(f);
        ContentValues mNewValues = new ContentValues();

        mNewValues.put(funkoprovider.POP_NAME, f.getName());
        mNewValues.put(funkoprovider.POP_NUMBER, f.getNumber());
        mNewValues.put(funkoprovider.POP_TYPE, f.getType());
        mNewValues.put(funkoprovider.FANDOM, f.getFandom());
        mNewValues.put(funkoprovider.COLUMN_ON, f.getOn());
        mNewValues.put(funkoprovider.ULTIMATE, f.getUltimate());
        mNewValues.put(funkoprovider.PRICE, f.getPrice());

        getContentResolver().insert(funkoprovider.CONTENT_URI, mNewValues);

        //clear();
        updatelistUI();

    }

    public void deleteFromList(Funko f){
        funkos.remove(f);
    }

    public void updatelistUI(){
        mCursor = getContentResolver().query(
                funkoprovider.CONTENT_URI, null, null,
                null, null);
        funkos = new LinkedList<>();
        if (mCursor != null) {
            mCursor.moveToFirst();
            if (mCursor.getCount() > 0) {
                while(mCursor.isAfterLast() == false) {
                    String name = mCursor.getString(1);
                    int num = mCursor.getInt(2);
                    String type = mCursor.getString(3);
                    String fandom = mCursor.getString(4);
                    String on = mCursor.getString(5);
                    String ultimate = mCursor.getString(6);
                    Double price = mCursor.getDouble(7);

                    funkos.add(new Funko(name, num, type, fandom, on, ultimate, price));
                    mCursor.moveToNext();
                }
            }
        }

        ArrayAdapter<Funko> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, funkos);
        listView.setAdapter(adapter);
    }
}