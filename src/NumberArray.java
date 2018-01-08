
/*
 * Program where a given int array which represents a number is incremented and an int array is returned which represents 
 * the incremented number. Example, number 454 can be represented by array [4,5,4].
 * Example: 
 * given array: [1,4,1], returned array: [1,4,2]
 * given array: [9,9,9], returned array: [1,0,0,0]
 * given array: [1,4,9], returned array: [1,5,0]
 * */

public class NumberArray {
	
	int carry;
	boolean useCarry;
	boolean allNine;
	
	public NumberArray() {
		//insert some code here
		this.carry = 1;
		this.useCarry = false;
		this.allNine = false;
	}
	
	public int[] addOne(int[] arr) {
		/*
		 * Examples: 
		 * [1,2,3] -> [1,2,4]
		 * [1,9,9] -> [2,0,0]
		 * [9,9,9] -> [1,0,0,0]
		 * */
		int arrLength = arr.length;
		int lastDigit = arr[arrLength - 1];
		int sumDigits = 0;
		/*check if all digits are nine */
		for(int i=0; i<arrLength; i++) {
			sumDigits = sumDigits + arr[i];
		}
		if ((sumDigits/9) == arrLength) {
			allNine = true;
		} else {
			allNine = false;
		}
		
		/*check if last digit is nine, then set useCarry to true*/
		if(lastDigit == 9) {
			useCarry = true;
		}else {
			useCarry = false;
		}
		
		/*Begin operations - divided into broad cases where all digits are 
		 * either 9, or not all are 9. 
		 * After that we have sub cases. The usage of carry is determined 
		 * by whether the last digit is 9, and then whether previous digits 
		 * which are being incremented are 9 or not.
		 * The case where all digits are nine is a simplified one - subcases are only two.
		 * The case where all digits are not nine, is a more complicated case.
		 */
		//Case 1:ALL DIGITS ARE 9
		if(allNine == true) {
			//must return a new array whose size is 1+ given array size
			int[] newArr = new int[arrLength + 1];
			
			for(int i=arrLength-1; i>=0; i--) {
				//SubCase 1: last digit, equals 9
				if((i==arrLength-1) && (arr[i] == 9)) {
					newArr[i+1] = 0;
					useCarry = true;
					continue;
				}
				//SubCase 2: not last digit, equals 9, and carry is true
				if((i<(arrLength-1)) && (arr[i]==9) && (useCarry==true)) {
					newArr[i+1] = 0;
					useCarry = true;
					continue;
				}
			}
			newArr[0] = 1;
			
			return newArr;
		}
		
		//CASE 2: ALL DIGITS ARE NOT 9
		else {
			//must return a new array whose size is same as given array size
			int[] newArr = new int[arrLength];
			
			for(int i=arrLength-1; i>=0; i--) {
				//SubCase 1: last digit, equals 9
				if((i==arrLength-1) && (arr[i] == 9)) {
					newArr[i] = 0;
					useCarry = true;
					continue;
				}
				//SubCase 2: last digit, not equals 9
				if((i==arrLength-1) && (arr[i]!=9)) {
					newArr[i] = arr[i]+1;
					useCarry=false;
					continue;
				}
				
				//SubCase 3: not last digit, equals 9, and carry is true
				if((i<(arrLength-1)) && (arr[i]==9) && (useCarry==true)) {
					newArr[i] = 0;
					useCarry = true;
					continue;
				}
				
				//SubCase 4: not last digit, not equals 9, and carry is true
				if((i<(arrLength-1)) && (arr[i]!=9) && (useCarry==true)) {
					newArr[i] = arr[i]+1;
					useCarry=false;
					continue;
				}
				
				//SubCase 5: not last digit, and carry is false
				if((i<(arrLength-1)) && useCarry==false) {
					newArr[i] = arr[i];
				}	
			}
			return newArr;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NumberArray na = new NumberArray();
		int[] array = {9,9,9,8};
		
		int[] newArray = na.addOne(array);
		
		System.out.println("Given Array: ");
		for (int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		System.out.println("New Array: ");
		for (int i=0; i<newArray.length; i++) {
			System.out.print(newArray[i] + " ");
		}
	}

}
