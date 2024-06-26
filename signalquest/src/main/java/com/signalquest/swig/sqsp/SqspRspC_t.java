/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.sqsp;

public class SqspRspC_t {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected SqspRspC_t(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SqspRspC_t obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected static long swigRelease(SqspRspC_t obj) {
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
        sqspJNI.delete_SqspRspC_t(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setMfgDataId(SWIGTYPE_p_uint16_t value) {
    sqspJNI.SqspRspC_t_mfgDataId_set(swigCPtr, this, SWIGTYPE_p_uint16_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint16_t getMfgDataId() {
    return new SWIGTYPE_p_uint16_t(sqspJNI.SqspRspC_t_mfgDataId_get(swigCPtr, this), true);
  }

  public void setBatteryLevel(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_batteryLevel_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getBatteryLevel() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_batteryLevel_get(swigCPtr, this), true);
  }

  public void setSystemPowerState(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_systemPowerState_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getSystemPowerState() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_systemPowerState_get(swigCPtr, this), true);
  }

  public void setRunTimeRemaining(SWIGTYPE_p_uint16_t value) {
    sqspJNI.SqspRspC_t_runTimeRemaining_set(swigCPtr, this, SWIGTYPE_p_uint16_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint16_t getRunTimeRemaining() {
    return new SWIGTYPE_p_uint16_t(sqspJNI.SqspRspC_t_runTimeRemaining_get(swigCPtr, this), true);
  }

  public void setSatelliteCount(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_satelliteCount_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getSatelliteCount() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_satelliteCount_get(swigCPtr, this), true);
  }

  public void setAidingBins(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_aidingBins_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getAidingBins() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_aidingBins_get(swigCPtr, this), true);
  }

  public void setDifferential(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_differential_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getDifferential() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_differential_get(swigCPtr, this), true);
  }

  public void setGpsFixOk(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_gpsFixOk_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getGpsFixOk() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_gpsFixOk_get(swigCPtr, this), true);
  }

  public void setAmbiguity(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_ambiguity_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getAmbiguity() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_ambiguity_get(swigCPtr, this), true);
  }

  public void setFix(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_fix_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getFix() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_fix_get(swigCPtr, this), true);
  }

  public void setCoprocOk(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_coprocOk_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getCoprocOk() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_coprocOk_get(swigCPtr, this), true);
  }

  public void setSurveyFailed(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_surveyFailed_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getSurveyFailed() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_surveyFailed_get(swigCPtr, this), true);
  }

  public void setSurveyPositionValid(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_surveyPositionValid_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getSurveyPositionValid() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_surveyPositionValid_get(swigCPtr, this), true);
  }

  public void setSurveyInProgress(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_surveyInProgress_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getSurveyInProgress() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_surveyInProgress_get(swigCPtr, this), true);
  }

  public void setHeadingFlag(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_headingFlag_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getHeadingFlag() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_headingFlag_get(swigCPtr, this), true);
  }

  public void setPosValid(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_posValid_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getPosValid() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_posValid_get(swigCPtr, this), true);
  }

  public void setValidTime(SWIGTYPE_p_uint8_t value) {
    sqspJNI.SqspRspC_t_validTime_set(swigCPtr, this, SWIGTYPE_p_uint8_t.getCPtr(value));
  }

  public SWIGTYPE_p_uint8_t getValidTime() {
    return new SWIGTYPE_p_uint8_t(sqspJNI.SqspRspC_t_validTime_get(swigCPtr, this), true);
  }

  public void setMode(SqspLbModeC_t value) {
    sqspJNI.SqspRspC_t_mode_set(swigCPtr, this, SqspLbModeC_t.getCPtr(value), value);
  }

  public SqspLbModeC_t getMode() {
    long cPtr = sqspJNI.SqspRspC_t_mode_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SqspLbModeC_t(cPtr, false);
  }

  public SqspRspC_t() {
    this(sqspJNI.new_SqspRspC_t(), true);
  }

}
