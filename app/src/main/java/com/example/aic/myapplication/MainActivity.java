package com.example.aic.myapplication;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private ArrayAdapter adapter2;
    private ArrayAdapter adapter3;
    private LinearLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TabHostオブジェクト取得
        TabHost tabhost = (TabHost)findViewById(android.R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec tab1 = tabhost.newTabSpec("tab1");
        tab1.setIndicator("タブ１");
        tab1.setContent(R.id.tab1);
        tabhost.addTab(tab1);

        TabHost.TabSpec tab2 = tabhost.newTabSpec("tab2");
        tab2.setIndicator("タブ2");
        tab2.setContent(R.id.tab2);
        tabhost.addTab(tab2);

        // TextViewを作成してテキストを設定
        TextView tv = new TextView(this);
        tv.setText("aaa");
        CheckBox checkBox = new CheckBox(this);
        checkBox.setId(0);

        TextView tv1 = new TextView(this);
        tv1.setText("aaa");
        tv1.setId(0);

        Spinner spinner = new Spinner(this);
        spinner.setId(0);

        TextView tv2 = new TextView(this);
        tv2.setText("aaa");

        TextView tv3 = new TextView(this);
        tv3.setText("aaa");

        TextView tv4 = new TextView(this);
        tv4.setText("aaa");

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(new String("W"));
        adapter.add(new String("R"));

        adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.add(new String("aaaa"));
        adapter2.add(new String("bbbb"));

        adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.add(new String("cccc"));
        adapter3.add(new String("dddd"));


        lay = (LinearLayout) this.findViewById(R.id.tab2_linear);
        addLinears();

        // 親のViewGroupを取得して、Viewを追加
        TabHost.TabSpec tab3 = tabhost.newTabSpec("tab3");
        tab3.setIndicator("タブ3");
        tab3.setContent(R.id.tab3);
        tabhost.addTab(tab3);

        tabhost.setCurrentTab(0);

        Button clear = (Button)findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            // チェックボックスがクリックされた時に呼び出されます
            public void onClick(View v) {
                lay.removeAllViews();
                addLinears();
            }
        });

        Button execute = (Button)findViewById(R.id.execute);
        execute.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });

        Button save = (Button)findViewById(R.id.save);
        final EditText text = new EditText(this);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Save")
                .setMessage("Input Senario Name")
                .setView(text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (text.getText().length() == 0) {
                            System.out.println("no name");
                        } else {
                            execute(text.getText().toString());
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                text.setText("");
                alertDialog.show();
            }
        });
    }

    private void execute(String senarioName) {

    }

    private void addLinears() {
        int cnt = lay.getChildCount();
        for (int i = 0; i < lay.getChildCount(); i++) {
            CheckBox checkBox2 = (CheckBox) this.findViewById(i + 1);
            if (!checkBox2.isChecked()) {
                return;
            }
        }

        int i = cnt + 1;
        final LinearLayout linearLayout1 = new LinearLayout(this);
        CheckBox checkBox1 = new CheckBox(this);
        checkBox1.setId(i);
        final Spinner spinner1 = new Spinner(this);
        spinner1.setId(i);

        final Spinner spinner2 = new Spinner(this);
        spinner2.setId(10000 + i);

        final Spinner spinner3 = new Spinner(this);
        spinner3.setId(20000 + i);
        linearLayout1.addView(checkBox1);
        linearLayout1.addView(spinner1);
        linearLayout1.addView(spinner2);
        linearLayout1.addView(spinner3);
        spinner1.setAdapter(adapter);

        lay.addView(linearLayout1);

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            // チェックボックスがクリックされた時に呼び出されます
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                // チェックボックスのチェック状態を取得します
                boolean checked = checkBox.isChecked();
                if (checked) {
                    spinner1.setEnabled(false);
                    spinner2.setEnabled(false);
                    spinner3.setEnabled(false);
                    addLinears();
                } else {
                    spinner1.setEnabled(true);
                    spinner2.setEnabled(true);
                    spinner3.setEnabled(true);
                }
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String item = (String) spinner1.getSelectedItem();
                if (item.equals("W")) {
                    spinner3.setEnabled(true);
                    spinner2.setAdapter(adapter2);
                    setSpinner3Value(spinner3,"aaa");
                } else {
                    spinner3.setEnabled(false);
                    spinner2.setAdapter(adapter3);
                    spinner3.setAdapter(null);
                }
            }
            public void onNothingSelected(AdapterView parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String category = (String) spinner1.getSelectedItem();
                if (category.equals("W")) {
                    String item = (String) spinner2.getSelectedItem();
                    setSpinner3Value(spinner3,item);
                }
            }
            public void onNothingSelected(AdapterView parent) {
            }
        });

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(
                LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.alert_dialog,
                (ViewGroup)findViewById(R.id.dialog_root));
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle((String) spinner2.getSelectedItem())
                .setMessage("Input values")
                .setView(layout)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                final String chara = (String) spinner2.getSelectedItem();
                if (chara.equals("bbbb")) {
                    alertDialog.show();
                }
            }
            public void onNothingSelected(AdapterView parent) {
            }
        });
    }

    private void setSpinner3Value(Spinner spinner3, String name) {
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (name.equals("aaaa")) {
            adapter.add(new String("0"));
            adapter.add(new String("1"));
        } else {
            adapter.add(new String("100"));
            adapter.add(new String("200"));
        }
        spinner3.setAdapter(adapter);
    }
}
