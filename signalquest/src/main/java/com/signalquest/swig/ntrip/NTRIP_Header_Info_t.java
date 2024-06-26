/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.ntrip;

public class NTRIP_Header_Info_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected NTRIP_Header_Info_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(NTRIP_Header_Info_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(NTRIP_Header_Info_t obj) {
    long ptr = 0;
    if (obj != null) {
      if (!obj.swigCMemOwn)
        throw new RuntimeException("Cannot release ownership as memory is not owned");
      ptr = obj.swigCPtr;
      obj.swigCMemOwn = false;
      obj.delete();
    }
    return ptr;
  }

  @SuppressWarnings({"deprecation", "removal"})
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ntripJNI.delete_NTRIP_Header_Info_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setError_Status(Error_Status_t value) {
    ntripJNI.NTRIP_Header_Info_t_Error_Status_set(swigCPtr, this, value.swigValue());
  }

  public Error_Status_t getError_Status() {
    return Error_Status_t.swigToEnum(ntripJNI.NTRIP_Header_Info_t_Error_Status_get(swigCPtr, this));
  }

  public void setAuthorized(boolean value) {
    ntripJNI.NTRIP_Header_Info_t_Authorized_set(swigCPtr, this, value);
  }

  public boolean getAuthorized() {
    return ntripJNI.NTRIP_Header_Info_t_Authorized_get(swigCPtr, this);
  }

  public void setCode(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Code_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getCode() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Code_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setShort_Text(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Short_Text_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getShort_Text() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Short_Text_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setDescription(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Description_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getDescription() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Description_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setFirst_HTTP_Reply(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_First_HTTP_Reply_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getFirst_HTTP_Reply() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_First_HTTP_Reply_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setAuthorization(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Authorization_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getAuthorization() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Authorization_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setCache_Control(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Cache_Control_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getCache_Control() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Cache_Control_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setConnection(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Connection_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getConnection() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Connection_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setContent_Length(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Content_Length_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getContent_Length() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Content_Length_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setContent_Type(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Content_Type_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getContent_Type() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Content_Type_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setDate(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Date_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getDate() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Date_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setHost(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Host_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getHost() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Host_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setServer(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Server_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getServer() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Server_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setPragma(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Pragma_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getPragma() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Pragma_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setTransfer_Encoding(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Transfer_Encoding_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getTransfer_Encoding() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Transfer_Encoding_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setWWW_Authenticate(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_WWW_Authenticate_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getWWW_Authenticate() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_WWW_Authenticate_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setNtrip_Version(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Ntrip_Version_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getNtrip_Version() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Ntrip_Version_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setNtrip_Flags(SWIGTYPE_p_uint8_t value) {
    ntripJNI.NTRIP_Header_Info_t_Ntrip_Flags_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getNtrip_Flags() {
    long cPtr = ntripJNI.NTRIP_Header_Info_t_Ntrip_Flags_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setCode_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Code_string_set(swigCPtr, this, value);
  }

  public String getCode_string() {
    return ntripJNI.NTRIP_Header_Info_t_Code_string_get(swigCPtr, this);
  }

  public void setShort_Text_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Short_Text_string_set(swigCPtr, this, value);
  }

  public String getShort_Text_string() {
    return ntripJNI.NTRIP_Header_Info_t_Short_Text_string_get(swigCPtr, this);
  }

  public void setDescription_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Description_string_set(swigCPtr, this, value);
  }

  public String getDescription_string() {
    return ntripJNI.NTRIP_Header_Info_t_Description_string_get(swigCPtr, this);
  }

  public void setFirst_HTTP_Reply_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_First_HTTP_Reply_string_set(swigCPtr, this, value);
  }

  public String getFirst_HTTP_Reply_string() {
    return ntripJNI.NTRIP_Header_Info_t_First_HTTP_Reply_string_get(swigCPtr, this);
  }

  public void setAuthorization_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Authorization_string_set(swigCPtr, this, value);
  }

  public String getAuthorization_string() {
    return ntripJNI.NTRIP_Header_Info_t_Authorization_string_get(swigCPtr, this);
  }

  public void setCache_Control_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Cache_Control_string_set(swigCPtr, this, value);
  }

  public String getCache_Control_string() {
    return ntripJNI.NTRIP_Header_Info_t_Cache_Control_string_get(swigCPtr, this);
  }

  public void setConnection_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Connection_string_set(swigCPtr, this, value);
  }

  public String getConnection_string() {
    return ntripJNI.NTRIP_Header_Info_t_Connection_string_get(swigCPtr, this);
  }

  public void setContent_Length_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Content_Length_string_set(swigCPtr, this, value);
  }

  public String getContent_Length_string() {
    return ntripJNI.NTRIP_Header_Info_t_Content_Length_string_get(swigCPtr, this);
  }

  public void setContent_Type_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Content_Type_string_set(swigCPtr, this, value);
  }

  public String getContent_Type_string() {
    return ntripJNI.NTRIP_Header_Info_t_Content_Type_string_get(swigCPtr, this);
  }

  public void setDate_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Date_string_set(swigCPtr, this, value);
  }

  public String getDate_string() {
    return ntripJNI.NTRIP_Header_Info_t_Date_string_get(swigCPtr, this);
  }

  public void setHost_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Host_string_set(swigCPtr, this, value);
  }

  public String getHost_string() {
    return ntripJNI.NTRIP_Header_Info_t_Host_string_get(swigCPtr, this);
  }

  public void setServer_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Server_string_set(swigCPtr, this, value);
  }

  public String getServer_string() {
    return ntripJNI.NTRIP_Header_Info_t_Server_string_get(swigCPtr, this);
  }

  public void setPragma_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Pragma_string_set(swigCPtr, this, value);
  }

  public String getPragma_string() {
    return ntripJNI.NTRIP_Header_Info_t_Pragma_string_get(swigCPtr, this);
  }

  public void setTransfer_Encoding_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Transfer_Encoding_string_set(swigCPtr, this, value);
  }

  public String getTransfer_Encoding_string() {
    return ntripJNI.NTRIP_Header_Info_t_Transfer_Encoding_string_get(swigCPtr, this);
  }

  public void setWWW_Authenticate_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_WWW_Authenticate_string_set(swigCPtr, this, value);
  }

  public String getWWW_Authenticate_string() {
    return ntripJNI.NTRIP_Header_Info_t_WWW_Authenticate_string_get(swigCPtr, this);
  }

  public void setNtrip_Version_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Ntrip_Version_string_set(swigCPtr, this, value);
  }

  public String getNtrip_Version_string() {
    return ntripJNI.NTRIP_Header_Info_t_Ntrip_Version_string_get(swigCPtr, this);
  }

  public void setNtrip_Flags_string(String value) {
    ntripJNI.NTRIP_Header_Info_t_Ntrip_Flags_string_set(swigCPtr, this, value);
  }

  public String getNtrip_Flags_string() {
    return ntripJNI.NTRIP_Header_Info_t_Ntrip_Flags_string_get(swigCPtr, this);
  }

  public NTRIP_Header_Info_t() {
    this(ntripJNI.new_NTRIP_Header_Info_t(), true);
  }

}
