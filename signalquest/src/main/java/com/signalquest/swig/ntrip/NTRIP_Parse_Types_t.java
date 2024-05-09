/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.ntrip;

public final class NTRIP_Parse_Types_t {
  public final static NTRIP_Parse_Types_t NTRIP_RTCM_Message = new NTRIP_Parse_Types_t("NTRIP_RTCM_Message", ntripJNI.NTRIP_RTCM_Message_get());
  public final static NTRIP_Parse_Types_t NTRIP_V1_Header_Message = new NTRIP_Parse_Types_t("NTRIP_V1_Header_Message", ntripJNI.NTRIP_V1_Header_Message_get());
  public final static NTRIP_Parse_Types_t NTRIP_V2_Header_Message = new NTRIP_Parse_Types_t("NTRIP_V2_Header_Message", ntripJNI.NTRIP_V2_Header_Message_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static NTRIP_Parse_Types_t swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + NTRIP_Parse_Types_t.class + " with value " + swigValue);
  }

  private NTRIP_Parse_Types_t(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private NTRIP_Parse_Types_t(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private NTRIP_Parse_Types_t(String swigName, NTRIP_Parse_Types_t swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static NTRIP_Parse_Types_t[] swigValues = { NTRIP_RTCM_Message, NTRIP_V1_Header_Message, NTRIP_V2_Header_Message };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}
