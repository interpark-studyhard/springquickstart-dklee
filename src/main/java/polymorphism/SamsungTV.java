package polymorphism;

import org.springframework.stereotype.Component;

@Component("samsung")
public class SamsungTV extends AbstractTV {

	@Override
	protected String getName() {
		return getClass().getSimpleName();
	}

}
