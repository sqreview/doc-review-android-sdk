// Invoke SWIG from this dir, w/ `swig -java -package com.signalquest.swig.sqsp -outdir ../../java/com/signalquest/swig/sqsp sqsp.i`
%module sqsp

%{
#include "os_target.h"
#include "sqspTypes.h"
#include "sqspTypeWrap.h"


//Retain for technique example: (Currently unused)
 SqspStatus_t sqspStatusCopyCast( uint8_t *JavaByteArray )
{
    SqspStatus_t *x = (SqspStatus_t *)JavaByteArray;
    SqspStatus_t s = *x;
    return s;
}

%}

%include "various.i"  // for BYTE typemaps

//Following line is mapping for "uint8_t *JavaByteArray" to 'char* BYTE'
%apply (char* BYTE) { (uint8_t *JavaByteArray) }; // NOTE: JavaByteArray is an SQ construct using BYTE typemaps from "various.i"

%include "carrays.i"

%include "os_target.h"
// Make sure Swig doesn't mess up structure alignments,
// from https://stackoverflow.com/a/17890717/2199492
#ifdef PACKED_ATTR
    #undef PACKED_ATTR
    #define PACKED_ATTR
#endif

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


%include "sqspTypes.h"
%include "sqspTypeWrap.h"


//Retain for technique example: (Currently unused)
SqspStatus_t sqspStatusCopyCast( uint8_t *JavaByteArray );

//int SqspStatus_tByteArray     (uint8_t *buffer, int length,  SqspStatus_t *Status   );
