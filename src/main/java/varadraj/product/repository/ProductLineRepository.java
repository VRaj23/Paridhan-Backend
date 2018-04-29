package varadraj.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import varadraj.product.model.ProductHeader;
import varadraj.product.model.ProductLine;

public interface ProductLineRepository extends CrudRepository<ProductLine, Long>{
	
	List<ProductLine> findByProductHeader(ProductHeader productHeader);
	
	ProductLine findByLineID(long productLineID);
	
	
}
