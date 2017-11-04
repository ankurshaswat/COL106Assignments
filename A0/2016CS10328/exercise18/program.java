import java.util.*;
public class program
{
	public int test(String number)
	{
		/*
		Exercise 18: Most frequent digit- Given a number, the objective is to find out
		the most frequently occuring digit in the number. If more than 2 digits have
		the same frequency, return the smallest digit. The number is input as a string
		and the output should be the digit as an integer. For e.g. if the number is
		12345121, the most frequently occuring digit is 1. If the number is 9988776655
		the output should be 5 as it is the smallest of the digits with the highest frequency.
		*/

		HashMap<Character,Integer> hm=new HashMap<>();
		Character c;
		for (int i=0;i<number.length() ;i++ ) {
			c=number.charAt(i);
			if(hm.get(c)==null){
				hm.put(c,1);
			}else{
				hm.put(c,hm.get(c)+1);
			}
		}
		int max=hm.get(number.charAt(0));
		Character max_c=number.charAt(0);

		for(int i=1;i<number.length();i++){
			c=number.charAt(i);
			if(hm.get(c)>max || (hm.get(c)==max && c<max_c)){
				max=hm.get(c);
				max_c=number.charAt(i);
			}
		}
		// System.out.println((int)(max_c)-'0');
		return (int)(max_c)-'0';
	}
}
