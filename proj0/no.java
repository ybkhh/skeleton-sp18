public class No{
    public static double main (){
        In in = new In("planets.txt");
        double radius=in.readDouble();
        return radius;
    }
}