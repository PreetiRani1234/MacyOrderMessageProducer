package com.macy.producer.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.macy.producer.component.ProducerComponent;
import com.macy.producer.dto.json.ProducerDto;
import com.macy.producer.dto.xml.FulfillmentOrder;
import com.macy.producer.entity.ProducerEntity;
import com.macy.producer.repository.ProducerRepository;

@Service
public class ProduceServiceImplementation implements ProducerServiceInterface {

	@Autowired
	ProducerComponent producerComponent;

	@Override
	public ResponseEntity<Boolean> produceXmlMessage(FulfillmentOrder fulfillmentOrder) {
		try {
			producerComponent.send(fulfillmentOrder);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Boolean> produceJsonMessage(ProducerDto producerDto) {
		try {
			producerComponent.send(producerDto);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
