/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.sqtp;

public class SqtpFrameReader_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SqtpFrameReader_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SqtpFrameReader_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(SqtpFrameReader_t obj) {
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
        sqtpJNI.delete_SqtpFrameReader_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setFrame(SWIGTYPE_p_uint8_t value) {
    sqtpJNI.SqtpFrameReader_t_frame_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getFrame() {
    long cPtr = sqtpJNI.SqtpFrameReader_t_frame_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_uint8_t(cPtr, false);
  }

  public void setLength(long value) {
    sqtpJNI.SqtpFrameReader_t_length_set(swigCPtr, this, value);
  }

  public long getLength() {
    return sqtpJNI.SqtpFrameReader_t_length_get(swigCPtr, this);
  }

  public void setIndex(long value) {
    sqtpJNI.SqtpFrameReader_t_index_set(swigCPtr, this, value);
  }

  public long getIndex() {
    return sqtpJNI.SqtpFrameReader_t_index_get(swigCPtr, this);
  }

  public void setStatus(SqtpStatus_t value) {
    sqtpJNI.SqtpFrameReader_t_status_set(swigCPtr, this, value.swigValue());
  }

  public SqtpStatus_t getStatus() {
    return SqtpStatus_t.swigToEnum(sqtpJNI.SqtpFrameReader_t_status_get(swigCPtr, this));
  }

  public void setSubframe(SqtpSubframe_t value) {
    sqtpJNI.SqtpFrameReader_t_subframe_set(swigCPtr, this, SqtpSubframe_t.getCPtr(value), value);
  }

  public SqtpSubframe_t getSubframe() {
    long cPtr = sqtpJNI.SqtpFrameReader_t_subframe_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SqtpSubframe_t(cPtr, false);
  }

  public SqtpFrameReader_t() {
    this(sqtpJNI.new_SqtpFrameReader_t(), true);
  }

}
