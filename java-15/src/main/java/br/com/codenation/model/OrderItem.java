package br.com.codenation.model;

import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

import javax.swing.text.html.Option;
import java.util.Optional;

public class OrderItem {

	private Long productId;
	private Long quantity;

	private ProductRepository repository = new ProductRepositoryImpl();
	
	public OrderItem(Long productId, Long quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public Double total() {
		return this.productValue() * this.quantity;
	}

	private Double productValue() {
		// E calcular o valor de um Product é responsabilidade do próprio;
		// Aqui só estamos trabalhando com o Optional
		return this.product().map(Product::saleValue).orElse(0.0);
	}

	private Optional<Product> product() {
		return this.repository.findById(this.productId);
	}
}
