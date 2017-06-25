package App;

import controller.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Travis Rogers
 */

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContextMain.xml");
        Controller controller = ctx.getBean("Controller", Controller.class);
        controller.run();
    }
}
