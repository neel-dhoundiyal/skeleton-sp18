public class Planet{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  public Planet(double xP, double yP, double xV,double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m ;
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
  public double calcDistance(Planet p){
    double x_dis = this.xxPos - p.xxPos;
    double y_dis = this.yyPos - p.yyPos;
    double d = Math.sqrt(((x_dis*x_dis) + (y_dis*y_dis)));
    return d;
    }
  public double calcForceExertedBy(Planet p){
    double G = 6.67e-11;
    double f = (this.mass*p.mass*G)/Math.pow(this.calcDistance(p),2);
    return f;
    }
  public double calcForceExertedByX(Planet p){
    double f_x = this.calcForceExertedBy(p)*(p.xxPos - this.xxPos)/this.calcDistance(p);
    return f_x;
    }
  public double calcForceExertedByY(Planet p){
    double f_y = this.calcForceExertedBy(p)*(p.yyPos - this.yyPos)/this.calcDistance(p);
    return f_y;
    }
  public double calcNetForceExertedByX(Planet[] planets){
    double x_sum = 0;
    for(int i = 0; i < planets.length; ++i){
      if(planets[i] == this)
        continue;
      x_sum = x_sum + this.calcForceExertedByX(planets[i]);
      }
    return x_sum;
    }
  public double calcNetForceExertedByY(Planet[] planets){
    double y_sum = 0;
    for(int i =0; i< planets.length ;++i){
      if(planets[i] == this)
        continue;
      y_sum = y_sum + this.calcForceExertedByY(planets[i]);
      }
    return y_sum;
    }
  public void update(double t, double x_f, double y_f){
    double a_x = x_f/this.mass;
    double a_y = y_f/this.mass;
    this.xxVel = this.xxVel + a_x*t ;
    this.yyVel = this.yyVel + a_y*t ;
    this.xxPos = this.xxPos + this.xxVel*t;
    this.yyPos = this.yyPos + this.yyVel*t;
    }
  public void draw(){
    String filename = "images/"+ this.imgFileName;
    StdDraw.picture(this.xxPos,this.yyPos,filename);
  }

}
