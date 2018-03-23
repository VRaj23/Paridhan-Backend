package varadraj.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.model.PriceCategory;

public interface PriceCategoryRepository extends CrudRepository<PriceCategory, Long>{
	
	public PriceCategory findByUpperLimit(int upperLimit);

}
