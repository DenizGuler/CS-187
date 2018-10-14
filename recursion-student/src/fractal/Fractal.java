package fractal;

import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;
import fractal.Turtle;

import java.awt.*;
import java.awt.geom.*;

/**
 * A class that implements some basic fractal drawing methods using recursion.
 * These methods include the Koch curve, tree, Sierpinski triangle and carpet.
 */
public class Fractal {
	private Graphics2D g2d = null;				// a Graphics2D object as canvas for drawing
	private int width=0, height=0;				// image width and height
	private int max_recursion_level = 0;		// maximum recursion level
	private String fractal_type = "Koch Curve";	// type of fractal
	private Color color = Color.green;			// draw color
	
	public void setGraphics(Graphics g, int w, int h)	{
		g2d = (Graphics2D)g; width = w; height = h;
	}
	public void setFractalType(String t)	{ fractal_type = t; }
	public void setColor(Color c)			{ color = c; }
	public void setMaxRecursion(int n)		{ max_recursion_level = n; }

	// Recursive method for drawing the Koch curve given two points and the recursion level
	private void drawKochCurve(Point2D p1, Point2D p2, int level) {
		if(level==0) {	// base case
			drawLine(p1, p2);
			return;
		}
		// Koch subdivision rule: ___ ->  _/\_
		double dist = p1.distance(p2);
		Turtle turt = new Turtle(p1, p2);

		turt.move(dist/3);
		Point2D p3 = turt.getPosition();

		turt.turnLeft(60);
		turt.move(dist/3);
		Point2D p4 = turt.getPosition();

		turt.turnRight(120);
		turt.move(dist/3);
		Point2D p5 = turt.getPosition();

		drawKochCurve(p1, p3, level - 1);
		drawKochCurve(p3, p4, level - 1);
		drawKochCurve(p4, p5, level - 1);
		drawKochCurve(p5, p2, level - 1);
	}
	
	// Recursive method for drawing a fractal Tree given two points and the recursion level
	private void drawTree(Point2D p1, Point2D p2, int level) {
		if(level==0) {	// base case
			drawLine(p1, p2);
			return;
		}
		// TODO
		double dist = p1.distance(p2);
		Turtle branch1 = new Turtle(p1, p2);
		Turtle branch2 = new Turtle(p1, p2);

		branch1.move(dist/3); branch2.move(dist/3);;
		Point2D p3 = branch1.getPosition();

		branch1.turnLeft(60);
		branch1.move(2*dist/3);
		Point2D p4 = branch1.getPosition();

		branch2.turnRight(15);
		branch2.move(2*dist/3);
		Point2D p5 = branch2.getPosition();

		drawTree(p1, p3, 0);
		drawTree(p3, p4, level - 1);
		drawTree(p3, p5, level - 1);
	}
	
	// Recursive method for drawing the Sierpinski Triangle given the three points
	// that define the triangle and the recursion level
	private void drawSierpinskiTriangle(Point2D p1, Point2D p2, Point2D p3, int level) {
		if(level==0) {	// base case
			drawTriangle(p1, p2, p3);
			return;
		}
		// TODO

		double dist = p1.distance(p2);
		//double dist23 = p2.distance(p3);
		//double dist13 = p1.distance(p3);
		Turtle turt12 = new Turtle(p1, p2);
		Turtle turt23 = new Turtle(p2, p3);
		Turtle turt13 = new Turtle(p1, p3);

		turt12.move(dist/2);
		Point2D p4 = turt12.getPosition();

		turt23.move(dist/2);
		Point2D p5 = turt23.getPosition();

		turt13.move(dist/2);
		Point2D p6 = turt13.getPosition();

		drawSierpinskiTriangle(p1, p4, p6, level - 1);
		drawSierpinskiTriangle(p2, p4, p5, level - 1);
		drawSierpinskiTriangle(p3, p5, p6, level - 1);
	}
	
	// Recursive method for drawing the Sierpinski Carpet given the lower-left corner
	// of the square (p), the side length (a) of the square, and the recursion level
	private void drawSierpinskiCarpet(Point2D p, double a, int level) {
		if(level==0) {	// base case
			drawRectangle(p, a, a);
			return;
		}
		// TODO
		Point2D temp = new Point2D.Double();
		drawSierpinskiCarpet(p, a/3, level - 1);

		temp.setLocation(p.getX(), p.getY() + a/3);
		drawSierpinskiCarpet(temp, a/3, level - 1);

		temp.setLocation(p.getX(), temp.getY() + a/3);
		drawSierpinskiCarpet(temp, a/3, level - 1);

		temp.setLocation(temp.getX() + a/3, temp.getY());
		drawSierpinskiCarpet(temp, a/3, level - 1);

		temp.setLocation(temp.getX() + a/3, temp.getY());
		drawSierpinskiCarpet(temp, a/3, level - 1);

		temp.setLocation(temp.getX(), p.getY());
		drawSierpinskiCarpet(temp, a/3, level - 1);

		temp.setLocation(temp.getX() , p.getY() + a/3);
		drawSierpinskiCarpet(temp, a/3, level - 1);

		temp.setLocation(temp.getX() - a/3, p.getY());
		drawSierpinskiCarpet(temp, a/3, level - 1);
	}
	
