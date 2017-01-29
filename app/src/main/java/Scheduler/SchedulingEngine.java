package Scheduler;

import com.example.lohan.itslife.DB.App;
import com.example.lohan.itslife.DB.DailyData;
import com.example.lohan.itslife.DB.DailyDataDao;
import com.example.lohan.itslife.DB.DbActions;
import com.example.lohan.itslife.DB.JoinDailyDataWithTasksToBeDone;
import com.example.lohan.itslife.DB.JoinDailyDataWithTasksToBeDoneDao;
import com.example.lohan.itslife.DB.Label;
import com.example.lohan.itslife.DB.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by lohan on 29-01-2017.
 */

public class SchedulingEngine {
    private static List<Task> taskList,tasksToBeIncluded;
    private static List<Label> labelList;
    private static Date currentDate;
    public static void makeDailyData(Date date){
        currentDate=date;
        getTasksForToday();
        DailyData dailyData=new DailyData(null,currentDate,null,null,null);
        DailyDataDao dailyDataDao= App.getDaoSession().getDailyDataDao();
        dailyDataDao.insert(dailyData);
        JoinDailyDataWithTasksToBeDoneDao joinDailyDataWithTasksToBeDoneDao=App.getDaoSession().getJoinDailyDataWithTasksToBeDoneDao();
        for(int i=0;i<tasksToBeIncluded.size();i++){
            JoinDailyDataWithTasksToBeDone joinDailyDataWithTasksToBeDone=new JoinDailyDataWithTasksToBeDone(null,dailyData.getDailyDataId(),tasksToBeIncluded.get(i).getTaskId());
            joinDailyDataWithTasksToBeDoneDao.insert(joinDailyDataWithTasksToBeDone);
        }
    }
    private static void fetchFromDb(){
        taskList= DbActions.getTaskListFromDb();
        labelList=DbActions.getLabelListFromDb();
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getTaskInLabelId().compareTo(o2.getTaskInLabelId());
            }
        });
        Collections.sort(labelList, new Comparator<Label>() {
            @Override
            public int compare(Label o1, Label o2) {
                return o1.getLabelId().compareTo(o2.getLabelId());
            }
        });
    }
    private static void getTasksForToday(){
        int j=0,taskListSize=taskList.size(),labelListSize=labelList.size();
        for(int i=0;i<labelListSize;i++){
            Label label=labelList.get(i);
            List<Task> tasksInThisLabel=new ArrayList<>();
            boolean incrementForLabel=false;
            int tasksPerDay=0;
            if(label.getLabelRepeatString()!=null) {
                Date nextLabelOcc = NextOccurrence.getNextOccurrence(label.getLabelRepeatString(), label.getLastOccurrenceDate());
                if(nextLabelOcc.equals(currentDate)) {
                    incrementForLabel = true;
                    tasksPerDay=label.getTasksPerDay();
                }

            }
            //// TODO: 29-01-2017 in NextOccurrence if lastOcc of label is null and repeatString!=null then send nextOcc as today
            for(;j<taskListSize;j++){
                Task task=taskList.get(j);
                if(task.getTaskInLabelId().equals(label.getLabelId())){
                    tasksInThisLabel.add(task);
                    if(task.getMostRecentBacklogDays()>0) {
                        tasksToBeIncluded.add(task);
                        if(incrementForLabel)
                            tasksPerDay--;
                        continue;
                    }
                    if(currentDate.after(task.getStartDate())){
                        Date nextOcc=NextOccurrence.getNextOccurrence(task.getTaskRepeatString(), task.getLastOccurrenceDate());
                        //lastOccurrence can be null, handle in getNextOccurrence appropriately
                        if(currentDate.equals(nextOcc)){
                            tasksToBeIncluded.add(task);
                            if(incrementForLabel)
                                tasksPerDay--;
                        }
                    }
                    else{
                        continue;
                    }
                }
                else{
                    break;
                }
            }
            if(tasksPerDay>0){
                Collections.sort(tasksInThisLabel, new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        Date o1LastOccurrenceDate=o1.getLastOccurrenceDate();
                        Date o2LastOccurrenceDate=o2.getLastOccurrenceDate();
                        if(o1LastOccurrenceDate!=null && o2LastOccurrenceDate!=null)
                            return o1LastOccurrenceDate.compareTo(o2LastOccurrenceDate);
                        else{
                            if(o1LastOccurrenceDate==null && o2LastOccurrenceDate==null)
                                return 0;
                            else if(o1LastOccurrenceDate==null)
                                return -1;
                            else
                                return 1;
                        }
                    }
                });
                for(int k=0;k<tasksInThisLabel.size();k++){
                    Task task=tasksInThisLabel.get(k);
                    if(task.getStartDate()==null || currentDate.after(task.getStartDate())){
                        if(!tasksToBeIncluded.contains(task)){
                            tasksToBeIncluded.add(task);
                            tasksPerDay--;
                        }
                    }
                    if(tasksPerDay==0)
                        break;
                }
            }
        }
    }
}
