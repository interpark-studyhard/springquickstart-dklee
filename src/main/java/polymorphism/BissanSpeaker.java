package polymorphism;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("bissan")
public class BissanSpeaker implements Speaker {
	
	private static Logger logger = LoggerFactory.getLogger(DdongSpeaker.class);

	@Override
	public void volumeUp() {
		logger.info("볼륨을 높였더니 소리가 커졌지만 음질은 모르겠습니다.");
	}

	@Override
	public void volumeDown() {
		logger.info("볼륨을 낮췄더니 소리가 작아졌지만 음질은 모르겠습니다.");
	}

}
