package varadraj.product.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.product.model.Brand;

public interface BrandRepository extends CrudRepository<Brand, Long>{
	
	public Brand findByName(String name);

}
