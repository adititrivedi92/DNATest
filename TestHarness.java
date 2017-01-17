import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestHarness {
	/*
	 * main method
	 */
public static void main(String[] args){
	
	DnaTest d1 = new DnaTest ();
	File f = new File ("input.txt");
	/*
	 * accepting and reading file input
	 */
	Scanner sc = null;
	try {
		sc=new Scanner(f);
		
		while(sc.hasNextLine()){
			/*
			 * number of strands to be read
			 */
			int t=sc.nextInt(); 
			String blank = sc.nextLine();
			
			/*
			 * for loop reads each strand
			 * ignores whitespaces
			 */
			for(int i=1; i<=t; i++){
				/*original strand read as is*/
				String b = sc.nextLine();
				
				/*removing whitespaces*/ 
				String b1 = b.replaceAll("\\s", ""); 
				
				/*storing first character as number of bases in that strand*/
				char n=b.charAt(0);
				
				/*converting char to int*/
				int len = Character.getNumericValue(n);
				
				/*
				 * constructing new linked list using each character as read above
				 */
				for(int j=1; j<=len; j++){
					d1.addNode(b1.charAt(j));	
				}
				
				/*
				 * displaying linked list of dna strand as read from input file
				 * obtaining reverse complement of strand
				 * printing gc content and reverse complement of dna strands from inout file
				 */
				d1.displayDNA(d1.getFront());
				Node rc = DnaTest.reverseComplement(d1.getFront());
				System.out.print("\t"+"GC content: "+DnaTest.gcContent(d1.getFront())+"\t"+"Reverse complement: ");
				d1.displayDNA(rc);
				System.out.println();
				
				/*initializing d1 to read next line in file*/
				d1 = new DnaTest();
				}
			/*
			 * reading last line of input file
			 * to generate strands of given length and rate
			 * else block displays valid possible combinations of ATGC
			 */
			int len_gen = sc.nextInt();
			int rate_gen = sc.nextInt();
			if(len_gen<1){
				System.out.println("length of strand to be generated must be greater than 0");
			}
			else{
				DnaTest.generateStrandsHelper(len_gen, 1, rate_gen);
				System.out.println();
				}
			}
		}
	catch (FileNotFoundException e) {
			e.printStackTrace();
		}sc.close();
	}
}


	

	

