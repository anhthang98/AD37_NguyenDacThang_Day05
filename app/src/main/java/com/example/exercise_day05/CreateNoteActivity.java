package com.example.exercise_day05;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.security.cert.CRLException;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtTitle, edtDescription;
    TextView tvTime, tvDate, tvTag, tvWeek;
    Spinner spType;
    Button btnTune;
    ArrayList<String> type;
    String[] listItemWeek, listItemTag;
    boolean[] checkedItemsTag, checkedItemsWeek;
    ArrayList<Integer> mUserItemsTag = new ArrayList<>();
    ArrayList<Integer> mUserItemsWeek = new ArrayList<>();
    int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        edtTitle = findViewById(R.id.edtTitle);
        edtDescription = findViewById(R.id.edtDescription);
        tvTime = findViewById(R.id.tvTime);
        tvDate = findViewById(R.id.tvDate);
        tvTag = findViewById(R.id.tvTag);
        tvWeek = findViewById(R.id.tvWeeek);
        btnTune = findViewById(R.id.btnTune);
        spType = findViewById(R.id.spType);

        listItemTag = getResources().getStringArray(R.array.tag_item);
        checkedItemsTag = new boolean[listItemTag.length];

        listItemWeek = getResources().getStringArray(R.array.week_item);
        checkedItemsWeek = new boolean[listItemWeek.length];

        tvDate.setOnClickListener(CreateNoteActivity.this);
        tvTime.setOnClickListener(CreateNoteActivity.this);

        type = new ArrayList<>();
        type.add("Work");
        type.add("Friend");
        type.add("Family");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, type);
        spType.setAdapter(arrayAdapter);

        tvTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(CreateNoteActivity.this)
                        .setTitle("Choose tags:")
                        .setMultiChoiceItems(listItemTag, checkedItemsTag, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean isChecks) {
                                if (isChecks) {
                                    if (!mUserItemsTag.contains(i)) {
                                        mUserItemsTag.add(i);
                                    }
                                } else if (mUserItemsTag.contains(i)) {
                                    mUserItemsTag.remove(Integer.valueOf(i));
                                }
                            }
                        })
                        .setCancelable(false)

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String item = "";
                                for (i = 0; i < mUserItemsTag.size(); i++) {
                                    item = item + listItemTag[mUserItemsTag.get(i)];
                                    if (i != mUserItemsTag.size() - 1) {
                                        item = item + ", ";
                                    }
                                }
                                tvTag.setText(item);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                alertDialog.show();

            }
        });

        tvWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateNoteActivity.this);

                builder.setTitle("Choose day:");

                builder.setMultiChoiceItems(listItemWeek, checkedItemsWeek, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecks) {

                        if (isChecks) {
                            mUserItemsWeek.add(i);
                        } else {
                            mUserItemsWeek.remove((Integer.valueOf(i)));
                        }

                    }
                });
                builder.setCancelable(false);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String item = "";
                        for (i = 0; i < mUserItemsWeek.size(); i++) {
                            item = item + listItemWeek[mUserItemsWeek.get(i)];
                            if (i != mUserItemsWeek.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        tvWeek.setText(item);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.tune_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mnItemFromFile:
                                Toast.makeText(getBaseContext(), "Save", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mnItemDefault:
                                String[] tuneType = {"Nexus Tune", "Window Tune", "Peep Tune", "Nokia Tune", "Etc"};
                                AlertDialog alertDialog = new AlertDialog.Builder(CreateNoteActivity.this)
                                        .setTitle("Choose tune:")
                                        .setSingleChoiceItems(tuneType, 1, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        })
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .create();
                                alertDialog.show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnItemFromFile:
                Toast.makeText(getBaseContext(), R.string.from_file, Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnItemDefault:
                Toast.makeText(getBaseContext(), R.string.from_default, Toast.LENGTH_SHORT).show();
                break;

            case R.id.mnItemCancel:
                edtTitle.setText("");
                edtDescription.setText("");
                tvTag.setText(R.string.tag_default);
                tvWeek.setText(R.string.week_default);
                tvTime.setText(R.string.time_default);
                tvDate.setText(R.string.date_default);
                // startActivity(new Intent(getBaseContext(), MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        if (view == tvDate) {

            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateNoteActivity.this, new OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    tvDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }, year, month, day);
            datePickerDialog.show();
        }

        if (view == tvTime) {
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(CreateNoteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    tvTime.setText(hourOfDay + ":" + minute);
                }
            }, hour, minute, false);
            timePickerDialog.show();
        }
    }
}
