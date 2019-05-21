public class NBody{
  public static double readRadius(String file){
    In in = new In(file);
    int planets = in.readInt();
    double radius = in.readDouble();
    return radius;
  }
  public static Planet[] readPlanets(String file){
    In in = new In(file);
    int n = in.readInt();
    double radius = in.readDouble();
    Planet[] p = new Planet[n];
    for(int i = 0 ; i < n ; ++i){
      p[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
      }
    return p;
  }
  public static void main(String[] args){
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    Planet[] planets = readPlanets(filename);
    double radius = readRadius(filename);
    StdDraw.setScale(-radius,radius);
    StdDraw.picture(0,0,"images/starfield.jpg");
    for(int i = 0; i< planets.length; ++i){
      planets[i].draw();
    }
    StdDraw.enableDoubleBuffering();
    for(int t = 0 ; t<= T; t+=dt){
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];
      for(int j=0 ; j< planets.length; ++j){
        xForces[j] = planets[j].calcNetForceExertedByX(planets);
        yForces[j] = planets[j].calcNetForceExertedByY(planets);
      }
      for(int i = 0 ; i<planets.length;++i){
        planets[i].update(t,xForces[i],yForces[i]);
      }
      StdDraw.picture(0,0,"images/starfield.jpg");
      for(int i = 0; i< planets.length; ++i){
        planets[i].draw();
      }
      StdDraw.show();
  		StdDraw.pause(10);
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
                }
  }
}
