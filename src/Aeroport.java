import static java.lang.Math.pow;

public class Aeroport {
    private final String name;
    private final String country;
    private final double latitude;
    private final double longitude;
    private final String IATA;

    public Aeroport(String name, String country, double latitude, double longitude, String IATA) {
        this.name = name;
        this.IATA = IATA;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "nom='" + name + '\'' +
                ", pays=" + country +
                ", IATA=" + IATA +
                ", lattitude='" + latitude + '\'' +
                ", longitude=" + longitude +
                '}' + '\n';
    }
    public double distance(double lat_ext, double long_ext){
        return Math.pow(this.latitude-lat_ext,2) + Math.pow((this.longitude-long_ext)*Math.cos((this.latitude+lat_ext)/2),2);
    }

    public String getIATA() {

        return IATA;
    }
    public double getLatitude(){
        return latitude;
    }
    public double getLongitude(){
        return longitude;
    }

}