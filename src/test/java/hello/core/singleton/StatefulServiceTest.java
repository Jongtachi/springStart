package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        //statefulService1.order("userA", 10000);
        int userAPrice = statefulService1.order("userA", 10000);

        // ThreadB : B사용자 20000원 주문
        //statefulService2.order("userB", 20000);
        int iserBPrice = statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A가 주문 금액 조회
        // 10,000원이 나와야 하는데 20,000원이 나왔다.
        //int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(10000);


    }
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}