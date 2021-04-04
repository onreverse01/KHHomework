package common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessonCounterListener
 *
 */
@WebListener
public class SessonCounterListener implements HttpSessionListener {
	
	private static int activeSessions; //전역변수는 지정값을 정하지 않아도 기본값은 0이다.

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	activeSessions++;
    	System.out.println("세션 생성! : 현재 세션수는 [" + activeSessions + "]개 입니다.");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	if(activeSessions > 0) activeSessions--;
    	System.out.println("세션 해제! : 현재 세션수는 [" + activeSessions + "]개 입니다.");
    }
	
}
