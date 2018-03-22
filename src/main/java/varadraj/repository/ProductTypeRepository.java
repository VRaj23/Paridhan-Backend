package varadraj.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.model.ProductType;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long>{
	
	public ProductType findByDescription(String description);

}
