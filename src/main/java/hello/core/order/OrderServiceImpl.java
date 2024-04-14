package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // OrderServiceImpl 파일은 계산 같은 건 안하고 보여주기만 한다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    // 회원정보 조회 후
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인정책 적용

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
