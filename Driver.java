import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
public static void main(String [] args) throws FileNotFoundException, IOException, Exception{ 
 		/* Method : Constructor
		 */
		String r = "Result: ";
		int error = 0;
		//Case 1A
		System.out.println("Case 1A: 0 Polynomial");
		Polynomial z1 = new Polynomial();
		System.out.println(r + z1.getString());
		error += compare(z1.getString(), "0.0");
		//Case 1B
		System.out.println("Case 1B: File 0 Polynomial");
		Polynomial z2 = new Polynomial(new File("0.txt"));
		z2.saveToFile("0.out");
		System.out.println(r + z2.getString());
		error += compare(z2.getString(), "0.0");
		//Case 2A
		System.out.println("Case 2A: Constant");
		double[] c = {123};
		int[] e = {0};
		Polynomial pc1 = new Polynomial(c, e);
		System.out.println(r + pc1.getString());
		error += compare(pc1.getString(), "123.0");
		//Case 2B
		System.out.println("Case 2B: File Constant");
		Polynomial pc2 = new Polynomial(new File("Constant.txt"));
		pc2.saveToFile("Constant.out");
		System.out.println(r + pc2.getString());
		error += compare(pc2.getString(), "123.0");
		//Case 3A
		System.out.println("Case 2A: 1 Polynomial");
		c[0] = 1;
		e[0] = 1;
		Polynomial ps1 = new Polynomial(c, e);
		System.out.println(r + ps1.getString());
		error += compare(ps1.getString(), "1.0x1");
		//Case 3B
		System.out.println("Case 3B: File 1 Polynomial");
		Polynomial ps2 = new Polynomial(new File("1 Polynomial.txt"));
		ps2.saveToFile("1 Polynomial.out");
		System.out.println(r + ps2.getString());
		error += compare(ps2.getString(), "2.0x3");
		//Case 4A
		System.out.println("Case 4A: Nonordered Polynomial");
		double[] c1 = {10, 43, 942, 1, 0, 5, 12};
		int[] e1 = {4, 2, 1, 0, 5, 5, 2};
		Polynomial s1 = new Polynomial(c1, e1);
		System.out.println(r + s1.getString());
		error += compare(s1.getString(), "10.0x4+55.0x2+942.0x1+1.0+5.0x5");
		//Case 4B
		System.out.println("Case 4B: File Nonordered Polynomial");
		Polynomial s2 = new Polynomial(new File("Nonorder.txt"));
		s2.saveToFile("Nonorder.out");
		System.out.println(r + s2.getString());
		error += compare(s2.getString(), "4.0x2+1.0x1+2.0x5+13.0");
		//Case 5A
		System.out.println("Case 5A: Mixed Polynomial");
		double[] c2 = {10, -43, 942, -1, 0, 5, 12, -2, 29, -52};
		int[] e2 = {4, 2, 1, 0, 5, 5, 2, 0, 5, 3};
		Polynomial p1 = new Polynomial(c2, e2);
		System.out.println(r + p1.getString());
		error += compare(p1.getString(), "10.0x4-31.0x2+942.0x1-3.0+34.0x5-52.0x3");
		//Case 5B
		System.out.println("Case 5B: File Mixed Polynomial");
		Polynomial p2 = new Polynomial(new File("Mixed.txt"));
		p2.saveToFile("Mixed.out");
		System.out.println(r + p2.getString());
		error += compare(p2.getString(), "58.0x2+9.0-232.0x1-2.0x5+49.0x67-2.0x44");
		System.out.println("-===================-");
 		/* Method : Multiply
		 */
		//Case 1
		System.out.println("Case 1: 0 Multiply Polynomial");
		Polynomial multi = z1.multiply(p2);
		System.out.println(r + multi.getString());
		error += compare(multi.getString(), "0.0");
		//Case 2
		System.out.println("Case 2: 0 and 0 Multiply");
		multi = z1.multiply(z2);
		System.out.println(r + multi.getString());
		error += compare(multi.getString(), "0.0");
		//Case 3
		System.out.println("Case 3: Constant Multiply");
		multi = pc1.multiply(pc2);
		System.out.println(r + multi.getString());
		error += compare(multi.getString(), "15129.0");
		//Case 4
		System.out.println("Case 4: Constant and 0 Multiply");
		multi = pc1.multiply(z1);
		System.out.println(r + multi.getString());
		error += compare(multi.getString(), "0.0");
		//Case 5
		System.out.println("Case 5: Multiply Monomial");
		multi = ps1.multiply(ps2);
		System.out.println(r + multi.getString());
		error += compare(multi.getString(), "2.0x4");
		//Case 6
		System.out.println("Case 6: Multiply Monomial and Polynomial");
		multi = ps2.multiply(p2);
		System.out.println(r + multi.getString());
		error += compare(multi.getString(), "116.0x5+18.0x3-464.0x4-4.0x8+98.0x70-4.0x47");
		//Case 7
		System.out.println("Case 7: Multiply Two Polynomial");
		multi = s2.multiply(p1);
		System.out.println(r + multi.getString());
		error += compare(multi.getString(), "1958.0x6-46.0x4+3061.0x3+527.0x2+74.0x7+238.0x5+12243.0x1+20.0x9+68.0x10-104.0x8-39.0");
		System.out.println("-===================-");
 		/* Method : Add
		 */
		//Case 1
		System.out.println("Case 1: 0 Add Polynomial");
		Polynomial added = z1.add(p2);
		System.out.println(r + added.getString());
		error += compare(added.getString(), p2.getString());
		//Case 2
		System.out.println("Case 2: 0 and 0 Add");
		added = z1.add(z2);
		System.out.println(r + added.getString());
		error += compare(added.getString(), "0.0");
		//Case 3
		System.out.println("Case 3: Constant Add");
		added = pc1.add(pc2);
		System.out.println(r + added.getString());
		error += compare(added.getString(), "246.0");
		//Case 4
		System.out.println("Case 4: Constant and 0 Add");
		added = pc1.add(z1);
		System.out.println(r + added.getString());
		error += compare(added.getString(), pc1.getString());
		//Case 5
		System.out.println("Case 5: Add Monomial");
		added = ps1.add(ps2);
		System.out.println(r + added.getString());
		error += compare(added.getString(), "2.0x3+1.0x1");
		//Case 6
		System.out.println("Case 6: Add Monomial and Polynomial");
		added = ps1.add(p2);
		System.out.println(r + added.getString());
		error += compare(added.getString(), "58.0x2+9.0-231.0x1-2.0x5+49.0x67-2.0x44");
		//Case 7
		System.out.println("Case 7: Add Two Polynomial");
		added = s2.add(p1);
		System.out.println(r + added.getString());
		error += compare(added.getString(), "10.0x4-27.0x2+943.0x1+10.0+36.0x5-52.0x3");
		//Case 8
		System.out.println("Case 8: Different Degrees");
		double[] c3 = {13.0, 1.0, 4.0, 2.0, 45.0};
		int[] e3 = {0, 1, 2, 5, 23};
		Polynomial s3 = new Polynomial(c3, e3);
		added = p2.add(s3);
		System.out.println(r + added.getString());
		error += compare(added.getString(), "22.0-231.0x1+62.0x2+45.0x23+49.0x67-2.0x44");
		System.out.println("-===================-");
 		/* Method : Evaluate
		 */
		//Case 1
		System.out.println("Case 1: 0 Evaluate");
		System.out.println(z1.evaluate(23232));
        error += z1.evaluate(23232) == 0 ? 0 : 1;
		//Case 2
		System.out.println("Case 2: Constant Evaluate");
		System.out.println(pc1.evaluate(23232));
        error += pc1.evaluate(23232) == 123 ? 0 : 1;
		//Case 3
		System.out.println("Case 3: Polynomial (x^2 - 9) Evaluate at x = 2");
		Polynomial dSq = new Polynomial(new File("DiffSq.txt"));
		System.out.println(dSq.evaluate(2));
        error += dSq.evaluate(2) == -5 ? 0 : 1;
		System.out.println("-===================-");
 		/* Method : hasRoot
		 */
		//Case 1
		System.out.println("Case 1: 0 Root");
		System.out.println(z1.hasRoot(23232));
        error += z1.hasRoot(23232) ? 0 : 1;
		//Case 2
		System.out.println("Case 2: Constant hasRoot");
		System.out.println(pc1.hasRoot(23232));
        error += pc1.hasRoot(23232) ? 1 : 0;
		//Case 3
		System.out.println("Case 3: Polynomial (x^2 - 9) hasRoot at -3 and 3");
		System.out.println(dSq.getString());
		System.out.println("x = -3:" + dSq.hasRoot(-3) + " and x = 3:" + dSq.hasRoot(3));
        error += dSq.hasRoot(-3) == dSq.hasRoot(3) == true ? 0 : 1;
		System.out.println("Case 4: Polynomial (x^2 - 9) hasRoot at -4");
		System.out.println("x = -4:" + dSq.hasRoot(-4));
        error += dSq.hasRoot(-4) ? 1 : 0;
		System.out.println("-===================-");
		System.out.println("Errors: " + error);
 	} 
	public static int compare(String a, String b){
		if(a.equals(b))
			return 0;
		System.out.println("Wrong!");
		return 1;
	}
}