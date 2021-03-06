package yyz.njupt.server;

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: LoginReq.proto

public final class LoginReqProto {
  private LoginReqProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface LoginReqOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 recCode = 2;
    /**
     * <code>required int32 recCode = 2;</code>
     */
    boolean hasRecCode();
    /**
     * <code>required int32 recCode = 2;</code>
     */
    int getRecCode();

    // required string recContend = 1;
    /**
     * <code>required string recContend = 1;</code>
     */
    boolean hasRecContend();
    /**
     * <code>required string recContend = 1;</code>
     */
    java.lang.String getRecContend();
    /**
     * <code>required string recContend = 1;</code>
     */
    com.google.protobuf.ByteString
        getRecContendBytes();

    // optional string userName = 3;
    /**
     * <code>optional string userName = 3;</code>
     */
    boolean hasUserName();
    /**
     * <code>optional string userName = 3;</code>
     */
    java.lang.String getUserName();
    /**
     * <code>optional string userName = 3;</code>
     */
    com.google.protobuf.ByteString
        getUserNameBytes();

    // optional string passwd = 4;
    /**
     * <code>optional string passwd = 4;</code>
     */
    boolean hasPasswd();
    /**
     * <code>optional string passwd = 4;</code>
     */
    java.lang.String getPasswd();
    /**
     * <code>optional string passwd = 4;</code>
     */
    com.google.protobuf.ByteString
        getPasswdBytes();

    // optional int32 UID = 5;
    /**
     * <code>optional int32 UID = 5;</code>
     */
    boolean hasUID();
    /**
     * <code>optional int32 UID = 5;</code>
     */
    int getUID();

