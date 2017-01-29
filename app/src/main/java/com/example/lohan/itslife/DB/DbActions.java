package com.example.lohan.itslife.DB;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lohan on 28-01-2017.
 */

public class DbActions {
    private static DaoSession daoSession = App.getDaoSession();
    public static List<Label> getLabelListFromDb(){
        LabelDao labelDao = daoSession.getLabelDao();
        QueryBuilder qb=labelDao.queryBuilder().orderAsc(LabelDao.Properties.LabelId);
        List<Label> labelList=qb.list();
        return labelList;
    }
    public static List<Task> getTaskListFromDb(){
        TaskDao taskDao = daoSession.getTaskDao();
        QueryBuilder qb=taskDao.queryBuilder().orderAsc(TaskDao.Properties.Title);//sorted by title to enable binary search in NewLabel
        List<Task> taskList=qb.list();
        return taskList;
    }
    public static List<DailyData> getDailyDataListFromDb(){
        DailyDataDao dailyDataDao=daoSession.getDailyDataDao();
        QueryBuilder qb=dailyDataDao.queryBuilder();
        List<DailyData> dailyDataList=qb.list();
        return dailyDataList;
    }
    public static List<DailyData> getDateSpecificDailyDataFromDb(String date){
        DailyDataDao dailyDataDao=daoSession.getDailyDataDao();
        QueryBuilder qb=dailyDataDao.queryBuilder();
        qb.where(DailyDataDao.Properties.Date.eq(date));
        List<DailyData> dailyDataList=qb.list();
        return dailyDataList;
    }
    public static List<Objective> getObjectivesFromDb(){
        ObjectiveDao objectiveDao=daoSession.getObjectiveDao();
        QueryBuilder qb=objectiveDao.queryBuilder();
        return qb.list();
    }

}
