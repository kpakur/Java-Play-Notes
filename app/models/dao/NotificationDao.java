package models.dao;


import models.Notification;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;

public class NotificationDao {
    private EntityManager getEm(){
        return JPA.em();
    }

    public void persist(Notification notification){
        getEm().persist(notification);
    }
}
