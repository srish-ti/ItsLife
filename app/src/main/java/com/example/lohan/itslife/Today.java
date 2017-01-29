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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lohan.itslife.DB.App;
import com.example.lohan.itslife.DB.DailyData;
import com.example.lohan.itslife.DB.DailyDataDao;
import com.example.lohan.itslife.DB.DbActions;
import com.example.lohan.itslife.DB.Label;
import com.example.lohan.itslife.DB.Task;

import java.util.Date;
import java.util.List;

public class Today extends AppCompatActivity {
private Button dateButton;
    private EditText noteEditText;
    private  ChipView chipView;
    private Spinner fulfilledSpinner;// // TODO: 28-01-2017
    private String[] taskNamesArray;
    private ArrayAdapter<String> chipAdapter;

    private Date date;
    private String note,fulfilled;
    private List<Task> taskList,tasksToBeDone,tasksActuallyDone;
    private List<DailyData> dailyDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        fetchFromDb();
        getTaskNamesArray();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.today_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("New Objective");

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        noteEditText=(EditText)findViewById(R.id.today_note_editText);


        chipAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskNamesArray);

        chipView = (ChipView) findViewById(R.id.today_tasks_chipView);
        chipView.setAdapter(chipAdapter);
        chipView.allowDuplicates(false);
        if(savedInstanceState==null){
            chipView.setPrefix("Tasks :");
        }

        dateButton=(Button)findViewById(R.id.today_date_button);
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
                    insertNewDailyDataToDb();
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
        //// TODO: 29-01-2017 initialize fulfilled to a default
        for(DailyData dailyData:dailyDataList){
            if(dailyData.getDate().equals(date))
                return "Daily data for the same date already exists";
        }
        if(fulfilled==null)
            return "Enter fulfilled field";
        return null;
    }
    private void fetchFromDb(){
        dailyDataList= DbActions.getDailyDataListFromDb();
        taskList=DbActions.getTaskListFromDb();
    }
    private boolean insertNewDailyDataToDb(){
        //// TODO: 29-01-2017 the DailyDataObject for that day will already be there unless it is of a different custom date by user. Thus we have to edit and not create a new DailyData
        try {
            DailyDataDao dailyDataDao= App.getDaoSession().getDailyDataDao();
            DailyData dailyData=new DailyData(null,date,note,fulfilled,null);
            dailyDataDao.insert(dailyData);
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

}
