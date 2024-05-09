/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.sqsp;

public class SqspLlaC_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SqspLlaC_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SqspLlaC_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(SqspLlaC_t obj) {
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
        sqspJNI.delete_SqspLlaC_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setITOW(SWIGTYPE_p_uint32_t value) {
    sqspJNI.SqspLlaC_t_iTOW_set(swigCPtr, this, SWIGTYPE_p_uint32_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint32_t getITOW() {
    return new SWIGTYPE_p_uint32_t(sqspJNI.SqspLlaC_t_iTOW_get(swigCPtr, this), true);
  }

  public void setLon(SWIGTYPE_p_int32_t value) {
    sqspJNI.SqspLlaC_t_lon_set(swigCPtr, this, SWIGTYPE_p_int32_t.getCPtr(value));
  }

  public SWIGTYPE_p_int32_t getLon() {
    return new SWIGTYPE_p_int32_t(sqspJNI.SqspLlaC_t_lon_get(swigCPtr, this), true);
  }

  public void setLat(SWIGTYPE_p_int32_t value) {
    sqspJNI.SqspLlaC_t_lat_set(swigCPtr, this, SWIGTYPE_p_int32_t.getCPtr(value));
  }

  public SWIGTYPE_p_int32_t getLat() {
    return new SWIGTYPE_p_int32_t(sqspJNI.SqspLlaC_t_lat_get(swigCPtr, this), true);
  }

  public void setHeight(SWIGTYPE_p_int32_t value) {
    sqspJNI.SqspLlaC_t_height_set(swigCPtr, this, SWIGTYPE_p_int32_t.getCPtr(value));
  }

  public SWIGTYPE_p_int32_t getHeight() {
    return new SWIGTYPE_p_int32_t(sqspJNI.SqspLlaC_t_height_get(swigCPtr, this), true);
  }

  public void setLonHp(SWIGTYPE_p_int8_t value) {
    sqspJNI.SqspLlaC_t_lonHp_set(swigCPtr, this, SWIGTYPE_p_int8_t.getCPtr(value));
  }

  public SWIGTYPE_p_int8_t getLonHp() {
    return new SWIGTYPE_p_int8_t(sqspJNI.SqspLlaC_t_lonHp_get(swigCPtr, this), true);
  }

  public void setLatHp(SWIGTYPE_p_int8_t value) {
    sqspJNI.SqspLlaC_t_latHp_set(swigCPtr, this, SWIGTYPE_p_int8_t.getCPtr(value));
  }

  public SWIGTYPE_p_int8_t getLatHp() {
    return new SWIGTYPE_p_int8_t(sqspJNI.SqspLlaC_t_latHp_get(swigCPtr, this), true);
  }

  public void setHeightHp(SWIGTYPE_p_int8_t value) {
    sqspJNI.SqspLlaC_t_heightHp_set(swigCPtr, this, SWIGTYPE_p_int8_t.getCPtr(value));
  }

  public SWIGTYPE_p_int8_t getHeightHp() {
    return new SWIGTYPE_p_int8_t(sqspJNI.SqspLlaC_t_heightHp_get(swigCPtr, this), true);
  }

  public void setHMSLHp(SWIGTYPE_p_int8_t value) {
    sqspJNI.SqspLlaC_t_hMSLHp_set(swigCPtr, this, SWIGTYPE_p_int8_t.getCPtr(value));
  }

  public SWIGTYPE_p_int8_t getHMSLHp() {
    return new SWIGTYPE_p_int8_t(sqspJNI.SqspLlaC_t_hMSLHp_get(swigCPtr, this), true);
  }

  public void setHAcc(SWIGTYPE_p_uint32_t value) {
    sqspJNI.SqspLlaC_t_hAcc_set(swigCPtr, this, SWIGTYPE_p_uint32_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint32_t getHAcc() {
    return new SWIGTYPE_p_uint32_t(sqspJNI.SqspLlaC_t_hAcc_get(swigCPtr, this), true);
  }

  public void setVAcc(SWIGTYPE_p_uint32_t value) {
    sqspJNI.SqspLlaC_t_vAcc_set(swigCPtr, this, SWIGTYPE_p_uint32_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint32_t getVAcc() {
    return new SWIGTYPE_p_uint32_t(sqspJNI.SqspLlaC_t_vAcc_get(swigCPtr, this), true);
  }

  public void setHMSL(SWIGTYPE_p_int32_t value) {
    sqspJNI.SqspLlaC_t_hMSL_set(swigCPtr, this, SWIGTYPE_p_int32_t.getCPtr(value));
  }

  public SWIGTYPE_p_int32_t getHMSL() {
    return new SWIGTYPE_p_int32_t(sqspJNI.SqspLlaC_t_hMSL_get(swigCPtr, this), true);
  }

  public SqspLlaC_t() {
    this(sqspJNI.new_SqspLlaC_t(), true);
  }

}
