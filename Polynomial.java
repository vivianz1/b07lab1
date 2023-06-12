import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Polynomial {
    double[] coefficients;
    int[] exponents;

    public Polynomial() {
        this.coefficients = null;
        this.exponents = null;
    }

    public Polynomial(double[] numbers, int[] exp) {
        this.coefficients = numbers;
        this.exponents = exp;
        this.removeRedundantSelf ();
    }

    public Polynomial(File file) throws Exception {
        Scanner myScanner = new Scanner (file);

        if (!myScanner.hasNextLine()){
            this.coefficients = null;
            this.exponents = null;
        }
        else {
            String line = myScanner.nextLine();
            if (line.equals("0") || line.equals("0.0"))
            {
                this.coefficients = null;
                this.exponents = null;
            }
            else{
                line = line.replace("-", "+-");

                String[] poly_arr = line.split("\\+");
                this.exponents = new int[poly_arr.length];
                this.coefficients = new double[poly_arr.length];

                for(int i = 0; i < poly_arr.length; i++){
                    String[] subArray = poly_arr[i].split("x");
                    if (subArray.length == 0 || subArray[0].length() == 0) coefficients[i] = 1;
                    else coefficients[i] = Double.parseDouble(subArray[0]);

                    if(subArray.length > 1){
                        exponents[i] = Integer.parseInt(subArray[1]);
                    }
                    else if(poly_arr[i].contains("x")){
                        exponents[i] = 1;
                    }
                    else {
                        exponents[i] = 0;
                    }
                }
                this.removeRedundantSelf();
            }
        }
        myScanner.close();
    }

    public String getString () {
        String writeString = "";
        if (this.coefficients != null && this.exponents != null && this.coefficients.length == this.exponents.length)
        {
            for(int i = 0; i < this.coefficients.length; i++){
                if (coefficients[i] != 0){
                    writeString += coefficients[i];
                    if (exponents[i] != 0){
                        writeString += "x" + exponents[i];
                    }
                    writeString += "+";
                }
            }
            if (writeString.endsWith("+")){
                writeString = writeString.substring(0, writeString.length()-1);
            }
            writeString = writeString.replace("+-", "-");
        }
        else writeString = "0.0";
        return writeString;
    }

    public void saveToFile(String name) throws IOException{

        File file = new File(name);
		if(!file.exists())
			file.createNewFile();
		FileWriter fileIn = new FileWriter(name);
		fileIn.write(getString());
		fileIn.close();
    }

    public Polynomial add(Polynomial p) {
        if (this == null && p == null && this.coefficients == null && this.exponents == null && p.coefficients == null && p.exponents == null) return new Polynomial();
        else if(this == null || this.coefficients == null || this.exponents == null) return new Polynomial (p.coefficients, p.exponents);
        else if(p == null || p.coefficients == null || p.exponents == null) return new Polynomial(this.coefficients, this.exponents);
        double[] coeff = new double[0];
        int[] exp = new int[0];
        for (int i = 0; i < p.exponents.length; i++){
            coeff = addCo (p.coefficients[i], coeff);
            exp = addExp (p.exponents[i], exp);
        }
        for (int i = 0; i < this.exponents.length; i++){
            coeff = addCo (this.coefficients[i], coeff);
            exp = addExp (this.exponents[i], exp);
        }
        Polynomial result = new Polynomial(coeff, exp);
        result = result.removeRedundant();
        result.removeZero();
        return result;
    }

    public double evaluate(double x) {
        if (this.exponents == null || this.coefficients == null) return 0;
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++)
            sum += coefficients[i]*Math.pow(x, exponents[i]);
        return sum;
    }

    public boolean hasRoot(double y) {
        return evaluate(y) == 0;
    }

    public int[] addExp(int n, int[] exp) {
        int size = exp.length;
        int[] newExp = new int[size+1];
        for (int i = 0; i < size; i++) newExp[i] = exp[i];
        newExp[size] = n;
        return newExp;
    }

    public double[] addCo(double n, double[] co) {
        int size = co.length;
        double[] newCo = new double[size+1];
        for (int i = 0; i < size; i++) newCo[i] = co[i];
        newCo[size] = n;
        return newCo;
    }

    public int hasContain (int[] exp, int x){
        for (int i = 0; i < exp.length; i++) 
            if (exp[i] == x) return i;
        return -1;
    }

    public Polynomial removeRedundant(){
        if (this.exponents == null || this.coefficients == null) return new Polynomial();
        int[] exp = new int[0];
        double[] coeff = new double[0];
        for(int i = 0; i < this.exponents.length; i++){
            int ind = hasContain (exp, this.exponents[i]);
            if (ind == -1){
                exp = addExp (this.exponents[i], exp);
                coeff = addCo (this.coefficients[i], coeff);
            }
            else coeff[ind] += this.coefficients[i];
        }
        return new Polynomial(coeff, exp);
    }

    public void removeRedundantSelf(){
        if (this.exponents == null || this.coefficients == null) return;
        int[] exp = new int[0];
        double[] coeff = new double[0];
        for(int i = 0; i < this.exponents.length; i++){
            int ind = hasContain (exp, this.exponents[i]);
            if (ind == -1){
                exp = addExp (this.exponents[i], exp);
                coeff = addCo (this.coefficients[i], coeff);
            }
            else coeff[ind] += this.coefficients[i];
        }
        this.exponents = exp;
        this.coefficients = coeff;
    }

    public Polynomial multiply(Polynomial p) {
        if (this == null || p == null || p.exponents == null || p.coefficients == null || this.coefficients == null) return new Polynomial();
        Polynomial max = Math.max(p.exponents.length, this.exponents.length) == p.exponents.length ? p : this;
        Polynomial min = Math.min(p.exponents.length, this.exponents.length) == this.exponents.length ? this : p;
        double[] coefficients = new double[0];
        int[] exponents = new int[0];
        for (int i = 0; i < min.exponents.length; i++)
            for (int j = 0; j < max.exponents.length; j++){
                exponents = addExp(min.exponents[i] + max.exponents[j], exponents);
                coefficients = addCo(min.coefficients[i] * max.coefficients[j], coefficients);
            }
        Polynomial result = new Polynomial(coefficients, exponents);
        result = result.removeRedundant();
        result.removeZero();
        return result;
    }

    public void removeZero()
	{
		int keep = 0;
		for(int i = 0; i < this.exponents.length; i++)
			if(this.coefficients[i] != 0) keep++;
		if(keep == 0){
			this.coefficients = null;
			this.exponents = null;
            return;
		}
        double[] coeffs = new double[keep];
        int[] exps = new int[keep];
        int count = 0;
        for(int i = 0; i < this.exponents.length; i++)
        {
            if(this.coefficients[i] != 0){
                exps[count] = this.exponents[i];
                coeffs[count] = this.coefficients[i];
                count++;
            }	
            if(count == keep)
            {	
                this.coefficients = coeffs;
                this.exponents = exps;	
                break;
            }
        }
	}
}
