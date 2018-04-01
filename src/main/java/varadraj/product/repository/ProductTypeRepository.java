package varadraj.product.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.product.model.ProductType;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long>{
	
	public ProductType findByDescription(String description);
	public ProductType findByTypeID(long typeID);

}
