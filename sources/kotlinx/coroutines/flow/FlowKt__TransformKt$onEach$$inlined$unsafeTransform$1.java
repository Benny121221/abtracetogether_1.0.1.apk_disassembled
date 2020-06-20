package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollectorKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7"}, k = 1, mv = {1, 1, 15})
/* compiled from: SafeCollector.kt */
public final class FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 implements Flow<T> {
    final /* synthetic */ Function2 $action$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    public FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(Flow flow, Function2 function2) {
        this.$this_unsafeTransform$inlined = flow;
        this.$action$inlined = function2;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        return this.$this_unsafeTransform$inlined.collect(new FlowCollector<T>() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x0065  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x00a5 A[RETURN] */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x00a6 A[PHI: r11 
  PHI: (r11v2 java.lang.Object) = (r11v3 java.lang.Object), (r11v1 java.lang.Object) binds: [B:19:0x00a3, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
            public Object emit(Object obj, Continuation continuation) {
                AnonymousClass1 r0;
                int i;
                FlowCollector flowCollector;
                AnonymousClass2 r8;
                Object obj2;
                AnonymousClass1 r6;
                Object obj3;
                Continuation continuation2;
                Object obj4;
                if (continuation instanceof AnonymousClass1) {
                    r0 = (AnonymousClass1) continuation;
                    if ((r0.label & Integer.MIN_VALUE) != 0) {
                        r0.label -= Integer.MIN_VALUE;
                        Object obj5 = r0.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        i = r0.label;
                        if (i != 0) {
                            ResultKt.throwOnFailure(obj5);
                            FlowCollector flowCollector2 = flowCollector;
                            Continuation continuation3 = r0;
                            Function2 function2 = this.$action$inlined;
                            r0.L$0 = this;
                            r0.L$1 = obj;
                            r0.L$2 = r0;
                            r0.L$3 = obj;
                            r0.L$4 = continuation3;
                            r0.L$5 = obj;
                            r0.L$6 = flowCollector2;
                            r0.label = 1;
                            if (function2.invoke(obj, r0) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            r8 = this;
                            obj3 = obj;
                            obj2 = obj3;
                            r6 = r0;
                            continuation2 = continuation3;
                            obj4 = obj2;
                            flowCollector = flowCollector2;
                        } else if (i == 1) {
                            flowCollector = (FlowCollector) r0.L$6;
                            obj4 = r0.L$5;
                            continuation2 = (Continuation) r0.L$4;
                            obj3 = r0.L$3;
                            r6 = (AnonymousClass1) r0.L$2;
                            obj2 = r0.L$1;
                            r8 = (AnonymousClass2) r0.L$0;
                            ResultKt.throwOnFailure(obj5);
                        } else if (i == 2) {
                            FlowCollector flowCollector3 = (FlowCollector) r0.L$6;
                            Object obj6 = r0.L$5;
                            Continuation continuation4 = (Continuation) r0.L$4;
                            Object obj7 = r0.L$3;
                            AnonymousClass1 r10 = (AnonymousClass1) r0.L$2;
                            Object obj8 = r0.L$1;
                            AnonymousClass2 r102 = (AnonymousClass2) r0.L$0;
                            ResultKt.throwOnFailure(obj5);
                        } else {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        r0.L$0 = r8;
                        r0.L$1 = obj2;
                        r0.L$2 = r6;
                        r0.L$3 = obj3;
                        r0.L$4 = continuation2;
                        r0.L$5 = obj4;
                        r0.L$6 = flowCollector;
                        r0.label = 2;
                        obj5 = flowCollector.emit(obj4, r0);
                        return obj5 != coroutine_suspended ? coroutine_suspended : obj5;
                    }
                }
                r0 = new ContinuationImpl(this, continuation) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    int label;
                    /* synthetic */ Object result;
                    final /* synthetic */ AnonymousClass2 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return this.this$0.emit(null, this);
                    }
                };
                Object obj52 = r0.result;
                Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = r0.label;
                if (i != 0) {
                }
                r0.L$0 = r8;
                r0.L$1 = obj2;
                r0.L$2 = r6;
                r0.L$3 = obj3;
                r0.L$4 = continuation2;
                r0.L$5 = obj4;
                r0.L$6 = flowCollector;
                r0.label = 2;
                obj52 = flowCollector.emit(obj4, r0);
                if (obj52 != coroutine_suspended2) {
                }
            }
        }, continuation);
    }
}
