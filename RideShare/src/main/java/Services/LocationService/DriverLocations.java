package Services.LocationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DriverLocations {
    private String driverId;

    private HashMap<Long,Location> locations;
    private long curLocationId;

    public DriverLocations(String driverId) {
        this.driverId = driverId;
        this.locations = new HashMap<>();
        this.curLocationId = 0;
    }
    public void addLocation(Location location){
        long id = ++curLocationId;
        location.setId(id);
        locations.put(id,location);
    }

    public List<Location> getAllLocations(){
        return new ArrayList<>(locations.values());
    }

    public Location getCurrentLocation(){
        return locations.get(curLocationId);
    }

    public Location getLocation(long id){
        return locations.getOrDefault(id,null);
    }

    public boolean updateLocation(long locationId, Location newLocation){
        if(!locations.containsKey(locationId)){
            return false;
        }
        Location location = locations.get(locationId);
        location.setLatitude(newLocation.getLatitude());
        location.setLongitude(newLocation.getLongitude());
        return true;
    }
    public boolean delteLocation(long locationId){
        if(!locations.containsKey(locationId)){
            return false;
        }
        locations.remove(locationId);
        return true;

    }
}
