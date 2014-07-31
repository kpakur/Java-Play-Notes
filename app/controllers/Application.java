package controllers;

import com.avaje.ebean.Ebean;
import models.Notification;

import models.dao.NotificationDao;
import play.*;
import play.api.mvc.RequestHeader;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import play.db.DB;

import views.html.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import play.db.jpa.JPA;

import static play.data.Form.form;

public class Application extends Controller {

    final static Form<Notification> notForm = form(Notification.class);

    public static Result index() {

        return ok(index.render(notForm));
    }

    // Note: later on this could be changed to dep injection with google guice, instead of this manual workaround
    // and then get rid of static methods as well.
    private static NotificationDao notificationDao;

    private static NotificationDao getNotificationDao() {
        return notificationDao == null ? new NotificationDao(): notificationDao;
    }

    public static void setNotificationDao(NotificationDao notificationDao) {
        Application.notificationDao = notificationDao;
    }

    @Transactional
    public static Result add() {
        Form<Notification> filledForm = notForm.bindFromRequest();

        if(filledForm.hasErrors()){
            return badRequest(index.render(filledForm));
        }

        Notification created = filledForm.get();
        getNotificationDao().persist(created);

        return ok(notes.render(created));
    }

}
