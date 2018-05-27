package varadraj.product.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import varadraj.product.model.Color;
import varadraj.product.repository.ColorRepository;

@Component
public class AllColorDataFetcher implements DataFetcher<List<Color>>{
	
	@Autowired
	private ColorRepository colorRepo;

	@Override
	public List<Color> get(DataFetchingEnvironment environment) {
		return colorRepo.findAll();
	}

}
