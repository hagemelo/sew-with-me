package br.com.jhage.sew_with_me.domain_service.interfaces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import br.com.jhage.sew_with_me.domain_service.exception.RespostaErro;
import br.com.jhage.sew_with_me.domain_service.model.Client;
import br.com.jhage.sew_with_me.domain_service.model.Order;
import br.com.jhage.sew_with_me.domain_service.model.Sew;
import br.com.jhage.sew_with_me.domain_service.repository.ClientRepository;
import br.com.jhage.sew_with_me.domain_service.repository.OrderRepository;

@FunctionalInterface
interface ShouldDo {
	void test(ResponseEntity<?> response);
}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
		"spring.profiles.active=test" })
public class OrderControllerImplTest {

	private String HOST = "http://localhost:";

	@MockBean
	private ClientRepository clientRepositoryMock;

	@MockBean
	private OrderRepository orderRepositoryMock;

	@LocalServerPort
	private int port;

	private Order requestOrder;

	@Autowired
	private TestRestTemplate restTemplate;

	@AfterEach
	public void clearMock() {
		clearInvocations(clientRepositoryMock);
		clearInvocations(orderRepositoryMock);
	}

	@Test
	public void whenNewOrderApiIsCallAndWhenClientIsnull() {
		String api = "/order/newOrder";
		String uri = HOST + port + api;

		requestOrder = new Order(new Date());
		Client client = null;
		requestOrder.addClient(client);
		ResponseEntity<RespostaErro> response = restTemplate.postForEntity(uri, requestOrder, RespostaErro.class);
		ShouldDo shouldReturnOrderWithoutClientException = (ResponseEntity<?> res) -> {
			assertThat(res.getStatusCode().is4xxClientError()).isTrue();
			assertThat(res.getStatusCode().isError()).isTrue();
			assertThat(res.getStatusCode().value()).isEqualByComparingTo(422);
			assertThat(((RespostaErro) res.getBody()).getMensagem()).isEqualTo("Order without client");
		};
		shouldReturnOrderWithoutClientException.test(response);
	}

	@Test
	public void whenNewOrderApiIsCallAndWhenSewsIsnull() {
		String api = "/order/newOrder";
		String uri = HOST + port + api;
		requestOrder = new Order(new Date());
		Client client = new Client("Teste", "002145");
		requestOrder.addClient(client);
		ResponseEntity<RespostaErro> response = restTemplate.postForEntity(uri, requestOrder, RespostaErro.class);
		ShouldDo shouldReturnOrderWithoutClientException = (ResponseEntity<?> res) -> {
			assertThat(res.getStatusCode().is4xxClientError()).isTrue();
			assertThat(res.getStatusCode().isError()).isTrue();
			assertThat(res.getStatusCode().value()).isEqualByComparingTo(422);
			assertThat(((RespostaErro) res.getBody()).getMensagem()).isEqualTo("Order without sew");
		};
		shouldReturnOrderWithoutClientException.test(response);
	}

	@Test
	public void whenNewOrderApiIsCallAndWhenOrderRepositorySaveThrowException() {
		String api = "/order/newOrder";
		String uri = HOST + port + api;
		requestOrder = new Order(new Date());
		Client client = new Client("Teste", "002145");
		requestOrder.addClient(client);
		Sew sew = new Sew("TRANSFORMCAO", new Date(), 58.1);
		requestOrder.addSew(sew);
		when(orderRepositoryMock.save(any())).thenThrow(new RuntimeException());
		ResponseEntity<RespostaErro> response = restTemplate.postForEntity(uri, requestOrder, RespostaErro.class);
		ShouldDo shouldReturnErrorActionAbrupt = (ResponseEntity<?> res) -> {
			assertThat(res.getStatusCode().is4xxClientError()).isTrue();
			assertThat(res.getStatusCode().isError()).isTrue();
			assertThat(((RespostaErro) res.getBody()).getMensagem()).isEqualTo("ERROR_ACTION_ABRUPT");
		};
		shouldReturnErrorActionAbrupt.test(response);
	}

	@Test
	public void whenNewOrderApiIsCallAndWhenClientRepositoryLoadByNameThrowException() {
		String api = "/order/newOrder";
		String uri = HOST + port + api;
		requestOrder = new Order(new Date());
		Client client = new Client("Teste", "002145");
		requestOrder.addClient(client);
		Sew sew = new Sew("TRANSFORMCAO", new Date(), 58.1);
		requestOrder.addSew(sew);
		when(clientRepositoryMock.loadByName(client.getName())).thenThrow(new RuntimeException());
		ResponseEntity<RespostaErro> response = restTemplate.postForEntity(uri, requestOrder, RespostaErro.class);
		ShouldDo shouldReturnErrorActionAbrupt = (ResponseEntity<?> res) -> {
			assertThat(res.getStatusCode().is4xxClientError()).isTrue();
			assertThat(res.getStatusCode().isError()).isTrue();
			assertThat(((RespostaErro) res.getBody()).getMensagem()).isEqualTo("ERROR_ACTION_ABRUPT");
		};
		shouldReturnErrorActionAbrupt.test(response);
	}

	@Test
	public void whenNewOrderApiIsCallAndWhenClientRepositorySaveThrowException() {
		String api = "/order/newOrder";
		String uri = HOST + port + api;
		requestOrder = new Order(new Date());
		Client client = new Client("Teste", "002145");
		requestOrder.addClient(client);
		Sew sew = new Sew("TRANSFORMCAO", new Date(), 58.1);
		requestOrder.addSew(sew);
		when(clientRepositoryMock.save(any())).thenThrow(new RuntimeException());
		ResponseEntity<RespostaErro> response = restTemplate.postForEntity(uri, requestOrder, RespostaErro.class);
		ShouldDo shouldReturnErrorActionAbrupt = (ResponseEntity<?> res) -> {
			assertThat(res.getStatusCode().is4xxClientError()).isTrue();
			assertThat(res.getStatusCode().isError()).isTrue();
			assertThat(((RespostaErro) res.getBody()).getMensagem()).isEqualTo("ERROR_ACTION_ABRUPT");
		};
		shouldReturnErrorActionAbrupt.test(response);
	}

	@Test
	public void andWhenExecuteWithSuccess() {
		String api = "/order/newOrder";
		String uri = HOST + port + api;
		requestOrder = new Order(new Date());
		Client client = new Client("Teste", "002145");
		requestOrder.addClient(client);
		Sew sew = new Sew("TRANSFORMCAO", new Date(), 58.1);
		requestOrder.addSew(sew);
		when(orderRepositoryMock.save(any())).thenReturn(requestOrder);
		ResponseEntity<Order> response = restTemplate.postForEntity(uri, requestOrder, Order.class);
		System.out.println(response.toString());
		ShouldDo shouldReturnErrorActionAbrupt = (ResponseEntity<?> res) -> {
			assertNotNull(res);
			assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
			assertThat(res.getStatusCode().isError()).isFalse();
			Order orderResponse = (Order) res.getBody();
			assertThat(orderResponse.getClient().getContact()).isEqualTo(client.getContact());
			assertThat(orderResponse.getClient().getName()).isEqualTo(client.getName());
		};
		shouldReturnErrorActionAbrupt.test(response);
	}

}
