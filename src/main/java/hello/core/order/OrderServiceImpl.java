package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // OrderServiceImpl 파일은 계산 같은 건 안하고 보여주기만 한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    /** 2. set */
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
////        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
////        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
    /** set 끝*/

    /** 3. 필드주입 -> 사용하지말자 ^-^*/
    //  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {}은 주석처리 하고 실행
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    /** 필드주입 끝*/


    // 생성자가 2개이면 @Autowired를 꼭 지정해줘야한다.
    // 생성자가 하나일땐 @Autowired를 생략해도 자동 주입 된다.
    /*
    public OrderServiceImpl() {
    }
    */

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*OCP 위반*/
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    // 회원정보 조회 후
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인정책 적용

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
