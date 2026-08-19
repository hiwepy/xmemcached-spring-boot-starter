package net.rubyeye.xmemcached.transcoders;

/**\n * Auto-configuration for CustomTypeTranscoder.\n *\n * @author <a href="https://github.com/loong10k">Loong Wan</a>\n * @since 1.0.0\n */
public class CustomTypeTranscoder<T> extends BaseSerializingTranscoder implements Transcoder<T> {

    @Override
    /**
     * encode.
     *
     * @param l the l
     * @return the result
     */
    public CachedData encode(T l) {
        byte[] b = encodeString(l.toString());
        int flags = 0;
        if (b.length > this.compressionThreshold) {
            byte[] compressed = compress(b);
            if (compressed.length < b.length) {
                if (log.isDebugEnabled()) {
                    log.debug("Compressed " + l.getClass().getName() + " from " + b.length + " to "
                            + compressed.length);
                }
                b = compressed;
                flags |= SerializingTranscoder.COMPRESSED;
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Compression increased the size of " + l.getClass().getName() + " from "
                            + b.length + " to " + compressed.length);
                }
            }
        }
        return new CachedData(flags, b, b.length, -1);
    }

    @Override
    /**
     * decode.
     *
     * @param d the d
     * @return the result
     */
    public T decode(CachedData d) {
        return null;
    }

    @Override
    /**
     * Sets the primitive as string.
     *
     * @param primitiveAsString the primitive as string
     */
    public void setPrimitiveAsString(boolean primitiveAsString) {
    }

    @Override
    /**
     * Sets the pack zeros.
     *
     * @param packZeros the pack zeros
     */
    public void setPackZeros(boolean packZeros) {
    }

    @Override
    /**
     * Returns the primitive as string.
     *
     * @return the primitive as string
     */
    public boolean isPrimitiveAsString() {
        return false;
    }

    @Override
    /**
     * Returns the pack zeros.
     *
     * @return the pack zeros
     */
    public boolean isPackZeros() {
        return false;
    }

}
