package java17.ex03;

import org.junit.Test;

import java17.data.Data;
import java17.data.domain.Customer;
import java17.data.domain.Gender;
import java17.data.domain.Order;
import java17.data.domain.Pizza;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 03 - Collectors
 */
public class Stream_03_Test {

	@Test
	public void test_max() throws Exception {

		List<Order> orders = new Data().getOrders();

		// TODO Retrouver la commande avec le prix le plus élevé
		Optional<Order> result = orders.stream().max(Comparator.comparing(Order::getPrice));

		assertThat(result.isPresent(), is(true));
		assertThat(result.get().getPrice(), is(2200.0));
	}

	@Test
	public void test_min() throws Exception {

		List<Order> orders = new Data().getOrders();

		// TODO Retrouver la commande avec le prix le moins élevé
		Optional<Order> result =orders.stream().min(Comparator.comparing(Order:: getPrice));

		assertThat(result.isPresent(), is(true));
		assertThat(result.get().getPrice(), is(1000.0));
	}

	@Test
	public void test_map_collect_joining() throws Exception {

		List<Customer> customers = new Data().getCustomers();

		// TODO construire une chaîne contenant les prénoms des clients triés et séparés
		// par le caractère "|"
		String result = customers.stream().map(t -> t.getFirstname()).sorted().collect(Collectors.joining("|"));

		System.out.println(result);
		assertThat(result, is("Alexandra|Cyril|Johnny|Marion|Sophie"));
	}

	@Test
	public void test_flatMap() throws Exception {

		List<Order> orders = new Data().getOrders();

		// TODO Extraire la liste des pizzas de toutes les commandes
		List<Pizza> result = orders.stream()
				.flatMap(t -> t.getPizzas().stream())
				.toList();

		assertThat(result.size(), is(9));
	}

	@Test
	public void test_flatMap_distinct() throws Exception {

		List<Order> orders = new Data().getOrders();

		// TODO Extraire la liste des différentes pizzas de toutes les commandes
		List<Pizza> result = orders.stream()
				.flatMap(t -> t.getPizzas().stream())
				.distinct()
				.toList();

		assertThat(result.size(), is(4));
	}

	@Test
	public void test_grouping() throws Exception {

		List<Order> orders = new Data().getOrders();

		// TODO construire une Map <Client, Commandes effectuées par le client
		Map<Customer, List<Order>> result =  orders.stream()
						.collect(Collectors.groupingBy(Order::getCustomer));

		assertThat(result.size(), is(2));
		assertThat(result.get(new Customer(1)), hasSize(4));
		assertThat(result.get(new Customer(2)), hasSize(4));
	}

	@Test
	public void test_partitionning() throws Exception {
		List<Pizza> pizzas = new Data().getPizzas();

		// TODO Séparer la liste des pizzas en 2 ensembles :
		// TODO true -> les pizzas dont le nom commence par "L"
		// TODO false -> les autres
		Map<Boolean, List<Pizza>> result =  pizzas.stream()
				.collect(Collectors.partitioningBy(p -> p.getName().startsWith("L")))
				;

		assertThat(result.get(true), hasSize(6));
		assertThat(result.get(false), hasSize(2));
	}
}
