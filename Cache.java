package p1;
/**
 * Class Cache
 * Author: Osama Natouf
 * V1.0
 * 
 * This is class represent an implementation for the actual cache that we have inside the computers
 * 
 */

import java.util.LinkedList;
public class Cache<T>  {
	LinkedList<T>list=new LinkedList<T>();
	LinkedList<T>list2=new LinkedList<T>();
	double NH1=0; // number of	hits in level 1
	double NH2=0; // number of hits in level 2
	double NR1=0; // number of reference for level 1
	double NR2=0; // number of reference for level 2
	double miss=0; // number of misses for cache 1
	int size1=0,size=0;
	boolean dlayer=false;
	/**
	 * This is Cache Constructor for one level Cahce
	 * @param size: the size of the Cache
	 * @throws Exception: Through exception if the size less than zero
	 */
	public Cache(int size) throws Exception {
		this.size=size;
		if( size<=0) {
			throw new Exception("The size of the first cache should be smaller than the second");
		}
		for(int i=0; i<size;i++) {
			list.add(null);
		}
		
	}
	/**
	 * Overloaded Constructor for the Cache Class
	 * @param size1: the size of the first level Cache
	 * @param size2: the size of the second level Cache
	 * @throws Exception: the size of the cache is less than zero 
	 *  or if the size of the first cache is larger than the second Cache
	 */
	public Cache(int size1, int size2) throws Exception {
		this.size=size1;
		this.size1=size2;
		if(size2<=size1|| size1<=0) {
			throw new Exception("The size of the first cache should be smaller than the second");
		}
	
		for(int i=0; i<size1;i++) {
			list.add(null);
		}
		for(int i=0; i<size2;i++) {
			list2.add(null);
		}
		dlayer=true;
	}
	/**
	 * 	This method apply the Cache Logic to reading elements from files
	 * 
	 * 	F1(): The function take the element serach the 1st level Cache increase the number of hits
	 * 	if the element found, and the times of reference each time we search the 1st level Cache
	 *	Then moves the element to the front of the Cache.
	 * 	if the element does not exist in the Caceh we delete the last element and then add the new
	 *  element to the fornt of the Cache.
	 *
	 *	In Case Of 2 level Cache the function would perform the F1() steps then on Cach2.
	 * 	Then,move the element to the front of the Second level Cache.
	 *	if the element does not exist in either of the cache, we delete the last element in both of
	 *	the cache, and then add that to the front. 
	 * 
	 * @param ele: the element that we need to add the method
	 * 
	 */
	void addObj(T ele) {
		//search in first layer
		NR1++;
		if(list.remove(ele)) {
			NH1++;
			list.addFirst(ele);
			if(dlayer) {
				list2.remove(ele);
				list2.addFirst(ele);
			}
			
			return ;
			
		}
		
		miss++;
		// search in second layer if the consturctor2 is called.
		// if the element was not found in the first level Cache
		if(dlayer) {
			if(list2.remove(ele)) {
				NH2++;
				list2.addFirst(ele);
				list.removeLast();
				list.addFirst(ele);
			}else {
			
				list.removeLast();
				list.addFirst(ele);
				list2.removeLast();
				list2.addFirst(ele);				
				
			}
			return ;
		}
		list.addFirst(ele);
		
	}// end of the add method
	/**
	 * remove the object from the Caches if it was two or one level Cache
	 * @param ele: the element that we want to remove
	 * @return true if we found the element an d remove that or false otherwise.
	 */
	boolean removeObj(T ele) {
		//first then secon
		NR1++;
		if(list.remove(ele)) {
			NH1++;
			if(dlayer) {
				
				list2.remove(ele);
				list2.addLast(null);
			}
			list.addLast(null);
			return true;
		}
		miss++;
		if(dlayer) {
			if(list2.remove(ele)) {
				
				list2.addLast(null);
				return true;
			}
			
			
			
		}
		return false;
	}
	/**
	 * The Method returns the Value of the object at the specified Index
	 *  
	 * @param index: of object in the Cache
	 * @return : the value stored at the specified index
	 * @throws Exception: through an exception if the index<0 || index>= size 
	 */
	T getObj(int index) throws Exception {
		T ele;
		boolean cond=true;
		if(!dlayer) {
				if(index>=list.size()||index<0) {
				throw new IndexOutOfBoundsException("The index is greater than the size of cache");
			}
			ele=list.get(index);
		}else {
			
			if((index>=list.size()||index<0)&&(index>=list2.size()||index<0)) {
				throw new IndexOutOfBoundsException("The index is greater than the size of cache");
			}
			ele=list2.get(index);
		}
	
		return ele;
		
	}
	/**
	 * delete at the elements in the Cache and give it the default value. 
	 */
	
	void clearCache() {
		//first then second.
		if(dlayer) {
			list.clear();
			list2.clear();
			for(int i=0; i<this.size;i++) {
				list.add(null);
				}
			for(int i=0; i<size1;i++) {
				list2.add(null);
				}
			}else {
				list.clear();
				for(int i=0; i<this.size;i++) {
					list.add(null);
					}
				}
		}
	
	/**
	 * This method to print the information of the Cache in at the startin of the program
	 */
	public void printinfo() {
		String str="";
		if(dlayer) {
			str=	"First level Cache with "+size+" has been created\n"+
					"Second level Cache with "+size1+" has been created ";
			
		}else {
			
			str=	"First level Cache with "+size+" has been created";
					
		}
		System.out.println(str);
	}
	/**
	 * This method used to print the information gathered from the Cache
	 */
	public void PrintData() {
		
		String str="";
		if(dlayer) {
			str=	
					"The number of global references: "+ (int) NR1+"\n"+
					"The number of global cache hits: " + (int)(NH1+NH2) +"\n"+
					"The global hit ratio:" +((NH1+NH2)/NR1)+"\n"+
					"\n"+
					"The number of 1st-level references: " + (int)NR1+"\n"+ 
					"The number of 1st-level cache hits: " + (int)NH1+"\n"+ 
					"The 1st-level cache hit ratio: " + (NH1/NR1)+"\n"+ 
					"\n" + 
					"The number of 2nd-level references: " + (int)miss +"\n" +
					"The number of 2nd-level cache hits: " +  (int)NH2 +"\n"+
					"The 2nd-level cache hit ratio: " +(NH2/miss) +"\n" ;
					
		}else {
			str=	
					"The number of global references: "+ (int) NR1+"\n"
					+"The number of global cache hits: " + (int)(NH1+NH2) +"\n"+
					"The global hit ratio:" +((NH1+NH2)/NR1)+"\n"+
					"\n"+
					"The number of 1st-level references: " + (int)NR1+"\n"+ 
					"The number of 1st-level cache hits: " + (int)NH1+"\n"+ 
					"The 1st-level cache hit ratio: " + (NH1/NR1)+"\n"+ 
						"\n";
			
		}
		
		System.out.println(str);
	}
	
}