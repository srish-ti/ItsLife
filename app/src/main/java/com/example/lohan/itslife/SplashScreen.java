package com.example.lohan.itslife;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.example.lohan.itslife.DB.App;
import com.example.lohan.itslife.DB.DailyData;
import com.example.lohan.itslife.DB.DailyDataDao;
import com.example.lohan.itslife.DB.DaoMaster;
import com.example.lohan.itslife.DB.DaoSession;
import com.example.lohan.itslife.DB.DbActions;
import com.example.lohan.itslife.DB.Label;
import com.example.lohan.itslife.DB.LabelDao;
import com.example.lohan.itslife.DB.Task;
import com.example.lohan.itslife.Date.DateManipulations;

import java.util.List;

/**
 * Created by lohan on 28-01-2017.
 */

public class SplashScreen extends AppCompatActivity {
    public static DaoSession daoSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoSession = new DaoMaster(db).newSession();
        App.setDaoSession(daoSession);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {
            // run your one time code

            initLabels();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }

    }
    private void initLabels(){
        LabelDao labelDao = daoSession.getLabelDao();
        Label label=new Label(null,"Extra",null,null,DateManipulations.getCurrentDate(),0,null,false,null);
        labelDao.insert(label);


    }
    /*
    private void makeDailyDataForToday(){//make this at every 12 AM
        String date=DateManipulations.getCurrentDate();
        //check if DailyData exists for this date
        //if does not exists then make it and put tasks in it
        List<DailyData> dailyDataList= DbActions.getDateSpecificDailyDataFromDb(date);
        if(dailyDataList==null || dailyDataList.size()==0){//does not exist
            DailyDataDao dailyDataDao=daoSession.getDailyDataDao();
            DailyData dailyData=new DailyData(null,date,DbEnums.N_A.name(),null,1.0*DbEnums.N_A.getVal());
            dailyDataDao.insert(dailyData);
            List<Task> taskList=DbActions.getTaskListFromDb();
            List<Label> labelList=DbActions.getLabelListFromDb();
            makeTodaysData(dailyData,taskList,labelList);
        }
    }*/
}
