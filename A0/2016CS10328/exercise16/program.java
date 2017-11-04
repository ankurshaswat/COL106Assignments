public class program
{
	public String test(String hex)
	{
		/*
		Exercise 16: Hex to binary- Given a string representing a number in hexadecimal
		format, convert it into its equivalent binary string. For e.g. if the input if ”1F1”
		then its binary equivalent is ”111110001”. If the input is ”13AFFFF”, the output
		should be ”1001110101111111111111111”.
		*/
		String binary="11";
		binary="";
		for (int i=0;i<hex.length() ;i++ ) {
			switch(hex.charAt(i)){
				case '0':
						if(binary=="") continue;
						else	binary+="0000";
						break;
				case '1':
						if(binary=="") binary+="1";
						else	binary+="0001";
						break;
				case '2':
						if(binary=="") binary+="10";
						else	binary+="0010";
						break;
				case '3':
						if(binary=="") binary+="11";
						else	binary+="0011";
						break;
				case '4':
						if(binary=="") binary+="100";
						else	binary+="0100";
						break;
				case '5':
						if(binary=="") binary+="101";
						else	binary+="0101";
						break;
				case '6':
						if(binary=="") binary+="110";
						else	binary+="0110";
						break;
				case '7':
						if(binary=="") binary+="111";
						else	binary+="0111";
						break;
				case '8':
						binary+="1000";
						break;
				case '9':
						binary+="1001";
						break;
				case 'A':
						binary+="1010";
						break;
				case 'B':
						binary+="1011";
						break;
				case 'C':
						binary+="1100";
						break;
				case 'D':
						binary+="1101";
						break;
				case 'E':
						binary+="1110";
						break;
				case 'F':
						binary+="1111";
						break;

			}
		}
		return binary;
	}
}
