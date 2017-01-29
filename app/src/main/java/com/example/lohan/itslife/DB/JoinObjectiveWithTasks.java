package com.example.lohan.itslife.DB;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lohan on 28-01-2017.
 */
@Entity
public class JoinObjectiveWithTasks {
    @Id(autoincrement = true)
    private Long id;
    private Long objectiveId;
    private Long taskId;
    @Generated(hash = 1289523847)
    public JoinObjectiveWithTasks(Long id, Long objectiveId, Long taskId) {
        this.id = id;
        this.objectiveId = objectiveId;
        this.taskId = taskId;
    }
    @Generated(hash = 948314299)
    public JoinObjectiveWithTasks() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getObjectiveId() {
        return this.objectiveId;
    }
    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }
    public Long getTaskId() {
        return this.taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