    // repeated int32 friendsUIDs = 6;
    /**
     * <code>repeated int32 friendsUIDs = 6;</code>
     */
    java.util.List<java.lang.Integer> getFriendsUIDsList();
    /**
     * <code>repeated int32 friendsUIDs = 6;</code>
     */
    int getFriendsUIDsCount();
    /**
     * <code>repeated int32 friendsUIDs = 6;</code>
     */
    int getFriendsUIDs(int index);
  }
  /**
   * Protobuf type {@code LoginReq}
   */
  public static final class LoginReq extends
      com.google.protobuf.GeneratedMessage
      implements LoginReqOrBuilder {
    // Use LoginReq.newBuilder() to construct.
    private LoginReq(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private LoginReq(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final LoginReq defaultInstance;
    public static LoginReq getDefaultInstance() {
      return defaultInstance;
    }

    public LoginReq getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private LoginReq(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
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
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000002;
              recContend_ = input.readBytes();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000001;
              recCode_ = input.readInt32();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              userName_ = input.readBytes();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              passwd_ = input.readBytes();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              uID_ = input.readInt32();
              break;
            }
            case 48: {
              if (!((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
                friendsUIDs_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000020;
              }
              friendsUIDs_.add(input.readInt32());
              break;
            }
            case 50: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000020) == 0x00000020) && input.getBytesUntilLimit() > 0) {
                friendsUIDs_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000020;
              }
              while (input.getBytesUntilLimit() > 0) {
                friendsUIDs_.add(input.readInt32());
              }
              input.popLimit(limit);
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
          friendsUIDs_ = java.util.Collections.unmodifiableList(friendsUIDs_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return LoginReqProto.internal_static_LoginReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return LoginReqProto.internal_static_LoginReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              LoginReqProto.LoginReq.class, LoginReqProto.LoginReq.Builder.class);
    }

    public static com.google.protobuf.Parser<LoginReq> PARSER =
        new com.google.protobuf.AbstractParser<LoginReq>() {
      public LoginReq parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new LoginReq(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<LoginReq> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 recCode = 2;
    public static final int RECCODE_FIELD_NUMBER = 2;
    private int recCode_;
    /**
     * <code>required int32 recCode = 2;</code>
     */
    public boolean hasRecCode() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 recCode = 2;</code>
     */
    public int getRecCode() {
      return recCode_;
    }

    // required string recContend = 1;
    public static final int RECCONTEND_FIELD_NUMBER = 1;
    private java.lang.Object recContend_;
    /**
     * <code>required string recContend = 1;</code>
     */
    public boolean hasRecContend() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string recContend = 1;</code>
     */
    public java.lang.String getRecContend() {
      java.lang.Object ref = recContend_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          recContend_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string recContend = 1;</code>
     */
    public com.google.protobuf.ByteString
        getRecContendBytes() {
      java.lang.Object ref = recContend_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        recContend_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional string userName = 3;
    public static final int USERNAME_FIELD_NUMBER = 3;
    private java.lang.Object userName_;
    /**
     * <code>optional string userName = 3;</code>
     */
    public boolean hasUserName() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string userName = 3;</code>
     */
    public java.lang.String getUserName() {
      java.lang.Object ref = userName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          userName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string userName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getUserNameBytes() {
      java.lang.Object ref = userName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional string passwd = 4;
    public static final int PASSWD_FIELD_NUMBER = 4;
    private java.lang.Object passwd_;
    /**
     * <code>optional string passwd = 4;</code>
     */
    public boolean hasPasswd() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string passwd = 4;</code>
     */
    public java.lang.String getPasswd() {
      java.lang.Object ref = passwd_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          passwd_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string passwd = 4;</code>
     */
    public com.google.protobuf.ByteString
        getPasswdBytes() {
      java.lang.Object ref = passwd_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        passwd_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional int32 UID = 5;
    public static final int UID_FIELD_NUMBER = 5;
    private int uID_;
    /**
     * <code>optional int32 UID = 5;</code>
     */
    public boolean hasUID() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int32 UID = 5;</code>
     */
    public int getUID() {
      return uID_;
    }

    // repeated int32 friendsUIDs = 6;
    public static final int FRIENDSUIDS_FIELD_NUMBER = 6;
    private java.util.List<java.lang.Integer> friendsUIDs_;
    /**
     * <code>repeated int32 friendsUIDs = 6;</code>
     */
    public java.util.List<java.lang.Integer>
        getFriendsUIDsList() {
      return friendsUIDs_;
    }
    /**
     * <code>repeated int32 friendsUIDs = 6;</code>
     */
    public int getFriendsUIDsCount() {
      return friendsUIDs_.size();
    }
    /**
     * <code>repeated int32 friendsUIDs = 6;</code>
     */
    public int getFriendsUIDs(int index) {
      return friendsUIDs_.get(index);
    }

    private void initFields() {
      recCode_ = 0;
      recContend_ = "";
      userName_ = "";
      passwd_ = "";
      uID_ = 0;
      friendsUIDs_ = java.util.Collections.emptyList();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasRecCode()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasRecContend()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(1, getRecContendBytes());
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(2, recCode_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getUserNameBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, getPasswdBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, uID_);
      }
      for (int i = 0; i < friendsUIDs_.size(); i++) {
        output.writeInt32(6, friendsUIDs_.get(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getRecContendBytes());
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, recCode_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getUserNameBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, getPasswdBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, uID_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < friendsUIDs_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(friendsUIDs_.get(i));
        }
        size += dataSize;
        size += 1 * getFriendsUIDsList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static LoginReqProto.LoginReq parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static LoginReqProto.LoginReq parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static LoginReqProto.LoginReq parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static LoginReqProto.LoginReq parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static LoginReqProto.LoginReq parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static LoginReqProto.LoginReq parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static LoginReqProto.LoginReq parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static LoginReqProto.LoginReq parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static LoginReqProto.LoginReq parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static LoginReqProto.LoginReq parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(LoginReqProto.LoginReq prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code LoginReq}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements LoginReqProto.LoginReqOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return LoginReqProto.internal_static_LoginReq_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return LoginReqProto.internal_static_LoginReq_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                LoginReqProto.LoginReq.class, LoginReqProto.LoginReq.Builder.class);
      }

      // Construct using LoginReqProto.LoginReq.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        recCode_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        recContend_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        userName_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        passwd_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        uID_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        friendsUIDs_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000020);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return LoginReqProto.internal_static_LoginReq_descriptor;
      }

      public LoginReqProto.LoginReq getDefaultInstanceForType() {
        return LoginReqProto.LoginReq.getDefaultInstance();
      }

      public LoginReqProto.LoginReq build() {
        LoginReqProto.LoginReq result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public LoginReqProto.LoginReq buildPartial() {
        LoginReqProto.LoginReq result = new LoginReqProto.LoginReq(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.recCode_ = recCode_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.recContend_ = recContend_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.userName_ = userName_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.passwd_ = passwd_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.uID_ = uID_;
        if (((bitField0_ & 0x00000020) == 0x00000020)) {
          friendsUIDs_ = java.util.Collections.unmodifiableList(friendsUIDs_);
          bitField0_ = (bitField0_ & ~0x00000020);
        }
        result.friendsUIDs_ = friendsUIDs_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof LoginReqProto.LoginReq) {
          return mergeFrom((LoginReqProto.LoginReq)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(LoginReqProto.LoginReq other) {
        if (other == LoginReqProto.LoginReq.getDefaultInstance()) return this;
        if (other.hasRecCode()) {
          setRecCode(other.getRecCode());
        }
        if (other.hasRecContend()) {
          bitField0_ |= 0x00000002;
          recContend_ = other.recContend_;
          onChanged();
        }
        if (other.hasUserName()) {
          bitField0_ |= 0x00000004;
          userName_ = other.userName_;
          onChanged();
        }
        if (other.hasPasswd()) {
          bitField0_ |= 0x00000008;
          passwd_ = other.passwd_;
          onChanged();
        }
        if (other.hasUID()) {
          setUID(other.getUID());
        }
        if (!other.friendsUIDs_.isEmpty()) {
          if (friendsUIDs_.isEmpty()) {
            friendsUIDs_ = other.friendsUIDs_;
            bitField0_ = (bitField0_ & ~0x00000020);
          } else {
            ensureFriendsUIDsIsMutable();
            friendsUIDs_.addAll(other.friendsUIDs_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasRecCode()) {
          
          return false;
        }
        if (!hasRecContend()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        LoginReqProto.LoginReq parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (LoginReqProto.LoginReq) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 recCode = 2;
      private int recCode_ ;
      /**
       * <code>required int32 recCode = 2;</code>
       */
      public boolean hasRecCode() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 recCode = 2;</code>
       */
      public int getRecCode() {
        return recCode_;
      }
      /**
       * <code>required int32 recCode = 2;</code>
       */
      public Builder setRecCode(int value) {
        bitField0_ |= 0x00000001;
        recCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 recCode = 2;</code>
       */
      public Builder clearRecCode() {
        bitField0_ = (bitField0_ & ~0x00000001);
        recCode_ = 0;
        onChanged();
        return this;
      }

      // required string recContend = 1;
      private java.lang.Object recContend_ = "";
      /**
       * <code>required string recContend = 1;</code>
       */
      public boolean hasRecContend() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string recContend = 1;</code>
       */
      public java.lang.String getRecContend() {
        java.lang.Object ref = recContend_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          recContend_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string recContend = 1;</code>
       */
      public com.google.protobuf.ByteString
          getRecContendBytes() {
        java.lang.Object ref = recContend_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          recContend_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string recContend = 1;</code>
       */
      public Builder setRecContend(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        recContend_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string recContend = 1;</code>
       */
      public Builder clearRecContend() {
        bitField0_ = (bitField0_ & ~0x00000002);
        recContend_ = getDefaultInstance().getRecContend();
        onChanged();
        return this;
      }
      /**
       * <code>required string recContend = 1;</code>
       */
      public Builder setRecContendBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        recContend_ = value;
        onChanged();
        return this;
      }

      // optional string userName = 3;
      private java.lang.Object userName_ = "";
      /**
       * <code>optional string userName = 3;</code>
       */
      public boolean hasUserName() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public java.lang.String getUserName() {
        java.lang.Object ref = userName_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          userName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public com.google.protobuf.ByteString
          getUserNameBytes() {
        java.lang.Object ref = userName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          userName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public Builder setUserName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        userName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public Builder clearUserName() {
        bitField0_ = (bitField0_ & ~0x00000004);
        userName_ = getDefaultInstance().getUserName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string userName = 3;</code>
       */
      public Builder setUserNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        userName_ = value;
        onChanged();
        return this;
      }

      // optional string passwd = 4;
      private java.lang.Object passwd_ = "";
      /**
       * <code>optional string passwd = 4;</code>
       */
      public boolean hasPasswd() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional string passwd = 4;</code>
       */
      public java.lang.String getPasswd() {
        java.lang.Object ref = passwd_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          passwd_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string passwd = 4;</code>
       */
      public com.google.protobuf.ByteString
          getPasswdBytes() {
        java.lang.Object ref = passwd_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          passwd_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string passwd = 4;</code>
       */
      public Builder setPasswd(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        passwd_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string passwd = 4;</code>
       */
      public Builder clearPasswd() {
        bitField0_ = (bitField0_ & ~0x00000008);
        passwd_ = getDefaultInstance().getPasswd();
        onChanged();
        return this;
      }
      /**
       * <code>optional string passwd = 4;</code>
       */
      public Builder setPasswdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        passwd_ = value;
        onChanged();
        return this;
      }

      // optional int32 UID = 5;
      private int uID_ ;
      /**
       * <code>optional int32 UID = 5;</code>
       */
      public boolean hasUID() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional int32 UID = 5;</code>
       */
      public int getUID() {
        return uID_;
      }
      /**
       * <code>optional int32 UID = 5;</code>
       */
      public Builder setUID(int value) {
        bitField0_ |= 0x00000010;
        uID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 UID = 5;</code>
       */
      public Builder clearUID() {
        bitField0_ = (bitField0_ & ~0x00000010);
        uID_ = 0;
        onChanged();
        return this;
      }

      // repeated int32 friendsUIDs = 6;
      private java.util.List<java.lang.Integer> friendsUIDs_ = java.util.Collections.emptyList();
      private void ensureFriendsUIDsIsMutable() {
        if (!((bitField0_ & 0x00000020) == 0x00000020)) {
          friendsUIDs_ = new java.util.ArrayList<java.lang.Integer>(friendsUIDs_);
          bitField0_ |= 0x00000020;
         }
      }
      /**
       * <code>repeated int32 friendsUIDs = 6;</code>
       */
      public java.util.List<java.lang.Integer>
          getFriendsUIDsList() {
        return java.util.Collections.unmodifiableList(friendsUIDs_);
      }
      /**
       * <code>repeated int32 friendsUIDs = 6;</code>
       */
      public int getFriendsUIDsCount() {
        return friendsUIDs_.size();
      }
      /**
       * <code>repeated int32 friendsUIDs = 6;</code>
       */
      public int getFriendsUIDs(int index) {
        return friendsUIDs_.get(index);
      }
      /**
       * <code>repeated int32 friendsUIDs = 6;</code>
       */
      public Builder setFriendsUIDs(
          int index, int value) {
        ensureFriendsUIDsIsMutable();
        friendsUIDs_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 friendsUIDs = 6;</code>
       */
      public Builder addFriendsUIDs(int value) {
        ensureFriendsUIDsIsMutable();
        friendsUIDs_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 friendsUIDs = 6;</code>
       */
      public Builder addAllFriendsUIDs(
          java.lang.Iterable<? extends java.lang.Integer> values) {
        ensureFriendsUIDsIsMutable();
        super.addAll(values, friendsUIDs_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 friendsUIDs = 6;</code>
       */
      public Builder clearFriendsUIDs() {
        friendsUIDs_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000020);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:LoginReq)
    }

    static {
      defaultInstance = new LoginReq(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:LoginReq)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_LoginReq_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_LoginReq_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016LoginReq.proto\"s\n\010LoginReq\022\017\n\007recCode\030" +
      "\002 \002(\005\022\022\n\nrecContend\030\001 \002(\t\022\020\n\010userName\030\003 " +
      "\001(\t\022\016\n\006passwd\030\004 \001(\t\022\013\n\003UID\030\005 \001(\005\022\023\n\013frie" +
      "ndsUIDs\030\006 \003(\005B\017B\rLoginReqProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_LoginReq_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_LoginReq_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_LoginReq_descriptor,
              new java.lang.String[] { "RecCode", "RecContend", "UserName", "Passwd", "UID", "FriendsUIDs", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
