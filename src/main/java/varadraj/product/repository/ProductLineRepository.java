package varadraj.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import varadraj.product.model.ProductHeader;
import varadraj.product.model.ProductLine;

public interface ProductLineRepository extends CrudRepository<ProductLine, Long>{
	/*
	@Query(
		value = "SELECT pl.* FROM product_line pl WHERE pl.header_id =:headerID",
		nativeQuery = true
		)
	public List<ProductLine> findByHeaderID(@Param("headerID")long headerID);
	*/
	
	public List<ProductLine> findByProductHeader(ProductHeader productHeader);

}
