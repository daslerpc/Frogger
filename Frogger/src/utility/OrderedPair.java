package utility;

public class OrderedPair<T> {
    private T first; //first member of pair
    private T second; //second member of pair

    public OrderedPair() {
    }
    
    public OrderedPair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public OrderedPair(OrderedPair<T> pair) {
		first = pair.getFirst();
		second = pair.getSecond();
	}

	public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }
}
