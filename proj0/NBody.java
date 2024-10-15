public class NBody{

    public static double readRadius(String filename){
	
        In in = new In(filename);
        int num = in.readInt();
        double radius=in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[num];
		for (int i = 0; i < num; i++) {
			double xp = in.readDouble();
			double yp = in.readDouble();
			double vx = in.readDouble();
			double vy = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xp, yp, vx, vy, m, img);	
		}
		return planets;}
    }
    public static void main(String[] args){
        double T = new Double(args[0]);
		double dt = new Double(args[1]);	
		String filename = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        
    StdDraw.setXscale(-r,r);
    StdDraw.setYscale(-r,r);
    StdDraw.enableDoubleBuffering();
    double t =0;
    int num= planets.length;
    double [] xforce= new double[num];
    double [] yforce=new double[num];
    while (t<T){
        for(int i=0;i<num;num++){
            xforce[i]=calcNetForceExertedByX(planets);
            yforce[i]=calcNetForceExertedByY(planets);
        
        }
        
        for (int i=0;i<num;num++){
           planets[i].update(dt, xforce[i], yforce[i]);
        
        }
        StdDraw.picture(0, 0, "images/starfield.jpg");

    }
    for(Planet planet :planets){
        planet.draw();
    }
    StdDraw.show();
			StdDraw.pause(10);
     t+=dt;


    }

    
