/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.sqsp;

public class SqspRsp_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SqspRsp_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SqspRsp_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(SqspRsp_t obj) {
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
        sqspJNI.delete_SqspRsp_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setMfgDataId(short value) {
    sqspJNI.SqspRsp_t_mfgDataId_set(swigCPtr, this, value);
  }

  public short getMfgDataId() {
    return sqspJNI.SqspRsp_t_mfgDataId_get(swigCPtr, this);
  }

  public void setBatteryLevel(char value) {
    sqspJNI.SqspRsp_t_batteryLevel_set(swigCPtr, this, value);
  }

  public char getBatteryLevel() {
    return sqspJNI.SqspRsp_t_batteryLevel_get(swigCPtr, this);
  }

  public void setSystemPowerState(char value) {
    sqspJNI.SqspRsp_t_systemPowerState_set(swigCPtr, this, value);
  }

  public char getSystemPowerState() {
    return sqspJNI.SqspRsp_t_systemPowerState_get(swigCPtr, this);
  }

  public void setRunTimeRemaining(short value) {
    sqspJNI.SqspRsp_t_runTimeRemaining_set(swigCPtr, this, value);
  }

  public short getRunTimeRemaining() {
    return sqspJNI.SqspRsp_t_runTimeRemaining_get(swigCPtr, this);
  }

  public void setSatelliteCount(char value) {
    sqspJNI.SqspRsp_t_satelliteCount_set(swigCPtr, this, value);
  }

  public char getSatelliteCount() {
    return sqspJNI.SqspRsp_t_satelliteCount_get(swigCPtr, this);
  }

  public void setAidingBins(char value) {
    sqspJNI.SqspRsp_t_aidingBins_set(swigCPtr, this, value);
  }

  public char getAidingBins() {
    return sqspJNI.SqspRsp_t_aidingBins_get(swigCPtr, this);
  }

  public void setDifferential(char value) {
    sqspJNI.SqspRsp_t_differential_set(swigCPtr, this, value);
  }

  public char getDifferential() {
    return sqspJNI.SqspRsp_t_differential_get(swigCPtr, this);
  }

  public void setGpsFixOk(char value) {
    sqspJNI.SqspRsp_t_gpsFixOk_set(swigCPtr, this, value);
  }

  public char getGpsFixOk() {
    return sqspJNI.SqspRsp_t_gpsFixOk_get(swigCPtr, this);
  }

  public void setAmbiguity(char value) {
    sqspJNI.SqspRsp_t_ambiguity_set(swigCPtr, this, value);
  }

  public char getAmbiguity() {
    return sqspJNI.SqspRsp_t_ambiguity_get(swigCPtr, this);
  }

  public void setFix(char value) {
    sqspJNI.SqspRsp_t_fix_set(swigCPtr, this, value);
  }

  public char getFix() {
    return sqspJNI.SqspRsp_t_fix_get(swigCPtr, this);
  }

  public void setCoprocOk(char value) {
    sqspJNI.SqspRsp_t_coprocOk_set(swigCPtr, this, value);
  }

  public char getCoprocOk() {
    return sqspJNI.SqspRsp_t_coprocOk_get(swigCPtr, this);
  }

  public void setSurveyFailed(char value) {
    sqspJNI.SqspRsp_t_surveyFailed_set(swigCPtr, this, value);
  }

  public char getSurveyFailed() {
    return sqspJNI.SqspRsp_t_surveyFailed_get(swigCPtr, this);
  }

  public void setSurveyPositionValid(char value) {
    sqspJNI.SqspRsp_t_surveyPositionValid_set(swigCPtr, this, value);
  }

  public char getSurveyPositionValid() {
    return sqspJNI.SqspRsp_t_surveyPositionValid_get(swigCPtr, this);
  }

  public void setSurveyInProgress(char value) {
    sqspJNI.SqspRsp_t_surveyInProgress_set(swigCPtr, this, value);
  }

  public char getSurveyInProgress() {
    return sqspJNI.SqspRsp_t_surveyInProgress_get(swigCPtr, this);
  }

  public void setHeadingFlag(char value) {
    sqspJNI.SqspRsp_t_headingFlag_set(swigCPtr, this, value);
  }

  public char getHeadingFlag() {
    return sqspJNI.SqspRsp_t_headingFlag_get(swigCPtr, this);
  }

  public void setPosValid(char value) {
    sqspJNI.SqspRsp_t_posValid_set(swigCPtr, this, value);
  }

  public char getPosValid() {
    return sqspJNI.SqspRsp_t_posValid_get(swigCPtr, this);
  }

  public void setValidTime(char value) {
    sqspJNI.SqspRsp_t_validTime_set(swigCPtr, this, value);
  }

  public char getValidTime() {
    return sqspJNI.SqspRsp_t_validTime_get(swigCPtr, this);
  }

  public void setMode(SqspLbMode_t value) {
    sqspJNI.SqspRsp_t_mode_set(swigCPtr, this, SqspLbMode_t.getCPtr(value), value);
  }

  public SqspLbMode_t getMode() {
    long cPtr = sqspJNI.SqspRsp_t_mode_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SqspLbMode_t(cPtr, false);
  }

  public SqspRsp_t() {
    this(sqspJNI.new_SqspRsp_t(), true);
  }

}
