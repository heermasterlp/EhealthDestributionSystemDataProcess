package myApp;

import java.util.ArrayList;
import java.util.List;

public class UnionMath {
	
	
	/**
     *  判断是否同时出现所有的中药
     * @param e
     * @param names
     * @return
     */
    public static boolean hasThisMedicine(String[] e,String[] names){
          
           boolean hasMedicine = true;
           boolean flag = false;
           int length = e.length;
           for(String s : names){
                 flag = false;
                 for( int i = 0; i < length; i++){
                       if( s.equals( e[i])){
                             flag = true; // 同时出现则为true = 只要有一个不出现就为false
                      }
                       if(! flag && i == length - 1){
                             flag = false;
                      }
                }
                 hasMedicine = hasMedicine && flag;
          }
          
           return hasMedicine;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] a = {"a","b","c","d","e","f"};
		String[] b = {"f","c"};
		System.out.println(hasThisMedicine(a, b));
		
	}

}
