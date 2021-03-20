package com.condominio.acesso;

import com.condominio.acesso.controller.ApartmentController;
import com.condominio.acesso.controller.AuthController;
import com.condominio.acesso.controller.AuthInfo;
import com.condominio.acesso.controller.UserController;
import com.condominio.acesso.dto.*;
import com.condominio.acesso.entities.ApplicationUser;
import com.condominio.acesso.exceptions.IncorrectPassword;
import com.condominio.acesso.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScacApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private AuthController authController;

	@Autowired
	private ApartmentController apartmentController;

	@BeforeTestClass
	void prepare(){
		userController.createUser(new UserDTO(
				null,
				"Andŕe",
				"10362645051",
				"11976509163",
				"andre.montero702@gmail.com",
				"ADMIN"
		));
	}

	@Test
	@DisplayName("Registrar usuário, realizar primeiro acesso e fazer login")
	void test_login() {
		UserDTO user = userController.createUser(new UserDTO(null, "Bianca", "37270190003", "11954645124", "bianca@gmail.com", "ADMIN"));
		LoginInfoDTO loginInfo = new LoginInfoDTO("bianca@gmail.com", "123456");
		authController.doFirstAccess(loginInfo);
		AuthInfo authInfo = authController.doLogin(loginInfo);
		assertNotNull(authInfo);
	}

	@Test
	@DisplayName("Registrar usuário, realizar primeiro acesso e fazer login com senha errada")
	void test_wrong_password() {
		UserDTO user = userController.createUser(new UserDTO(null, "José", "28090170005", "11954645124", "jose@gmail.com", "ADMIN"));
		LoginInfoDTO loginInfo = new LoginInfoDTO("jose@gmail.com", "123456");
		authController.doFirstAccess(loginInfo);
		assertThrows(IncorrectPassword.class,()-> {
			AuthInfo authInfo = authController.doLogin(new LoginInfoDTO("jose@gmail.com","123457"));
		});
	}

	@Test
	@DisplayName("Registrar apartamento com usuários e realizar consulta")
	void test_apartment_registry(){
		UserDTO alison = userController.createUser(new UserDTO(null, "Alison", "29657389089", "11954645124", "alison@gmail.com", "RESIDENT"));
		UserDTO joana = userController.createUser(new UserDTO(null, "Joana", "29657389089", "11954645124", "joana@gmail.com", "RESIDENT"));
		ApartmentDTO apartment = apartmentController.saveApartment(new ApartmentSaveDTO("A215", List.of(alison.id, joana.id)));
		List<UserApartmentDTO> residentsByCpf = userController.findResidentsByCpf("29657389089");
		assertTrue(residentsByCpf.size() > 0);
		assertNotNull(apartment);
	}
}
