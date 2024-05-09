// Invoke SWIG from this directory, w/ `swig -java -package com.signalquest.swig.ntrip -outdir ../../java/com/signalquest/swig/ntrip ntrip.i`
%module ntrip

%include "various.i"  // for BYTE typemaps

%{
#include "NTRIPBuffer.h"

// NTRIP_GetPoll_Buffer() is optional, no longer needed, can use "Arrays.copyOf(PollBuffer, (int) poll.getLength());" or similar
//NTRIP_Parse_Return_t	NTRIP_GetPoll_Buffer(NTRIP_Parse_Context_t* Context, uint8_t *JavaByteArray, int MaxLength)
NTRIP_Parse_Return_t	NTRIP_GetPoll_Buffer(NTRIP_Parse_Context_t* Context, uint8_t *buffer, int length)
{
    NTRIP_Parse_Poll_t *P = &(Context->Poll);

    if( P->Status != NTRIP_MESSAGE_PENDING)  return(P->Status);
    if( P->Buffer == NULL)  return(NTRIP_NO_BUFFER);
    if( P->Length == 0)  return(NTRIP_NO_BUFFER);
//    if( P->Length > MaxLength)  return(NTRIP_BUFFER_OVERRUN);
    if( P->Length > length)  return(NTRIP_BUFFER_OVERRUN);

//    for(int i=0; i< P->Length; i++)
//        JavaByteArray[i] = P->Buffer[i];
    for(int i=0; i< P->Length; i++)
        buffer[i] = P->Buffer[i];
//TODO: Need to return Length! To MaxLength?
    return(P->Status);
}

//Testing...
//NTRIP_Parse_Return_t	NTRIP_GetPoll_Buffer_2(NTRIP_Parse_Context_t* Context, uint8_t *JavaByteArray, int MaxLength)
NTRIP_Parse_Return_t	NTRIP_GetPoll_Buffer_2(NTRIP_Parse_Context_t* Context, uint8_t *buffer, int length)
{
    NTRIP_Parse_Poll_t *P = &(Context->Poll);

    if( P->Status != NTRIP_MESSAGE_PENDING)  return(P->Status);
    if( P->Buffer == NULL)  return(NTRIP_NO_BUFFER);
    if( P->Length == 0)  return(NTRIP_NO_BUFFER);
//    if( P->Length > MaxLength)  return(NTRIP_BUFFER_OVERRUN);
    if( P->Length > length)  return(NTRIP_BUFFER_OVERRUN);

//    for(int i=0; i< P->Length; i++)
//        JavaByteArray[i] = P->Buffer[i];
    for(int i=0; i< P->Length; i++)
        buffer[i] = P->Buffer[i];

    return(P->Status);
}

////unsigned char (*)[7]
//uint8_t *getAccel_2()   //ought to declare size of returned byte[] array here, but typemaps declare it.
//{
//    static  uint8_t x[8] = {1,2,3}; //Needs to be static? Not allocated off stack? or Global? Or?
////    uint8_t x[8] = {1,2,3};  //NOTE: size returned need to match typemaps below  // size not declared here, lets' see what breaks with mismatch between size declared in typemaps and here.
//
//    x[0] = 0;
//    x[1] = 1;
//
//    x[4] = 7;   //This might/should break something since x isn't big enough!
//
//    return((uint8_t *)(&x));
//}
//
//
//uint8_t *getAccel_3(uint8_t *buffer, int length)
//{
//    static  uint8_t x[] = {1,2,3,4};
//
//    x[3] = 5;
//    x[2] = buffer[0];
//    x[1] = buffer[1];
//    x[0] = buffer[2];
//
////    return(&x);
//    return((uint8_t *)(&x));
//}
//
//char *getFirst_HTTP_Reply()
//{
//    static const char string[] = "Hello!";
//
//    return(&string);
////    return("Hello!");
//}
//
//char *getFirst_HTTP_Reply2(NTRIP_Header_Info_t *header)
//{
//    static const char string[] = "Hello!";
//
//
////    return("Hello!");
////    return(&string);
//    return(header->First_HTTP_Reply);
//}
//
////'char* myFunc2(void)' returns a Java String   https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/types.html#modified_utf_8_strings
//char* myFunc2(void) //'char* myFunc2(void)' returns a Java String
//{
//    return("Hello!");   //implicitly static and constant
//}

int getPollIndex(NTRIP_Parse_Context_t *context)
{
    if(context == NULL)  return(0); // Do no harm

    if(context->WorkingBuffer == NULL)  return(0); // Do no harm
    if(context->Poll.Buffer == NULL)  return(0); // Do no harm
    if(((context->Poll.Buffer - context->WorkingBuffer)) < 0)  return(0); // Do no harm
    if(((context->Poll.Buffer - context->WorkingBuffer)) > context->Length)  return(0); // Do no harm

    int Index = (context->Poll.Buffer - context->WorkingBuffer);

    return(Index);
}

int getRTCMID(uint8_t *buffer, int length)
{
//    uint16_t RTCM_ID = ((data->om_data[3] << 4) | (data->om_data[4] >> 4));

    if(length < (4+1))  return -1;  //  BAD LENGTH

    uint16_t RTCM_ID = ((buffer[3] << 4) | (buffer[4] >> 4)) & 0x0FFF;  // 12 bit ID's
    // Consider returning Sub ID's? In a different function?

    return((int)RTCM_ID);
}

int getRTCMLength(uint8_t *buffer, int length)
{
//    uint16_t Length  = (((data->om_data[1] & 0x03) << 8) | data->om_data[2]);

    if(length < (2+1))  return -1;  //  BAD LENGTH

    uint16_t RTCMLength = (((buffer[1] & 0x03) << 8) | buffer[2]);

    return((int)RTCMLength);
}

int getRTCMCRC(uint8_t *buffer, int length)
{
//    uint32_t MsgCRC  = (((uint32_t)data->om_data[3 + Length] << 16) | (data->om_data[3 + Length + 1] << 8) | data->om_data[3 + Length + 2]);

    if(length < (2+1))  return -1;  //  BAD LENGTH

    uint16_t RTCMLength = (((buffer[1] & 0x03) << 8) | buffer[2]);

    if(length < (3 + RTCMLength + 3))  return -1;  //  BAD LENGTH

    uint32_t MsgCRC = (((uint32_t)buffer[3 + RTCMLength] << 16) | (buffer[3 + RTCMLength + 1] << 8) | buffer[3 + RTCMLength + 2]);

    return(MsgCRC);
}


%}

