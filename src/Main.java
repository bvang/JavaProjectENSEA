public class Main {
    public static void main(String[] args) {

        Aeroport CDG= new Aeroport ("Charles de Gaulle", "France", 45.01, 10.23, "CDG");
        System.out.println(CDG.toString());
        World world = new World ("./data/airport-codes_no_comma.csv");
        //System.out.println(world.getList());


        System.out.println("Found "+world.getList().size()+" airports.");
        Aeroport procheparis = world.findNearestAirport(48.866, 2.316);
        Aeroport cdg = world.findByCode("CDG");

        System.out.println("proche paris : "+procheparis);


    }
}