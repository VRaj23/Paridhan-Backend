package varadraj.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import varadraj.product.model.ProductHeader;
import varadraj.product.model.ProductType;

public interface ProductHeaderRepository extends CrudRepository<ProductHeader, Long>{

	public List<ProductHeader> findByProductType(ProductType type);
	
	

}
