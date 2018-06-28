package varadraj.order.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import varadraj.order.model.OrderResponse;
import varadraj.order.service.OrderService;

@Component
public class AllOrderDataFetcher implements DataFetcher<List<OrderResponse>>{

	@Autowired
	private OrderService orderService;
	
	@Override
	public List<OrderResponse> get(DataFetchingEnvironment environment) {
		return orderService.getAllOrdersAdmin();
	}

}
