package myApp;

import java.awt.SystemTray;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ConbinationTest {

	/**
	 *  测试排列组合算法
	 * @param args
	 */
	
	
	
	public static void main(String[] args){
		  ArrayList<String> l=new ArrayList<String>();
		  System.out.println("请输入要排序的字母,输入$结束:");
		  String[] str = {"太子参","党参","甘草","当归","莪术"};
		 
		  List<String> comsList = combiantion(str);
		  System.out.println(comsList);
		 }
	
	public static List<String> combiantion(String[] strs){  
	    if(strs.length == 0) return null;  
	    List<String> list = new ArrayList<String>();
	    Stack<String> stack = new Stack<String>();  
	    for(int i = 2; i <= strs.length; i++){  
	        combine(strs, 0, i, stack, list);  
	    }
	    List<String> resultList = new ArrayList<String>();
		  
		  for(String s : list){
			  String[] ss = s.split(",");
			  String beginString = ss[0].replace("[", "");
			  String endString = ss[ss.length-1].replace("]", "");
			  ss[0] = beginString;
			  ss[ss.length-1] = endString;
			  
			  String nameString = "";
			  for(int i = 0; i < ss.length; i++){
				  nameString += ss[i] + " ";
			  }
			  resultList.add(nameString);
		  }
		  return resultList;
	}  
	//从字符数组中第begin个字符开始挑选number个字符加入list中  
	public static void combine(String[] strs, int begin, int number, Stack<String> stack,List<String> list){  
	       if(number == 0){  
	        list.add(stack.toString());
	        return ;  
	       }  
	       if(begin == strs.length){  
	        return;  
	       }  
	       stack.push(strs[begin]);  
	       combine(strs, begin + 1, number - 1, stack,list);  
	       stack.pop();  
	       combine(strs, begin + 1, number, stack,list);  
	}  

}