	// This method is left for you to experiment with creative fractals
	// designed by yourself. You will NOT be graded on this method 
	void drawMyFractal(/* other parameters that you may need */ int level) {
		if(level==0) {	// base case
			return;
		}
		/* Your creative fractal shape */
	}
	
	/** The code below provides utility methods.
	 *  You should NOT need to modify any code below.
	 */
	public void draw() {
		if(g2d==null || width==0 || height==0) return;
		g2d.setBackground(Color.black);
		g2d.clearRect(0, 0, width, height);
		g2d.setColor(color);
		if(fractal_type.equalsIgnoreCase("Koch Curve")) {
			drawKochCurve(new Point2D.Double(0.0, 0.4), new Point2D.Double(1.0, 0.4), max_recursion_level);
		} else if(fractal_type.equalsIgnoreCase("Snowflake")) {
			double r = 0.5;
			Point2D p1 = new Point2D.Double(r*Math.cos(Math.toRadians(150))+0.5,
											r*Math.sin(Math.toRadians(150))+0.5);
			Point2D p2 = new Point2D.Double(r*Math.cos(Math.toRadians(30))+0.5,
											r*Math.sin(Math.toRadians(30))+0.5);
			Point2D p3 = new Point2D.Double(r*Math.cos(Math.toRadians(-90))+0.5,
											r*Math.sin(Math.toRadians(-90))+0.5);
			// Snowflake is made of three Koch curves segments
			//  p1____p2
			//    \  /
			//     \/
			//     p3
			drawKochCurve(p1, p2, max_recursion_level);
			drawKochCurve(p2, p3, max_recursion_level);
			drawKochCurve(p3, p1, max_recursion_level);
		} else if(fractal_type.equalsIgnoreCase("Tree")) {
			// double the maximum recursion level because tree subdivides very slowly
			drawTree(new Point2D.Double(0.6, 0.1), new Point2D.Double(0.6, 0.9),
					max_recursion_level*2);
		} else if(fractal_type.equalsIgnoreCase("Sp Triangle")) {
			double r = 0.5;
			Point2D p1 = new Point2D.Double(r*Math.cos(Math.toRadians(90))+0.5,
											r*Math.sin(Math.toRadians(90))+0.5);
			Point2D p2 = new Point2D.Double(r*Math.cos(Math.toRadians(-150))+0.5,
											r*Math.sin(Math.toRadians(-150))+0.5);
			Point2D p3 = new Point2D.Double(r*Math.cos(Math.toRadians(-30))+0.5,
											r*Math.sin(Math.toRadians(-30))+0.5);
			drawSierpinskiTriangle(p1, p2, p3, max_recursion_level);
		} else if(fractal_type.equalsIgnoreCase("Sp Carpet")) {
			drawSierpinskiCarpet(new Point2D.Double(0, 0), 1, max_recursion_level);
		} else {
			drawMyFractal(/* other parameters that you may need */ max_recursion_level);
		}
	}
	/** Draw a straight line between two points P1 and P2.
	 * The given x and y values of p1 and p2 are assumed to be within [0, 1] (i.e. normalized).
	 * This allows our fractals to be resolution-independent. The method below converts the normalized
	 * coordinates to integer coordinates based on the actual image resolution. 
	 */
	private void drawLine(Point2D p1, Point2D p2) {
		int x1 = (int)(p1.getX()*width);
		int x2 = (int)(p2.getX()*width);
		// flip the Y coordinate
		// because screen Y axis is flipped from mathematical Y axis
		int y1 = (int)((1.0-p1.getY())*height);
		int y2 = (int)((1.0-p2.getY())*height);
		g2d.drawLine(x1, y1, x2, y2);
	}
	
	// Draw a solid rectangle given its lower left corner and its size
	private void drawRectangle(Point2D p, double w, double h) {
		int x0 = (int)(p.getX()*width);
		int y0 = (int)((1.0-p.getY())*height);
		int x1 = (int)((p.getX()+w)*width);;
		int y1 = (int)((1.0-(p.getY()+h))*height);
		int xpoints[] = {x0, x0, x1, x1};
		int ypoints[] = {y0, y1, y1, y0};
		g2d.fillPolygon(xpoints, ypoints, 4);
	}
	
	// Draw a solid triangle given its three vertices
	private void drawTriangle(Point2D p1, Point2D p2, Point2D p3) {
		int xpoints[] = {(int)(p1.getX()*width),
						 (int)(p2.getX()*width),
						 (int)(p3.getX()*width)};
		int ypoints[] = {(int)((1.0-p1.getY())*height),
						 (int)((1.0-p2.getY())*height),
						 (int)((1.0-p3.getY())*height)};
		g2d.fillPolygon(xpoints, ypoints, 3);
	}
}
