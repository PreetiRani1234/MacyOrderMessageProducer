package com.macy.producer.controller;

import java.io.InputStream;

import javax.print.attribute.standard.Media;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.macy.producer.dto.json.ProducerDto;
import com.macy.producer.dto.xml.FulfillmentOrder;
import com.macy.producer.service.ProducerServiceInterface;

@RestController
@RequestMapping("/macy/producer")
public class ProducerController {

	@Autowired
	ProducerServiceInterface producerServiceInterface;
	
    @PostMapping(value = "/xml",
            consumes = {MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<Boolean> produceXmlMessage(@RequestBody FulfillmentOrder fulfillmentOrder) {
        return producerServiceInterface.produceXmlMessage(fulfillmentOrder);
    }

    @PostMapping(value = "/json",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Boolean> produceJsonMessage(@RequestBody ProducerDto orderMessageJson) {
        return producerServiceInterface.produceJsonMessage(orderMessageJson);
    }

	
	void getLocalXml() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream soapXML = classLoader.getResourceAsStream("static/test.xml");
        XMLInputFactory f = XMLInputFactory.newFactory();
        XMLStreamReader sr = null;
        try {
            sr = f.createXMLStreamReader(soapXML);
            XmlMapper mapper = new XmlMapper();
            FulfillmentOrder fulfillmentOrder = mapper.readValue(sr, FulfillmentOrder.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



