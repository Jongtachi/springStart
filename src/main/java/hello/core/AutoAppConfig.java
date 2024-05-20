package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages : 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
        // basePackages = {"hello.core", "hello.service"} 이렇게 여러 시작 위치를 지정할 수도 있다.
        //basePackages = "hello.core.member",

        // basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
        //basePackageClasses  = AutoAppConfig.class,

        /** 중요. 만약 위에 둘다 지정하지 않으면.
         * *@ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
         * 현재 해당 파일의 패키지 겅료는 -> package hello.core;
         * */

        // component scan을 제외할 것들 넣어준다.(이전에 만든 코드들을 안전하게 사용하기 위해서 제외)
        // 그렇지 않으면 ComponentScan 충돌이 난다.
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 수동으로 등록
    /*@Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
