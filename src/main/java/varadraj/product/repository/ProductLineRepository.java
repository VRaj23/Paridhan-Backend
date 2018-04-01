package varadraj.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import varadraj.product.model.ProductHeader;
import varadraj.product.model.ProductLine;

public interface ProductLineRepository extends CrudRepository<ProductLine, Long>{
	
	public List<ProductLine> findByProductHeader(ProductHeader productHeader);
	
}
