package com.mock.exotourista;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.exotourista.Controller.HotelController;
import com.mock.exotourista.Model.Hotel;
import com.mock.exotourista.Repository.HotelRepo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class ExotouristaApplicationTests {

	private MockMvc mvc;
	@Mock
	private HotelRepo hotelRepository;

	@InjectMocks
	private HotelController hotelController;

	private JacksonTester<Hotel> jsonHotel;

	private JacksonTester<Collection<Hotel>> jsonHotels;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(hotelController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void canCreateANewHotel() throws Exception {
		Hotel hotel = new Hotel(1L, "abc", "abc", "abc", "abc", "khi", "abc", true, 1000);
    //    when(bookRepository.save(book)).thenReturn((book));
		mvc.perform(post("/hotels/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonHotel.write(hotel).getJson()))
				.andExpect(status().isOk());

	}

	@Test
	public void canGetAllHotels() throws Exception {
		Hotel hotel1 = new Hotel(1L, "abc", "abc", "abc", "abc", "khi", "abc", true, 1000);
		Hotel hotel2 = new Hotel(1L, "abc", "abc", "abc", "abc", "khi", "abc", true, 2000);

       
		List<Hotel> hotelList = new ArrayList<>();
		hotelList.add(hotel1);
		hotelList.add(hotel2);
		when(hotelRepository.findAll()).thenReturn(hotelList);
		mvc.perform(get("/hotels/getall")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonHotels.write(hotelList).getJson()));
	}

	@Test
	public void canGetAHotel() throws Exception {
		Hotel hotel1 = new Hotel(1L, "abc", "abc", "abc", "abc", "khi", "abc", true, 1000);
		when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel1));
		mvc.perform(MockMvcRequestBuilders.get("/hotels/add/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonHotel.write(hotel1).getJson()));
	}
	}

