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
public class Objective {
    @Id(autoincrement = true)
    private Long objectiveId;
    @Unique
    @NotNull
    private String title;
    private String note;
    @Unique @NotNull
    private Date startDate;//startDate will be unique as one objective per duration
    @Unique @NotNull
    private Date endDate;
    @NotNull
    private Date creationDate;
    @ToMany
    @JoinEntity(
            entity = JoinObjectiveWithTasks.class,
            sourceProperty = "objectiveId",
            targetProperty = "taskId"
    )
    private List<Task> taskList;
    @NotNull
    private boolean notMe;
    @NotNull
    private boolean completed;
    private Double objectiveRating;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 891148383)
    private transient ObjectiveDao myDao;
    @Generated(hash = 210073366)
    public Objective(Long objectiveId, @NotNull String title, String note,
            @NotNull Date startDate, @NotNull Date endDate,
            @NotNull Date creationDate, boolean notMe, boolean completed,
            Double objectiveRating) {
        this.objectiveId = objectiveId;
        this.title = title;
        this.note = note;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creationDate = creationDate;
        this.notMe = notMe;
        this.completed = completed;
        this.objectiveRating = objectiveRating;
    }
    @Generated(hash = 1852293154)
    public Objective() {
    }
    public Long getObjectiveId() {
        return this.objectiveId;
    }
    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
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
    public Date getStartDate() {
        return this.startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getCreationDate() {
        return this.creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public boolean getNotMe() {
        return this.notMe;
    }
    public void setNotMe(boolean notMe) {
        this.notMe = notMe;
    }
    public boolean getCompleted() {
        return this.completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public Double getObjectiveRating() {
        return this.objectiveRating;
    }
    public void setObjectiveRating(Double objectiveRating) {
        this.objectiveRating = objectiveRating;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1062968330)
    public List<Task> getTaskList() {
        if (taskList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TaskDao targetDao = daoSession.getTaskDao();
            List<Task> taskListNew = targetDao
                    ._queryObjective_TaskList(objectiveId);
            synchronized (this) {
                if (taskList == null) {
                    taskList = taskListNew;
                }
            }
        }
        return taskList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1036990935)
    public synchronized void resetTaskList() {
        taskList = null;
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
    @Generated(hash = 1997486554)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getObjectiveDao() : null;
    }
}
