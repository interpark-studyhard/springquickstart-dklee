package polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class LgTV extends AbstractTV {
	
	private String name;
	
	public LgTV() {
		this(null);
	}

	public LgTV(String name) {
		this.name = (name != null)? name + " LG TV": getClass().getSimpleName();
	}

	@Override
	protected String getName() {
		return name;
	}

	@Autowired
	@Qualifier("ddong")
	@Override
	public void setSpeaker(Speaker speaker) {
		super.setSpeaker(speaker);
	}

}
