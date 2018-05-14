package varadraj.product.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import varadraj.product.model.ProductLine;

public class ProductLineSerializer extends JsonSerializer<ProductLine>{

	@Override
	public void serialize(ProductLine line, JsonGenerator jsonGen, 
			SerializerProvider serializers) throws IOException {
		jsonGen.writeStartObject();
		jsonGen.writeNumberField("lineID", line.getLineID());
		jsonGen.writeStringField("color", line.getColor().getValue());
		jsonGen.writeNumberField("sizeNum", line.getSize().getSizeNumber());
		jsonGen.writeStringField("sizeChar", line.getSize().getSizeCharacter());
		jsonGen.writeStringField("productName", 
				line.getProductHeader().getBrand().getName()+" "
				+line.getProductHeader().getProductType().getDescription()+" "
				+line.getSize().getSizeCharacter()+" "+line.getColor().getName());
		
		jsonGen.writeEndObject();
		
	}

}
