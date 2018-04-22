package varadraj.order.model;

import varadraj.product.model.ProductLine;

public class OrderLine {

	private long orderLineID;
	private OrderHeader orderHeader;
	private ProductLine item;
	private int quantity = 1;
}
