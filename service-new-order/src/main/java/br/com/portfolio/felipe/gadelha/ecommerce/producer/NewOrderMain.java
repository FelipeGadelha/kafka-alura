package br.com.portfolio.felipe.gadelha.ecommerce.producer;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import br.com.portfolio.felipe.gadelha.ecommerce.model.Order;

public class NewOrderMain {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		try (var orderDispatcher = new KafkaDispatcher<Order>()) {
			try (var emailDispatcher = new KafkaDispatcher<String>()) {
				for (var i = 0; i < 10; i++) {
					var userId = UUID.randomUUID().toString();
					var orderId = UUID.randomUUID().toString();
					var amount = new BigDecimal(Math.random() * 5000 + 1);
					var order = new Order(userId, orderId, amount);

					orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

					var email = "Welcome! We are processing your order!";
					emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
				}
			}
		}
	}
}
