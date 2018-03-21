package varadraj.model;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {
	
	private ProductHeader pHeader;
	private List<ProductLine> pLine = new ArrayList<>();
	
	
	
	public ProductModel(ProductHeader pHeader, List<ProductLine> pLine) {
		super();
		this.pHeader = pHeader;
		this.pLine = pLine;
	}
	
	public ProductModel() {
		
	}

	public ProductHeader getpHeader() {
		return pHeader;
	}
	public void setpHeader(ProductHeader pHeader) {
		this.pHeader = pHeader;
	}
	public List<ProductLine> getpLine() {
		return pLine;
	}
	public void setpLine(List<ProductLine> pLine) {
		this.pLine = pLine;
	}
	
	

}
