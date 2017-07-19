package polymorphism;

public class BeanFactory {

	public static TV getTV(String beanName) {
		if (beanName.equals("samsung")) {
			return new SamsungTV();
		}
		else if (beanName.equals("lg")) {
			return new LgTV();
		}
		else {
			return new LgTV(beanName);
		}
	}
}
