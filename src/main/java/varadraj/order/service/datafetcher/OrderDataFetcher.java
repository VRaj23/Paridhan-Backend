package varadraj.order.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import varadraj.order.model.OrderResponseAdmin;
import varadraj.order.service.OrderService;

@Component
public class OrderDataFetcher implements DataFetcher<OrderResponseAdmin>{

	@Autowired
	private OrderService orderService;
	
	@Override
	public OrderResponseAdmin get(DataFetchingEnvironment environment) {
		int orderID = environment.getArgument("id");
		return this.orderService.getOrderAdmin(new Long(orderID));
	}

}
