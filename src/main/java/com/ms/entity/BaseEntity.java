package com.ms.entity;

<<<<<<< HEAD
=======
import com.ms.entity.listener.BaseEntityListener;

>>>>>>> refs/remotes/maksymshemet/master
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by max shemet on 11/2/2016.
 */
@MappedSuperclass
<<<<<<< HEAD
//@EntityListeners(BaseEntityListener.class)
=======
@EntityListeners(BaseEntityListener.class)
>>>>>>> refs/remotes/maksymshemet/master
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="create_date", updatable = false)
    private Timestamp created;

<<<<<<< HEAD
    @Version
=======
>>>>>>> refs/remotes/maksymshemet/master
    @Column(name="update_date")
    private Timestamp updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
