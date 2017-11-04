import java.util.*;
public class program
{
	public String[] test(String fileNames[])
	{
		/*
		Exercise 17: Java files- You have been given the list of the names
		of the files in a directory.
		You have to select Java files from them.
		A file is a Java file if it’s name ends with ”.java”.
		For e.g. ”File-Names.java” is a Java file, ”FileNames.java.pdf” is not.
		If the input is {”can.java”,”nca.doc”,”and.java”,”dan.txt”,”can.java”,”andjava.pdf”}
		the expected output is {”can.java”,”and.java”,”can.java”}
		*/
		String javaFiles[] = {"a.java"};
		Vector<String> v=new Vector<>();
		for (int i=0;i<fileNames.length ;i++ ) {
			// System.out.println(fileNames[i].substring(fileNames[i].length()-5));
			if(fileNames[i].length()>4){
				if(fileNames[i].substring(fileNames[i].length()-5).equals(".java")){
					v.add(fileNames[i]);
					// System.out.println("in");
				}
			}
		}
		javaFiles=v.toArray(new String[v.size()]);
		// for (int i=0;i<javaFiles.length ;i++ ) {
			// System.out.println(v.get(i));
		// }
		return javaFiles;
	}
}
