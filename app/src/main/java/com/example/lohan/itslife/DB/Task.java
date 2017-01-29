package com.example.lohan.itslife.DB;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lohan on 28-01-2017.
 */
@Entity
public class Task {
    @Id(autoincrement = true)
    private Long taskId;
    @Unique @NotNull
    private String title;
    private String note;
    private String taskRepeatString;
    @NotNull
    private Long taskInLabelId;
    @ToOne(joinProperty = "taskInLabelId")
    private Label taskInLabel;
    @NotNull
    private Date creationDate;
    @NotNull
    private Date startDate;
    private Date actualEndDate;
    @NotNull
    private String importance;
    @NotNull
    private int revise;
    @NotNull
    private boolean trivial;
    @NotNull
    private boolean completed;
    private Double taskRating;
    @NotNull
    private Integer mostRecentBacklogDays;
    private Date lastOccurrenceDate;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;
    @Generated(hash = 1184898921)
    public Task(Long taskId, @NotNull String title, String note, String taskRepeatString,
            @NotNull Long taskInLabelId, @NotNull Date creationDate, @NotNull Date startDate, Date actualEndDate,
            @NotNull String importance, int revise, boolean trivial, boolean completed, Double taskRating,
            @NotNull Integer mostRecentBacklogDays, Date lastOccurrenceDate) {
        this.taskId = taskId;
        this.title = title;
        this.note = note;
        this.taskRepeatString = taskRepeatString;
        this.taskInLabelId = taskInLabelId;
        this.creationDate = creationDate;
        this.startDate = startDate;
        this.actualEndDate = actualEndDate;
        this.importance = importance;
        this.revise = revise;
        this.trivial = trivial;
        this.completed = completed;
        this.taskRating = taskRating;
        this.mostRecentBacklogDays = mostRecentBacklogDays;
        this.lastOccurrenceDate = lastOccurrenceDate;
    }
    @Generated(hash = 733837707)
    public Task() {
    }
    public Long getTaskId() {
        return this.taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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
    public String getTaskRepeatString() {
        return this.taskRepeatString;
    }
    public void setTaskRepeatString(String taskRepeatString) {
        this.taskRepeatString = taskRepeatString;
    }
    public Long getTaskInLabelId() {
        return this.taskInLabelId;
    }
    public void setTaskInLabelId(Long taskInLabelId) {
        this.taskInLabelId = taskInLabelId;
    }
    public Date getCreationDate() {
        return this.creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getActualEndDate() {
        return this.actualEndDate;
    }
    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }
    public String getImportance() {
        return this.importance;
    }
    public void setImportance(String importance) {
        this.importance = importance;
    }
    public int getRevise() {
        return this.revise;
    }
    public void setRevise(int revise) {
        this.revise = revise;
    }
    public boolean getTrivial() {
        return this.trivial;
    }
    public void setTrivial(boolean trivial) {
        this.trivial = trivial;
    }
    public boolean getCompleted() {
        return this.completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public Double getTaskRating() {
        return this.taskRating;
    }
    public void setTaskRating(Double taskRating) {
        this.taskRating = taskRating;
    }
    public Integer getMostRecentBacklogDays() {
        return this.mostRecentBacklogDays;
    }
    public void setMostRecentBacklogDays(Integer mostRecentBacklogDays) {
        this.mostRecentBacklogDays = mostRecentBacklogDays;
    }
    public Date getLastOccurrenceDate() {
        return this.lastOccurrenceDate;
    }
    public void setLastOccurrenceDate(Date lastOccurrenceDate) {
        this.lastOccurrenceDate = lastOccurrenceDate;
    }
    @Generated(hash = 566076732)
    private transient Long taskInLabel__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1291134383)
    public Label getTaskInLabel() {
        Long __key = this.taskInLabelId;
        if (taskInLabel__resolvedKey == null
                || !taskInLabel__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LabelDao targetDao = daoSession.getLabelDao();
            Label taskInLabelNew = targetDao.load(__key);
            synchronized (this) {
                taskInLabel = taskInLabelNew;
                taskInLabel__resolvedKey = __key;
            }
        }
        return taskInLabel;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1627937085)
    public void setTaskInLabel(@NotNull Label taskInLabel) {
        if (taskInLabel == null) {
            throw new DaoException(
                    "To-one property 'taskInLabelId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.taskInLabel = taskInLabel;
            taskInLabelId = taskInLabel.getLabelId();
            taskInLabel__resolvedKey = taskInLabelId;
        }
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
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }

}
