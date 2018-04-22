package varadraj.order.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderHeader {
	
	private long orderID;
	private long customerID;
	private String statusID;
	private double amount;
	private ZonedDateTime creationDateTime;
	private ZonedDateTime lastUpateDateTime;
	private List<OrderLine> orderLine = new ArrayList<>();

}
