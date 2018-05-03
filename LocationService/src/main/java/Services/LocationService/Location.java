package Services.LocationService;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Location {
    private double latitude;
    private double longitude;
    private LocalDateTime time;
    private long id;
    @JsonProperty
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = LocalDateTime.now();
    }

    @JsonProperty
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @JsonProperty
    public String getHash(){
        return GeoHashUtils.encode(latitude,longitude);
    }

}
