public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private final double G = 6.67 * Math.pow(10, -11);

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel= xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel= p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = p.xxPos - xxPos;
		double dy = p.yyPos - yyPos;
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

	public double calcForceExertedBy(Planet p) {
		double distance = calcDistance(p);
		double force = (G * mass * p.mass) / (distance * distance);
		return force;
	}

	public double calcForceExertedByX(Planet p) {
		double force = calcForceExertedBy(p);
		double distance = calcDistance(p);
		double dx = p.xxPos - xxPos;
		return (force * dx)  / distance;
	}

	public double calcForceExertedByY(Planet p) {
		double force = calcForceExertedBy(p);
		double distance = calcDistance(p);
		double dy = p.yyPos - yyPos;
		return (force * dy)  / distance;
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double netForceX = 0;
		for(int i = 0; i < planets.length; i++) {
			Planet currP = planets[i];
			if(currP.equals(this)) {
				continue;
			}
			netForceX += calcForceExertedByX(currP);
		}

		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double netForceY = 0;
		for(int i = 0; i < planets.length; i++) {
			Planet currP = planets[i];
			if(currP.equals(this)) {
				continue;
			}
			netForceY += calcForceExertedByY(currP);
		}

		return netForceY;
	}

	public void update(double dt, double fX, double fY) {
		double netAccX = fX / mass;
		double netAccY = fY / mass;
		double newVeloX = xxVel + (dt * netAccX);
		double newVeloY = yyVel + (dt * netAccY);
		double newPosiX = xxPos + (dt * newVeloX);
		double newPosiY = yyPos + (dt * newVeloY);
		xxPos = newPosiX;
		yyPos = newPosiY;
		xxVel = newVeloX;
		yyVel = newVeloY;
	}

	public void draw() {

		/* Stamps three copies of advice.png in a triangular pattern. */
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}
}