package com.example.lohan.itslife;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.lohan.itslife.DB.App;
import com.example.lohan.itslife.DB.DbActions;
import com.example.lohan.itslife.DB.Label;
import com.example.lohan.itslife.DB.Task;
import com.example.lohan.itslife.DB.TaskDao;
import com.example.lohan.itslife.Date.DateManipulations;

import java.util.Date;
import java.util.List;

public class NewTask extends AppCompatActivity {
private EditText titleEditText;
    private EditText noteEditText;
    private Date startDate, actualEndDate;
    private Button repeatStringButton;
    private Spinner inLabelSpinner;//todo
    private Button startDateButton;
    private Button todayButton;
    private Button tomorrowButton;
    private Spinner importanceSpinner;//todo
    private EditText reviseEditText;
    private Switch trivialSwitch;
    private Switch completedSwitch;
    private String title, repeatString, note,importance;
    private int revise;
    private Label taskInLabel;
    private boolean trivial,completed;
    private List<Label> labelList;
    private List<Task> taskList;

    //// TODO: 29-01-2017 taskInLabel will have "Extra label by default"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        fetchFromDb();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.task_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("NewTask");
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        titleEditText=(EditText)findViewById(R.id.task_title_editText);
        noteEditText=(EditText)findViewById(R.id.task_note_editText);
        repeatStringButton=(Button)findViewById(R.id.task_repeat_button);
        startDateButton=(Button)findViewById(R.id.task_startDate_Button);
        todayButton=(Button)findViewById(R.id.task_startDate_Today);
        tomorrowButton=(Button)findViewById(R.id.task_startDate_Tomorrow);
        reviseEditText=(EditText)findViewById(R.id.task_revise_editText);

        completedSwitch = (Switch) findViewById(R.id.task_completed_switch);
        completedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    completed=true;
                } else {
                    // The toggle is disabled
                    completed=false;
                }
            }
        });

        trivialSwitch = (Switch) findViewById(R.id.task_trivial_switch);
        trivialSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    trivial=true;
                } else {
                    // The toggle is disabled
                    trivial=false;
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.appBar_save:
                String error=validate();
                if(error==null){
                    insertNewTaskToDb();
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
                }
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    private String validate(){
        if(!(title==null || ("").equals(title.trim()))){
            for(Task task:taskList){
                if(task.getTitle().equalsIgnoreCase(title))
                    return "This name already exists";
            }
            //// TODO: 29-01-2017 set taskInLabel to extra and importance to something by default don't need to check then
            if(!(taskInLabel.getTitle().equalsIgnoreCase("Extra")) && taskInLabel.getLabelRepeatString()==null && repeatString==null)
                return "Label and task both do not repeat. Use Extra label";
        }
        else{
            return "Set a valid title for Task";
        }
        if(trivial)
            revise=0;
        return null;
    }
    private void fetchFromDb(){
        //// TODO: 29-01-2017 maybe fetch from DB only once, make Lists and perform on them. Limitation:data structure knowledge
        labelList= DbActions.getLabelListFromDb();
        taskList=DbActions.getTaskListFromDb();

    }
    private boolean insertNewTaskToDb(){
        try{
            TaskDao taskDao= App.getDaoSession().getTaskDao();
            Task task=new Task(null,title,note,repeatString,taskInLabel.getLabelId(),DateManipulations.getCurrentDate(),startDate,actualEndDate,importance,revise,trivial,completed,null,0,null);
            taskDao.insert(task);
            task.setTaskInLabel(taskInLabel);
            return true;
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
