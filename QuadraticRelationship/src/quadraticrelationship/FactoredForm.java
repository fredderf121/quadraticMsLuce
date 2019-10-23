package quadraticrelationship;

import java.util.Scanner;
/**
 * @author C35127
 */
public class FactoredForm extends QuadraticRelationship{
    
//    public FactoredForm(){
//        a=1;
//        root1=0;
//        root2=0;
//    }
    
    @Override
    public void userInput(){
        // to allow user to enter values for a and the roots
        Scanner input = new Scanner(System.in);
        System.out.println("The value of 'a' must NOT be 0!");
        System.out.println("Please enter the a and root values for the factored equation.");
        while (true){
            this.a=getValue("a: ");
            if (a!=0) break;
            else System.out.println("\"a\" must NOT equal 0!");
        }  
        this.root1=getValue("one root: ");
        this.root2=getValue("other root: ");    
    }
    
    // Overloading this superclass method, based on pre-existing roots
    public double findAxis(double r1,double r2){
        return (r1+r2)/2;
    }
    
    // Overloading this superclass, trying to pass parameters rather than 
    // object attributes inside the method
    public double findY(double x, double A,double r1, double r2){
        return A*(x-r1)*(x-r2);
    }
    
    @Override
    public void results(Object quad){
    // output statements and calls to other methods
        System.out.println("The y-intercept is "+this.root1*this.root2);    
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

        if (this.root1==this.root2)
            System.out.println("There is one root at the vertex which is "+this.root1);
        else 
            System.out.println("The roots are "+this.root1+" and "+this.root2);          
        
        // display table of values for points around the vertex
        System.out.printf("%7s %12s","x","y");
        System.out.println();
        double start=this.axis-5;
        double stop=this.axis+5;
        while (start<=stop){
            System.out.printf("%8.2f %12.2f",start,this.findY(start,this.a, this.root1, this.root2));
            System.out.println();
            start+=1;            
        }
    }
}
