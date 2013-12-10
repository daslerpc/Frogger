package utility;

public class OrderedPair {
    private Float first; //first member of pair
    private Float second; //second member of pair

    public OrderedPair() {
    	first = new Float(0f);
    	second = new Float(0f);
    }
    
    public OrderedPair(Float first, Float second) {
        this.first = first;
        this.second = second;
    }

    public OrderedPair(OrderedPair pair) {
		first = pair.getFirst();
		second = pair.getSecond();
	}

	public void setFirst(Float first) {
        this.first = first;
    }

    public void setSecond(Float second) {
        this.second = second;
    }

    public Float getFirst() {
        return first;
    }

    public Float getSecond() {
        return second;
    }
}
