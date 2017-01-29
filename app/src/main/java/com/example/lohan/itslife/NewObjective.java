package com.example.lohan.itslife;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.lohan.itslife.DB.App;
import com.example.lohan.itslife.DB.DbActions;
import com.example.lohan.itslife.DB.JoinObjectiveWithTasks;
import com.example.lohan.itslife.DB.JoinObjectiveWithTasksDao;
import com.example.lohan.itslife.DB.Objective;
import com.example.lohan.itslife.DB.ObjectiveDao;
import com.example.lohan.itslife.DB.Task;
import com.example.lohan.itslife.Date.DateManipulations;
import com.example.lohan.itslife.Date.DatePickerFragment;
import com.example.lohan.itslife.Date.GetDate;
import com.tokenautocomplete.TokenCompleteTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class NewObjective extends AppCompatActivity implements TokenCompleteTextView.TokenListener, GetDate{
private EditText titleEditText,noteEditText;
    private Button startDateButton, endDateButton;
    private Switch  notMeSwitch,completedSwitch;
    private ChipView chipView;
    private String[] taskNamesArray;
    private ArrayAdapter<String> chipAdapter;

    private String title=null;
    private String note=null;
    private Date startDate,endDate;
    private List<Task> taskList,tasksInObjective;
    private List<Objective> objectiveList;
    boolean notMe,completed;
    private final String START_DATE_FRAGMENT_TAG="objective_startDate_fragment";
    private final String END_DATE_FRAGMENT_TAG="objective_endDate_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_objective);
        fetchFromDb();
        getTaskNamesArray();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.objective_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("New Objective");
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        tasksInObjective=new ArrayList<>();
        notMeSwitch = (Switch) findViewById(R.id.objective_not_me_switch);
        notMeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    notMe=true;
                } else {
                    // The toggle is disabled
                    notMe=false;
                }
            }
        });

        completedSwitch = (Switch) findViewById(R.id.objective_completed_switch);
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


        chipAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskNamesArray);

        chipView = (ChipView) findViewById(R.id.objective_tasks_chipView);
        chipView.setAdapter(chipAdapter);
        chipView.allowDuplicates(false);
        if(savedInstanceState==null){
            chipView.setPrefix("Tasks:");
        }

        titleEditText=(EditText)findViewById(R.id.objective_title_editText);
        noteEditText=(EditText)findViewById(R.id.objective_note_editText);
        startDateButton=(Button)findViewById(R.id.objective_time_duration_startDate_button);
        endDateButton=(Button)findViewById(R.id.objective_time_duration_endDate_button);

    }

    public void onObjectiveDateButtonPressed(View v){
        String tag=null;
        if(v==startDateButton)
            tag=START_DATE_FRAGMENT_TAG;
        else if(v==endDateButton)
            tag=END_DATE_FRAGMENT_TAG;
        DialogFragment dialogFragment=new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(),tag);
    }

    @Override
    public void onTokenAdded(Object token) {
        String taskName=(String)token;
        Task task=getTaskFromString(taskName);
        if(task!=null)
            tasksInObjective.add(task);
    }

    @Override
    public void onTokenRemoved(Object token) {
        String taskName=(String)token;
        Task task=getTaskFromString(taskName);
        if(task!=null)
            tasksInObjective.remove(task);
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
                    insertNewObjectiveToDb();
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
        //// TODO: 29-01-2017 maybe letting sql to check for repeat or null, just handle those errors
       if(!(title==null || ("").equals(title.trim()))){
           if(objectiveList!=null || objectiveList.size()>0){
               for(Objective objective:objectiveList) {
                   if (objective.getTitle().equalsIgnoreCase(title))
                       return "This name already exists";
                   Date obSd=objective.getStartDate();
                   Date obEd=objective.getEndDate();
                   int val=startDate.compareTo(obSd)*startDate.compareTo(obEd)*endDate.compareTo(obSd)*endDate.compareTo(obEd);
                   if(!(val>0))
                       return "Objective for this time duration already exists";
               }
               if(startDate==null || endDate==null)
                   return "Enter valid dates";
               if(!endDate.after(startDate))
                   return "End date should be greater than start date";

           }
       }
        else{
           return "Set a valid title for objective";
       }
        return null;
    }
    private void fetchFromDb(){
        this.objectiveList= DbActions.getObjectivesFromDb();
        this.taskList=DbActions.getTaskListFromDb();
    }
    private boolean insertNewObjectiveToDb(){
        try {
            ObjectiveDao objectiveDao = App.getDaoSession().getObjectiveDao();
            Objective objective = new Objective(null, title, note, startDate, endDate, DateManipulations.getCurrentDate(), notMe, completed, null);
            objectiveDao.insert(objective);
            JoinObjectiveWithTasksDao joinObjectiveWithTasksDao=App.getDaoSession().getJoinObjectiveWithTasksDao();
            if(tasksInObjective!=null && tasksInObjective.size()>0){
                for(Task task:tasksInObjective){
                    JoinObjectiveWithTasks joinObjectiveWithTasks=new JoinObjectiveWithTasks(null,objective.getObjectiveId(),task.getTaskId());
                    joinObjectiveWithTasksDao.insert(joinObjectiveWithTasks);
                }
            }
            return true;
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            return false;
        }
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
    public void changeDateButton(Date date,String tag) {
        if(tag.equals(START_DATE_FRAGMENT_TAG)){
            startDate=date;
            startDateButton.setText(DateManipulations.getStringFromDate(date));
        }
        else if(tag.equals(END_DATE_FRAGMENT_TAG)){
            endDate=date;
            endDateButton.setText(DateManipulations.getStringFromDate(date));
        }
    }
}
