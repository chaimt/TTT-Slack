package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tikal.ttt.configuration.Application;


@RestController
@RequestMapping("/ttt")
class Controller {
	static Logger logger = LoggerFactory.getLogger(Application.class);


	@RequestMapping(method = RequestMethod.POST)
	public String add(@RequestBody String input) {
		logger.info(input);
		return  "hellow Slak";
	}

}


