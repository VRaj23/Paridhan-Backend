package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.product.model.ProductType;
import varadraj.product.service.ProductTypeService;

@RestController
@RequestMapping("/product/admin/type")
public class ProductAdminType {
	
	@Autowired
	private ProductTypeService ptService;
	
	@PostMapping("/addProductType")
	public JsonResponse addType(@RequestBody ProductType productType) {
		ptService.addProductType(productType);
		return new JsonResponse(201,"New Product Type Created");
	}
	
	@PutMapping("/updateProductType")
	public JsonResponse updateType(@RequestBody ProductType newProductType) {
		if(newProductType.getTypeID() == 0)
			return new JsonResponse(400,"No ProductType ID provided in request. "
					+ "Valid ProductType ID required for update");
		
		ProductType type = ptService.findByTypeID(newProductType.getTypeID());
		if(type == null)
			return new JsonResponse(404,"Invalid ProductType ID. Update Failed");
		
		ptService.updateProductType(type,newProductType);
		return new JsonResponse(200, "ProductType Updated");
	}
	
	@DeleteMapping("/deleteProductType/{productTypeID}")
	public JsonResponse deleteType(@PathVariable long productTypeID) {
		
		ProductType type = ptService.findByTypeID(productTypeID);
		if(type == null)
			return new JsonResponse(404,"Invalid ProductType ID. DELETE failed");
		
		ptService.deleteProductType(productTypeID);
		return new JsonResponse(200,"ProductType Deleted");
	}

}
