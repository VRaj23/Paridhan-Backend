package varadraj.order.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import varadraj.order.model.OrderResponse;
import varadraj.order.service.OrderService;

@Component
public class OrderDataFetcher implements DataFetcher<OrderResponse>{

	@Autowired
	private OrderService orderService;
	
	@Override
	public OrderResponse get(DataFetchingEnvironment environment) {
		int orderID = environment.getArgument("orderID");
		return this.orderService.getOrderAdmin(new Long(orderID));
	}

}
