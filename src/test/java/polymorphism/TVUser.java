package polymorphism;

public class TVUser {

	public static void main(String[] args) {

		String beanName = null;
		if (args.length > 0) {
			beanName = args[0];
		}
		else {
			beanName = "나의 카와이한";
		}

		TV tv = BeanFactory.getTV(beanName);
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}

}
