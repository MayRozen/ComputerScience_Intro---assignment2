package Ex2;
/**Name and Id: May Rozen, 212051007.
 */
/** 
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * In general, such an array {-1,2,3.1} represents the following polynom 3.1x^2+2x-1=0,
 * The index of the entry represents the power of x.
 * 
 * Your goal is to complete the functions below, see the marking: // *** add your code here ***
 *
 * @author boaz.benmoshe
 *
 */
public class Ex2 {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynom is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};
	
	/** Two polynoms are equal if and only if the have the same coefficients - up to an epsilon (aka EPS) value.
	 * @param p1 first polynom
	 * @param p2 second polynom
	 * @return true iff p1 represents the same polynom as p2.
	 */
	public static boolean equals(double[] p1, double[] p2) {
		boolean ans = true;
		int i = 0;
		if (p1.length != p2.length) { //If the lengths are different, stop and return false.
			return false;
		}
		while( i<p1.length )
		{
			if (Math.abs(p1[i]-p2[i])>EPS){//Run until the arrays have the same coefficients - up to an epsilon and if they bigger than eps, stop and return false.
				return false;
			}
			i++;
		}
		return ans; //If false does'nt returned, the arrays are equal.
	}
	/**
	 * Computes the f(x) value of the polynom at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynom value at x.
	 */
	public static double f(double[] poly, double x) {
		double ans = 0;
		for(int i=0; i<poly.length ; i++) //Do math.pow on x according to i.
		{
			ans = ans + (Math.pow(x, i))*poly[i];//multiply the answer with the value of array[i].
		}
		return ans;
	}
	/** 
	 * Computes a String representing the polynom.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 * @param poly the polynom represented as an array of doubles
	 * @return String representing the polynom: 
	 */
	public static String poly(double[] poly) {
		String ans = "";
		int i=0;
		while(i<poly.length) //Ran all the values of poly.
		{
			if(poly[i]<0) { //If the value is negative, put minus before the value.
				if(i==0) { //If the value is a coefficient of x.
					ans = ans+"-"+Math.abs(poly[i]);
				}
				else { //The value is a coefficient of x.
					if(poly[i-1]<0) {//If the ans we have yet is negative, it's meaning it has already a sign (minus sign).
						ans = "-"+Math.abs(poly[i])+"x"+"^"+i+ans;//Because the value has sign minus, we don't need to add new one.
					}
					else {//We don't need to add new one.
						ans = "-"+Math.abs(poly[i])+"x"+"^"+i+"+"+ans;
					}
				}
			}
			else { //If the value is positive
				if(i==0) {//The value isn't a coefficient of x.
					ans = ans+Math.abs(poly[i]);
				}
				else//The value is a coefficient of x.
					if(poly[i-1]<0) {
						ans =poly[i]+"x"+"^"+i+ans;
					}
					else {
						ans =poly[i]+"x"+"^"+i+"+"+ans;
					}
			}
			i=i+1;
		}
		return ans;
	}

	/**
	 * Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented iteratively (none recursive).
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		double a = x1; //a save for us the minimal value.
		double b = x2; //b save for us the maximum value.
		while(Math.abs(f(p,x12))>eps) {// If it is, we will want to approximate to eps.
			if(f(p,x12)<eps) {//If our f(x) smaller than eps, we will want to check the values between a to the average.
				b=x12;
				x12=(a+b)/2;//Crate a new average between the new range.
			}
			else {//If our f(x) bigger than eps, we will want to check the values between the average to b.
				if(f(p,x12)>eps) {
				a=x12;
				x12=(a+b)/2;
				}
			}
		}
		return x12;
	}
	/** Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented recursivly.
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		if (Math.abs(f(p,x12))<=eps)//If our f(x) smaller or equal to eps, we will want to check the values between the average to b.
			return x12;
		if(f(p,x12)>eps)//If our f(x) bigger than eps, we will want to check the values between the average to x2.
			return root_rec(p,x12,x2,eps);
		else
			if(f(p,x12)<eps)//If our f(x) smaller than eps, we will want to check the values between x1 to the average.
				return root_rec(p,x1,x12,eps);
		return x12;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) -p2(x)| < eps.
	 */
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		double x = 0;//If there is no any x.
		if (Math.abs(f(p1,x12)-f(p2,x12))<eps) {//If |p1(x) - p2(x)| < eps, return this x.
			return x12;
		}
		else {
			if((f(p1,x1)-f(p2,x1))>(f(p1,x2)-f(p2,x2))) {//If (p1(x1)-p2(x1)) is bigger, the x we are looking for is in the range between the average to x2.
				return sameValue(p1,p2,x12,x2,eps);
			}
			else
				if((f(p1,x1)-f(p2,x1))<(f(p1,x2)-f(p2,x2))){//If(p1(x2)-p2(x2)) is bigger, the x we are looking for is in the range between the x1 to the average.		
					return sameValue(p1,p2,x1,x12,eps);	
				}
		}
		return x;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of "boxes". 
	 * This function computes an approximation of the area between the polynoms within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfBoxes - a natural number representing the number of boxes between xq and x2.
	 * @return the approximated area between the two polynoms within the [x1,x2] range.
	 */
	public static double area(double[] p1,double[] p2, double x1, double x2, int numberOfBoxes) {
		double ans = 0;
		double widthbox = (x2-x1)/numberOfBoxes;//The width of every rectangle.
		while(x1<x2) {
			if(f(p2,x1)>f(p1,x1)){ //If p2(x)>p1(x) it means we need to take difference between the area of p2 and p1.
				ans = ans + Math.abs(f(p2,x1)-f(p1,x1))*widthbox; //Area of rectangle it's its length multiplied by its width.
			}
			else {//If p2(x)<p1(x).
				ans = ans + Math.abs(f(p1,x1)-f(p2,x1))*widthbox;	
			}
			x1 = x1 + widthbox;//Because we have "numberOfBoxes".
		}
		return ans;
     }
	/**
	 * This function computes the array representation of a polynom from a String
	 * representation. Note:given a polynom represented as a double array,  
	 * getPolynomFromString(poly(p)) should return an array equals to p.
	 * 
	 * @param p - a String representing polynom.
	 * @return
	 */
	public static double[] getPolynomFromString(String p) {
		if(p.equals(null) || p.equals("0")) {//If p=0 or null, return 0.
			return ZERO;
		}
		
		String[] strp = p.split("[+]");//Take out all the chars '+' and create the new array strp.
		String[] polynom = new String[strp.length] ;//A new array is strp.length long with no values.
		int i=0,pow=0,n=0;//i will help as in our loop, pow it's the decimal power and n it's the numbers of coefficients of x we have.
		
		while(i<polynom.length) {//Run all over polynom values.
			if(strp[i].indexOf('^')!=-1) {//If the strp[i] includes '^', it means this string has coefficients of x and decimal power.
				char ch='0';
				if(strp[i].charAt(0)=='-') {//If the first char is '-' (our coefficients of x is negative).
					ch=strp[i].charAt(6);//ch = the pow (the last char).
					String st=ch+"";//Conversion to string.
					pow = Integer.parseInt(st);//Conversion to int.
					polynom[pow]="-"+String.valueOf(strp[i].charAt(1));//Conversion to string and put in the index - pow the value of the coefficients of x in strp[i].
				}
				else {//Our coefficients of x is positive.
					ch=strp[i].charAt(5);//ch = the pow (the last char).
					String st=ch+"";
					pow = Integer.parseInt(st);
					polynom[pow]=String.valueOf(strp[i].charAt(0));//because we don't have minus, now we will want to get the char who in the index 0.
				}
				n++;//Counting the numbers of coefficients of x.
			}
			else {//If the strp[i] is number without pow.
				char ch='0';
				if(strp[i].indexOf('x')!=-1) {//If it's a coefficients of x.
					pow = 1;
					ch=strp[i].charAt(0);
					String st=ch+"";
					polynom[pow]=st;
					n++;
					
				}
				else {//If strp[i] is only a number.
					String st=strp[i]+"";
					polynom[0]=st;
					n++;
				}
			}
			i=i+1;
		}
		
		double[] ans = new double[n];//The array we will return.
		for(int j=0; j<n; j++) {
			ans[j] = Double.parseDouble(polynom[j]);//Conversion to double.
		}
		
		return ans;
	}
	/**
	 * This function computes the polynom which is the sum of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] add(double[] p1, double[] p2) {
		int n=Math.max(p1.length, p2.length);//n is the maximum between p1.length and p2.length.
		double [] ans = new double[n];//A new array which its length is n.
		if(p1.length<p2.length) {
			for(int i=0; i<p1.length; i++) {//Sum all the values according to the indexes.
				ans[i]=p1[i]+p2[i];
			}
			for(int i=p1.length; i<p2.length; i++) {//Add the remaining values of p2.
				ans[i]=p2[i];
			}
		return ans;
		}
		else{
			for(int i=0; i<p2.length; i++) {//Sum all the values according to the indexes.
				ans[i]=p1[i]+p2[i];
			}
			for(int i=p2.length; i<p1.length; i++) {//Add the remaining values of p1.
				ans[i]=p1[i];
			}
		return ans;
		}
	}
	/**
	 * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] mul(double[] p1, double[] p2) {
		int n=Math.min(p1.length, p2.length);//n is the minimum between p1.length and p2.length.
		double [] ans = new double[n];//A new array which its length is n.
		if(p1.length<p2.length) {
			for(int i=0; i<p1.length; i++) {
				ans[i]=p1[i]*p2[i];//ans[i]=the multiplication of p1's and p2's values. 
			}
		return ans;
		}
		else{
			for(int i=0; i<p2.length; i++) {
				ans[i]=p1[i]*p2[i];
			}
		return ans;
		}
	}
	/**
	 * This function computes the derivative polynom of po.
	 * @param po
	 * @return
	 */
	public static double[] derivative (double[] po) {
		double [] ans = new double[po.length-1];//Because derivative of function include one less number.
		if(po.length==1) {//If its length is 1, we have only a number without x, so the derivative will be 0;
			ans[0]=0;
			return ans;
		}
		else {
			for(int i=1; i<po.length; i++) {//Run all the po values and do the multiplication of the pow and coefficients of x.
				ans[i-1]=i*(po[i]);
			}
			return ans;
		}
	}
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynom.
	 * Note: this function only works for a set of points containing three points, else returns null.
	 * @param xx
	 * @param yy
	 * @return an array of doubles representing the coefficients of the polynom.
	 * Note: you can assume xx[0]!=xx[1]!=xx[2]
	 */
	public static double[] PolynomFromPoints(double[] xx, double[] yy) {
		double [] ans = null;//Creating a new array with no values or length.
		if(xx!=null && yy!=null && xx.length==3 && yy.length==3) {//If we have three points.
			ans = new double[3];//The length of the array will be 3 (for a, b and c, the coenfficients of x).
			
			double p1=Math.pow(xx[1], 2)-Math.pow(xx[0], 2); //=(x2^2-x1^2)
			double p2=Math.pow(xx[2], 2)-Math.pow(xx[0], 2);//=(x3^2-x1^2)
			double p3=Math.pow(xx[2], 2)-Math.pow(xx[1], 2);//=(x3^2-x2^2)

			double b= ((p1*yy[2])-(p2*yy[1])-(p3*yy[0]))/((p1*(xx[2]-xx[1]))-p3*(xx[1]-xx[0]));//By calculatin of three equaptions with three vanishings.
			double a=(yy[2]-yy[1]-b*xx[2]+b*xx[1])/p3;
			double c= yy[0]-b*xx[0]-a*Math.pow(xx[0],2);
			
			ans[0]=c;//Entering the values to ans.
			ans[1]=b;
			ans[2]=a;
			
			return ans;
		}
		return ans;//Will return null if we don't have three points.
	}
	///////////////////// Private /////////////////////
	// you can add any additional functions (private) below
}
