package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    /**
     *  각 메서드명을 보는 순간 역할이 드러난다.
     **/

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // 이걸 보면 싱글톤이 깨지는 게 아닌가? 생각한다.

    /*예상 기대값*/
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    /*실제 결과물 (각 하나씩만 호출된다.)*/
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    @Bean
    public MemberService memberService(){
        // 생성자 주입
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }


    /** 주문 서비스 역할 */
    @Bean
    public OrderService orderService(){
        // 생성자 주입
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
        //return null;
    }

    /** 회원 저장소 역할 */
    @Bean
    @Primary
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    /** 할인 정책 역할 */
    @Bean
    public DiscountPolicy discountPolicy(){
        // 고정 할인 구현
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
