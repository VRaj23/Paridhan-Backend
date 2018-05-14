package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.product.model.Color;
import varadraj.product.service.ColorService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/product/color")
public class ProductAdminColor {

	@Autowired
	private ColorService colorService;
	
	@PostMapping("/addColor")
	public JsonResponse<Void> addColor(@RequestBody Color color) {
		
		if(color.getValue() == null || color.getValue().length() != 6)
			return new JsonResponse<Void>(400
					, JsonResponseMessage.INVALID_INPUT
					, null);
		
		if(colorService.findByColorValue(color.getValue()) != null)
			return new JsonResponse<Void>(409
					, JsonResponseMessage.ALREADY_EXISTS
					, null);
		
		colorService.addColor(color);
		return new JsonResponse<Void>(201
				, JsonResponseMessage.CREATED
				, null);
	}
	
	@DeleteMapping("/deleteColor/{colorID}")
	public JsonResponse<Void> deleteColor(@PathVariable long colorID) {
		Color color = colorService.findByColorID(colorID);
		if( color == null)
			return new JsonResponse<Void>(400
					, JsonResponseMessage.INVALID_INPUT
					, null);
		
		colorService.deleteColor(color);
		return new JsonResponse<Void>(200
				, JsonResponseMessage.OK
				, null);
	}
}
