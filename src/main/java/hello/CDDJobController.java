package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/v1/CDDJob")
public class CDDJobController {

	@PreAuthorize("hasAuthority('ROLE_USER')")
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
	@PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/start", method=RequestMethod.POST)
    public CDDJob start() {
    	Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(3) + 1;
		
    	if (randomInt==1) {
    		return new CDDJob("RUNNING", randomInt);
		} else if(randomInt==2) {
			return new CDDJob("STARTING");
		} else if (randomInt==3) {
			return new CDDJob("FAILED","Failed to start due to DB issue", "Some boring error description here");
		} else {
			return new CDDJob("STARTED", randomInt);
		}
    }
	@PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/stop", method=RequestMethod.POST)
    public CDDJob stop() {
    	Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(3) + 1;
		if (randomInt==1) {
    		return new CDDJob("STOPPING", randomInt);
		} else if(randomInt==2) {
			return new CDDJob("STOPPED");
		} else {
			return new CDDJob("FAILED","Failed to stop due to DB issue", "Some boring error description here");
		}
    }    
}
