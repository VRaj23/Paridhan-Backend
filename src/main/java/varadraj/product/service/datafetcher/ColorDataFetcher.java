package varadraj.product.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import varadraj.product.model.Color;
import varadraj.product.repository.ColorRepository;

@Component
public class ColorDataFetcher implements DataFetcher<Color>{

	@Autowired
	private ColorRepository colorRepo;
	
	@Override
	public Color get(DataFetchingEnvironment environment) {
		int id = environment.getArgument("id");
		return colorRepo.findByColorID(new Long(id));
	}

}
