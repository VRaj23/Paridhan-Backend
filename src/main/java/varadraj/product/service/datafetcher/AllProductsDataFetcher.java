package varadraj.product.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import varadraj.product.model.ProductModel;
import varadraj.product.service.ProductService;

@Component
public class AllProductsDataFetcher implements DataFetcher<List<ProductModel>>{

	@Autowired
	private ProductService productService;
	
	@Override
	public List<ProductModel> get(DataFetchingEnvironment environment) {
		return this.productService.getAllProducts();
	}

}
