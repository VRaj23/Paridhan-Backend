package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.ResponseMessage;
import varadraj.product.model.Size;
import varadraj.product.service.SizeService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/product/size")
public class ProductAdminSize {

	@Autowired
	private SizeService sizeService;
	
	@PostMapping("/add")
	public JsonResponse<Void> addSize(@RequestBody Size size) {
		if(size.getSizeCharacter() == null || size.getSizeNumber() == 0)
			return new JsonResponse<Void>(400
					, ResponseMessage.INVALID_INPUT
					, null);

		sizeService.addSize(size);
		return new JsonResponse<Void>(201
				, ResponseMessage.CREATED
				, null);
	}
}
