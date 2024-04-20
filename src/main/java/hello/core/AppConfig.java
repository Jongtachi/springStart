package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    /**
     *  각 메서드명을 보는 순간 역할이 드러난다.
     **/
    public MemberService memberService(){
        // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    /** 주문 서비스 역할 */
    public OrderService orderService(){
        // 생성자 주입
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    /** 회원 저장소 역할 */
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    /** 할인 정책 역할 */
    public DiscountPolicy discountPolicy(){
        // 고정 할인 구현
        return new FixDiscountPolicy();
    }

}
