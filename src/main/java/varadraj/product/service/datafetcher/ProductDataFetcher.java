package varadraj.product.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import varadraj.product.model.ProductModel;
import varadraj.product.service.ProductService;

@Component
public class ProductDataFetcher implements DataFetcher<ProductModel>{

	@Autowired
	private ProductService productService;
	
	@Override
	public ProductModel get(DataFetchingEnvironment environment) {
		int headerID = environment.getArgument("headerID");
		return productService.getProductModel(new Long(headerID));
	}

}
