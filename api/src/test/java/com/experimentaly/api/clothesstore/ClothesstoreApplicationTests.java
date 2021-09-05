package com.experimentaly.api.clothesstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.FileInputStream;
import javax.validation.ValidationException;
import com.experimentaly.api.clothesstore.application.rest.request.ProductRequestSave;
import com.experimentaly.api.clothesstore.core.model.ProductModel;
import com.experimentaly.api.clothesstore.core.ports.input.ProductMapper;
import com.experimentaly.api.clothesstore.infrastructure.configuration.DataInitializer;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.base.Popularity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ProductEntity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository.CountryRepository;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository.ProductRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = ClothesstoreApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
class ClothesstoreApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(ClothesstoreApplicationTests.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int port;


	@Autowired
	private DataInitializer dataInitializer;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private CountryRepository countryRepository;

	static ProductEntity product;

	@Test
	void contextLoads() {
		logger.info("context");

		assertTrue(true);
	}

	private void populate() {
		try {
			dataInitializer.run("populate");
		} catch (Exception e) {
			logger.error("error while execute data initializer", e);
		}
	}

	@Test
	@Order(1)
	void saveWithUnknowCountry() {
		populate();
		var requestModel = createRequestSave();

		requestModel.setSelledCountry("VENEZUELA");


		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("files", requestModel.getFiles());
		map.add("name", requestModel.getName());
		map.add("description", requestModel.getDescription());
		map.add("price", requestModel.getPrice());
		map.add("discount", requestModel.getDiscount());
		map.add("selledCountry", requestModel.getSelledCountry());

		var headers = new HttpHeaders();

		assertNull(requestModel.getId());
		var request = new HttpEntity<>(map, headers);

		var response = restTemplate.postForEntity(createURLWithPortV1("products"), request,
				ProductModel.class);


		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}


	@Test
	@Order(2)
	void saveWithAllInfoCorrectly() {

		var requestModel = createRequestSave();

		requestModel.setSelledCountry("COLOMBIA");
		requestModel.setDiscount(13);


		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("files", requestModel.getFiles());
		map.add("name", requestModel.getName());
		map.add("description", requestModel.getDescription());
		map.add("price", requestModel.getPrice());
		map.add("discount", requestModel.getDiscount());
		map.add("selledCountry", requestModel.getSelledCountry());

		var headers = new HttpHeaders();

		assertNull(requestModel.getId());
		var request = new HttpEntity<>(map, headers);

		var response = restTemplate.postForEntity(createURLWithPortV1("products"), request,
				ProductModel.class);


		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}


	@Test
	@Order(4)
	void updateWithOutId() {

		var requestModel = createRequestSave();

		requestModel.setSelledCountry("COLOMBIA");
		requestModel.setDiscount(13);
		var model = productMapper.convert(requestModel);
		var entity = productMapper.convertEntity(model);
		var country = this.countryRepository.findByNameAndDeletedAtIsNull("COLOMBIA")
				.orElseThrow(() -> new ValidationException());
		entity.setCountry(country);
		entity = productRepository.save(entity);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("files", requestModel.getFiles());
		map.add("name", requestModel.getName());
		map.add("description", requestModel.getDescription());
		map.add("price", requestModel.getPrice());
		map.add("discount", requestModel.getDiscount());
		map.add("selledCountry", requestModel.getSelledCountry());
		map.add("id", entity.getId());

		var headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		assertNull(requestModel.getId());
		var request = new HttpEntity<>(map, headers);

		var response = restTemplate.exchange(createURLWithPortV1("products"), HttpMethod.PUT,
				request, ProductModel.class);


		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@Order(5)
	void listWithQueryParams() {


		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(createURLWithPortV1("products")).queryParam("name", "product 1")
				.queryParam("popularity", Popularity.HIGHEST).queryParam("lessDiscount", 20)
				.queryParam("graterDiscount", 10).queryParam("lessPrice", 10000)
				.queryParam("graterPrice", 50000).queryParam("deleted", false)
				.queryParam("country", "COLOMBIA").queryParam("page", 0).queryParam("size", 100);


		var request = new HttpEntity<>(headers);

		var response =
				restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, Object.class);


		assertEquals(HttpStatus.OK, response.getStatusCode());
	}



	private void createEntityWithPopularity(Popularity popularity) {
		var requestModel = createRequestSave();

		requestModel.setSelledCountry("COLOMBIA");
		requestModel.setDiscount(13);
		var model = productMapper.convert(requestModel);
		var entity = productMapper.convertEntity(model);
		var country = this.countryRepository.findByNameAndDeletedAtIsNull("COLOMBIA")
				.orElseThrow(() -> new ValidationException());
		entity.setCountry(country);
		entity.setPopularity(popularity);
		productRepository.save(entity);

	}


	private ProductRequestSave createRequestSave() {

		var request = new ProductRequestSave();
		var price = Math.floor(Math.random() * (1000000 - 1000 + 1) + 1000);
		var percentage = Math.floor(Math.random() * 100);
		request.setName(generateRamdomName());
		request.setDescription(getDescription());
		request.setPrice(Float.parseFloat(price + ""));
		request.setDiscount(Float.parseFloat(percentage + ""));
		request.setSelledCountry(generateRandomCountry());

		return request;
	}

	private String generateRamdomName() {

		var posibleNames = new String[] {"Pera", "Computador", "Teclado", "Pagina", "llanta",
				"Celular", "Bafles", "cama", "Control", "Reloj", "Moto", "Retenedor",
				"Libro de jeugo de tronos", "libro de it", "cuarderno", "Pintura", "Cortina",
				"Vidrio", "Carne", "Mango"};
		var index = (int) Math.floor(Math.random() * 19);
		System.out.println(posibleNames[index]);
		return posibleNames[index];
	}

	private String generateRandomCountry() {
		var posibles = new String[] {"MEXICO", "COLOMBIA", "PERÚ", "CHILE"};

		var index = (int) Math.floor(Math.random() * 3);

		return posibles[index];
	}

	private String getDescription() {
		return " simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of ";
	}

	private MultipartFile[] getFile() {
		var multi = new MultipartFile[1];
		try {

			var file = ResourceUtils.getFile("classpath:img1.png");


			var input = new FileInputStream(file);
			multi[0] = new MockMultipartFile("file", file.getName(), "text/plain",
					IOUtils.toByteArray(input));
		} catch (Exception e) {
			logger.error("error getting file", e);
		}
		return multi;

	}

	private String createURLWithPortV1(String uri) {
		return "http://localhost:" + port + "/api/v1/" + uri;
	}

}
