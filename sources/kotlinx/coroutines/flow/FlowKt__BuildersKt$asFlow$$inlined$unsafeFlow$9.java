package kotlinx.coroutines.flow;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.ranges.IntRange;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollectorKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
/* compiled from: SafeCollector.kt */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9 implements Flow<Integer> {
    final /* synthetic */ IntRange $this_asFlow$inlined;

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9(IntRange intRange) {
        this.$this_asFlow$inlined = intRange;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        AnonymousClass1 r0;
        int i;
        FlowCollector flowCollector2;
        Iterator it;
        FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9;
        Continuation continuation2;
        Iterable iterable;
        Object obj;
        AnonymousClass1 r1;
        FlowCollector flowCollector3;
        if (continuation instanceof AnonymousClass1) {
            r0 = (AnonymousClass1) continuation;
            if ((r0.label & Integer.MIN_VALUE) != 0) {
                r0.label -= Integer.MIN_VALUE;
                Object obj2 = r0.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = r0.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    Continuation continuation3 = r0;
                    Iterable iterable2 = this.$this_asFlow$inlined;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9 = this;
                    continuation2 = continuation3;
                    flowCollector2 = flowCollector;
                    it = iterable2.iterator();
                    iterable = iterable2;
                    obj = coroutine_suspended;
                    r1 = r0;
                    flowCollector3 = flowCollector2;
                } else if (i == 1) {
                    int i2 = r0.I$0;
                    Object obj3 = r0.L$6;
                    it = (Iterator) r0.L$5;
                    Iterable iterable3 = (Iterable) r0.L$4;
                    FlowCollector flowCollector4 = (FlowCollector) r0.L$3;
                    continuation2 = (Continuation) r0.L$2;
                    FlowCollector flowCollector5 = (FlowCollector) r0.L$1;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9 = (FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9) r0.L$0;
                    ResultKt.throwOnFailure(obj2);
                    flowCollector2 = flowCollector4;
                    iterable = iterable3;
                    obj = coroutine_suspended;
                    r1 = r0;
                    flowCollector3 = flowCollector5;
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                while (it.hasNext()) {
                    Object next = it.next();
                    int intValue = ((Number) next).intValue();
                    Integer boxInt = Boxing.boxInt(intValue);
                    r1.L$0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9;
                    r1.L$1 = flowCollector3;
                    r1.L$2 = continuation2;
                    r1.L$3 = flowCollector2;
                    r1.L$4 = iterable;
                    r1.L$5 = it;
                    r1.L$6 = next;
                    r1.I$0 = intValue;
                    r1.label = 1;
                    if (flowCollector2.emit(boxInt, r1) == obj) {
                        return obj;
                    }
                }
                return Unit.INSTANCE;
            }
        }
        r0 = new ContinuationImpl(this, continuation) {
            int I$0;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            Object L$6;
            int label;
            /* synthetic */ Object result;
            final /* synthetic */ FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9 this$0;

            {
                this.this$0 = r1;
            }

            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.collect(null, this);
            }
        };
        Object obj22 = r0.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = r0.label;
        if (i != 0) {
        }
        while (it.hasNext()) {
        }
        return Unit.INSTANCE;
    }
}
