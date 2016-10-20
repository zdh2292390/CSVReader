import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularMatch {
	public static boolean matchInter(String param){
		Pattern pattern = Pattern.compile("^\\+?[1-9][0-9]*$");    
	    return pattern.matcher(param).matches();
	}
	public static boolean matchFloat(String param){
		Pattern pattern = Pattern.compile("(-?\\d+)(\\.\\d+)?$");    
	    return pattern.matcher(param).matches();
	}
	public static boolean matchDouble(String param){
		Pattern pattern = Pattern.compile("^[-+]?(/d+(/./d*)?|/./d+)([eE]([-+]?([012]?/d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$");    
	    return pattern.matcher(param).matches();
	}
	 public static boolean matchDate(String param) {   
	        String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";   
	        Pattern pattern = Pattern.compile(eL);    
		    return pattern.matcher(param).matches();
	  }  
	 public static boolean macthBoolean(String param){
		 if(param.equals("true")||param.equals("false")){
			 return true;
		 }
		 return false;
	 }
	 public static String matchType(String param){
		 //string, tinyint, smallint, int, bigint, float, double, boolean, string, timestamp
		 if(matchInter(param)){
			 return "INT";
		 }else if(matchDouble(param)){
			 return "DOUBLE";
		 }else if(matchFloat(param)){
			 return "DOUBLE";
		 }else if(macthBoolean(param)){
			 return "BOOLEAN";
		 }else if(matchDate(param)){
			 return "TIMESTAMP";
		 }else {
			 return "STRING";
		}
	 }
	 public static String jdbcSwitchImpalaType(String type){
		 String regex = "\\(.*\\)";
		 type=type.replaceAll(regex, "");
		 String[] intType={"tinyint","smallint","mediumint","int","bigint"};
		 String[] dateType={"date","time","datetime","timestamp","year"};
		 String[] floatType={"float","double","decimal"};
		 String[] strType={"char","varchar","tinytext","text","mediumtext","longtext"};
		 if(Arrays.asList(intType).indexOf(type)>=0){
			 return "INT";
		 }else if(Arrays.asList(dateType).indexOf(type)>=0){
			 return "TIMESTAMP";
		 }else if(Arrays.asList(floatType).indexOf(type)>=0){
			 return "DOUBLE";
		 }else if(Arrays.asList(strType).indexOf(type)>=0){
			 return "STRING";
		 }else{
			 return "STRING";
		 }
	 }
	public static void main(String[] args){
		/*String typeString="varchar(12)";
		String regex = "\\(.*\\)";*/
		System.out.println(matchType("-60.704"));
		
		
	}
}
