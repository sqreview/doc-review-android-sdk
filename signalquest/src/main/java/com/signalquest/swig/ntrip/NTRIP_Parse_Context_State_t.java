/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.ntrip;

public final class NTRIP_Parse_Context_State_t {
  public final static NTRIP_Parse_Context_State_t NTRIP_INIT = new NTRIP_Parse_Context_State_t("NTRIP_INIT", ntripJNI.NTRIP_INIT_get());
  public final static NTRIP_Parse_Context_State_t NTRIP_WAIT_FOR_BUFFER = new NTRIP_Parse_Context_State_t("NTRIP_WAIT_FOR_BUFFER");
  public final static NTRIP_Parse_Context_State_t NTRIP_WAIT_FOR_HEADER_START = new NTRIP_Parse_Context_State_t("NTRIP_WAIT_FOR_HEADER_START");
  public final static NTRIP_Parse_Context_State_t NTRIP_WAIT_FOR_HEADER_END = new NTRIP_Parse_Context_State_t("NTRIP_WAIT_FOR_HEADER_END");
  public final static NTRIP_Parse_Context_State_t NTRIP_WAIT_FOR_CHUNK_HEADER = new NTRIP_Parse_Context_State_t("NTRIP_WAIT_FOR_CHUNK_HEADER");
  public final static NTRIP_Parse_Context_State_t NTRIP_WAIT_FOR_CHUNK_END = new NTRIP_Parse_Context_State_t("NTRIP_WAIT_FOR_CHUNK_END");
  public final static NTRIP_Parse_Context_State_t NTRIP_WAIT_FOR_ONE_OR_MORE_RTCM_FRAMES = new NTRIP_Parse_Context_State_t("NTRIP_WAIT_FOR_ONE_OR_MORE_RTCM_FRAMES");
  public final static NTRIP_Parse_Context_State_t NTRIP_WORK_THROUGH_CHUNK = new NTRIP_Parse_Context_State_t("NTRIP_WORK_THROUGH_CHUNK");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static NTRIP_Parse_Context_State_t swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + NTRIP_Parse_Context_State_t.class + " with value " + swigValue);
  }

  private NTRIP_Parse_Context_State_t(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private NTRIP_Parse_Context_State_t(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private NTRIP_Parse_Context_State_t(String swigName, NTRIP_Parse_Context_State_t swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static NTRIP_Parse_Context_State_t[] swigValues = { NTRIP_INIT, NTRIP_WAIT_FOR_BUFFER, NTRIP_WAIT_FOR_HEADER_START, NTRIP_WAIT_FOR_HEADER_END, NTRIP_WAIT_FOR_CHUNK_HEADER, NTRIP_WAIT_FOR_CHUNK_END, NTRIP_WAIT_FOR_ONE_OR_MORE_RTCM_FRAMES, NTRIP_WORK_THROUGH_CHUNK };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

