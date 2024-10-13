public class Planet{
    
        public double xxPos;
        public double yyPos;
        public double xxVel;
        public double yyVel;
        public double mass;
        public String imgFileName;
        private static final double G = 6.67e-11;
        
    
        public Planet(double xP, double yP, double xV,
                      double yV, double m, String img){
            xxPos = xP;
            yyPos = yP;
            xxVel = xV;
            yyVel = yV;
            mass = m;
            imgFileName = img;
        }
    
        public Planet(Planet p){
            xxPos = p.xxPos;
            yyPos = p.yyPos;
            xxVel = p.xxVel;
            yyVel = p.yyVel;
            mass = p.mass;
            imgFileName = p.imgFileName;
        }
     public double calcDistance (Planet p){
        return Math.sqrt((this.xxPos-p.xxPos)* (this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));

     }
     public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
		return G * mass * p.mass / (r * r);
     }
     public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - xxPos;
		double r = calcDistance(p);
		return calcForceExertedBy(p) * dx / r;}
     public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - yyPos;
		double r = calcDistance(p);
		return calcForceExertedBy(p) * dy / r;

     }
     public double calcNetForceExertedByX(Planet [] arrPlanets){
        double fx=0;
         for (Planet p: arrPlanets) {
            if( !equals(p)){
                fx+=calcForceExertedByX(p);

            }
        

        }
        return fx;
     }
     public double calcNetForceExertedByY(Planet [] arrPlanets){
        double fy=0;
         for (Planet p: arrPlanets) {
            if( !equals(p)){
                fy+=calcForceExertedByY(p);

            }
        

        }
         return fy;}
     public  void update (double dt, double fX, double fY){
        double ax = fX / mass;
		double ay = fY / mass;
		xxVel += dt * ax;
		yyVel += dt * ay;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
     }
     public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}