//Following line is mapping for "NTRIP_Parse_Return_t	NTRIP_Parse_Init(NTRIP_Parse_Context_t *Context, uint8_t *WorkingBuffer, size_t length, NTRIP_Parse_Call_Back_t	*Callback)"
%apply (char* BYTE) { (uint8_t *JavaByteArray) }; //NOTE: JavaByteArray is an SQ construct

%include "carrays.i"

%include "NTRIPBuffer.h"

//NTRIP_Parse_Return_t	NTRIP_GetPoll_Buffer(NTRIP_Parse_Context_t* Context, uint8_t *JavaByteArray, int MaxLength);
//NTRIP_Parse_Return_t	NTRIP_GetPoll_Buffer_2(NTRIP_Parse_Context_t* Context, uint8_t *JavaByteArray, int MaxLength);

//------------

// Input Type Mapping for < uint8_t *buffer, int length >
%typemap(jtype) (uint8_t *buffer, int length) "byte[]"
%typemap(jstype) (uint8_t *buffer, int length) "byte[]"
%typemap(jni) (uint8_t *buffer, int length) "jbyteArray"
%typemap(javain) (uint8_t *buffer, int length) "$javainput"

%typemap(in,numinputs=1) (uint8_t *buffer, int length) {
$1 = JCALL2(GetByteArrayElements, jenv, $input, NULL);
$2 = JCALL1(GetArrayLength, jenv, $input);
}
//------------

////------------// Output Type Mapping for < int *xxx() >
//%typemap(jni) int* "jintArray"
//%typemap(jstype) int* "int[]"
//%typemap(jtype) int* "int[]"
//
//%typemap(javaout) int* {
//return $jnicall;
//}
////------------


//------------
//%typemap(out) int *getAccel_2 {
//        $result = JCALL1(SetByteArrayRegion, jenv, 3);
//        JCALL4(SetByteArrayRegion, jenv, $result, 0, 3, (const signed char*)$1);
//}
//------------

//------------
// Output Type Mapping for < uint8_t *xxx() >
%typemap(jni) uint8_t* "jbyteArray"
%typemap(jstype) uint8_t* "byte[]"
%typemap(jtype) uint8_t* "byte[]"

%typemap(javaout) uint8_t* {
return $jnicall;
}

//Remember to set size of returned byte[] in JCALL1/4 lines below
%typemap(out) uint8_t *getAccel_2 {
        $result = JCALL1(NewByteArray, jenv, 8);
        JCALL4(SetByteArrayRegion, jenv, $result, 0, 8, (const signed char*)$1);
}

//Remember to set size of returned byte[] in JCALL1/4 lines below
%typemap(out) uint8_t *getAccel_3 {
        $result = JCALL1(NewByteArray, jenv, 3);
        JCALL4(SetByteArrayRegion, jenv, $result, 0, 3, (const signed char*)$1);
}


