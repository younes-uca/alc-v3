package ma.learn.quiz.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CallConfiguration {

	@Autowired
	private Environment env;

	public String getConfigurationByName(String configName) {

		return env.getProperty(configName);
	}

}
