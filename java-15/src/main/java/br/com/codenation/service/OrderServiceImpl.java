package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		// Sinceramente, calcular o total de um OrderItem é responsabilidade dele
		return items.stream().mapToDouble(OrderItem::total).sum();
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream()
				.map(productRepository::findById)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return 0.0;
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		HashMap<Boolean, List<Product>> map = new HashMap<>();
		map.put(true, new ArrayList<>());
		map.put(false, new ArrayList<>());
		return map;
	}

}