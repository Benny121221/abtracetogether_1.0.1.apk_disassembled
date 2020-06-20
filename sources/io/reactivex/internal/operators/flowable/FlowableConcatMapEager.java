package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscribers.InnerQueuedSubscriber;
import io.reactivex.internal.subscribers.InnerQueuedSubscriberSupport;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableConcatMapEager<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;
    final int maxConcurrency;
    final int prefetch;

    static final class ConcatMapEagerDelayErrorSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, InnerQueuedSubscriberSupport<R> {
        private static final long serialVersionUID = -4255299542215038287L;
        volatile boolean cancelled;
        volatile InnerQueuedSubscriber<R> current;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        final int maxConcurrency;
        final int prefetch;
        final AtomicLong requested = new AtomicLong();
        final SpscLinkedArrayQueue<InnerQueuedSubscriber<R>> subscribers;
        Subscription upstream;

        ConcatMapEagerDelayErrorSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends Publisher<? extends R>> function, int i, int i2, ErrorMode errorMode2) {
            this.downstream = subscriber;
            this.mapper = function;
            this.maxConcurrency = i;
            this.prefetch = i2;
            this.errorMode = errorMode2;
            this.subscribers = new SpscLinkedArrayQueue<>(Math.min(i2, i));
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                subscription.request(i == Integer.MAX_VALUE ? LongCompanionObject.MAX_VALUE : (long) i);
            }
        }

        public void onNext(T t) {
            try {
                Publisher publisher = (Publisher) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher");
                InnerQueuedSubscriber innerQueuedSubscriber = new InnerQueuedSubscriber(this, this.prefetch);
                if (!this.cancelled) {
                    this.subscribers.offer(innerQueuedSubscriber);
                    publisher.subscribe(innerQueuedSubscriber);
                    if (this.cancelled) {
                        innerQueuedSubscriber.cancel();
                        drainAndCancel();
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                drainAndCancel();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainAndCancel() {
            if (getAndIncrement() == 0) {
                do {
                    cancelAll();
                } while (decrementAndGet() != 0);
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelAll() {
            InnerQueuedSubscriber<R> innerQueuedSubscriber = this.current;
            this.current = null;
            if (innerQueuedSubscriber != null) {
                innerQueuedSubscriber.cancel();
            }
            while (true) {
                InnerQueuedSubscriber innerQueuedSubscriber2 = (InnerQueuedSubscriber) this.subscribers.poll();
                if (innerQueuedSubscriber2 != null) {
                    innerQueuedSubscriber2.cancel();
                } else {
                    return;
                }
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void innerNext(InnerQueuedSubscriber<R> innerQueuedSubscriber, R r) {
            if (innerQueuedSubscriber.queue().offer(r)) {
                drain();
                return;
            }
            innerQueuedSubscriber.cancel();
            innerError(innerQueuedSubscriber, new MissingBackpressureException());
        }

        public void innerError(InnerQueuedSubscriber<R> innerQueuedSubscriber, Throwable th) {
            if (this.errors.addThrowable(th)) {
                innerQueuedSubscriber.setDone();
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.cancel();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void innerComplete(InnerQueuedSubscriber<R> innerQueuedSubscriber) {
            innerQueuedSubscriber.setDone();
            drain();
        }

        /* JADX WARNING: Removed duplicated region for block: B:78:0x012a  */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x012f  */
        public void drain() {
            InnerQueuedSubscriber<R> innerQueuedSubscriber;
            int i;
            long j;
            long j2;
            boolean z;
            int i2;
            if (getAndIncrement() == 0) {
                InnerQueuedSubscriber<R> innerQueuedSubscriber2 = this.current;
                Subscriber<? super R> subscriber = this.downstream;
                ErrorMode errorMode2 = this.errorMode;
                int i3 = 1;
                while (true) {
                    long j3 = this.requested.get();
                    if (innerQueuedSubscriber2 != null) {
                        innerQueuedSubscriber = innerQueuedSubscriber2;
                    } else if (errorMode2 == ErrorMode.END || ((Throwable) this.errors.get()) == null) {
                        innerQueuedSubscriber = (InnerQueuedSubscriber) this.subscribers.poll();
                        if (this.done && innerQueuedSubscriber == null) {
                            Throwable terminate = this.errors.terminate();
                            if (terminate != null) {
                                subscriber.onError(terminate);
                            } else {
                                subscriber.onComplete();
                            }
                            return;
                        } else if (innerQueuedSubscriber != null) {
                            this.current = innerQueuedSubscriber;
                        }
                    } else {
                        cancelAll();
                        subscriber.onError(this.errors.terminate());
                        return;
                    }
                    if (innerQueuedSubscriber != null) {
                        SimpleQueue queue = innerQueuedSubscriber.queue();
                        if (queue != null) {
                            j = 0;
                            while (true) {
                                i2 = (j > j3 ? 1 : (j == j3 ? 0 : -1));
                                i = i3;
                                if (i2 == 0) {
                                    break;
                                } else if (this.cancelled) {
                                    cancelAll();
                                    return;
                                } else if (errorMode2 != ErrorMode.IMMEDIATE || ((Throwable) this.errors.get()) == null) {
                                    boolean isDone = innerQueuedSubscriber.isDone();
                                    try {
                                        Object poll = queue.poll();
                                        boolean z2 = poll == null;
                                        if (isDone && z2) {
                                            this.current = null;
                                            this.upstream.request(1);
                                            innerQueuedSubscriber = null;
                                            z = true;
                                            break;
                                        } else if (z2) {
                                            break;
                                        } else {
                                            subscriber.onNext(poll);
                                            j++;
                                            innerQueuedSubscriber.requestOne();
                                            i3 = i;
                                        }
                                    } catch (Throwable th) {
                                        Throwable th2 = th;
                                        Exceptions.throwIfFatal(th2);
                                        this.current = null;
                                        innerQueuedSubscriber.cancel();
                                        cancelAll();
                                        subscriber.onError(th2);
                                        return;
                                    }
                                } else {
                                    this.current = null;
                                    innerQueuedSubscriber.cancel();
                                    cancelAll();
                                    subscriber.onError(this.errors.terminate());
                                    return;
                                }
                            }
                            z = false;
                            if (i2 == 0) {
                                if (this.cancelled) {
                                    cancelAll();
                                    return;
                                } else if (errorMode2 != ErrorMode.IMMEDIATE || ((Throwable) this.errors.get()) == null) {
                                    boolean isDone2 = innerQueuedSubscriber.isDone();
                                    boolean isEmpty = queue.isEmpty();
                                    if (isDone2 && isEmpty) {
                                        this.current = null;
                                        this.upstream.request(1);
                                        innerQueuedSubscriber = null;
                                        z = true;
                                    }
                                } else {
                                    this.current = null;
                                    innerQueuedSubscriber.cancel();
                                    cancelAll();
                                    subscriber.onError(this.errors.terminate());
                                    return;
                                }
                            }
                            j2 = 0;
                            if (!(j == j2 || j3 == LongCompanionObject.MAX_VALUE)) {
                                this.requested.addAndGet(-j);
                            }
                            if (!z) {
                                innerQueuedSubscriber2 = innerQueuedSubscriber;
                                i3 = i;
                            } else {
                                i3 = addAndGet(-i);
                                if (i3 != 0) {
                                    innerQueuedSubscriber2 = innerQueuedSubscriber;
                                } else {
                                    return;
                                }
                            }
                        }
                    }
                    i = i3;
                    z = false;
                    j2 = 0;
                    j = 0;
                    this.requested.addAndGet(-j);
                    if (!z) {
                    }
                }
            }
        }
    }

    public FlowableConcatMapEager(Flowable<T> flowable, Function<? super T, ? extends Publisher<? extends R>> function, int i, int i2, ErrorMode errorMode2) {
        super(flowable);
        this.mapper = function;
        this.maxConcurrency = i;
        this.prefetch = i2;
        this.errorMode = errorMode2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super R> subscriber) {
        Flowable flowable = this.source;
        ConcatMapEagerDelayErrorSubscriber concatMapEagerDelayErrorSubscriber = new ConcatMapEagerDelayErrorSubscriber(subscriber, this.mapper, this.maxConcurrency, this.prefetch, this.errorMode);
        flowable.subscribe((FlowableSubscriber<? super T>) concatMapEagerDelayErrorSubscriber);
    }
}