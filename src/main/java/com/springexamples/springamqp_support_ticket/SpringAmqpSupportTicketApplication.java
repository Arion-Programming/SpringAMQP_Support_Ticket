package com.springexamples.springamqp_support_ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringAmqpSupportTicketApplication {
	protected final String helloWorldQueueName = "hello.world.queue";

	@Bean CachingConnectionFactory cachingConnectionFactory() {
		return new CachingConnectionFactory("localhost");
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		var connectionFactory = cachingConnectionFactory();
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}



	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		//The routing key is set to the name of the queue by the broker for the default exchange.
		template.setRoutingKey(this.helloWorldQueueName);
		//Where we will synchronously receive messages from
		template.setDefaultReceiveQueue(this.helloWorldQueueName);
		return template;
	}

	@Bean
	// Every queue is bound to the default direct exchange
	public Queue helloWorldQueue() {
		return new Queue(this.helloWorldQueueName);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringAmqpSupportTicketApplication.class, args);
	}

}
