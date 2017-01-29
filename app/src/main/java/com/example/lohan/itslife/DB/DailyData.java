package com.example.lohan.itslife.DB;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;
import java.util.List;

/**
 * Created by lohan on 28-01-2017.
 */
@Entity
public class DailyData {
    @Id(autoincrement = true)
    private Long dailyDataId;
    @Unique @NotNull
    private Date date;
    private String note;
    @ToMany
    @JoinEntity(
            entity = JoinDailyDataWithTasksToBeDone.class,
            sourceProperty = "dailyDataId",
            targetProperty = "taskId"
    )
    private List<Task>taskToBeDone;
    @ToMany
    @JoinEntity(
            entity = JoinDailyDataWithTasksActuallyDone.class,
            sourceProperty = "dailyDataId",
            targetProperty = "taskId"
    )
    private List<Task> tasksDone;
    private String fulfilled;//when made by scheduler this is null
    private Double dailyRating;
/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;
/** Used for active entity operations. */
@Generated(hash = 461791968)
private transient DailyDataDao myDao;
@Generated(hash = 702863802)
public DailyData(Long dailyDataId, @NotNull Date date, String note,
        String fulfilled, Double dailyRating) {
    this.dailyDataId = dailyDataId;
    this.date = date;
    this.note = note;
    this.fulfilled = fulfilled;
    this.dailyRating = dailyRating;
}
@Generated(hash = 556979270)
public DailyData() {
}
public Long getDailyDataId() {
    return this.dailyDataId;
}
public void setDailyDataId(Long dailyDataId) {
    this.dailyDataId = dailyDataId;
}
public Date getDate() {
    return this.date;
}
public void setDate(Date date) {
    this.date = date;
}
public String getNote() {
    return this.note;
}
public void setNote(String note) {
    this.note = note;
}
public String getFulfilled() {
    return this.fulfilled;
}
public void setFulfilled(String fulfilled) {
    this.fulfilled = fulfilled;
}
public Double getDailyRating() {
    return this.dailyRating;
}
public void setDailyRating(Double dailyRating) {
    this.dailyRating = dailyRating;
}
/**
 * To-many relationship, resolved on first access (and after reset).
 * Changes to to-many relations are not persisted, make changes to the target entity.
 */
@Generated(hash = 230257992)
public List<Task> getTaskToBeDone() {
    if (taskToBeDone == null) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        TaskDao targetDao = daoSession.getTaskDao();
        List<Task> taskToBeDoneNew = targetDao
                ._queryDailyData_TaskToBeDone(dailyDataId);
        synchronized (this) {
            if (taskToBeDone == null) {
                taskToBeDone = taskToBeDoneNew;
            }
        }
    }
    return taskToBeDone;
}
/** Resets a to-many relationship, making the next get call to query for a fresh result. */
@Generated(hash = 534354566)
public synchronized void resetTaskToBeDone() {
    taskToBeDone = null;
}
/**
 * To-many relationship, resolved on first access (and after reset).
 * Changes to to-many relations are not persisted, make changes to the target entity.
 */
@Generated(hash = 361216850)
public List<Task> getTasksDone() {
    if (tasksDone == null) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        TaskDao targetDao = daoSession.getTaskDao();
        List<Task> tasksDoneNew = targetDao
                ._queryDailyData_TasksDone(dailyDataId);
        synchronized (this) {
            if (tasksDone == null) {
                tasksDone = tasksDoneNew;
            }
        }
    }
    return tasksDone;
}
/** Resets a to-many relationship, making the next get call to query for a fresh result. */
@Generated(hash = 1322108661)
public synchronized void resetTasksDone() {
    tasksDone = null;
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 128553479)
public void delete() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 1942392019)
public void refresh() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 713229351)
public void update() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
}
/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 950922481)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getDailyDataDao() : null;
}
}
