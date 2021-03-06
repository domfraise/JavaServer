// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: main/java/DeviceRPC/decryptiondevice.proto

package io.grpc.decryptiondevice;

/**
 * <pre>
 * Root Tree Hash
 * Random nonce used as message ID
 * Signature over rth and nonce
 * </pre>
 *
 * Protobuf type {@code decryptiondevice.RootTreeHash}
 */
public  final class RootTreeHash extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:decryptiondevice.RootTreeHash)
    RootTreeHashOrBuilder {
  // Use RootTreeHash.newBuilder() to construct.
  private RootTreeHash(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RootTreeHash() {
    rth_ = com.google.protobuf.ByteString.EMPTY;
    nonce_ = com.google.protobuf.ByteString.EMPTY;
    sig_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private RootTreeHash(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {

            rth_ = input.readBytes();
            break;
          }
          case 18: {

            nonce_ = input.readBytes();
            break;
          }
          case 26: {

            sig_ = input.readBytes();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.grpc.decryptiondevice.DecryptionDeviceProto.internal_static_decryptiondevice_RootTreeHash_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.decryptiondevice.DecryptionDeviceProto.internal_static_decryptiondevice_RootTreeHash_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.decryptiondevice.RootTreeHash.class, io.grpc.decryptiondevice.RootTreeHash.Builder.class);
  }

  public static final int RTH_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString rth_;
  /**
   * <code>bytes rth = 1;</code>
   */
  public com.google.protobuf.ByteString getRth() {
    return rth_;
  }

  public static final int NONCE_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString nonce_;
  /**
   * <code>bytes nonce = 2;</code>
   */
  public com.google.protobuf.ByteString getNonce() {
    return nonce_;
  }

  public static final int SIG_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString sig_;
  /**
   * <code>bytes sig = 3;</code>
   */
  public com.google.protobuf.ByteString getSig() {
    return sig_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!rth_.isEmpty()) {
      output.writeBytes(1, rth_);
    }
    if (!nonce_.isEmpty()) {
      output.writeBytes(2, nonce_);
    }
    if (!sig_.isEmpty()) {
      output.writeBytes(3, sig_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!rth_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, rth_);
    }
    if (!nonce_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, nonce_);
    }
    if (!sig_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, sig_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.grpc.decryptiondevice.RootTreeHash)) {
      return super.equals(obj);
    }
    io.grpc.decryptiondevice.RootTreeHash other = (io.grpc.decryptiondevice.RootTreeHash) obj;

    boolean result = true;
    result = result && getRth()
        .equals(other.getRth());
    result = result && getNonce()
        .equals(other.getNonce());
    result = result && getSig()
        .equals(other.getSig());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RTH_FIELD_NUMBER;
    hash = (53 * hash) + getRth().hashCode();
    hash = (37 * hash) + NONCE_FIELD_NUMBER;
    hash = (53 * hash) + getNonce().hashCode();
    hash = (37 * hash) + SIG_FIELD_NUMBER;
    hash = (53 * hash) + getSig().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.decryptiondevice.RootTreeHash parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.grpc.decryptiondevice.RootTreeHash prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Root Tree Hash
   * Random nonce used as message ID
   * Signature over rth and nonce
   * </pre>
   *
   * Protobuf type {@code decryptiondevice.RootTreeHash}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:decryptiondevice.RootTreeHash)
      io.grpc.decryptiondevice.RootTreeHashOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.decryptiondevice.DecryptionDeviceProto.internal_static_decryptiondevice_RootTreeHash_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.decryptiondevice.DecryptionDeviceProto.internal_static_decryptiondevice_RootTreeHash_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.decryptiondevice.RootTreeHash.class, io.grpc.decryptiondevice.RootTreeHash.Builder.class);
    }

    // Construct using io.grpc.decryptiondevice.RootTreeHash.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      rth_ = com.google.protobuf.ByteString.EMPTY;

      nonce_ = com.google.protobuf.ByteString.EMPTY;

      sig_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.decryptiondevice.DecryptionDeviceProto.internal_static_decryptiondevice_RootTreeHash_descriptor;
    }

    public io.grpc.decryptiondevice.RootTreeHash getDefaultInstanceForType() {
      return io.grpc.decryptiondevice.RootTreeHash.getDefaultInstance();
    }

    public io.grpc.decryptiondevice.RootTreeHash build() {
      io.grpc.decryptiondevice.RootTreeHash result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public io.grpc.decryptiondevice.RootTreeHash buildPartial() {
      io.grpc.decryptiondevice.RootTreeHash result = new io.grpc.decryptiondevice.RootTreeHash(this);
      result.rth_ = rth_;
      result.nonce_ = nonce_;
      result.sig_ = sig_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.grpc.decryptiondevice.RootTreeHash) {
        return mergeFrom((io.grpc.decryptiondevice.RootTreeHash)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.decryptiondevice.RootTreeHash other) {
      if (other == io.grpc.decryptiondevice.RootTreeHash.getDefaultInstance()) return this;
      if (other.getRth() != com.google.protobuf.ByteString.EMPTY) {
        setRth(other.getRth());
      }
      if (other.getNonce() != com.google.protobuf.ByteString.EMPTY) {
        setNonce(other.getNonce());
      }
      if (other.getSig() != com.google.protobuf.ByteString.EMPTY) {
        setSig(other.getSig());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.grpc.decryptiondevice.RootTreeHash parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.decryptiondevice.RootTreeHash) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.protobuf.ByteString rth_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes rth = 1;</code>
     */
    public com.google.protobuf.ByteString getRth() {
      return rth_;
    }
    /**
     * <code>bytes rth = 1;</code>
     */
    public Builder setRth(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      rth_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes rth = 1;</code>
     */
    public Builder clearRth() {
      
      rth_ = getDefaultInstance().getRth();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString nonce_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes nonce = 2;</code>
     */
    public com.google.protobuf.ByteString getNonce() {
      return nonce_;
    }
    /**
     * <code>bytes nonce = 2;</code>
     */
    public Builder setNonce(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      nonce_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes nonce = 2;</code>
     */
    public Builder clearNonce() {
      
      nonce_ = getDefaultInstance().getNonce();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString sig_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes sig = 3;</code>
     */
    public com.google.protobuf.ByteString getSig() {
      return sig_;
    }
    /**
     * <code>bytes sig = 3;</code>
     */
    public Builder setSig(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      sig_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes sig = 3;</code>
     */
    public Builder clearSig() {
      
      sig_ = getDefaultInstance().getSig();
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:decryptiondevice.RootTreeHash)
  }

  // @@protoc_insertion_point(class_scope:decryptiondevice.RootTreeHash)
  private static final io.grpc.decryptiondevice.RootTreeHash DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.decryptiondevice.RootTreeHash();
  }

  public static io.grpc.decryptiondevice.RootTreeHash getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RootTreeHash>
      PARSER = new com.google.protobuf.AbstractParser<RootTreeHash>() {
    public RootTreeHash parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RootTreeHash(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RootTreeHash> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RootTreeHash> getParserForType() {
    return PARSER;
  }

  public io.grpc.decryptiondevice.RootTreeHash getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

