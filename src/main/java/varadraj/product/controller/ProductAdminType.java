package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.product.model.ProductType;
import varadraj.product.service.ProductTypeService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/product/type")
public class ProductAdminType {
	
	@Autowired
	private ProductTypeService ptService;
	
	@PostMapping("/add")
	public JsonResponse<Void> addType(@RequestBody ProductType productType) {
		ptService.addProductType(productType);
		return new JsonResponse<Void>(201
				, JsonResponseMessage.CREATED
				, null);
	}
	
	@PutMapping("/update")
	public JsonResponse<Void> updateType(@RequestBody ProductType newProductType) {
		if(newProductType.getTypeID() == 0)
			return new JsonResponse<Void>(400
					,JsonResponseMessage.INVALID_INPUT
					,null);
		
		ProductType type = ptService.findByTypeID(newProductType.getTypeID());
		if(type == null)
			return new JsonResponse<Void>(404
					,JsonResponseMessage.NOT_FOUND
					,null);
		
		ptService.updateProductType(type,newProductType);
		return new JsonResponse<Void>(200, JsonResponseMessage.CREATED ,null);
	}
	
	@DeleteMapping("/{productTypeID}")
	public JsonResponse<Void> deleteType(@PathVariable long productTypeID) {
		
		ProductType type = ptService.findByTypeID(productTypeID);
		if(type == null)
			return new JsonResponse<Void>(404
					, JsonResponseMessage.NOT_FOUND
					, null);
		
		ptService.deleteProductType(productTypeID);
		return new JsonResponse<Void>(200
				,JsonResponseMessage.CREATED
				,null);
	}

}
