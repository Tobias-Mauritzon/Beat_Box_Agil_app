package model;

import java.util.Random;

public class calculator {
	int num1;
	int num2;
	
	public static void main(String[] args) 
	{
		//number size
		int[] stor = {1,100};
		// allowed modifiers
		int[] mod = {1,2,3,4};
		String[] s = rec(5, stor,new String[2], mod);
		
		System.out.println(s[0]);
		System.out.println(s[1]);
	}
	
	
	/**
	 * @param antalTal
	 * @param storlek
	 * @param talOchSvar
	 * @param mod
	 * @return
	 */
	private static String[] rec(int antalTal, int[] storlek, String[] talOchSvar, int[] mod) {
		
		if(antalTal != 0) {
			antalTal--;
			if(talOchSvar[1] != null) {
				Random rand = new Random();
				
				// generates the next number for the expression
				int genNumber = rand.nextInt((storlek[1] - (storlek[0]) + 1) + (storlek[0]));
				boolean allowed = false;
				int whichMod = 0;
				
				while(!allowed) {
					// generates a number 1 - 4 radomly for modifier
					whichMod = (int) ((Math.random() * (4 - 1))+1);
					
					for(int i : mod) {
						if(i == whichMod) {
							allowed = true;
						}
					}
				}				
				if(whichMod == 1) {					
					talOchSvar[0] = talOchSvar[0]+" + "+genNumber;
					int nyttSvar = Integer.parseInt(talOchSvar[1]) + genNumber;
					talOchSvar[1] = Integer.toString(nyttSvar);					
				}else if(whichMod == 2){					
					talOchSvar[0] = talOchSvar[0]+" - "+genNumber;
					int nyttSvar = Integer.parseInt(talOchSvar[1]) - genNumber;
					talOchSvar[1] = Integer.toString(nyttSvar);					
				}else if(whichMod == 3){
					boolean run = true;
					while(run) {						
						try {
							talOchSvar[0] = "("+talOchSvar[0]+")/"+genNumber;
							int nyttSvar = Integer.parseInt(talOchSvar[1]) / genNumber;
							talOchSvar[1] = Integer.toString(nyttSvar);
							run= false;
						}catch(Exception e) {
							genNumber = rand.nextInt((storlek[1] - (storlek[0]) + 1) + (storlek[0]));
						}
					}															
				}else if(whichMod == 4){					
					talOchSvar[0] = "("+talOchSvar[0]+") * "+genNumber;
					int nyttSvar = Integer.parseInt(talOchSvar[1]) * genNumber;
					talOchSvar[1] = Integer.toString(nyttSvar);					
				}								
			}else {
				Random rand = new Random();
				
				int genNumber = rand.nextInt((storlek[1] - (storlek[0])+1)+(storlek[0]));
				
				talOchSvar[0] = Integer.toString(genNumber);
				talOchSvar[1] = Integer.toString(genNumber);
			}			
			talOchSvar = rec(antalTal, storlek, talOchSvar, mod);
		}		
		return talOchSvar;
	}
}
