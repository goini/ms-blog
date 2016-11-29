package com.ms.entity.listener;

import com.ms.entity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by mshemet on 11/11/2016.
 */
public class BaseEntityListener {

    @PrePersist
    public void validate(final BaseEntity obj) {
        final Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        obj.setCreated(timestamp);
        obj.setUpdated(timestamp);
    }

    @PreUpdate
    public void preUpdate(final BaseEntity obj) {
        final Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        obj.setUpdated(timestamp);
    }
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/maksymshemet/master
}
