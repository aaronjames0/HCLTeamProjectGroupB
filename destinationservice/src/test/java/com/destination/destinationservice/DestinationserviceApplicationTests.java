package com.destination.destinationservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import com.destination.destinationservice.Entity.Destination;
import com.destination.destinationservice.Repository.DestinationRepo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class DestinationserviceApplicationTests {

	@Autowired
	DestinationRepo dRepo;

	@Test
	@Order(1)
	public void testCreate() {
		Destination dest = new Destination();
		dest.setPlace("Los Angeles");
		dest.setCountry("United States");
		dest.setLatitude(34.0522);
		dest.setLongitude(118.2437);
		dest.setInfo("Hollywood is located here");
		dest.setImage("LosAngeles.jpeg");
		dRepo.save(dest);
		assertNotNull(dRepo.findById(28L).get());
	}

	@Test
	@Order(2)
	public void testDestination() {
		Destination dest = dRepo.findById(28L).get();
		assertEquals("Los Angeles", dest.getPlace());
	}

	@Test
	@Order(3)
	public void testReadAll() {
		List dests = dRepo.findAll();
		assertThat(dests).size().isGreaterThan(0);
	}

	@Test
	@Order(4)
	public void testUpdate() {
		Destination dest = dRepo.findById(28L).get();
		dest.setImage("LA.png");
		dRepo.save(dest);
		assertNotEquals("Los Angeles", dRepo.findById(28L).get().getImage());
	}

	@Test
	@Order(5)
	public void testDelete() {
		dRepo.deleteById(28L);
		assertThat(dRepo.existsById(28L)).isFalse();
	}

}
