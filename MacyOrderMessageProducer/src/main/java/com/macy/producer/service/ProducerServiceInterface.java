package com.macy.producer.service;

import org.springframework.http.ResponseEntity;

import com.macy.producer.dto.json.ProducerDto;
import com.macy.producer.dto.xml.FulfillmentOrder;

public interface ProducerServiceInterface {


    ResponseEntity<Boolean> produceXmlMessage(FulfillmentOrder fulfillmentOrder);

    ResponseEntity<Boolean> produceJsonMessage(ProducerDto orderMessageJson);

	
	

}
