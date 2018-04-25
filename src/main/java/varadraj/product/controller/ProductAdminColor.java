package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.product.model.Color;
import varadraj.product.service.ColorService;

@RestController
@RequestMapping("product/admin/color")
public class ProductAdminColor {

	@Autowired
	private ColorService colorService;
	
	@PostMapping("/addColor")
	public JsonResponse addColor(@RequestBody Color color) {
		
		if(color.getValue() == null || color.getValue().length() != 6)
			return new JsonResponse(400, "Invalid Color Hex Value provided. Color creation Failed");
		
		if(colorService.findByColorValue(color.getValue()) != null)
			return new JsonResponse(400, "Color already exists.");
		
		colorService.addColor(color);
		return new JsonResponse(201, "Color Added");
	}
	
	@DeleteMapping("/deleteColor/{colorID}")
	public JsonResponse deleteColor(@PathVariable long colorID) {
		Color color = colorService.findByColorID(colorID);
		if( color == null)
			return new JsonResponse(400,"Invalid ColorID");
		
		colorService.deleteColor(color);
		return new JsonResponse(200,"Color deleted");
	}
}
