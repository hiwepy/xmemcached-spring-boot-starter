package net.rubyeye.xmemcached.transcoders;

/**
 * <p>Boolean Transcoder.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class BooleanTranscoder extends PrimitiveTypeTranscoder<Boolean> {

    @Override
    /**
     * encode.
     *
     * @param l the l
     * @return the result
     */
    public CachedData encode(Boolean l) {
        /**
         * store integer as string
         */
        if (this.primitiveAsString) {
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
        return new CachedData(SerializingTranscoder.SPECIAL_BOOLEAN, this.tu.encodeBoolean(l));
    }

    @Override
    /**
     * decode.
     *
     * @param d the d
     * @return the result
     */
    public Boolean decode(CachedData d) {
        if (this.primitiveAsString) {
            byte[] data = d.getData();
            if ((d.getFlag() & SerializingTranscoder.COMPRESSED) != 0) {
                data = decompress(d.getData());
            }
            int flag = d.getFlag();
            if (flag == 0) {
                return Boolean.valueOf(decodeString(data));
            } else {
                return null;
            }
        } else {
            if (SerializingTranscoder.SPECIAL_BOOLEAN == d.getFlag()) {
                return this.tu.decodeBoolean(d.getData());
            } else {
                return null;
            }
        }
    }

    @Override
    /**
     * Sets the primitive as string.
     *
     * @param primitiveAsString the primitive as string
     */
    public void setPrimitiveAsString(boolean primitiveAsString) {
        this.primitiveAsString = primitiveAsString;
    }

    @Override
    /**
     * Sets the pack zeros.
     *
     * @param packZeros the pack zeros
     */
    public void setPackZeros(boolean packZeros) {
        this.tu.setPackZeros(packZeros);
    }

}
