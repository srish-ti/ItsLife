package com.example.lohan.itslife.DB;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;
import java.util.List;

/**
 * Created by lohan on 28-01-2017.
 */
@Entity
public class Label {
    @Id(autoincrement = true)
    private Long labelId;
    @Unique @NotNull
    private String title;
    private String note;
    private String labelRepeatString;
    @NotNull
    private Date creationDate;
    @ToMany(referencedJoinProperty = "taskInLabelId")
    private List<Task> tasksInLabelList;
    @NotNull
    private int tasksPerDay;
    private Date lastOccurrenceDate;
    @NotNull
    private boolean completed;
    private Double labelRating;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 40777009)
    private transient LabelDao myDao;
    @Generated(hash = 1167732487)
    public Label(Long labelId, @NotNull String title, String note, String labelRepeatString,
            @NotNull Date creationDate, int tasksPerDay, Date lastOccurrenceDate, boolean completed,
            Double labelRating) {
        this.labelId = labelId;
        this.title = title;
        this.note = note;
        this.labelRepeatString = labelRepeatString;
        this.creationDate = creationDate;
        this.tasksPerDay = tasksPerDay;
        this.lastOccurrenceDate = lastOccurrenceDate;
        this.completed = completed;
        this.labelRating = labelRating;
    }
    @Generated(hash = 2137109701)
    public Label() {
    }
    public Long getLabelId() {
        return this.labelId;
    }
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getNote() {
        return this.note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getLabelRepeatString() {
        return this.labelRepeatString;
    }
    public void setLabelRepeatString(String labelRepeatString) {
        this.labelRepeatString = labelRepeatString;
    }
    public Date getCreationDate() {
        return this.creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public int getTasksPerDay() {
        return this.tasksPerDay;
    }
    public void setTasksPerDay(int tasksPerDay) {
        this.tasksPerDay = tasksPerDay;
    }
    public Date getLastOccurrenceDate() {
        return this.lastOccurrenceDate;
    }
    public void setLastOccurrenceDate(Date lastOccurrenceDate) {
        this.lastOccurrenceDate = lastOccurrenceDate;
    }
    public boolean getCompleted() {
        return this.completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public Double getLabelRating() {
        return this.labelRating;
    }
    public void setLabelRating(Double labelRating) {
        this.labelRating = labelRating;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1604520567)
    public List<Task> getTasksInLabelList() {
        if (tasksInLabelList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaskDao targetDao = daoSession.getTaskDao();
            List<Task> tasksInLabelListNew = targetDao
                    ._queryLabel_TasksInLabelList(labelId);
            synchronized (this) {
                if (tasksInLabelList == null) {
                    tasksInLabelList = tasksInLabelListNew;
                }
            }
        }
        return tasksInLabelList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1699823181)
    public synchronized void resetTasksInLabelList() {
        tasksInLabelList = null;
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
    @Generated(hash = 692607636)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLabelDao() : null;
    }

}
