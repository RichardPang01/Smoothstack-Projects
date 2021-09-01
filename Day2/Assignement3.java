interface Shape {
  void calculateArea(int length, int breadth);
  void display();
}

class Rectangle implements Shape {
  private double area = 0;
  public void calculateArea(int length, int breadth) {
    this.area = length*breadth;
  }
  public void display(){
      System.out.println("The area of the rectangle is " + area);
  }
}
class Circle implements Shape {
   private double area = 0;
   public void calculateArea(int radius, int value) {
      this.area = Math.PI * Math.pow(radius,2);
   }
  public void display(){
      System.out.println("The area of the circle is " + area);
  }
}
class Triangle implements Shape {
   private double area = 0;
   public void calculateArea(int length, int breadth) {
         this.area = .5*(length * breadth);
   }
   public void display(){
         System.out.println("The area of the triangle is " + area);
   }
}

class Assignment3 {
  public static void main(String[] args) {
    Rectangle r1 = new Rectangle();
    r1.calculateArea(5,4);
    r1.display();
    Circle c1 = new Circle();
    c1.calculateArea(5,0);
    c1.display();
    Triangle t1 = new Triangle();
    t1.calculateArea(5,5);
    t1.display();
  }
}