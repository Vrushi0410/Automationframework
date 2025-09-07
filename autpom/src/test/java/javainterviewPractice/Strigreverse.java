package javainterviewPractice;

public class Strigreverse {
	 String s1 = "hello java programming";
	public void StreverseWithBuilder() {
		StringBuilder builder = new StringBuilder(s1);
		 System.out.print(builder.reverse().toString());
	}
	
	public String reversse() {
		String rev="";
		
		for(int i=s1.length()-1;i>=0;i--) {
		   rev+=	s1.charAt(i);
				
			}
		return rev;
		}
      
	public String  withoutSwapworld() {
		String finalres =" ";
		String[] s2= s1.split(" ");
		for(int i=0;i<s2.length;i++){
			String temp= s2[i];
			for(int j=temp.length()-1;j>=0;j--) {
				 finalres += temp.charAt(j);
			}
			if (i < s2.length - 1) {
				finalres += " ";
	        }
		}
		return finalres;
	}
	public static void main(String[] args) {
		
		Strigreverse strev = new Strigreverse();
		System.out.println(strev.reversse());
		System.out.println(strev.withoutSwapworld());
		strev.StreverseWithBuilder();
		
		
	}

}
