/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.sqsp;

public class SqspRelPos_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SqspRelPos_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SqspRelPos_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(SqspRelPos_t obj) {
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
        sqspJNI.delete_SqspRelPos_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setITOW(int value) {
    sqspJNI.SqspRelPos_t_iTOW_set(swigCPtr, this, value);
  }

  public int getITOW() {
    return sqspJNI.SqspRelPos_t_iTOW_get(swigCPtr, this);
  }

  public void setPosN(double value) {
    sqspJNI.SqspRelPos_t_posN_set(swigCPtr, this, value);
  }

  public double getPosN() {
    return sqspJNI.SqspRelPos_t_posN_get(swigCPtr, this);
  }

  public void setPosE(double value) {
    sqspJNI.SqspRelPos_t_posE_set(swigCPtr, this, value);
  }

  public double getPosE() {
    return sqspJNI.SqspRelPos_t_posE_get(swigCPtr, this);
  }

  public void setPosD(double value) {
    sqspJNI.SqspRelPos_t_posD_set(swigCPtr, this, value);
  }

  public double getPosD() {
    return sqspJNI.SqspRelPos_t_posD_get(swigCPtr, this);
  }

  public SqspRelPos_t() {
    this(sqspJNI.new_SqspRelPos_t(), true);
  }

}
