package polymorphism;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("ddong")
public class DdongSpeaker implements Speaker {
	
	private static Logger logger = LoggerFactory.getLogger(DdongSpeaker.class);

	@Override
	public void volumeUp() {
		logger.info("볼륨을 높였지만 소리는 변화가 없었습니다.");
	}

	@Override
	public void volumeDown() {
		logger.info("볼륨을 낮췄지만 소리는 변화가 없었습니다.");
	}

}
