public class Planet {
    public double xxPos;   //Its current x position
    public double yyPos;   //Its current y position
    public double xxVel;   //x方向上的当前速度
    public double yyVel;   //y方向上的当前速度
    public double mass;    //其质量
    public String imgFileName;  // 与描述行星的图像相对应的文件名（例如jupiter.gif）

    static final double G = 6.67e-11;


    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass  = m;
        this.imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public Planet() {

    }

    public double calcDistance(Planet p2) {
        double dx,dy,r;
        dx = p2.xxPos - this.xxPos;
        dy = p2.yyPos - this.yyPos;
        r = Math.pow((dx*dx+dy*dy),0.5);
        return r;
    }


    public double calcForceExertedBy(Planet p2) {
        double r = calcDistance(p2);
        double force;
        force = G * this.mass * p2.mass / (r * r);
        return force;
    }

    public double calcForceExertedByX(Planet p2) {
        double force,forceXX;
        double dx,r;
        dx = p2.xxPos - this.xxPos;
        r = calcDistance(p2);
        force = calcForceExertedBy(p2);
        forceXX = force * dx / r;
        return forceXX;
    }

    public double calcForceExertedByY(Planet p2) {
        double force,forceYY;
        double dy,r;
        dy = p2.yyPos - this.yyPos;
        r = calcDistance(p2);
        force = calcForceExertedBy(p2);
        forceYY = force * dy / r;
        return forceYY;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceXX = 0.0;
        for(Planet pi: planets){
            if(!this.equals(pi))
            netForceXX += calcForceExertedByX(pi);
        }
        return netForceXX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceYY = 0.0;
        for(Planet pi: planets){
            if(!this.equals(pi))
            netForceYY += calcForceExertedByY(pi);
        }
        return netForceYY;
    }

    public void update(double v, double v1, double v2) {
        double aXX,aYY;
        aXX = v1 / this.mass;
        aYY = v2 / this.mass;
        this.xxVel = this.xxVel + v * aXX;
        this.yyVel = this.yyVel + v * aYY;
        this.xxPos = this.xxPos + v * this.xxVel;
        this.yyPos = this.yyPos + v * this.yyVel;
    }
    /**
     * The draw method uses the StdDraw API to draw the Body's
     * image at the Body's position.
     */
    public void draw() {
        String imgToDraw = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, imgToDraw);
    }
}
