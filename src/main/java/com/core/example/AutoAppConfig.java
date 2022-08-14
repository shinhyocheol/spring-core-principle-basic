package com.core.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // AutoAppConfig 는 수동으로 등록하는 클래스이고, 만약 여기서 제외하지 않으면 충돌이 발생한다.
        // 보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 변경해야 하는 상황이 발생할 수 있으니
        // 그런 상황을 피하기 위해 제외 대상으로 지정한 것이다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
