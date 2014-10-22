package de.kontext_e.spikes.trace_to_plantuml;

import java.util.Arrays;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;

public class TracingEvent {
    private final TraceType traceType;
    private final Class<? extends Object> source;
    private final Class<? extends Object> target;
    private final Signature signature;
    private final SourceLocation sourceLocation;
    private final Object[] args;

    public TracingEvent(final TraceType traceType, final Class<? extends Object> source, final Class<? extends Object> target, final Signature signature, final SourceLocation sourceLocation, final Object... args) {
        this.traceType = traceType;
        this.source = source;
        this.target = target;
        this.signature = signature;
        this.sourceLocation = sourceLocation;
        this.args = args;
    }

    public TraceType getTraceType() {
        return traceType;
    }

    public Class<? extends Object> getSource() {
        return source;
    }

    public Class<? extends Object> getTarget() {
        return target;
    }

    public Signature getSignature() {
        return signature;
    }

    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    public Object[] getArgs() {
        return args;
    }

    public String asCallMessage() {
        return source.getName() + " -> " + target.getName() + " : " + signature.getName() + "(" + Arrays.deepToString(args) + ")";
    }

    public String asExitMessage() {
        return target.getName() + " -> " + source.getName() + " : return" + "(" + Arrays.deepToString(args) + ")";
    }

    public String asThrowingMessage() {
        return target.getName() + " -> " + source.getName() + " : throws" + "(" + Arrays.deepToString(args) + ")";
    }

}
