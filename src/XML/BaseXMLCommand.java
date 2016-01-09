package XML;

import Common.BaseItems.UserItem;

public class BaseXMLCommand<K, T, V> {
	
	  private final K key;
          private final T argument;
	  private final V value;
	 
	  public BaseXMLCommand(K k,T t, V v) {  
	    key = k;
            argument = t;
	    value = v;   
	  }
          
	  public K getKey() {
	    return key;
	  }

          public T getArgument() {
	    return argument;
	  }
          
	  public V getValue() {
	    return value;
	  }

          public String toString() { 
	    return "(" + key + ", " + value + ")";  
	  }
}
