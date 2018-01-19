public class NBody {
	public static double readRadius(String path) {
		In in = new In(path);
		int number = Integer.valueOf(in.readLine());
		double radius = Double.parseDouble(in.readLine());
		return radius;
	}

	public static Planet[] readPlanets(String path) {
		In in = new In(path);
		int number = Integer.valueOf(in.readLine());
		Planet[] planets = new Planet[number];
		double radius = Double.parseDouble(in.readLine());
		for(int i = 0; i < number; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}

		return planets;
	}

	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("Please check the number of arguments!");
		}
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		//draw pictures of all the planets
		StdDraw.setScale(-radius, radius);
		String background = "./images/starfield.jpg";
		StdDraw.picture(0, 0, background);
		for(Planet p : planets) {
			System.out.println(p.imgFileName);
			p.draw();
		}

		for(double time = 0; time <= T; time += dt) {
			double xForces[] = new double[planets.length];
			double yForces[] = new double[planets.length];
			for(int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, background);
			for(Planet p : planets) {
				System.out.println(p.imgFileName);
				p.draw();
			}

			StdDraw.show(10);
		}
	}
}
