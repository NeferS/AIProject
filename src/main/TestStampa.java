package main;

import java.util.BitSet;

import representations.BasicGameEngine;

public class TestStampa {
	
	static BitSet n1 = new BitSet(32);
	static BitSet n2 = new BitSet(32);
	static BitSet n3 = new BitSet(32);
	static BitSet n4 = new BitSet(32);
	static BitSet n5 = new BitSet(32);
	static BitSet n6 = new BitSet(32);
	static BitSet n7 = new BitSet(32);
	static BitSet n8 = new BitSet(32);
	static BitSet n9 = new BitSet(32);
	static BitSet n10 = new BitSet(32);
	static BitSet n11 = new BitSet(32);
	static BitSet n12 = new BitSet(32);
	
	static BitSet b1 = new BitSet(32);
	static BitSet b2 = new BitSet(32);
	static BitSet b3 = new BitSet(32);
	static BitSet b4 = new BitSet(32);
	static BitSet b5 = new BitSet(32);
	static BitSet b6 = new BitSet(32);
	static BitSet b7 = new BitSet(32);
	static BitSet b8 = new BitSet(32);
	static BitSet b9 = new BitSet(32);
	static BitSet b10 = new BitSet(32);
	static BitSet b11 = new BitSet(32);
	static BitSet b12 = new BitSet(32);

	public static void main (String...arg) {
		initBitSet();
		System.out.println(valH());
		
		/*
		 * 
		for (int i=0; i< 12; i++) {
			System.out.println(player[i]);			
		}
		System.out.println();
		for (int i=0; i< 12; i++) {
			System.out.println(enemy[i]);
		}
		*/
	}
	
	public static void initBitSet() {
		//M2
		b2.set(21);
		b2.set(22);

		b4.set(23);
		b4.set(30);

		n4.set(1);
		n3.set(9);
		n4.set(17);
/*
 * 
		//M1
		b2.set(21);
		b2.set(22);
		b2.set(23);

		b3.set(19);
		b3.set(30);


		n4.set(1);
		n4.set(9);
		n4.set(17);
 */
	}
	
	
	
	public static double valH () {
		BitSet[] player= {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12}; 
		BitSet[] enemy= {n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12}; 
		double advantage = 0;
		int srcSquare = 0;
		int square =0;
		int totpp = 0; 
				int totep = 0;
		for(int i=0; i<12; i++) {
			srcSquare = 0;
			while(true) {
				srcSquare = player[i].nextSetBit(srcSquare);
				if(srcSquare == -1) break;
				for (int j=i; j<12; j++) {//conviene settare j a 0?
					square = 0;
					while(true){
						square = enemy[j].nextSetBit(square);
						if (square == -1) break;
						int distance = BasicGameEngine.distances[srcSquare][square];
						if (distance != -1) {
							System.out.println("Stack bianco da "+i+" e stack nero da "+j+" a distanza "+ distance);
							int diffPedine = i-j;
							if ( diffPedine < 0 && j+1 >= distance && i+1<=distance ) {
								System.out.println("primo if");
								advantage--;
							}else if (diffPedine > 0 && i+1 >= distance && j+1<=distance) {
								System.out.println("secondo if");
								advantage++;
							}
						}
						square++;
					}	
				}
				srcSquare++;
			}
			totpp += (player[i].cardinality()*(i+1));
			totep += (enemy[i].cardinality()*(i+1));
		}	
		return advantage+(totpp - totep);
	}

}
