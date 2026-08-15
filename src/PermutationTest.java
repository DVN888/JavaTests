import java.util.LinkedList;
import java.util.Arrays;

import static java.lang.Math.round;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PermutationTest {
	PermutationVariation p1;
	PermutationVariation p2;
	public int n1;
	public int n2;
	int cases=1;
	
	void initialize() {
		n1=4;
		n2=6;
		Cases c= new Cases();
		p1= c.switchforTesting(cases, n1);
		p2= c.switchforTesting(cases, n2);
	}
	

	@Test
	void testPermutation() {
		initialize();
		testConstructor(p1,n1);
		testConstructor(p2,n2);
	}

	@Test
	void testDerangements() {
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();
		genDerangements();

		testNumberDerangements(p1,n1);
		testNumberDerangements(p2,n2);
		testNoFixPoint(p1,n1);
		testNoFixPoint(p2,n2);
	}
	
	@Test
	void testsameElements() {
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();
		genDerangements();

		testAllPermutations(p1,n1);
		testAllPermutations(p2,n2);
	}
	
	void setCases(int c) {
		this.cases=c;
	}
	
	public void fixConstructor() {
		//in case there is something wrong with the constructor
		p1.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n1;i++)
			p1.original[i]=i+1;
		
		p2.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n2;i++)
			p2.original[i]=i+1;
	}

	public void genDerangements() {
		p1.derangements();
		p2.derangements();
	}

	private void testConstructor(PermutationVariation p , int n) {
		assertEquals(n,p.original.length);
		for(int i=0; i<n-1; i++){
			for(int j=i+1; j<n; j++){
				assertNotEquals(p.original[i],p.original[j]);
			}
		}
		assertNotNull(p.allDerangements);
		assertTrue(p.allDerangements.isEmpty());
	}

	private void testNumberDerangements(PermutationVariation p , int n) {
		int nfactorial = 1;
		for(int i=2;i<=n;i++) {
			nfactorial *= i;
		}
		double sum = 0;
		int ifactorial = 1;
		int plusminus = -1;
		for(int i=2;i<=n;i++){
			ifactorial *= i;
			plusminus *= -1;
			sum += (double) plusminus / ifactorial;
		}
		sum *= nfactorial;
		assertEquals(round(sum),p.allDerangements.size());
	}

	private void testNoFixPoint(PermutationVariation p, int n) {
        for (int[] arr : p.allDerangements) {
            for (int i = 0; i < n; i++) {
                assertNotEquals(i+1, arr[i]);
            }
        }
	}

	private void testAllPermutations(PermutationVariation p, int n) {
		assertFalse(p.allDerangements.isEmpty());
		for(int[] perm : p.allDerangements) {
//			for(int num : perm) {
//				assertTrue(isElementPresent(p.original,num));
//			}
			for(int i=0; i<n-1; i++){
				for(int j=i+1; j<n; j++){
					assertNotEquals(perm[j],perm[i]);
				}
			}
		}
	}

//	private static boolean isElementPresent(int[] arr, int key) {
//		for (int element : arr) {
//			if (element == key) {
//				return true;
//			}
//		}
//		return false;
//	}
}


