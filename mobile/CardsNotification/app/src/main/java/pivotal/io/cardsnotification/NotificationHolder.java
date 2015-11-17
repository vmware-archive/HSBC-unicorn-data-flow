package pivotal.io.cardsnotification;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by vcarvalho on 11/12/15.
 */
public class NotificationHolder {

    private static NotificationHolder instance;
    protected NotificationHolder(){}

    private Queue<String> notifications = new ConcurrentLinkedQueue<>();

    public static NotificationHolder getInstance(){
        if(instance == null){
            synchronized (NotificationHolder.class){
                if(instance == null){
                    instance = new NotificationHolder();
                }

            }
        }
        return  instance;
    }

    public void addNotification(String payload){
        this.notifications.add(payload);
    }

    public String popNotification(){
        return this.notifications.poll();
    }

}