//Reference: https://android.googlesource.com/platform/hardware/bsp/intel/+/bf215a6d8d17433b6ada853f0f9ba29ebd07b505/peripheral/libupm/docs/creating_java_bindings.md#wrapping-unbound-c-arrays-with-java-arrays-if-array-is-output
//NOTE: !!!! There is a difference between TYPE *name and TYPE * name in typemaps!!!!!
%typemap(jtype) (uint8_t *buffer, int length) "byte[]"
%typemap(jstype) (uint8_t *buffer, int length) "byte[]"
%typemap(jni) (uint8_t *buffer, int length) "jbyteArray"
%typemap(javain) (uint8_t *buffer, int length) "$javainput"

%typemap(in,numinputs=1) (uint8_t *buffer, int length) {
$1 = JCALL2(GetByteArrayElements, jenv, $input, NULL);
$2 = JCALL1(GetArrayLength, jenv, $input);
}



//Incompatible pointer types returning 'uint8_t (*)[3]' (aka 'unsigned char (*)[3]') from a function with result type 'uint8_t *' (aka 'unsigned char *')
//------------

NTRIP_Parse_Return_t	NTRIP_GetPoll_Buffer(NTRIP_Parse_Context_t* Context, uint8_t *buffer, int length);
NTRIP_Parse_Return_t	NTRIP_GetPoll_Buffer_2(NTRIP_Parse_Context_t* Context, uint8_t *buffer, int length);

int getPollIndex(NTRIP_Parse_Context_t *context);

int getRTCMID(uint8_t *buffer, int length);
int getRTCMLength(uint8_t *buffer, int length);
int getRTCMCRC(uint8_t *buffer, int length);


//uint8_t *getAccel_2();
//uint8_t *getAccel_3(uint8_t *buffer, int length);
//char *getFirst_HTTP_Reply();
////'char* myFunc2(void)' returns a Java String   https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/types.html#modified_utf_8_strings
//char* myFunc2(void); //'char* myFunc2(void)' returns a Java String
//char *getFirst_HTTP_Reply2(NTRIP_Header_Info_t *header);

//------------
//Ref: from java.swg:
///* The jni, jtype and jstype typemaps work together and so there should be one of each.
// * The jni typemap contains the JNI type used in the JNI (C/C++) code.
// * The jtype typemap contains the Java type used in the JNI intermediary class.
// * The jstype typemap contains the Java type used in the Java proxy classes, type wrapper classes and module class. */
//
//Ref: from various.i :
///*
// * char *BYTE typemaps.
// * These are input typemaps for mapping a Java byte[] array to a C char array.
// * Note that as a Java array is used and thus passed by reference, the C routine
// * can return data to Java via the parameter.
// *
// * Example usage wrapping:
// *   void foo(char *array);
// *
// * Java usage:
// *   byte b[] = new byte[20];
// *   modulename.foo(b);
// */

//Keep for reference:
// from https://iotdk.intel.com/docs/master/upm/md_docs_creating_java_bindings.html
// not sure how to hook this up, but should let us use byte[] on the Java side
//%typemap(jtype) (uint8_t *buffer, int length) "byte[]"
//%typemap(jstype) (uint8_t *buffer, int length) "byte[]"
//%typemap(jni) (uint8_t *buffer, int length) "jbyteArray"
//%typemap(javain) (uint8_t *buffer, int length) "$javainput"
//%typemap(in,numinputs=1) (uint8_t *buffer, int length) {
//    $1 = JCALL2(GetByteArrayElements, jenv, $input, NULL);
//    $2 = JCALL1(GetArrayLength, jenv, $input);
//}


//ref: https://sourceforge.net/p/swig/mailman/message/9897203/
//ref: https://android.googlesource.com/platform/hardware/bsp/intel/+/bf215a6d8d17433b6ada853f0f9ba29ebd07b505/peripheral/libupm/docs/creating_java_bindings.md
//ref: https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/functions.html
//ref: https://stackoverflow.com/questions/7448294/returning-an-array-from-c-to-java-with-swig?rq=4
//ref: https://stackoverflow.com/questions/8404850/modifying-swig-interface-file-to-support-c-void-and-structure-return-types/8406220#8406220
//ref: https://stackoverflow.com/questions/8142028/how-to-map-sockaddr-in-c-structure-to-java-using-swig/8148744#8148744
//ref: https://stackoverflow.com/questions/5670862/bytebuffer-allocate-vs-bytebuffer-allocatedirect
//  "...The memory-storage areas of direct buffers are not subject to garbage collection because they are outside the standard JVM heap...."

