package projectEuclidHelpers;
import java.util.ArrayList;
import projectEuclidHelpers.PrimeFactor;

public class PrimeNumberList{

	public ArrayList<Integer> primes = new ArrayList<Integer>();
	public int currentPrime;
	
	public void initialize() {
		primes.add(2);
		primes.add(3);
		currentPrime=3;
	}
	
	public void nextPrime() {
		boolean flag = true;
		int candidate=currentPrime;
		int ii;
		
		while (flag == true){
			ii=0;
			candidate=candidate+2;
			while (candidate % primes.get(ii) != 0 && ii+1 < primes.size()){
				ii++;
			}
			if( ii+1 == primes.size() && candidate % primes.get(ii) != 0){
				flag = false;
				primes.add(candidate);
				currentPrime=candidate;
			}
		}
	}
	
	public void populateToInteger(int max){
		if (max==2){
			primes.add(2);
			currentPrime=2;
		} else {
			initialize();
		}
		if (max > 3){
			while (currentPrime < max){
				nextPrime();
			}
			if (primes.get(primes.size()-1) > max){
				primes.remove(primes.size()-1);
				currentPrime=primes.get(primes.size()-1);
			}
		}
	}
	
	public Long sum(){
		Long sum = 0L;
		for( int ii = 0; ii < primes.size(); ii++){
			sum = sum + primes.get(ii);
		}
		return sum;
	}
	
	public Long prod(){
		Long prod = 1L;
		for( int ii = 0; ii < primes.size(); ii++){
			prod = prod*primes.get(ii);
		}
		return prod;
	}
	
	public int getCurrentPrime(){
		return currentPrime;
	}
	
	public ArrayList<Integer> getPrimesList(){
		return primes;
	}
	
	public ArrayList<PrimeFactor> computePrimeFactorization(int num){
		ArrayList<PrimeFactor> factors = new ArrayList<PrimeFactor>();
		
		int multiplicity = 0;
		int primesIndex = 0;
		
		
		// Check for factors already in list
		System.out.println("Checking primes already on the list...");
		while (primesIndex < primes.size()){
			if( num % primes.get(primesIndex) == 0 ){
				multiplicity = 0;
				while(num % primes.get(primesIndex) == 0){
					multiplicity++;
					num = num / primes.get(primesIndex);
				}
				System.out.println("Adding factor...");
				factors.add(new PrimeFactor(primes.get(primesIndex), multiplicity));
			}
			primesIndex++;
		}
		
		// Expand list for more factors
		System.out.println("Checking primes not yet on the list...");
		while (currentPrime < num){
			nextPrime();
			if( num % currentPrime == 0){
				multiplicity = 0;
				while (num % currentPrime == 0){
					// Divide out factors
					multiplicity++;
					num = num / currentPrime;
				}
				System.out.println("Adding factor...");
				factors.add(new PrimeFactor(currentPrime, multiplicity));
			}
		}
		
		return factors;
	}
}


class PrimeNumberListTest {
	public static void main(String[] args){
		PrimeNumberList pp = new PrimeNumberList();
		pp.initialize();
		System.out.println("List of Primes: " + pp.primes);
		System.out.println("Current Primes: " + pp.currentPrime);
	}
}

class PrimeNumberAddTest{
	public static void main(String[] args){
		PrimeNumberList pp = new PrimeNumberList();
		pp.initialize();
		System.out.println(pp.primes.get(0));
		System.out.println(pp.primes.get(1));
		System.out.println("List of Primes: " + pp.primes);
		pp.nextPrime();
		pp.nextPrime();
		pp.nextPrime();
		pp.nextPrime();
		pp.nextPrime();
		System.out.println("List of Primes: " + pp.primes);
		System.out.println("Current Prime: " + pp.currentPrime);
	}
}

class PrimeNumberPopulateToIntegerTest{
	public static void main(String[] args){
		PrimeNumberList pp = new PrimeNumberList();
		pp.populateToInteger(20);
		System.out.println("List of Primes: " + pp.primes);
		System.out.println("Current Prime: " + pp.currentPrime);
	}
}

class PrimeNumberComputePrimeFactorizationTest{
	public static void main(String[] args){
		int testNum = 20000;
		
		ArrayList<PrimeFactor> factors = new ArrayList<PrimeFactor>();
		ArrayList<Integer> primes = new ArrayList<Integer>();
		ArrayList<Integer> mult = new ArrayList<Integer>();
		PrimeNumberList pp = new PrimeNumberList();
		pp.initialize();
		
		factors = pp.computePrimeFactorization(23);
		factors = pp.computePrimeFactorization(testNum);
		
		for (int ii=0; ii<factors.size(); ii++){
			primes.add(factors.get(ii).prime);
			mult.add(factors.get(ii).multiplicity);
		}
		System.out.println(primes);
		System.out.println(mult);
	}
}