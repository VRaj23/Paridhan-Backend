package varadraj.order.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import varadraj.order.model.OrderResponseAdmin;
import varadraj.order.service.OrderService;

@Component
public class AllOrderDataFetcher implements DataFetcher<List<OrderResponseAdmin>>{

	@Autowired
	private OrderService orderService;
	
	@Override
	public List<OrderResponseAdmin> get(DataFetchingEnvironment environment) {
		return orderService.getAllOrdersAdmin();
	}

}
