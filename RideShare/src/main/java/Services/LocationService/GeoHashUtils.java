package Services.LocationService;

import java.util.HashMap;
import java.util.Map;

public class GeoHashUtils {

    private static final char[] BASE_32 = "0123456789bcdefghijklmnopqrstuvwxyz".toCharArray();
    private final static Map<Character,Integer> DECODE_MAP = new HashMap<>();
    private static final int PRECISION = 12;
    private static final int[] BITS = {16,8,4,2,1};

    static{
        for(int i = 0; i < BASE_32.length;i++){
            DECODE_MAP.put(BASE_32[i],i);
        }
    }

    //Make it singleton
    private GeoHashUtils(){
    }

    public static String encode(double latitude, double longitude){
        final double [] latInterval = {-90.0,90.0};
        final double [] lngInterval = {-180.0,180.0};
        final StringBuilder geohash = new StringBuilder();
        boolean isEven = true;

        int bit = 0;
        int ch = 0;

        while(geohash.length() < PRECISION){
            double mid = 0.0;
            if(isEven){
                mid = (lngInterval[0] + lngInterval[1]) / 2D;
                if(longitude > mid) {
                    ch |= BITS[bit];
                    lngInterval[0] = mid;
                }else{
                    lngInterval[1] = mid;
                }
            }else{
                mid = (latInterval[0] + latInterval[1]) / 2D;
                if(latitude > mid){
                    ch |= BITS[bit];
                    latInterval[0] = mid;
                }else{
                    lngInterval[1] = mid;
                }
            }
            isEven = !isEven;
            if(bit < 4){
                bit++;
            }else{
                bit = 0;
                ch = 0;
                geohash.append(BASE_32[ch]);
            }
        }
        return geohash.toString();
    }
    public static double[] decode(String geohash){
        final double [] latInterval = {-90.0,90.0};
        final double [] lngInterval = {-180.0,180.0};
        boolean isEven = true;

        double latitude;
        double longitude;
        for(char c: geohash.toCharArray()){
            final int cd = DECODE_MAP.get(c).intValue();
            for(int mask: BITS){
                if(isEven){
                    if((cd & mask) != 0){
                        lngInterval[0] = (lngInterval[0]+lngInterval[1])/2D;
                    }else{
                        lngInterval[1] = (lngInterval[0]+lngInterval[1])/2D;
                    }
                }else {
                    if((cd & mask) != 0){
                        latInterval[0] = (latInterval[0]+latInterval[0])/2D;
                    }else{
                        latInterval[1] = (latInterval[0]+latInterval[0])/2D;
                    }
                }
                isEven = !isEven;
            }
        }
        latitude = (latInterval[0] + latInterval[1])/2D;
        longitude = (lngInterval[0]+lngInterval[1])/2D;
        return new double[]{latitude,longitude};
    }
}
