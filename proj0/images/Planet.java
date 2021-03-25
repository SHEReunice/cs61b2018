package images;

public class Planet {
    public double xxPos;   //Its current x position
    public double yyPos;   //Its current y position
    public double xxVel;   //x方向上的当前速度
    public double yyVel;   //y方向上的当前速度
    public double mass;    //其质量
    public String imgFileName;  // 与描述行星的图像相对应的文件名（例如jupiter.gif）


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
}
