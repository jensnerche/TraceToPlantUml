package de.kontext_e.spikes.trace_to_plantuml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.SourceLocation;

@Aspect
public class TracingAspect {
    private boolean verbose = false;

    private static List<TracingEvent> messages = new ArrayList<>();

    @Before("within(de.kontext_e.spikes.trace_to_plantuml.application.*) && call(* de.kontext_e.spikes.trace_to_plantuml.application.*.*(..)) && !within(A)")
    public void before(final JoinPoint thisJoinPoint) {
        print(thisJoinPoint);
        traceEntry(getThis(thisJoinPoint), getTarget(thisJoinPoint), thisJoinPoint.getSignature(), thisJoinPoint.getSourceLocation(), thisJoinPoint.getArgs());
    }

    private void traceEntry(final Class<? extends Object> aThis, final Class<? extends Object> target, final Signature signature, final SourceLocation sourceLocation, final Object[] args) {
        if(aThis != null && target != null) {
            String message = aThis.getName() + " -> " + target.getName() + " : " + signature.getName() + "(" + Arrays.deepToString(args) + ")";
            messages.add(new TracingEvent(TraceType.ENTRY, aThis, target, signature, sourceLocation, args));
            System.out.println(message);
        }
    }

    @AfterReturning(pointcut = "within(de.kontext_e.spikes.trace_to_plantuml.application.*) && call(* de.kontext_e.spikes.trace_to_plantuml.application.*.*(..))", returning = "o")
    public void after(final JoinPoint thisJoinPoint, Object o) {
        traceExit(getThis(thisJoinPoint), getTarget(thisJoinPoint), thisJoinPoint.getSignature(), thisJoinPoint.getSourceLocation(), o);
    }

    private void traceExit(final Class<? extends Object> aThis, final Class<? extends Object> target, final Signature signature, final SourceLocation sourceLocation, final Object... returnValue) {
        if(aThis != null && target != null) {
            String message = target.getName() + " -> " + aThis.getName() + " : return" + "(" + Arrays.deepToString(returnValue) + ")";
            messages.add(new TracingEvent(TraceType.EXIT, aThis, target, signature, sourceLocation, returnValue));
            System.out.println(message);
        }
    }

    @AfterThrowing(pointcut = "within(de.kontext_e.spikes.trace_to_plantuml.application.*) && call(* de.kontext_e.spikes.trace_to_plantuml.application.*.*(..))", throwing = "t")
    public void afterThrowing(final JoinPoint thisJoinPoint, Throwable t) {
        traceThrowing(getThis(thisJoinPoint), getTarget(thisJoinPoint), thisJoinPoint.getSignature(), thisJoinPoint.getSourceLocation(), t);
    }

    private void traceThrowing(final Class<? extends Object> aThis, final Class<? extends Object> target, final Signature signature, final SourceLocation sourceLocation, final Throwable throwable) {
        if(aThis != null && target != null) {
            String message = target.getName() + " -> " + aThis.getName() + " : throws" + "(" + throwable + ")";
            messages.add(new TracingEvent(TraceType.EXCEPTION, aThis, target, signature, sourceLocation, throwable));
            System.out.println(message);
        }
    }

    private void print(JoinPoint thisJoinPoint) {
        if(! verbose) return;
        System.out.println("This     : "+ getThis(thisJoinPoint));
        System.out.println("Target   : "+ getTarget(thisJoinPoint));
        System.out.println("Signature: "+thisJoinPoint.getSignature());
        System.out.println("Args     : "+ Arrays.deepToString(thisJoinPoint.getArgs()));
        System.out.println("Source   : "+thisJoinPoint.getSourceLocation().toString());
        System.out.println("\n");
    }

    private Class<? extends Object> getTarget(JoinPoint thisJoinPoint) {
        if(thisJoinPoint == null || thisJoinPoint.getTarget() == null) return null;
        return thisJoinPoint.getTarget().getClass();
    }

    private Class<? extends Object> getThis(JoinPoint thisJoinPoint) {
        if(thisJoinPoint == null || thisJoinPoint.getThis() == null) return null;
        return thisJoinPoint.getThis().getClass();
    }

    public static List<TracingEvent> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
