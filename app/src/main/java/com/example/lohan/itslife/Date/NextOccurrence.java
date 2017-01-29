/*
package com.example.lohan.itslife.Date;

*/
/**
 * Created by lohan on 19-01-2017.
 *//*


//latest code

import com.example.lohan.mylife.RepeatEnum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

*/
/* Name of the class has to be "Main" only if the class is public. *//*

public class NextOccurrence
{
    //// TODO: 22-01-2017 don't get occurances which are complete like events or date if completed
    private static final String SUN= RepeatEnum.SUN.name(),
    MON=RepeatEnum.MON.name(),
    TUE=RepeatEnum.TUE.name(),
    WED=RepeatEnum.WED.name(),
    THU=RepeatEnum.THU.name(),
    FRI=RepeatEnum.FRI.name(),
    SAT=RepeatEnum.SAT.name(),
    DAILY=RepeatEnum.DAILY.name(),
    WEEKLY=RepeatEnum.WEEKLY.name(),
    MONTHLY=RepeatEnum.MONTHLY.name(),
    ANNUALLY=RepeatEnum.ANNUALLY.name(),
    UNTIL_DATE=RepeatEnum.UNTIL_DATE.name(),
    EVENTS=RepeatEnum.EVENTS.name(),
    SAME_DAY_MONTH=RepeatEnum.SAME_DAY_MONTH.name(),
    LAST_SAT_MONTH=RepeatEnum.LAST_SAT_MONTH.name(),
     FIRST_DAY_MONTH=RepeatEnum.FIRST_DAY_MONTH.name(),
      LAST_DAY_MONTH=RepeatEnum.LAST_DAY_MONTH.name();
    private static int[] whenSpecific;//stores the int value of days and month radio buttons
    private static String untilSpecific;
    private static String[] repeatArr=new String[3];
    private static ArrayList<String> whenTaskInWeek;

    public static Calendar getNextOccurrence(String lastOcc, String repeatString){
        parseRepeatString(repeatString);
        try{
            Date date=new SimpleDateFormat("dd/MM/yyyy").parse(lastOcc);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            int repeatFrequency=Integer.parseInt(repeatArr[1]);
            RepeatEnum whenEnum=RepeatEnum.valueOf(repeatArr[0]);
            switch(whenEnum){
                case DAILY:
                    c.add(Calendar.DATE,repeatFrequency);
                    break;
                case WEEKLY:
                    whenTaskInWeek=new ArrayList();
                    c.add(Calendar.WEEK_OF_YEAR,repeatFrequency);
                    for(int i=0;i<7;i++){
                        int day=c.get(Calendar.DAY_OF_WEEK);
                        for(int d:whenSpecific){
                            if(d==day){
                                //whenTaskInWeek.add(new SimpleDateFormat("EEE dd/MM/yyyy").format(c.getTime()));
                                return c;//remove this return and add break to generate all day's dates in which to repeat
                            }
                        }
                        c.add(Calendar.DATE,1);
                    }
                    break;
                case MONTHLY:
                    int month=c.get(Calendar.MONTH);
                    int year=c.get(Calendar.YEAR);
                    switch(whenSpecific[0]){
                        case 0://same day DONE
                            c.add(Calendar.MONTH,repeatFrequency);
                            break;
                        case 1://last sat DONE
                            c.add(Calendar.MONTH,repeatFrequency);
                            c.add(Calendar.MONTH,1);//goto next month and subtract for last Sat
                            c.add((Calendar.DAY_OF_MONTH),-(c.get(Calendar.DAY_OF_WEEK)));
                            break;
                        case 2://first day DONE
                            c.set(year,month,1);
                            c.add(Calendar.MONTH,repeatFrequency);
                            break;
                        case 3://last day DONE
                            c.add(Calendar.MONTH,repeatFrequency);
                            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                            break;
                    }
                    break;
                case ANNUALLY:
                    c.add(Calendar.YEAR,repeatFrequency);
                    break;
            }
            return c;
            */
/*System.out.println(new SimpleDateFormat("EEE dd/MM/yyyy").format(c.getTime()));
            if(repeatArr[2].equals("EVENTS")){
                //events should be updated only when the task is done

            }
            else if(repeatArr[2].equals("DATE")){

            }*//*


        }
        catch(Exception e){
         return null;
        }
    }
    private static void parseRepeatString(String repeatString){
        String[] parsed=repeatString.split(";");
        RepeatEnum whenEnum=RepeatEnum.valueOf(parsed[0]);
        switch(whenEnum){
            case WEEKLY:
                String[] spWhen=parsed[2].split(",");
                whenSpecific=new int[spWhen.length];
                for(int i=0;i<spWhen.length;i++){
                    RepeatEnum spWhenEnum=RepeatEnum.valueOf(spWhen[i]);
                    switch(spWhenEnum){
                        case SUN:
                            whenSpecific[i]=Calendar.SUNDAY;
                            break;
                        case MON:
                            whenSpecific[i]=Calendar.MONDAY;
                            break;
                        case TUE:
                            whenSpecific[i]=Calendar.TUESDAY;
                            break;
                        case WED:
                            whenSpecific[i]=Calendar.WEDNESDAY;
                            break;
                        case THU:
                            whenSpecific[i]=Calendar.THURSDAY;
                            break;
                        case FRI:
                            whenSpecific[i]=Calendar.FRIDAY;
                            break;
                        case SAT:
                            whenSpecific[i]=Calendar.SATURDAY;
                            break;
                    }
                }
                break;
            case MONTHLY:
                whenSpecific=new int[1];
                RepeatEnum whSpMonth=RepeatEnum.valueOf(parsed[2]);
                switch (whSpMonth){
                    case FIRST_DAY_MONTH:
                        whenSpecific[0]=Integer.parseInt(RepeatEnum.FIRST_DAY_MONTH.getValue());
                        break;
                    case LAST_DAY_MONTH:
                        whenSpecific[0]=Integer.parseInt(RepeatEnum.LAST_DAY_MONTH.getValue());
                        break;
                    case LAST_SAT_MONTH:
                        whenSpecific[0]=Integer.parseInt(RepeatEnum.LAST_SAT_MONTH.getValue());
                        break;
                    case SAME_DAY_MONTH:
                        whenSpecific[0]=Integer.parseInt(RepeatEnum.SAME_DAY_MONTH.getValue());
                        break;
                }
                break;
        }
        RepeatEnum unSpecific=RepeatEnum.valueOf(parsed[3]);
        switch(unSpecific){
            case EVENTS:
                untilSpecific=parsed[4];
                break;
            case UNTIL_DATE:
                untilSpecific=parsed[4];
                break;
        }
        repeatArr[0]=parsed[0];
        repeatArr[1]=parsed[1];
        repeatArr[2]=parsed[3];
    }

}
*/
