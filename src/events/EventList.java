package events;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A handy subclass of ArrayList for events, with a filter method
 * 
 * @author Nicholas Malcolm - malcolnich - 300170288
 *
 * @param <T>
 */
public class EventList<T extends Event> extends ArrayList<T>{
	
	private static final long serialVersionUID = 1L;

	
	private ArrayList<T> list = new ArrayList<T>();
	
	private boolean dirty = false;

	/**
	 * Returns a subset of this list which are only an instance
	 * of the class specified.
	 * @param klass - A Class object
	 * @return all the Events of that Class type in this EventList
	 */
	public EventList<T> showOnly(Class klass){
		
		EventList<T> newList = new EventList<T>();
		
		for(Event e : list){
			if( e.getClass().isInstance(klass)){
				newList.add((T) e);
			}
		}
		
		return newList;
	}
	
	public ArrayList<T> getList(){
		return list;
	}
	
	public boolean add(T event){
		dirty = true;
		return list.add(event);
	}
	
	public T get(int index){
		if(dirty){
			Collections.sort(list);
			dirty = false;
		}
		return list.get(index);
	}
	
	public boolean contains(Object o){
		return list.contains(o);
	}
	
	public int getSize(){
		return list.size();
	}
	
}
