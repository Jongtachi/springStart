package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // component scan을 제외할 것들 넣어준다.(이전에 만든 코드들을 안전하게 사용하기 위해서 제외)
        // 그렇지 않으면 ComponentScan 충돌이 난다.
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
