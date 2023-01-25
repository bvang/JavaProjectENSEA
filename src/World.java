import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class World {
    private ArrayList<Aeroport>list = new ArrayList<>();
    private String fileName;

    public World (String fileName) {
    this.fileName = fileName;
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            while (s != null) {
                s = s.replaceAll("\"", "");
//Enleve les guillemets qui s´eparent les champs de donn´ees GPS.
                String fields[] = s.split(",");
// Une bonne id´ee : placer un point d'arr^et ici pour du debuggage.
                if (fields[1].equals("large_airport")) {
// A continuer
                    list.add(new Aeroport(fields[2],fields[5],Double.parseDouble(fields[12]),Double.parseDouble(fields[11]),fields[9]));
                }
                s = buf.readLine();
            }
        } catch (Exception e) {
            System.out.println("Maybe the file isn't there ?");
            System.out.println(list.get(list.size() - 1));
            e.printStackTrace();
        }
    }
    public ArrayList getList(){
        return  list;
    }

    public Aeroport findNearestAirport (Double lat_ext, Double long_ext){
        Double distance_min = this.list.get(0).distance(lat_ext, long_ext);
        Aeroport aeroport_min = null;

        for (Aeroport aeroport : this.list){
            Double temp = aeroport.distance(lat_ext, long_ext);
            if (temp<=distance_min){
                distance_min = temp;
                aeroport_min = aeroport;
            }
        }
        return aeroport_min;
    }
    public Aeroport findByCode(String IATA){
        for (Aeroport aeroport : this.list){
            if (aeroport.getIATA().equals(IATA)){
                System.out.println("\nfound by code : "+aeroport);
                return aeroport;
            }
        }
        return null;
    }
}