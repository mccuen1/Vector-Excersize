package com.vector;



public final class Vector2D {

    // instance variables
    private double x;
    private double y;
    private double h;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
        this.h = 1.0;
    } // end constructor

    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getH() {
        return this.h;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setH(double h) {
        this.h = h;
    }
    
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    } // end dot(Vector2D)

    public double magnitude() {
        // pythagorean theorem
        return Math.sqrt(this.dot(this));
    } // end magnitude

    @Override
    public String toString() {
        return String.format("(%8.4f, %8.4f)", this.x, this.y);
    } // toString()

    public static void main(String[] args) {
        // System.out.printf("%14.12f\n", Math.PI);
        Vector2D u = new Vector2D(3, 4);
        System.out.println("u = " + u);
        System.out.println("x component of u = " + u.getX());
        System.out.println("y component of u = " + u.getY());
        System.out.println("h component of u = " + u.getH());

        Vector2D v = new Vector2D(5, 12);
        System.out.println("dot product = " + u.dot(v));
        System.out.println("magnitude = " + u.magnitude());
    } // end main()
}
