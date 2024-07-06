package com.pulse.content.config.trace.aop;

import io.grpc.Metadata;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapSetter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracingAspect {

    private final Tracer tracer = GlobalOpenTelemetry.getTracer("grpc-client");

    // gRPC 요청을 위한 TextMapSetter
    private static final TextMapSetter<Metadata> setter =
            (carrier, key, value) -> carrier.put(Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER), value);

    /**
     * gRPC 클라이언트 호출 시 Span을 생성하고 종료한다.
     *
     * @param joinPoint - 프록시 대상 메서드
     * @param context   - OpenTelemetry Context
     */
    @Around(value = "@annotation(com.pulse.content.config.trace.annotation.Traceable) && args(context)", argNames = "joinPoint,context")
    public Object traceGrpcClient(ProceedingJoinPoint joinPoint, Context context) throws Throwable {
        // 1. Span 생성
        Span span = tracer.spanBuilder("grpc-call").setParent(context).startSpan();

        // 2. Span을 현재 컨텍스트에 설정
        try (Scope scope = span.makeCurrent()) {
            // 2-1. 실제 메서드 호출
            return joinPoint.proceed();
        } catch (Exception e) {
            // 2-2. 예외 발생 시 Span에 기록
            span.recordException(e);
            throw e;
        } finally {
            // 3. Span 종료
            span.end();
        }
    }

    /**
     * gRPC 요청을 위한 TextMapSetter를 생성한다.
     *
     * @param context - OpenTelemetry Context
     * @return Metadata
     */
    public Metadata createMetadata(Context context) {
        Metadata metadata = new Metadata();
        GlobalOpenTelemetry.getPropagators().getTextMapPropagator().inject(context, metadata, setter);
        return metadata;
    }


}
