// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ReqLogin.proto

package com.game.pb.server.message;

public final class ReqLoginOuterClass {
  private ReqLoginOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ReqLoginOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ReqLogin)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string uid = 1;</code>
     */
    java.lang.String getUid();
    /**
     * <code>string uid = 1;</code>
     */
    com.google.protobuf.ByteString
        getUidBytes();

    /**
     * <code>string param = 2;</code>
     */
    java.lang.String getParam();
    /**
     * <code>string param = 2;</code>
     */
    com.google.protobuf.ByteString
        getParamBytes();
  }
  /**
   * Protobuf type {@code ReqLogin}
   */
  public  static final class ReqLogin extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ReqLogin)
      ReqLoginOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ReqLogin.newBuilder() to construct.
    private ReqLogin(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ReqLogin() {
      uid_ = "";
      param_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ReqLogin(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              uid_ = s;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              param_ = s;
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
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.game.pb.server.message.ReqLoginOuterClass.internal_static_ReqLogin_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.server.message.ReqLoginOuterClass.internal_static_ReqLogin_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.server.message.ReqLoginOuterClass.ReqLogin.class, com.game.pb.server.message.ReqLoginOuterClass.ReqLogin.Builder.class);
    }

    public static final int UID_FIELD_NUMBER = 1;
    private volatile java.lang.Object uid_;
    /**
     * <code>string uid = 1;</code>
     */
    public java.lang.String getUid() {
      java.lang.Object ref = uid_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        uid_ = s;
        return s;
      }
    }
    /**
     * <code>string uid = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUidBytes() {
      java.lang.Object ref = uid_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PARAM_FIELD_NUMBER = 2;
    private volatile java.lang.Object param_;
    /**
     * <code>string param = 2;</code>
     */
    public java.lang.String getParam() {
      java.lang.Object ref = param_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        param_ = s;
        return s;
      }
    }
    /**
     * <code>string param = 2;</code>
     */
    public com.google.protobuf.ByteString
        getParamBytes() {
      java.lang.Object ref = param_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        param_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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
      if (!getUidBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, uid_);
      }
      if (!getParamBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, param_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getUidBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, uid_);
      }
      if (!getParamBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, param_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.game.pb.server.message.ReqLoginOuterClass.ReqLogin)) {
        return super.equals(obj);
      }
      com.game.pb.server.message.ReqLoginOuterClass.ReqLogin other = (com.game.pb.server.message.ReqLoginOuterClass.ReqLogin) obj;

      boolean result = true;
      result = result && getUid()
          .equals(other.getUid());
      result = result && getParam()
          .equals(other.getParam());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + UID_FIELD_NUMBER;
      hash = (53 * hash) + getUid().hashCode();
      hash = (37 * hash) + PARAM_FIELD_NUMBER;
      hash = (53 * hash) + getParam().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parseFrom(
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
    public static Builder newBuilder(com.game.pb.server.message.ReqLoginOuterClass.ReqLogin prototype) {
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
     * Protobuf type {@code ReqLogin}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ReqLogin)
        com.game.pb.server.message.ReqLoginOuterClass.ReqLoginOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.server.message.ReqLoginOuterClass.internal_static_ReqLogin_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.server.message.ReqLoginOuterClass.internal_static_ReqLogin_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.server.message.ReqLoginOuterClass.ReqLogin.class, com.game.pb.server.message.ReqLoginOuterClass.ReqLogin.Builder.class);
      }

      // Construct using com.game.pb.server.message.ReqLoginOuterClass.ReqLogin.newBuilder()
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
        uid_ = "";

        param_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.server.message.ReqLoginOuterClass.internal_static_ReqLogin_descriptor;
      }

      public com.game.pb.server.message.ReqLoginOuterClass.ReqLogin getDefaultInstanceForType() {
        return com.game.pb.server.message.ReqLoginOuterClass.ReqLogin.getDefaultInstance();
      }

      public com.game.pb.server.message.ReqLoginOuterClass.ReqLogin build() {
        com.game.pb.server.message.ReqLoginOuterClass.ReqLogin result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.server.message.ReqLoginOuterClass.ReqLogin buildPartial() {
        com.game.pb.server.message.ReqLoginOuterClass.ReqLogin result = new com.game.pb.server.message.ReqLoginOuterClass.ReqLogin(this);
        result.uid_ = uid_;
        result.param_ = param_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
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
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.server.message.ReqLoginOuterClass.ReqLogin) {
          return mergeFrom((com.game.pb.server.message.ReqLoginOuterClass.ReqLogin)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.server.message.ReqLoginOuterClass.ReqLogin other) {
        if (other == com.game.pb.server.message.ReqLoginOuterClass.ReqLogin.getDefaultInstance()) return this;
        if (!other.getUid().isEmpty()) {
          uid_ = other.uid_;
          onChanged();
        }
        if (!other.getParam().isEmpty()) {
          param_ = other.param_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
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
        com.game.pb.server.message.ReqLoginOuterClass.ReqLogin parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.server.message.ReqLoginOuterClass.ReqLogin) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object uid_ = "";
      /**
       * <code>string uid = 1;</code>
       */
      public java.lang.String getUid() {
        java.lang.Object ref = uid_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          uid_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string uid = 1;</code>
       */
      public com.google.protobuf.ByteString
          getUidBytes() {
        java.lang.Object ref = uid_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          uid_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string uid = 1;</code>
       */
      public Builder setUid(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        uid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string uid = 1;</code>
       */
      public Builder clearUid() {
        
        uid_ = getDefaultInstance().getUid();
        onChanged();
        return this;
      }
      /**
       * <code>string uid = 1;</code>
       */
      public Builder setUidBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        uid_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object param_ = "";
      /**
       * <code>string param = 2;</code>
       */
      public java.lang.String getParam() {
        java.lang.Object ref = param_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          param_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string param = 2;</code>
       */
      public com.google.protobuf.ByteString
          getParamBytes() {
        java.lang.Object ref = param_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          param_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string param = 2;</code>
       */
      public Builder setParam(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        param_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string param = 2;</code>
       */
      public Builder clearParam() {
        
        param_ = getDefaultInstance().getParam();
        onChanged();
        return this;
      }
      /**
       * <code>string param = 2;</code>
       */
      public Builder setParamBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        param_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:ReqLogin)
    }

    // @@protoc_insertion_point(class_scope:ReqLogin)
    private static final com.game.pb.server.message.ReqLoginOuterClass.ReqLogin DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.game.pb.server.message.ReqLoginOuterClass.ReqLogin();
    }

    public static com.game.pb.server.message.ReqLoginOuterClass.ReqLogin getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ReqLogin>
        PARSER = new com.google.protobuf.AbstractParser<ReqLogin>() {
      public ReqLogin parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new ReqLogin(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ReqLogin> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ReqLogin> getParserForType() {
      return PARSER;
    }

    public com.game.pb.server.message.ReqLoginOuterClass.ReqLogin getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ReqLogin_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ReqLogin_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016ReqLogin.proto\"&\n\010ReqLogin\022\013\n\003uid\030\001 \001(" +
      "\t\022\r\n\005param\030\002 \001(\tB\034\n\032com.game.pb.server.m" +
      "essageb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_ReqLogin_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ReqLogin_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ReqLogin_descriptor,
        new java.lang.String[] { "Uid", "Param", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}