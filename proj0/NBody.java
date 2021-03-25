import java.sql.SQLInput;

import static sun.java2d.cmm.ColorTransform.In;

public class NBody {

    public static int readNumbers(String filename){
        In in = new In(filename);
        int number = in.readInt();
        return number;
    }
    public static double readRadius(String filename) {
        In in = new In("filename");

        /* Every time you call a read method from the In class,
         * it reads the next thing from the file, assuming it is
         * of the specified type. */

        /* Compare the calls below to the contents of BasicInDemo_input_file.txt */

        int total= in.readInt();
        double radius = in.readDouble();
        return radius;

    }


    public static Planet[] readPlanets(String filename) {
        In in = new In("filename");
        int total = in.readInt();
        Planet[] planets = new Planet[total];
        double radius = in.readDouble();
        for(int i = 0; i < total; i++){
            planets[i] = new Planet();
        }
        for(int i = 0; i < total; i++){
            double firstItemInFile = in.readDouble();
            double secondItemInFile = in.readDouble();
            double thirdItemInFile = in.readDouble();
            double fourthItemInFile = in.readDouble();
            double fifthItemInFile = in.readDouble();
            String sixthItemInFile = in.readString();
            planets[i] = new Planet(firstItemInFile, secondItemInFile, thirdItemInFile, fourthItemInFile, fifthItemInFile, sixthItemInFile);
        }
        return planets;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        int total = readNumbers(filename);
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int time;

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        for(time = 0; time <= T; ){
            double[] forceXX = new double[total];
            double[] forceYY = new double[total];
            int index = 0;
            for(Planet pi: planets){
                forceXX[index] = pi.calcNetForceExertedByX(planets);
                forceYY[index] = pi.calcNetForceExertedByY(planets);
                index++;
            }
            index = 0;
            for(Planet pi: planets){
                pi.update(dt, forceXX[index], forceYY[index]);
                index++;
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet pi: planets){
                pi.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n",planets.length);
        StdOut.printf("%.2e\n", radius);
        for(int i = 0; i < planets.length; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4 %12s\n" ,
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel,planets[i].mass, planets[i].imgFileName);
        }

    }
}
