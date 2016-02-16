package com.tikal.rest;

import com.tikal.api.UserManager;
import com.tikal.ttt.configuration.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ttt")
class Controller {
	static Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	UserManager userManager;

	@RequestMapping(method = RequestMethod.POST)
	public String add(@RequestBody String input) {
		logger.info(input);
//		userManager.saveUser();
		return  "hellow Slak";
	}


    @RequestMapping(method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}

