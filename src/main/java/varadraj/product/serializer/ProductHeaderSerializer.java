package varadraj.product.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import varadraj.product.model.ProductHeader;

public class ProductHeaderSerializer extends JsonSerializer<ProductHeader>{

	@Override
	public void serialize(ProductHeader header, JsonGenerator jsonGen, 
			SerializerProvider serializers) throws IOException {
		jsonGen.writeStartObject();
		jsonGen.writeNumberField("headerID",header.getHeader_id());
		jsonGen.writeNumberField("price", header.getPrice());
		jsonGen.writeStringField("brand", header.getBrand().getName());
		jsonGen.writeNumberField("imageID", header.getPrimaryImage().getImageID());
		jsonGen.writeEndObject();
		
	}

}
