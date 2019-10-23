package quadraticrelationship;
/**
 * @author C35127
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class QuadraticRelationship {
    /**
     * @param args the command line arguments
     */
    
    // Decided to put all attributes in the superclass as every 
    // quadratic relationship should have all these attributes.
    // Also allows for reuseability of the results() method,
    // rather than overriding or overloading it.
    double a;
    double b;
    double c;
    double axis;
    double optimum;
    double root1;
    double root2;

// Constructors not necessary in this case    
//    public QuadraticRelationship(){
//        a=1;
//        b=0;
//        c=0;
//    }
    
    public double getValue(String instruction){
        // helper method to bullet proof and force user to enter a double value
        System.out.print(instruction);
        Scanner input = new Scanner(System.in);
        double val;
        while (true){
            try{
                val=input.nextDouble();
                break;
            }
            catch (InputMismatchException err){
                input.next();
                System.out.print("Not a number, try again! "+instruction);
            }
        }
        return val;
    }
    
    public void userInput(){
        // allow user to enter the a, b, anc c values of a standard equation
        Scanner input = new Scanner(System.in);
        System.out.println("The value of 'a' must NOT be 0!");
        System.out.println("Please enter the a, b, and c values for the standard equation.");
        while (true){
            this.a=getValue("a: ");
            if (this.a!=0) break;
            else System.out.println("\"a\" must NOT equal 0!");
        }  
        this.b=getValue("b: ");
        this.c=getValue("c: ");    
    }
    
    public double findY(double x){
    // Given any x value, could be for the vertex or in a table of values
        return this.a*x*x + this.b*x + this.c;
    }
    
    public boolean isARoot(){
    // Checking if a relationship has a root or not
        double discrim = Math.pow(this.b,2) - 4*this.a*this.c;
        return discrim >= 0; // false: no roots, true: at least one root
    }
  
    public double findRoot1(){
        return((0-this.b) + Math.sqrt(Math.pow(this.b, 2) - 4*this.a*this.c))/(2*this.a);
    }
    
    public double findRoot2(){
        return ((0-b) - Math.sqrt(b*b - 4*a*c))/(2*a);         
    }

    public double findAxis(){
    // x value of the axis of symmetry
        return (-1)*this.b/(2*this.a);
    }
    
    public void results(Object quad){
    // output statements and calls to some other methods
        System.out.println("The y-intercept is "+this.c);    
        System.out.println("The axis of symmetry is: x="+this.axis);

        if (this.a>0){
            System.out.println("The parabola opens up");
            System.out.println("The optimim value is a minimum at "+this.optimum);
            }
        else {
            System.out.println("The parabola opens down");            
            System.out.println("The optimim value is a maximum at "+this.optimum);
        }
        System.out.println("The vertex is: ("+this.axis+", "+this.optimum+")");
        if (this.isARoot()){
            this.root1=this.findRoot1();
            this.root2=this.findRoot2();
            if (this.root1==this.root2)
                System.out.println("There is one root at the vertex which is "+this.root1);
            else 
                System.out.println("The roots are "+this.root1+" and "+this.root2);          
            }
        else
            System.out.println("There are no roots!");

        // display table of values for points around the vertex
        System.out.printf("%7s %12s","x","y");
        System.out.println();
        double start=this.axis-5;
        double stop=this.axis+5;
        while (start<=stop){
            System.out.printf("%8.2f %12.2f",start,this.findY(start));
            System.out.println();
            start+=1;            
        }
        
    }
    public static void main(String[] args) {
        // test drivers to make sure everything works
        
        QuadraticRelationship q1 = new QuadraticRelationship();
        q1.userInput();
        q1.axis=q1.findAxis();
        q1.optimum = q1.findY(q1.axis);
        q1.results(q1);
        
        VertexForm q2 = new VertexForm();
        q2.userInput();
        q2.b=q2.findB();
        q2.c=q2.findC();
        q2.results(q2);
        
        FactoredForm q3 = new FactoredForm();
        q3.userInput();
        q3.axis=q3.findAxis(q3.root1, q3.root2);
        q3.optimum =q3.findY(q3.axis, q3.a, q3.root1,q3.root2);
        q3.results(q3);        
    }    
}
