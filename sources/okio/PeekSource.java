package okio;

import java.io.IOException;

final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    PeekSource(BufferedSource bufferedSource) {
        this.upstream = bufferedSource;
        Buffer buffer2 = bufferedSource.buffer();
        this.buffer = buffer2;
        Segment segment = buffer2.head;
        this.expectedSegment = segment;
        this.expectedPos = segment != null ? segment.pos : -1;
    }

    public long read(Buffer buffer2, long j) throws IOException {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (!this.closed) {
            Segment segment = this.expectedSegment;
            if (segment != null && (segment != this.buffer.head || this.expectedPos != this.buffer.head.pos)) {
                throw new IllegalStateException("Peek source is invalid because upstream source was used");
            } else if (i == 0) {
                return 0;
            } else {
                if (!this.upstream.request(this.pos + 1)) {
                    return -1;
                }
                if (this.expectedSegment == null && this.buffer.head != null) {
                    this.expectedSegment = this.buffer.head;
                    this.expectedPos = this.buffer.head.pos;
                }
                long min = Math.min(j, this.buffer.size - this.pos);
                this.buffer.copyTo(buffer2, this.pos, min);
                this.pos += min;
                return min;
            }
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public Timeout timeout() {
        return this.upstream.timeout();
    }

    public void close() throws IOException {
        this.closed = true;
    }
}
