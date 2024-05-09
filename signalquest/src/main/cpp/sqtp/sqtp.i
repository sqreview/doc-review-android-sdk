// call, from this dir, w/ `swig -java -package com.signalquest.swig.sqtp -outdir ../../java/com/signalquest/swig/sqtp sqtp.i`
%module sqtp

%{

//#include "stdint.h"

#include "os_target.h"
//#include "../sqsp/sqspTypes.h"    //DO NOT enable: We'll pull this library's coding in using import statements
//#include "../sqsp/sqspTypeWrap.h" //DO NOT enable: We'll pull this library's coding in using import statements
#include "sqtpFrame.h"
#include "sqtpStatus.h"
#include "sqtpSubframeIDs.h"


//// Deprecated? Functional?
////SqtpStatus_t sqtpFrameWriterFinalizeData(SqtpFrameWriter_t *FW, uint8_t *JavaByteArray, int Length)
//SqtpStatus_t sqtpFrameWriterFinalizeData(SqtpFrameWriter_t *FW, uint8_t *buffer, int length)
//{
//    if( FW == NULL)  return(-11);
//    if( buffer == NULL)  return(-10);
//    if( length <= 0)  return(SQTP_STATUS_FRAME_BAD_LENGTH);
//    if( length < FW->index)  return(SQTP_STATUS_FRAME_BAD_LENGTH);
//
//    SqtpStatus_t status = sqtpFrameWriterFinalize(FW);
//    if(status < SQTP_STATUS_SUCCESS) return(status);
//
//    for(int i=0; i < FW->index; i++)
//        buffer[i] = FW->frame[i];
//
//    return(SQTP_STATUS_SUCCESS);
//}

//// Deprecated? Functional?
////SqtpStatus_t GetSubFramePayloadByteArray(SqtpSubframe_t *SubFrame, uint8_t *JavaByteArray, int Length)
////SqtpStatus_t GetSubFramePayloadByteArray(SqtpSubframe_t *SubFrame, uint8_t *buffer, int length)
//SqtpStatus_t GetSubFramePayloadByteArray(SqtpSubframe_t SubFrame, uint8_t *buffer, int length)
//{
//    if( &SubFrame == NULL)  return(-11);
//    if( buffer == NULL)  return(-10);
//    if( length <= 0)  return(SQTP_STATUS_FRAME_BAD_LENGTH);
//    if( SubFrame.length == 0)  return(SQTP_STATUS_FRAME_BAD_LENGTH);
//    if( length != SubFrame.length)  return(SQTP_STATUS_FRAME_BAD_LENGTH);
//
//    for(int i=0; i < SubFrame.length; i++)
//        buffer[i] = SubFrame.payload[i];
//
//    return(SQTP_STATUS_SUCCESS);
//}


//SqtpStatus_t GetSubFrameByteArray(SqtpFrameReader_t *Reader, uint8_t *JavaByteArray, int Length)
SqtpStatus_t GetSubFrameByteArray(SqtpFrameReader_t *Reader, uint8_t *buffer, int length)
{
    if( Reader == NULL)  return(-11);
    if( buffer == NULL)  return(-10);
    if( length <= 0)  return(SQTP_STATUS_FRAME_BAD_LENGTH);
    if( Reader->subframe.length == 0)  return(SQTP_STATUS_FRAME_BAD_LENGTH);
    if( length != Reader->subframe.length)  return(SQTP_STATUS_FRAME_BAD_LENGTH);

    for(int i=0; i < Reader->subframe.length; i++)
        buffer[i] = Reader->subframe.payload[i];

    return(SQTP_STATUS_SUCCESS);
}

%}

%include "various.i"  // for BYTE typemaps

//Following line is mapping for "uint8_t *JavaByteArray" to 'char* BYTE'
%apply (char* BYTE) { (uint8_t *JavaByteArray) }; // NOTE: JavaByteArray is an SQ construct using BYTE typemaps from "various.i"

%include "carrays.i"

//Put our 'C' Function prototypes here
//#include "stdint.h"

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

%include "os_target.h"
// Make sure SWIG doesn't mess up structure alignments in our header files,
// from https://stackoverflow.com/a/17890717/2199492
#ifdef PACKED_ATTR
    #undef PACKED_ATTR
    #define PACKED_ATTR
#endif

//%include "../sqsp/sqspTypes.h"    //DO NOT enable: We'll pull this library's coding in using import statements
//%include "../sqsp/sqspTypeWrap.h" //DO NOT enable: We'll pull this library's coding in using import statements
%include "sqtpFrame.h"
%include "sqtpStatus.h"
%include "sqtpSubframeIDs.h"

//SqtpStatus_t sqtpFrameWriterFinalizeData(SqtpFrameWriter_t *FW, uint8_t *buffer, int length);
//SqtpStatus_t GetSubFramePayloadByteArray(SqtpSubframe_t SubFrame, uint8_t *buffer, int length);
SqtpStatus_t GetSubFrameByteArray(SqtpFrameReader_t *Reader, uint8_t *buffer, int length);


