// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: main/java/DeviceRPC/decryptiondevice.proto

package io.grpc.decryptiondevice;

public final class DecryptionDeviceProto {
  private DecryptionDeviceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_decryptiondevice_DecryptionRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_decryptiondevice_DecryptionRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_decryptiondevice_Record_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_decryptiondevice_Record_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_decryptiondevice_RootTreeHashRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_decryptiondevice_RootTreeHashRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_decryptiondevice_RootTreeHash_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_decryptiondevice_RootTreeHash_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_decryptiondevice_PublicKeyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_decryptiondevice_PublicKeyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_decryptiondevice_Quote_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_decryptiondevice_Quote_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n*main/java/DeviceRPC/decryptiondevice.p" +
      "roto\022\020decryptiondevice\"Z\n\021DecryptionRequ" +
      "est\022\022\n\nciphertext\030\001 \001(\014\022\027\n\017proofOfPresen" +
      "ce\030\002 \001(\t\022\030\n\020proofOfExtension\030\003 \001(\t\"\033\n\006Re" +
      "cord\022\021\n\tplaintext\030\001 \001(\014\"$\n\023RootTreeHashR" +
      "equest\022\r\n\005nonce\030\001 \001(\014\"7\n\014RootTreeHash\022\013\n" +
      "\003rth\030\001 \001(\014\022\r\n\005nonce\030\002 \001(\014\022\013\n\003sig\030\003 \001(\014\"!" +
      "\n\020PublicKeyRequest\022\r\n\005nonce\030\001 \001(\014\"N\n\005Quo" +
      "te\022\r\n\005quote\030\001 \001(\t\022\031\n\021RSA_EncryptionKey\030\002" +
      " \001(\014\022\033\n\023RSA_VerificationKey\030\003 \001(\0142\217\002\n\020De",
      "cryptionDevice\022P\n\rDecryptRecord\022#.decryp" +
      "tiondevice.DecryptionRequest\032\030.decryptio" +
      "ndevice.Record\"\000\022Z\n\017GetRootTreeHash\022%.de" +
      "cryptiondevice.RootTreeHashRequest\032\036.dec" +
      "ryptiondevice.RootTreeHash\"\000\022M\n\014GetPubli" +
      "cKey\022\".decryptiondevice.PublicKeyRequest" +
      "\032\027.decryptiondevice.Quote\"\000B3\n\030io.grpc.d" +
      "ecryptiondeviceB\025DecryptionDeviceProtoP\001" +
      "b\006proto3"
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
    internal_static_decryptiondevice_DecryptionRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_decryptiondevice_DecryptionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_decryptiondevice_DecryptionRequest_descriptor,
        new java.lang.String[] { "Ciphertext", "ProofOfPresence", "ProofOfExtension", });
    internal_static_decryptiondevice_Record_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_decryptiondevice_Record_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_decryptiondevice_Record_descriptor,
        new java.lang.String[] { "Plaintext", });
    internal_static_decryptiondevice_RootTreeHashRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_decryptiondevice_RootTreeHashRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_decryptiondevice_RootTreeHashRequest_descriptor,
        new java.lang.String[] { "Nonce", });
    internal_static_decryptiondevice_RootTreeHash_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_decryptiondevice_RootTreeHash_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_decryptiondevice_RootTreeHash_descriptor,
        new java.lang.String[] { "Rth", "Nonce", "Sig", });
    internal_static_decryptiondevice_PublicKeyRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_decryptiondevice_PublicKeyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_decryptiondevice_PublicKeyRequest_descriptor,
        new java.lang.String[] { "Nonce", });
    internal_static_decryptiondevice_Quote_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_decryptiondevice_Quote_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_decryptiondevice_Quote_descriptor,
        new java.lang.String[] { "Quote", "RSAEncryptionKey", "RSAVerificationKey", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}