package Ex2;
/**Name and Id: May Rozen, 212051007.
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * This JUnit class represents a very simple unit testing for Ex2.
 * This class should be improved and generalized significantly.
 * Make sure you add documentations to this Tesing class.
 * @author boaz.ben-moshe
 *
 */

class Ex2Test {
	static double[] po1={2,0,3, -1,0}, po2 = {0.1,0,1, 0.1,3};

	@Test
	void testequals1() { //If the arrays are equal
		double[] p1 = {1,2,3,4};
		double[] p2 = {1,2,3,4};
		assertEquals(true, Ex2.equals(p1,p2));	
	}
	@Test
	void testequals2() {
		double[] p1 = {1,5,3,4};//If the arrays aren't equal
		double[] p2 = {1,2,3,4};
		assertEquals(false, Ex2.equals(p1,p2));	
	}
	@Test
	void testequals3() { //If the arrays are exactly the same after the decimal point.
		double[] p1 = {1.003,2,3,4.5,5,6};
		double[] p2 = {1.003,2,3,4.5,5,6};
		assertEquals(true, Ex2.equals(p1,p2));	
	}
	@Test
	void testequals4() {//If the arrays aren't exactly the same after the decimal point.
		double[] p1 = {1,5.005,3,4};
		double[] p2 = {1,2,3.5,4};
		assertEquals(false, Ex2.equals(p1,p2));	
	}
	@Test
	void testequals5() {//Test with negative values
		double[] p1 = {1,2,3,-4};
		double[] p2 = {1,2,3,-4};
		assertEquals(true, Ex2.equals(p1,p2));	
	}

	
	@Test
	void testF1() { //Checking if Computes the f(x) value of the polynom at x it's what we are expected.
		double fx0 = Ex2.f(po1, 0);
		double fx1 = Ex2.f(po1, 1);
		double fx2 = Ex2.f(po1, 2);
		assertEquals(fx0,2);
		assertEquals(fx1,4);
		assertEquals(fx2,6);
	}
	@Test
	void testF2() { //Checking a correct answer.
		double []p1 = {2,8,3,4};
		double fx = Ex2.f(p1, 0);
		assertEquals(fx,10);
	}
	@Test
	void testF3() { //Checking a correct answer with decimal numbers.
		double []p1 = {2,8,3,4};
		double fx = Ex2.f(p1, 1.2);
		assertEquals(fx,22.832);
	}
	@Test
	void testF4() {//Checking an incorrect answer with decimal numbers.
		double []p1 = {2,8,3,4};
		double fx = Ex2.f(p1, 1.2);
		assertEquals(fx,22.83);
	}

	
	@Test
	void testpoly1() {//Checking if poly return the string of the polynom like we expect.
		double []p1 = {1,2,5};
		String fx = "5.0x^2+2.0x^1+1.0";
		assertEquals(Ex2.poly(p1),fx);
	}
	@Test
	void testpoly2() {//Checking an incorrect case - the string we expect is different from poly's answer.
		double []p1 = {1,2,5};
		String fx = "10.0x^2+2.0x^1+1.0";
		assertEquals(Ex2.poly(p1),fx);
	}
	@Test
	void testpoly3() {//Checking a correct answer with decimal numbers.
		double []p1 = {1,2,5.234};
		String fx = "5.234x^2+2.0x^1+1.0";
		assertEquals(Ex2.poly(p1),fx);
	}
	@Test
	void testpoly4() {//Checking an incorrect answer with decimal numbers.
		double []p1 = {1,2,5};
		String fx = "5.24x^2+2.0x^1+1.0";
		assertEquals(Ex2.poly(p1),fx);
	}
	@Test
	void testpoly5() {//Checking a correct answer with negative numbers.
		double []p1 = {1,-2,5};
		String fx = "5.0x^2-2.0x^1+1.0";
		assertEquals(Ex2.poly(p1),fx);
	}
	
	
	@Test
	void testRoot1() {//Checking a correct answer.
		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 3.1958, Ex2.EPS);
	}
	@Test
	void testRoot2() {//Checking an incorrect answer.
		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 5.225, Ex2.EPS);
	}
	@Test
	void testRoot3() {//Checking the accuracy in decimal number.
		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 3.19, Ex2.EPS);
	}
	

	@Test
	void testRoot_rec1() {//Checking a correct answer.
		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 3.1958, Ex2.EPS);
	}
	@Test
	void testRoot_rec2() {//Checking an incorrect answer.
		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 5.225, Ex2.EPS);
	}
	@Test
	void testRoot_rec3() {//Checking the accuracy in decimal number.
		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 3.19, Ex2.EPS);
	}
	
	
	@Test
	void testsamevalue1() {//If the function are equal for every x - |p1(x) -p2(x)| < eps.
		double[] p2 = {2,0,3, -1,0};//p2==po1;
		assertEquals(Ex2.sameValue(po1,p2,0,10,Ex2.EPS),5,Ex2.EPS);	
		}
	@Test
	void testsamevalue2() {//If the function are equal for every x and range - |p1(x) -p2(x)| < eps.
		double[] p2 = {2,0,3, -1,0};//p2==po1;
		assertEquals(Ex2.sameValue(po1,p2,0.523,2.485,Ex2.EPS),1.504,Ex2.EPS);	
		}
	@Test
	void testsamevalue3() {//If the function are equal in (0,0).
		double[] p1 = {0,5,0,1};//p2!=po1, x^3+5x.
		double[] p2 = {0,4,1};//x^2+4x.
		assertEquals(Ex2.sameValue(p1,p2,-2,5,Ex2.EPS),0,Ex2.EPS);	
		}
	@Test
	void testsamevalue4() {//If the function are never equal and they are parallel.
		double[] p1 = {2,1};//p2!=po1;
		double[] p2 = {4,1};
		assertEquals(Ex2.sameValue(p1,p2,-2,5,Ex2.EPS),0,Ex2.EPS);	
		}
	
	@Test
	void testarea1() {//We have 20 rectangle and its width is 0.4.
		double [] p1={3,1};
		double [] p2={4,8};
		assertEquals(Ex2.area(p1,p2,0,8,20),220.8);	//Will compare the sum of 0.4*(difference y) for every rectangle to 220.8.
		}
	@Test
	void testarea2() {//Checking the test by comparing the correct function answer to an incorrect test's answer.
		double [] p1={3,1};
		double [] p2={4,8};
		assertEquals(Ex2.area(p1,p2,0,8,20),220.9);	
			}
	@Test
	void testarea3() {//Checking range with negative numbers.
		double[] p1 = {3,1};
		double[] p2 = {4,8};
		assertEquals(Ex2.area(p1,p2,-2,2,1),52);	
			}
	@Test
	void testarea4() {//Checking the accuracy of the test with decimal answer.
		double[] p1 = {3,1};
		double[] p2 = {4,8};
		assertEquals(Ex2.area(p1,p2,-2,2,1),52.0002);	
			}
	
	@Test
	void getPolynomFromString1() {//Checking if we send the strings poly(p) to getPolynomFromString we will get p.
		double[] polynom = {5,4,3}; 
		String p = Ex2.poly(polynom);//="3x^2+4x+5"
		double[] ansfunction= Ex2.getPolynomFromString(p);
		assertEquals(Ex2.poly(ansfunction),p);	
			}
	@Test
	void getPolynomFromString2() {//Test with Using decimal numbers.
		double[] polynom = {5.002,4,3}; 
		String p = Ex2.poly(polynom);//="3x^2+4x+5"
		double[] ansfunction= Ex2.getPolynomFromString(p);
		assertEquals(Ex2.poly(ansfunction),p);	
			}
	@Test
	void getPolynomFromString3() {//Test with Using negative numbers.
		double[] polynom = {5,-4,3}; 
		String p = Ex2.poly(polynom);//="3x^2-4x+5"
		double[] ansfunction= Ex2.getPolynomFromString(p);
		assertEquals(Ex2.poly(ansfunction),p);	
			}
	@Test
	void getPolynomFromString4() {//Checking the test by comparing the correct function answer with an incorrect answer (p).
		double[] polynom = {5,-4,3}; 
		String p = "2+"+Ex2.poly(polynom);//="2+3x^2-4x+5"
		double[] ansfunction= Ex2.getPolynomFromString(p);
		assertEquals(Ex2.poly(ansfunction),p);	
			}

	
	@Test
	void testAdd1() {
		double[] p12 = Ex2.add(po1, po2);//The sum up po1 and po2.
		double[] minus1 = {-1};
		double[] pp2 = Ex2.mul(po2, minus1);//the multiples of po2 and minus 1. 
		double[] p1 = Ex2.add(p12, pp2);//The sum of p12(=po1+po2) and pp2.
		assertEquals(Ex2.poly(po1), Ex2.poly(p1));//Comparing the results to their string (Otherwise we will get two different objects).
	}
	@Test
	void testAdd2() {//Comparing the results of add-function to our answer which constitutes a new array sum up p1 and p2.
		double[] p1 = {2,3,4,5};
		double[] p2 = {8,1,3};
		double[] pans = {10,4,7,5};
		assertEquals(Ex2.poly(Ex2.add(p1,p2)), Ex2.poly(pans));
	}
	@Test
	void testAdd3() {//Tests with negative numbers.
		double[] p1 = {-2.05,3,4,5};
		double[] p2 = {8,1,-3};
		double[] pans = {5.95,4,1,5};
		assertEquals(Ex2.poly(Ex2.add(p1,p2)), Ex2.poly(pans));
	}
	@Test
	void testAdd4() {//Tests with decimal numbers.
		double[] p1 = {2.006,3,4.2,5};
		double[] p2 = {8,1,3};
		double[] pans = {10.006,4,7.2,5};
		assertEquals(Ex2.poly(Ex2.add(p1,p2)), Ex2.poly(pans));
	}
	
	
	@Test
	void testmul1() {//Comparing the results of mul-function to our answer which constitutes a new array multiply p1 and p2 values.
		double[] p1 = {2,3,4,5};
		double[] p2 = {8,1,3};
		double[] pans = {16,3,12};
		assertEquals(Ex2.poly(Ex2.mul(p1,p2)), Ex2.poly(pans));
	}
	@Test
	void testmul2() {//Tests with negative numbers.
		double[] p1 = {2,-3,4,5};
		double[] p2 = {8,1,3};
		double[] pans = {16,-3,12};
		assertEquals(Ex2.poly(Ex2.mul(p1,p2)), Ex2.poly(pans));
	}
	@Test
	void testmul3() {//Tests with decimal numbers.
		double[] p1 = {2,-3.112,4,5};
		double[] p2 = {8,1,3};
		double[] pans = {16,-3.112,12};
		assertEquals(Ex2.poly(Ex2.mul(p1,p2)), Ex2.poly(pans));
	}
	@Test
	void testmul4() {//Checking the test by comparing the correct function answer with an incorrect answer (15.999!=16.0).
		double[] p1 = {2,-3.112,4,5};
		double[] p2 = {8,1,3};
		double[] pans = {15.999,-3.112,12};
		assertEquals(Ex2.poly(Ex2.mul(p1,p2)), Ex2.poly(pans));
	}
	
	
	@Test
	void derivative1() {//Compare the strings of our ans (that constitutes the derivative of p1) to Ex2.derivative(p1).
		double[] p1 = {5,8,10,2,1};
		double[] ans = {8,20,6,4};
		assertEquals(Ex2.poly(Ex2.derivative(p1)), Ex2.poly(ans));
	}
	@Test
	void derivative2() {//Checking with decimal numbers.
		double[] p1 = {5,8.2213000,10,2,1};
		double[] ans = {8.2213000,20,6,4};
		assertEquals(Ex2.poly(Ex2.derivative(p1)), Ex2.poly(ans));
	}
	@Test
	void derivative3() {//Checking the test by comparing the correct function answer with an incorrect answer.
		double[] p1 = {5,8.2213000,10,2,1};
		double[] ans = {8.2213400,20,6,4};
		assertEquals(Ex2.poly(Ex2.derivative(p1)), Ex2.poly(ans));
	}
	@Test
	void derivative4() {//Checking with negative numbers.
		double[] p1 = {5,8,-10,2,1};
		double[] ans = {8,-20,6,4};
		assertEquals(Ex2.poly(Ex2.derivative(p1)), Ex2.poly(ans));
	}
	
	@Test
	void PolynomFromPoints1() {//Comparing the strings' results of PolynomFromPoints-function to our answer.
		double[] xx = {1,2,3};
		double[] yy = {1,2,3};
		double[] ans = {-4,6,-1};
		assertEquals(Ex2.poly(Ex2.PolynomFromPoints(xx,yy)), Ex2.poly(ans));
	}
	@Test
	void PolynomFromPoints2() {//Checking the test by comparing the correct function answer with an incorrect answer.
		double[] xx = {1,2,3};
		double[] yy = {1,8,3};
		double[] ans = {-4,6,-1};
		assertEquals(Ex2.poly(Ex2.PolynomFromPoints(xx,yy)), Ex2.poly(ans));
	}
	@Test
	void PolynomFromPoints3() {//Checking with negative numbers.
		double[] xx = {-1,2,3};
		double[] yy = {1,2,3};
		double[] ans = {2,1,0};
		assertEquals(Ex2.poly(Ex2.PolynomFromPoints(xx,yy)), Ex2.poly(ans));
	}
	

	@Test
	void testMulDoubleArrayDoubleArray() {
		double[] p12 = Ex2.add(po1, po2);//Sum po1 and po2.
		double dd = Ex2.f(p12, 5);//dd is the f(5) of the polynom p12.
		assertEquals(dd, 1864.6, Ex2.EPS);//checking if 1864.6 is the value of dd.
	}
	@Test
	void testDerivativeArrayDoubleArray() {
		double[] p = {1,2,3}; // 3X^2+2x+1
		double[] dp1 = {2,6}; // 6x+2
		double[] dp2 = Ex2.derivative(p);//6x+2;
		assertEquals(dp1[0], dp2[0],Ex2.EPS);/**checking if we get the correct derivative of p with derivative-function by 
		*comparing every value in this function-output-array and our array.*/
		assertEquals(dp1[1], dp2[1],Ex2.EPS);
		assertEquals(dp1.length, dp2.length);//Checking the length of these two derivatives.
	}
	@Test
	public void testFromString() {
		double[] p = {-1.1,2.3,3.1}; 
		String sp = Ex2.poly(p); //3.1X^2+2.3x-1.1
		double[] p1 = Ex2.getPolynomFromString(sp);//{-1.1,2.3,3.1}.
		boolean isSame = Ex2.equals(p1, p);//Return true or false if p1=p;
		if(!isSame) {fail();}//If they aren't equal - so it's fail.
		assertEquals(sp, Ex2.poly(p1));//If the test isn't fail, comparing the string sp to the string of p1.
	}
}
