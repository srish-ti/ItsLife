package com.example.lohan.itslife.DB;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lohan on 28-01-2017.
 */
@Entity
public class JoinDailyDataWithTasksToBeDone {
    @Id(autoincrement = true)
    private Long id;
    private Long dailyDataId;
    private Long taskId;
    @Generated(hash = 546631949)
    public JoinDailyDataWithTasksToBeDone(Long id, Long dailyDataId, Long taskId) {
        this.id = id;
        this.dailyDataId = dailyDataId;
        this.taskId = taskId;
    }
    @Generated(hash = 1059072441)
    public JoinDailyDataWithTasksToBeDone() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDailyDataId() {
        return this.dailyDataId;
    }
    public void setDailyDataId(Long dailyDataId) {
        this.dailyDataId = dailyDataId;
    }
    public Long getTaskId() {
        return this.taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
