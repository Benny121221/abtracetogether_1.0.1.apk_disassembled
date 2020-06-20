package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15})
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$take$1", f = "Channels.common.kt", i = {0, 0, 1, 1, 1}, l = {994, 995}, m = "invokeSuspend", n = {"$this$produce", "remaining", "$this$produce", "remaining", "e"}, s = {"L$0", "I$0", "L$0", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$take$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $n;
    final /* synthetic */ ReceiveChannel $this_take;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private ProducerScope p$;

    ChannelsKt__Channels_commonKt$take$1(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        this.$this_take = receiveChannel;
        this.$n = i;
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$take$1 channelsKt__Channels_commonKt$take$1 = new ChannelsKt__Channels_commonKt$take$1(this.$this_take, this.$n, continuation);
        channelsKt__Channels_commonKt$take$1.p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$take$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$take$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008e  */
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope;
        ChannelsKt__Channels_commonKt$take$1 channelsKt__Channels_commonKt$take$1;
        int i;
        ChannelIterator channelIterator;
        int i2;
        ChannelIterator channelIterator2;
        int i3;
        ChannelsKt__Channels_commonKt$take$1 channelsKt__Channels_commonKt$take$12;
        ProducerScope producerScope2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = this.p$;
            int i5 = this.$n;
            if (i5 == 0) {
                return Unit.INSTANCE;
            }
            if (i5 >= 0) {
                i3 = this.$n;
                channelIterator2 = this.$this_take.iterator();
                channelsKt__Channels_commonKt$take$1 = this;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Requested element count ");
                sb.append(this.$n);
                sb.append(" is less than zero.");
                throw new IllegalArgumentException(sb.toString().toString());
            }
        } else if (i4 == 1) {
            channelIterator = (ChannelIterator) this.L$1;
            i = this.I$0;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__Channels_commonKt$take$12 = this;
            if (!((Boolean) obj).booleanValue()) {
                Object next = channelIterator.next();
                channelsKt__Channels_commonKt$take$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$take$12.I$0 = i;
                channelsKt__Channels_commonKt$take$12.L$1 = next;
                channelsKt__Channels_commonKt$take$12.L$2 = channelIterator;
                channelsKt__Channels_commonKt$take$12.label = 2;
                if (producerScope2.send(next, channelsKt__Channels_commonKt$take$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                producerScope = producerScope2;
                channelsKt__Channels_commonKt$take$1 = channelsKt__Channels_commonKt$take$12;
                i2 = i - 1;
                if (i2 == 0) {
                }
                return Unit.INSTANCE;
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        } else if (i4 == 2) {
            channelIterator = (ChannelIterator) this.L$2;
            i = this.I$0;
            ProducerScope producerScope3 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope = producerScope3;
            channelsKt__Channels_commonKt$take$1 = this;
            i2 = i - 1;
            if (i2 == 0) {
                int i6 = i2;
                channelIterator2 = channelIterator;
                i3 = i6;
            }
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        channelsKt__Channels_commonKt$take$1.L$0 = producerScope;
        channelsKt__Channels_commonKt$take$1.I$0 = i3;
        channelsKt__Channels_commonKt$take$1.L$1 = channelIterator2;
        channelsKt__Channels_commonKt$take$1.label = 1;
        Object hasNext = channelIterator2.hasNext(channelsKt__Channels_commonKt$take$1);
        if (hasNext == coroutine_suspended) {
            return coroutine_suspended;
        }
        ChannelsKt__Channels_commonKt$take$1 channelsKt__Channels_commonKt$take$13 = channelsKt__Channels_commonKt$take$1;
        producerScope2 = producerScope;
        obj = hasNext;
        channelsKt__Channels_commonKt$take$12 = channelsKt__Channels_commonKt$take$13;
        ChannelIterator channelIterator3 = channelIterator2;
        i = i3;
        channelIterator = channelIterator3;
        if (!((Boolean) obj).booleanValue()) {
        }
        return Unit.INSTANCE;
        return coroutine_suspended;
    }
}
