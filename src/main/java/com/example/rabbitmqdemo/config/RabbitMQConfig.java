package com.example.rabbitmqdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Levin
 */
@Component
@AllArgsConstructor
public class RabbitMQConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    /**
     * 点对点模式
     */
    @Component
    public class Direct {

        public static final String DIRECT_QUEUE_1 = "direct.queue1";
        public static final String DIRECT_EXCHANGE = "direct.exchange";

        @Bean(name = DIRECT_QUEUE_1)
        public Queue directQueue1() {
            return new Queue(DIRECT_QUEUE_1, true, false, false);
        }

        @Bean(name = DIRECT_EXCHANGE)
        public DirectExchange directExchange() {
            return new DirectExchange(DIRECT_EXCHANGE, true, false);
        }

        @Bean
        public Binding bindingDirectQueue1(@Qualifier(DIRECT_QUEUE_1) Queue directQueue1,
                                           @Qualifier(DIRECT_EXCHANGE) DirectExchange directExchange) {
            return BindingBuilder.bind(directQueue1).to(directExchange).withQueueName();
        }
    }

    /**
     * 发布订阅模式
     */
    @Component
    public class Fanout {
        public static final String FANOUT_EXCHANGE = "fanout.exchange";

        @Bean(name = FANOUT_EXCHANGE)
        public FanoutExchange fanoutExchange() {
            return new FanoutExchange(FANOUT_EXCHANGE, true, false);
        }

        public static final String FANOUT_QUEUE1 = "fanout.queue1";

        @Bean(name = FANOUT_QUEUE1)
        public Queue fanoutQueue1() {
            return new Queue(FANOUT_QUEUE1, true, false, false);
        }

        @Bean
        public Binding bindingSimpleQueue1(@Qualifier(FANOUT_QUEUE1) Queue fanoutQueue1,
                                           @Qualifier(FANOUT_EXCHANGE) FanoutExchange fanoutExchange) {
            return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
        }
    }

    /**
     * 主题模式
     */
    @Component
    public class Topic {
        public static final String TOPIC_EXCHANGE_NAME = "topic.exchange";
        public static final String TOPIC_EXCHANGE_QUEUE_A = "topic.queue.a";
        public static final String TOPIC_EXCHANGE_QUEUE_B = "topic.queue.b";
        public static final String TOPIC_EXCHANGE_QUEUE_C = "topic.queue.c";

        @Bean(name = TOPIC_EXCHANGE_NAME)
        public TopicExchange topicExchange() {
            return new TopicExchange(TOPIC_EXCHANGE_NAME, true, false);
        }

        @Bean(name = TOPIC_EXCHANGE_QUEUE_A)
        public Queue topicExchangeQueueA() {
            return new Queue(TOPIC_EXCHANGE_QUEUE_A, true, false, false);
        }

        @Bean(name = TOPIC_EXCHANGE_QUEUE_B)
        public Queue topicExchangeQueueB() {
            return new Queue(TOPIC_EXCHANGE_QUEUE_B, true, false, false);
        }

        @Bean(name = TOPIC_EXCHANGE_QUEUE_C)
        public Queue topicExchangeQueueC() {
            return new Queue(TOPIC_EXCHANGE_QUEUE_C, true, false, false);
        }

        @Bean
        public Binding bindTopicA(@Qualifier(TOPIC_EXCHANGE_QUEUE_A) Queue queue,
                                  @Qualifier(TOPIC_EXCHANGE_NAME) TopicExchange exchange) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with("a.*");
        }

        @Bean
        public Binding bindTopicB(@Qualifier(TOPIC_EXCHANGE_QUEUE_B) Queue queue,
                                  @Qualifier(TOPIC_EXCHANGE_NAME) TopicExchange exchange) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with("b.*");
        }

        @Bean
        public Binding bindTopicC(@Qualifier(TOPIC_EXCHANGE_QUEUE_C) Queue queue,
                                  @Qualifier(TOPIC_EXCHANGE_NAME) TopicExchange exchange) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with("c.#");
        }
    }

}
