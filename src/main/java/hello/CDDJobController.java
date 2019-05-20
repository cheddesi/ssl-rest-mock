package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
@RequestMapping("/v1/CDDJob")
public class CDDJobController {

    @RequestMapping("/status")
    public CDDJob status() {
    	Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(2) + 1;
		if (randomInt == 1) {
			return new CDDJob("RUNNING", randomInt);
		} else {
			return new CDDJob("STOPPED");
		}
    }
    @RequestMapping(value = "/start", method=RequestMethod.POST)
    public CDDJob start() {
    	Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(3) + 1;
		
    	if (randomInt==1) {
    		return new CDDJob("RUNNING", randomInt);
		} else if(randomInt==2) {
			return new CDDJob("STARTING");
		} else if (randomInt==3) {
			return new CDDJob("FAILED");
		} else {
			return new CDDJob("STARTED", randomInt);
		}
    }
    @RequestMapping(value = "/stop", method=RequestMethod.POST)
    public CDDJob stop() {
    	Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(3) + 1;
		if (randomInt==1) {
    		return new CDDJob("STOPPING", randomInt);
		} else if(randomInt==2) {
			return new CDDJob("STOPPED");
		} else {
			return new CDDJob("FAILED");
		}
    }    
}
