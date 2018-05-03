package Services.LocationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DriverController {
    AtomicLong counter = new AtomicLong();
    private static HashMap<String,Driver> drivers = new HashMap<>();
    static{
        drivers.put("0",new Driver("Tao","Pan"));
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String home(){
        return "Hello World";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Driver> create(@RequestBody(required = false) Driver driver){
        long id = counter.incrementAndGet();
        driver.setId(id);
        drivers.put(String.valueOf(id),driver);
        return new ResponseEntity<>(driver,HttpStatus.OK);
    }

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public ResponseEntity<List<Driver>> getAllDriver(){
        return new ResponseEntity<>(new ArrayList<>(drivers.values()),HttpStatus.OK);
    }

    @RequestMapping(value = "/drivers/{id}",method = RequestMethod.GET)
    public ResponseEntity<Driver> getDriver(@PathVariable("id") String id){
        Driver driver = null;
        if(!drivers.containsKey(id)){
            return new ResponseEntity<>(driver,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(drivers.get(id),HttpStatus.OK);
    }


    public static boolean isValidDriver(String id){
        return drivers.containsKey(id);
    }

}
