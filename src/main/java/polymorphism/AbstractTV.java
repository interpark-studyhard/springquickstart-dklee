package polymorphism;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

abstract public class AbstractTV implements TV {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractTV.class);

	@Autowired
	@Qualifier("bissan")
	private Speaker speaker;

	abstract protected String getName();

	@Override
	public final void powerOn() {
		logger.info("{} 전원 켠다.", getName());
	}

	@Override
	public final void powerOff() {
		logger.info("{} 전원 끈다.", getName());
	}

	@Override
	public final void volumeUp() {
		if (speaker != null)
			speaker.volumeUp();
		else {
			logger.info("{} 볼룸 높인다.", getName());
		}
	}

	@Override
	public final void volumeDown() {
		if (speaker != null) {
			speaker.volumeDown();
		}
		else {
			logger.info("{} 볼륨 낮춘다.", getName());
		}
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

}
