package com.example.helge.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Def. der Variablen
    private TextView n;
    private Button b;
    private EditText eingabe;
    private ListView anrede;
    private ArrayAdapter<String> adapter;
    private boolean firstClick = true;
    private String ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Zuweisungen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n = (TextView) findViewById(R.id.nachricht);
        b = (Button) findViewById(R.id.weiter);
        eingabe = (EditText) findViewById(R.id.eingabe);
        anrede = (ListView) findViewById(R.id.anrede);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.anredeAll));
        anrede.setAdapter(adapter);
        n.setText(R.string.hallo2);
        b.setText(R.string.btntext);
        b.setEnabled(false);

        // Aktion bei Auswahl der Anrede
        anrede.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ar = adapter.getItem(position) + " ";
                //anrede.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                //anrede.setSelector(android.R.color.holo_blue_dark);

            }
        });

        // Aktion bei Eingabe von Text
        eingabe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                b.setEnabled(editable.length() > 0);

            }
        });

        // Buttonfunktion
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstClick) {
                    n.setText(getString(R.string.hallo, ar + eingabe.getText()));
                    eingabe.setVisibility(View.INVISIBLE);
                    anrede.setVisibility(View.INVISIBLE);
                    firstClick = false;
                } else
                    finish();
            }
        });
    }
}
