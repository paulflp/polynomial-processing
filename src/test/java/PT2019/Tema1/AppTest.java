package PT2019.Tema1;

import Model.Monom;
import Model.Polinom;
import Model.PolynomialUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    public void testSum(){
    	Polinom p1 = new Polinom();
    	p1.addMonom(new Monom(3,2));
    	p1.addMonom(new Monom(2,4));
    	p1.addMonom(new Monom(1,5));
    	p1.addMonom(new Monom(0,3));
    	
    	Polinom p2 = new Polinom();
    	p2.addMonom(new Monom(4,1));
    	p2.addMonom(new Monom(2,1));
    	p2.addMonom(new Monom(1,4));
    	
    	Polinom p3 = new Polinom();
    	p3.addMonom(new Monom(4,1));
    	p3.addMonom(new Monom(3,2));
    	p3.addMonom(new Monom(2,5));
    	p3.addMonom(new Monom(1,9));
    	p3.addMonom(new Monom(0,3));
    	
    	assertTrue(p3.equals(p1.addPolynomials(p2)));
    }
    
    public void testSub(){
    	Polinom p1 = new Polinom();
    	p1.addMonom(new Monom(3,2));
    	p1.addMonom(new Monom(2,4));
    	p1.addMonom(new Monom(1,5));
    	p1.addMonom(new Monom(0,3));
    	
    	Polinom p2 = new Polinom();
    	p2.addMonom(new Monom(4,1));
    	p2.addMonom(new Monom(2,1));
    	p2.addMonom(new Monom(1,4));
    	
    	Polinom p3 = new Polinom();
    	p3.addMonom(new Monom(4,-1));
    	p3.addMonom(new Monom(3,2));
    	p3.addMonom(new Monom(2,3));
    	p3.addMonom(new Monom(1,1));
    	p3.addMonom(new Monom(0,3));
    	
    	assertTrue(p3.equals(p1.subPolynomials(p2)));
    }
    
    public void testMultiply(){
    	Polinom p1 = new Polinom();
    	p1.addMonom(new Monom(2,1));
    	p1.addMonom(new Monom(1,2));
    	p1.addMonom(new Monom(0,1));
    	
    	Polinom p2 = new Polinom();
    	p2.addMonom(new Monom(1,1));
    	p2.addMonom(new Monom(0,2));
    	
    	Polinom p3 = new Polinom();
    	p3.addMonom(new Monom(3,1));
    	p3.addMonom(new Monom(2,4));
    	p3.addMonom(new Monom(1,5));
    	p3.addMonom(new Monom(0,2));
    	
    	assertTrue(p3.equals(Polinom.multiplyPolynomials(p1, p2)));
    }
    
    public void testDivide(){
    	Polinom p1 = new Polinom();
    	p1.addMonom(new Monom(2,2));
    	p1.addMonom(new Monom(1,3));
    	p1.addMonom(new Monom(0,5));
    	
    	Polinom p2 = new Polinom();
    	p2.addMonom(new Monom(1,1));
    	p2.addMonom(new Monom(0,3));
    	
    	Polinom cat=new Polinom();
    	cat.addMonom(new Monom(1,2));
    	cat.addMonom(new Monom(0,-3));
    	
    	Polinom rest=new Polinom();
    	rest.addMonom(new Monom(0,14));
    	
    	Polinom rezCat = p1.dividePolynomials(p2);
    	assertTrue(p1.equals(rest));
    	assertTrue(rezCat.equals(cat));
    	
    }
    
    public void testDerive(){
    	Polinom p1 = new Polinom();
    	p1.addMonom(new Monom(4,3));
    	p1.addMonom(new Monom(3,8));
    	p1.addMonom(new Monom(2,5));
    	p1.addMonom(new Monom(1,4));
    	p1.addMonom(new Monom(0,3));
    	
    	Polinom p2 = new Polinom();
    	p2.addMonom(new Monom(3,12));
    	p2.addMonom(new Monom(2,24));
    	p2.addMonom(new Monom(1,10));
    	p2.addMonom(new Monom(0,4));
    	
    	assertTrue(p2.equals(p1.derivePolynomial()));
    }
    
    public void testIntegrate(){
    	Polinom p1 = new Polinom();
    	p1.addMonom(new Monom(4,3));
    	p1.addMonom(new Monom(3,8));
    	p1.addMonom(new Monom(2,5));
    	p1.addMonom(new Monom(1,4));
    	p1.addMonom(new Monom(0,3));
    	
    	Polinom p2 = new Polinom();
    	p2.addMonom(new Monom(5,3/5.0));
    	p2.addMonom(new Monom(4,2));
    	p2.addMonom(new Monom(3,5/3.0));
    	p2.addMonom(new Monom(2,2));
    	p2.addMonom(new Monom(1,3));
    	
    	assertTrue(p2.equals(p1.integratePolynomial()));
    }
    
    public void testParsing(){
    	String text = new String("-x^2   +   x    +3*x^2+++2x        +-5");
    	Polinom p1 = new Polinom();
    	p1.addMonom(new Monom(2,2));
    	p1.addMonom(new Monom(1,3));
    	p1.addMonom(new Monom(0,-5));
    	
    	PolynomialUtils util = new PolynomialUtils();
    	Polinom rez;
    	try{
    		rez = util.parsePolynomial(text);
    		assertTrue(p1.equals(rez));
    	}
    	catch(Exception e){
    		System.out.println(e.getMessage());
    		assertTrue(true);
    	}
    	
    }
    
    /*public void testParsing2(){
    	String text = new String("-$x^2   +   x    +3*x^2+++2x        +-5");
    	Polinom p1 = new Polinom();
    	p1.addMonom(new Monom(2,2));
    	p1.addMonom(new Monom(1,3));
    	p1.addMonom(new Monom(0,-5));
    	
    	PolynomialUtils util = new PolynomialUtils();
    	Polinom rez;
    	try{
    		rez = util.parsePolynomial(text);
    		assertTrue(p1.equals(rez));
    	}
    	catch(Exception e){
    		System.out.println(e.getMessage());
    		assertTrue(true);
    	}
    	
    }*/
}
