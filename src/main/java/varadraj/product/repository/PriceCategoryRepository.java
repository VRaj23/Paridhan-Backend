package varadraj.product.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.product.model.PriceCategory;

public interface PriceCategoryRepository extends CrudRepository<PriceCategory, Long>{
	
	public PriceCategory findByUpperLimit(int upperLimit);

}
