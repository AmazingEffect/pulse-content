package com.pulse.content.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @PersistenceAdapter는 영속성 관련 아웃바운드 어댑터에 사용되는 어노테이션입니다.
 * DB와 상호작용하는 어댑터를 명확히 식별하는 데 도움을 줍니다.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface PersistenceAdapter {
    String value() default "";
}
