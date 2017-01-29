package com.example.lohan.itslife;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.lohan.itslife.DB.App;
import com.example.lohan.itslife.DB.DbActions;
import com.example.lohan.itslife.DB.Label;
import com.example.lohan.itslife.DB.LabelDao;
import com.example.lohan.itslife.DB.Task;
import com.example.lohan.itslife.Date.DateManipulations;
import com.tokenautocomplete.TokenCompleteTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewLabel extends AppCompatActivity implements TokenCompleteTextView.TokenListener{

    private EditText titleEditText,noteEditText;
    private Button repeatStringButton;

    private EditText taskPerDayeditText;
    private Switch completedSwitch;
    private ChipView chipView;
    private List<Task> taskList;
    private List<Label> labelList;
    private ArrayAdapter<String> chipAdapter;

    private String title,note,repeatString;
    private String[] taskNamesArray;
    private List<Task> tasksInLabel;
    private int tasksPerDay;
    private boolean completed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_label);
        fetchFromDb();
        getTaskNamesArray();
        getTaskNamesArray();
        tasksInLabel=new ArrayList<>();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.label_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("New Objective");

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        chipAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskNamesArray);

        chipView = (ChipView) findViewById(R.id.label_tasks_chipView);
        chipView.setAdapter(chipAdapter);
        chipView.allowDuplicates(false);
        if(savedInstanceState==null){
            chipView.setPrefix("Tasks :");
        }
        titleEditText=(EditText)findViewById(R.id.label_title_editText);
        noteEditText=(EditText)findViewById(R.id.label_note_editText);
        repeatStringButton=(Button)findViewById(R.id.label_repeat_button);

    taskPerDayeditText=(EditText)findViewById(R.id.label_taskPerDay_editText);

        completedSwitch = (Switch) findViewById(R.id.label_completed_switch);
        completedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
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
                if(error==null) {
                    insertNewLabelToDb();
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
   // validateTaskAndLabelRepeatString(){//use tasksInLabel...call each time repeatString is changed or tasksInLabel is modified
        //// TODO: 29-01-2017 if a task is added to a label it is removed from previous label

    private String validate(){
        if(!(title==null || ("").equals(title.trim())))
        {
            if(tasksPerDay==0)
                return "Tasks/Day should be >=1";
            for(Label label:labelList){
                if(label.getTitle().equalsIgnoreCase(title))
                    return "This name already exists";
            }
        }
        else{
            return "Set a valid title for Task";
        }
        return null;
    }
    private boolean insertNewLabelToDb(){
        try{
            LabelDao labelDao= App.getDaoSession().getLabelDao();
            Label label=new Label(null,title,note,repeatString, DateManipulations.getCurrentDate(),tasksPerDay,null,completed,null);
            labelDao.insert(label);
            if(tasksInLabel!=null && tasksInLabel.size()>0){
                for(Task task:tasksInLabel){
                    task.setTaskInLabel(label);
                }
            }
            return true;
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private void fetchFromDb(){
        labelList=DbActions.getLabelListFromDb();
        taskList= DbActions.getTaskListFromDb();
    }
    private void getTaskNamesArray(){
        int size=taskList.size();
        taskNamesArray=new String[size];
        for(int i=0;i<size;i++){
            taskNamesArray[i]=taskList.get(i).getTitle();
        }

    }
    private Task getTaskFromString(String taskName){
        //taskList must be sorted according to names, then perform binary search on taskList wrt taskName
        //taskList is sorted by sql, no need to sort here
        Comparator c=new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        };
        Collections.sort(taskList,c);//// TODO: 29-01-2017 precaution, if DB returns sorted tasklist then don't do it
        int pos=Collections.binarySearch(taskList, taskName,c);
        if(pos>=0)
            return taskList.get(pos);
        return null;
    }
    @Override
    public void onTokenAdded(Object token) {
        String taskName=(String)token;
        Task task=getTaskFromString(taskName);
        if(task!=null)
            tasksInLabel.add(task);
    }

    @Override
    public void onTokenRemoved(Object token) {
        String taskName=(String)token;
        Task task=getTaskFromString(taskName);
        if(task!=null)
            tasksInLabel.remove(task);
    }
}
