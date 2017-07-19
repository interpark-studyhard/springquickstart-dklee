package polymorphism;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUserSpring {

	public static void main(String[] args) {

		TV tv = null;

		try (GenericXmlApplicationContext factory = new GenericXmlApplicationContext("com/yidigun/springquickstart/applicationContext.xml")) {
			tv = factory.getBean("tv", TV.class);
		}

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}

}
