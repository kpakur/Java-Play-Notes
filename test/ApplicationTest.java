import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import controllers.Application;
import controllers.routes;
import models.Notification;
import models.dao.NotificationDao;
import org.eclipse.jetty.util.Scanner;
import org.junit.*;

import play.data.Form;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.data.Form.form;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.assertEquals;
import static play.test.Helpers.fakeApplication;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {
    NotificationDao notificationDaoMock = mock(NotificationDao.class);

    String name = "tester";
    String surname = "kowalski";
    String email = "test@kowalski.com";
    String yob = "2014-11-11";
    String favDb = "1";
    String notes = "I like it";

    String wrongEmail = "asd@";

    @Before
    public void setUp() {
        doNothing().when(notificationDaoMock).persist(any());
        // todo: replace with guice later on
        Application.setNotificationDao(notificationDaoMock);


        // In memory db will not be used for tests, however added here just
        // so it does not try to connect to db at all even to check connectivity.
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
    }

    @Test
    public void testSubmit_Validation(){
        Result result = callAction(
                controllers.routes.ref.Application.add(),
                fakeRequest().withFormUrlEncodedBody(ImmutableMap.of("email", wrongEmail))
        );

        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }

//    @Test
//    public void simpleCheck() {
//        int a = 1 + 1;
//        assertThat(a).isEqualTo(2);
//    }
//
//    // view
//    @Test
//    public void renderTemplate() {
//
//        final Form<Notification> notForm = form(Notification.class);
//
//        running(fakeApplication(), new Runnable() {
//            public void run() {
//                Content html = views.html.index.render(notForm);
//                assertThat(contentType(html)).isEqualTo("text/html");
//                assertThat(contentAsString(html)).contains("Name:");
//                assertThat(contentAsString(html)).contains("Surname:");
//                assertThat(contentAsString(html)).contains("Email:");
//                assertThat(contentAsString(html)).contains("Year of birth:");
//                assertThat(contentAsString(html)).contains("Your favourite database:");
//                assertThat(contentAsString(html)).contains("Notes:");
//                assertThat(contentAsString(html)).contains("<input type=\"text\" id=\"surname\" name=\"surname\" value=\"\" />");
//            }
//        });
//
//    }
//
//
//    // routes
//    @Test
//    public void rootRoute() {
//        running(fakeApplication(), new Runnable() {
//            public void run() {
//                Result result = route(fakeRequest(GET, "/"));
//                assertThat(result).isNotNull();
//            }
//        });
//    }
//
//    @Test
//    public void badRoute() {
//        running(fakeApplication(), new Runnable() {
//            public void run() {
//                Result result = route(fakeRequest(GET, "/bad"));
//                assertThat(result).isNull();
//            }
//        });
//    }
//
//    // controller
//    @Test
//    public void callIndex() {
//        Result result = controllers.Application.index();
//        assertThat(status(result)).isEqualTo(OK);
//        assertThat(contentType(result)).isEqualTo("text/html");
//        assertThat(charset(result)).isEqualTo("utf-8");
//        assertThat(contentAsString(result)).contains("<a class=\"navbar-brand\" href=\"/\">Add new note</a>");
//    }
//
//    @Test
//    public void callAdd() {
//
//        running(fakeApplication(), new Runnable() {
//            public void run() {
//                Result result = callAction(routes.ref.Application.add(), new FakeRequest().withFormUrlEncodedBody(ImmutableMap.of("name", name, "favDb", favDb)));
//                assertThat(status(result)).isEqualTo(OK);
//            }
//        });
//    }
//
//    @Test
//    public void callAddWrong() {
//
//        running(fakeApplication(), new Runnable() {
//            public void run() {
//                Result result = callAction(routes.ref.Application.add(), new FakeRequest().withFormUrlEncodedBody(ImmutableMap.of("email", wrongEmail)));
//                assertThat(status(result)).isEqualTo(BAD_REQUEST);
//
//                //System.out.println(contentAsString(result));
//            }
//        });
//    }
}
