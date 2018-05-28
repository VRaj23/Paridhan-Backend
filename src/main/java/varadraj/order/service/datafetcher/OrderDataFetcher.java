package varadraj.order.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import varadraj.order.model.Orders;

public class OrderDataFetcher implements DataFetcher<Orders>{

	@Override
	public Orders get(DataFetchingEnvironment environment) {
		int orderID = environment.getArgument("id");
		return null;
	}

}
