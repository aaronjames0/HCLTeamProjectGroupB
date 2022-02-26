package com.example.Recommendmicroservice;

import com.example.Recommendmicroservice.Entity.Recommend;
import com.example.Recommendmicroservice.Repository.RecommendRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecommendMicroserviceApplicationTests {

	@Autowired
	RecommendRepo repo;



static Recommend recommend;
static Long Id;

	@BeforeAll
	  static void init() {
		recommend = new Recommend(81L, 44L, "Akhil", "Salem is not known for mangoes", 5);

		 Id = recommend.getRecommendationId();
	}
	@Test
	@Order(1)
	 void testCreate()
	{
		//Recommend recommend = new Recommend(81L, 41L, "Akhil", "Salem is not known for mangoes", 5);
		repo.save(recommend);
		System.out.println(recommend);
		assertNotNull(repo.findById(Id));
	}

	@Test
	@Order(2)
	public void getAll()
	{

		List<Recommend> list = repo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}


	@Test
	@Order(3)
	public void testGetRecommend()
	{
	 Recommend recommendGet = repo.findById(Id).get();
		 System.out.println(recommendGet);
		assertEquals(81L,recommendGet.getDestId());
	}

	@Test
	@Order(4)
	public void testUpdate()
	{
		Recommend r = repo.findById(Id).get();
		r.setAuthor("Aaron");
		repo.save(r);
		System.out.println(r);
		System.out.println(recommend);
		System.out.println(repo.findById(Id).get().getAuthor());
		assertEquals("Aaron",repo.findById(Id).get().getAuthor());
	}

	@Test
	@Order(5)
	public void testDelete()
	{
		repo.deleteById(Id);
		assertThat(repo.existsById(Id)).isFalse();
		//System.out.println(repo.findById(Id));
	}


}
