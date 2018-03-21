package varadraj.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import varadraj.model.ProductHeader;
import varadraj.model.ProductLine;

public interface ProductLineRepository extends CrudRepository<ProductLine, Long>{
	
	public List<ProductLine> findByProductHeader(ProductHeader header);

}
