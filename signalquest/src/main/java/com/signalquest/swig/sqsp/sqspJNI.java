/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (https://www.swig.org).
 * Version 4.2.1
 *
 * Do not make changes to this file unless you know what you are doing - modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.signalquest.swig.sqsp;

public class sqspJNI {
  public final static native int SQSP_SOLTYPE_OFFLINE_get();
  public final static native int SQSP_SOLTYPE_ACQUIRING_get();
  public final static native int SQSP_SOLTYPE_2D_get();
  public final static native int SQSP_SOLTYPE_3D_get();
  public final static native int SQSP_SOLTYPE_RTK_FLOAT_get();
  public final static native int SQSP_SOLTYPE_RTK_FIXED_get();
  public final static native int SQSP_SOLTYPE_AUTO_SURVEYING_get();
  public final static native int SQSP_SOLTYPE_FIXED_BASE_get();
  public final static native int SQSP_SOLTYPE_UNSUPPORTED_get();
  public final static native void SqspLbModeC_t_fixedBaseEnable_set(long jarg1, SqspLbModeC_t jarg1_, long jarg2);
  public final static native long SqspLbModeC_t_fixedBaseEnable_get(long jarg1, SqspLbModeC_t jarg1_);
  public final static native void SqspLbModeC_t_autoSurveyEnable_set(long jarg1, SqspLbModeC_t jarg1_, long jarg2);
  public final static native long SqspLbModeC_t_autoSurveyEnable_get(long jarg1, SqspLbModeC_t jarg1_);
  public final static native void SqspLbModeC_t_connected_set(long jarg1, SqspLbModeC_t jarg1_, long jarg2);
  public final static native long SqspLbModeC_t_connected_get(long jarg1, SqspLbModeC_t jarg1_);
  public final static native void SqspLbModeC_t_correctionOutput_set(long jarg1, SqspLbModeC_t jarg1_, long jarg2);
  public final static native long SqspLbModeC_t_correctionOutput_get(long jarg1, SqspLbModeC_t jarg1_);
  public final static native void SqspLbModeC_t_correctionInput_set(long jarg1, SqspLbModeC_t jarg1_, long jarg2);
  public final static native long SqspLbModeC_t_correctionInput_get(long jarg1, SqspLbModeC_t jarg1_);
  public final static native void SqspLbModeC_t_tripodCorrections_set(long jarg1, SqspLbModeC_t jarg1_, long jarg2);
  public final static native long SqspLbModeC_t_tripodCorrections_get(long jarg1, SqspLbModeC_t jarg1_);
  public final static native void SqspLbModeC_t_validOrNotBusy_set(long jarg1, SqspLbModeC_t jarg1_, long jarg2);
  public final static native long SqspLbModeC_t_validOrNotBusy_get(long jarg1, SqspLbModeC_t jarg1_);
  public final static native void SqspLbModeC_t_broadcastEnable_set(long jarg1, SqspLbModeC_t jarg1_, long jarg2);
  public final static native long SqspLbModeC_t_broadcastEnable_get(long jarg1, SqspLbModeC_t jarg1_);
  public final static native long new_SqspLbModeC_t();
  public final static native void delete_SqspLbModeC_t(long jarg1);
  public final static native void SqspLbConfigC_t_lon_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_lon_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_lat_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_lat_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_height_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_height_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_hAcc_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_hAcc_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_vAcc_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_vAcc_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_lonHp_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_lonHp_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_latHp_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_latHp_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_heightHp_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_heightHp_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_mode_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2, SqspLbModeC_t jarg2_);
  public final static native long SqspLbConfigC_t_mode_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_duration_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_duration_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_hMSL_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_hMSL_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_hMSLHp_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_hMSLHp_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native void SqspLbConfigC_t_reserved_set(long jarg1, SqspLbConfigC_t jarg1_, long jarg2);
  public final static native long SqspLbConfigC_t_reserved_get(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native long new_SqspLbConfigC_t();
  public final static native void delete_SqspLbConfigC_t(long jarg1);
  public final static native void SqspRspC_t_mfgDataId_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_mfgDataId_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_batteryLevel_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_batteryLevel_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_systemPowerState_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_systemPowerState_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_runTimeRemaining_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_runTimeRemaining_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_satelliteCount_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_satelliteCount_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_aidingBins_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_aidingBins_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_differential_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_differential_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_gpsFixOk_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_gpsFixOk_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_ambiguity_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_ambiguity_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_fix_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_fix_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_coprocOk_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_coprocOk_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_surveyFailed_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_surveyFailed_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_surveyPositionValid_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_surveyPositionValid_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_surveyInProgress_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_surveyInProgress_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_headingFlag_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_headingFlag_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_posValid_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_posValid_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_validTime_set(long jarg1, SqspRspC_t jarg1_, long jarg2);
  public final static native long SqspRspC_t_validTime_get(long jarg1, SqspRspC_t jarg1_);
  public final static native void SqspRspC_t_mode_set(long jarg1, SqspRspC_t jarg1_, long jarg2, SqspLbModeC_t jarg2_);
  public final static native long SqspRspC_t_mode_get(long jarg1, SqspRspC_t jarg1_);
  public final static native long new_SqspRspC_t();
  public final static native void delete_SqspRspC_t(long jarg1);
  public final static native void SqspStatusC_t_iTOW_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_iTOW_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_year_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_year_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_month_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_month_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_day_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_day_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_hour_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_hour_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_min_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_min_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_sec_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_sec_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_solType_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_solType_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_numSV_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_numSV_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_battery_set(long jarg1, SqspStatusC_t jarg1_, long jarg2);
  public final static native long SqspStatusC_t_battery_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native void SqspStatusC_t_rsp_set(long jarg1, SqspStatusC_t jarg1_, long jarg2, SqspRspC_t jarg2_);
  public final static native long SqspStatusC_t_rsp_get(long jarg1, SqspStatusC_t jarg1_);
  public final static native long new_SqspStatusC_t();
  public final static native void delete_SqspStatusC_t(long jarg1);
  public final static native void SqspRelPosC_t_iTOW_set(long jarg1, SqspRelPosC_t jarg1_, long jarg2);
  public final static native long SqspRelPosC_t_iTOW_get(long jarg1, SqspRelPosC_t jarg1_);
  public final static native void SqspRelPosC_t_posN_set(long jarg1, SqspRelPosC_t jarg1_, long jarg2);
  public final static native long SqspRelPosC_t_posN_get(long jarg1, SqspRelPosC_t jarg1_);
  public final static native void SqspRelPosC_t_posE_set(long jarg1, SqspRelPosC_t jarg1_, long jarg2);
  public final static native long SqspRelPosC_t_posE_get(long jarg1, SqspRelPosC_t jarg1_);
  public final static native void SqspRelPosC_t_posD_set(long jarg1, SqspRelPosC_t jarg1_, long jarg2);
  public final static native long SqspRelPosC_t_posD_get(long jarg1, SqspRelPosC_t jarg1_);
  public final static native void SqspRelPosC_t_posHpN_set(long jarg1, SqspRelPosC_t jarg1_, long jarg2);
  public final static native long SqspRelPosC_t_posHpN_get(long jarg1, SqspRelPosC_t jarg1_);
  public final static native void SqspRelPosC_t_posHpE_set(long jarg1, SqspRelPosC_t jarg1_, long jarg2);
  public final static native long SqspRelPosC_t_posHpE_get(long jarg1, SqspRelPosC_t jarg1_);
  public final static native void SqspRelPosC_t_posHpD_set(long jarg1, SqspRelPosC_t jarg1_, long jarg2);
  public final static native long SqspRelPosC_t_posHpD_get(long jarg1, SqspRelPosC_t jarg1_);
  public final static native long new_SqspRelPosC_t();
  public final static native void delete_SqspRelPosC_t(long jarg1);
  public final static native void SqspLlaC_t_iTOW_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_iTOW_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_lon_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_lon_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_lat_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_lat_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_height_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_height_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_lonHp_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_lonHp_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_latHp_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_latHp_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_heightHp_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_heightHp_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_hMSLHp_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_hMSLHp_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_hAcc_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_hAcc_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_vAcc_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_vAcc_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native void SqspLlaC_t_hMSL_set(long jarg1, SqspLlaC_t jarg1_, long jarg2);
  public final static native long SqspLlaC_t_hMSL_get(long jarg1, SqspLlaC_t jarg1_);
  public final static native long new_SqspLlaC_t();
  public final static native void delete_SqspLlaC_t(long jarg1);
  public final static native void SqspLbMode_t_fixedBaseEnable_set(long jarg1, SqspLbMode_t jarg1_, char jarg2);
  public final static native char SqspLbMode_t_fixedBaseEnable_get(long jarg1, SqspLbMode_t jarg1_);
  public final static native void SqspLbMode_t_autoSurveyEnable_set(long jarg1, SqspLbMode_t jarg1_, char jarg2);
  public final static native char SqspLbMode_t_autoSurveyEnable_get(long jarg1, SqspLbMode_t jarg1_);
  public final static native void SqspLbMode_t_connected_set(long jarg1, SqspLbMode_t jarg1_, char jarg2);
  public final static native char SqspLbMode_t_connected_get(long jarg1, SqspLbMode_t jarg1_);
  public final static native void SqspLbMode_t_correctionOutput_set(long jarg1, SqspLbMode_t jarg1_, char jarg2);
  public final static native char SqspLbMode_t_correctionOutput_get(long jarg1, SqspLbMode_t jarg1_);
  public final static native void SqspLbMode_t_correctionInput_set(long jarg1, SqspLbMode_t jarg1_, char jarg2);
  public final static native char SqspLbMode_t_correctionInput_get(long jarg1, SqspLbMode_t jarg1_);
  public final static native void SqspLbMode_t_tripodCorrections_set(long jarg1, SqspLbMode_t jarg1_, char jarg2);
  public final static native char SqspLbMode_t_tripodCorrections_get(long jarg1, SqspLbMode_t jarg1_);
  public final static native void SqspLbMode_t_validOrNotBusy_set(long jarg1, SqspLbMode_t jarg1_, char jarg2);
  public final static native char SqspLbMode_t_validOrNotBusy_get(long jarg1, SqspLbMode_t jarg1_);
  public final static native void SqspLbMode_t_broadcastEnable_set(long jarg1, SqspLbMode_t jarg1_, char jarg2);
  public final static native char SqspLbMode_t_broadcastEnable_get(long jarg1, SqspLbMode_t jarg1_);
  public final static native long new_SqspLbMode_t();
  public final static native void delete_SqspLbMode_t(long jarg1);
  public final static native void SqspLbConfig_t_lon_set(long jarg1, SqspLbConfig_t jarg1_, double jarg2);
  public final static native double SqspLbConfig_t_lon_get(long jarg1, SqspLbConfig_t jarg1_);
  public final static native void SqspLbConfig_t_lat_set(long jarg1, SqspLbConfig_t jarg1_, double jarg2);
  public final static native double SqspLbConfig_t_lat_get(long jarg1, SqspLbConfig_t jarg1_);
  public final static native void SqspLbConfig_t_height_set(long jarg1, SqspLbConfig_t jarg1_, double jarg2);
  public final static native double SqspLbConfig_t_height_get(long jarg1, SqspLbConfig_t jarg1_);
  public final static native void SqspLbConfig_t_hMSL_set(long jarg1, SqspLbConfig_t jarg1_, double jarg2);
  public final static native double SqspLbConfig_t_hMSL_get(long jarg1, SqspLbConfig_t jarg1_);
  public final static native void SqspLbConfig_t_hAcc_set(long jarg1, SqspLbConfig_t jarg1_, double jarg2);
  public final static native double SqspLbConfig_t_hAcc_get(long jarg1, SqspLbConfig_t jarg1_);
  public final static native void SqspLbConfig_t_vAcc_set(long jarg1, SqspLbConfig_t jarg1_, double jarg2);
  public final static native double SqspLbConfig_t_vAcc_get(long jarg1, SqspLbConfig_t jarg1_);
  public final static native void SqspLbConfig_t_duration_set(long jarg1, SqspLbConfig_t jarg1_, int jarg2);
  public final static native int SqspLbConfig_t_duration_get(long jarg1, SqspLbConfig_t jarg1_);
  public final static native void SqspLbConfig_t_mode_set(long jarg1, SqspLbConfig_t jarg1_, long jarg2, SqspLbMode_t jarg2_);
  public final static native long SqspLbConfig_t_mode_get(long jarg1, SqspLbConfig_t jarg1_);
  public final static native void SqspLbConfig_t_reserved_set(long jarg1, SqspLbConfig_t jarg1_, String jarg2);
  public final static native String SqspLbConfig_t_reserved_get(long jarg1, SqspLbConfig_t jarg1_);
  public final static native long new_SqspLbConfig_t();
  public final static native void delete_SqspLbConfig_t(long jarg1);
  public final static native void SqspRsp_t_mfgDataId_set(long jarg1, SqspRsp_t jarg1_, short jarg2);
  public final static native short SqspRsp_t_mfgDataId_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_batteryLevel_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_batteryLevel_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_systemPowerState_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_systemPowerState_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_runTimeRemaining_set(long jarg1, SqspRsp_t jarg1_, short jarg2);
  public final static native short SqspRsp_t_runTimeRemaining_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_satelliteCount_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_satelliteCount_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_aidingBins_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_aidingBins_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_differential_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_differential_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_gpsFixOk_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_gpsFixOk_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_ambiguity_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_ambiguity_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_fix_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_fix_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_coprocOk_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_coprocOk_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_surveyFailed_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_surveyFailed_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_surveyPositionValid_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_surveyPositionValid_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_surveyInProgress_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_surveyInProgress_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_headingFlag_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_headingFlag_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_posValid_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_posValid_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_validTime_set(long jarg1, SqspRsp_t jarg1_, char jarg2);
  public final static native char SqspRsp_t_validTime_get(long jarg1, SqspRsp_t jarg1_);
  public final static native void SqspRsp_t_mode_set(long jarg1, SqspRsp_t jarg1_, long jarg2, SqspLbMode_t jarg2_);
  public final static native long SqspRsp_t_mode_get(long jarg1, SqspRsp_t jarg1_);
  public final static native long new_SqspRsp_t();
  public final static native void delete_SqspRsp_t(long jarg1);
  public final static native void SqspStatus_t_iTOW_set(long jarg1, SqspStatus_t jarg1_, int jarg2);
  public final static native int SqspStatus_t_iTOW_get(long jarg1, SqspStatus_t jarg1_);
  public final static native void SqspStatus_t_time_set(long jarg1, SqspStatus_t jarg1_, int jarg2);
  public final static native int SqspStatus_t_time_get(long jarg1, SqspStatus_t jarg1_);
  public final static native void SqspStatus_t_solType_set(long jarg1, SqspStatus_t jarg1_, char jarg2);
  public final static native char SqspStatus_t_solType_get(long jarg1, SqspStatus_t jarg1_);
  public final static native void SqspStatus_t_numSV_set(long jarg1, SqspStatus_t jarg1_, char jarg2);
  public final static native char SqspStatus_t_numSV_get(long jarg1, SqspStatus_t jarg1_);
  public final static native void SqspStatus_t_battery_set(long jarg1, SqspStatus_t jarg1_, char jarg2);
  public final static native char SqspStatus_t_battery_get(long jarg1, SqspStatus_t jarg1_);
  public final static native void SqspStatus_t_reserved_set(long jarg1, SqspStatus_t jarg1_, char jarg2);
  public final static native char SqspStatus_t_reserved_get(long jarg1, SqspStatus_t jarg1_);
  public final static native void SqspStatus_t_rsp_set(long jarg1, SqspStatus_t jarg1_, long jarg2, SqspRsp_t jarg2_);
  public final static native long SqspStatus_t_rsp_get(long jarg1, SqspStatus_t jarg1_);
  public final static native void SqspStatus_t_reserved2_set(long jarg1, SqspStatus_t jarg1_, char jarg2);
  public final static native char SqspStatus_t_reserved2_get(long jarg1, SqspStatus_t jarg1_);
  public final static native long new_SqspStatus_t();
  public final static native void delete_SqspStatus_t(long jarg1);
  public final static native void SqspRelPos_t_iTOW_set(long jarg1, SqspRelPos_t jarg1_, int jarg2);
  public final static native int SqspRelPos_t_iTOW_get(long jarg1, SqspRelPos_t jarg1_);
  public final static native void SqspRelPos_t_posN_set(long jarg1, SqspRelPos_t jarg1_, double jarg2);
  public final static native double SqspRelPos_t_posN_get(long jarg1, SqspRelPos_t jarg1_);
  public final static native void SqspRelPos_t_posE_set(long jarg1, SqspRelPos_t jarg1_, double jarg2);
  public final static native double SqspRelPos_t_posE_get(long jarg1, SqspRelPos_t jarg1_);
  public final static native void SqspRelPos_t_posD_set(long jarg1, SqspRelPos_t jarg1_, double jarg2);
  public final static native double SqspRelPos_t_posD_get(long jarg1, SqspRelPos_t jarg1_);
  public final static native long new_SqspRelPos_t();
  public final static native void delete_SqspRelPos_t(long jarg1);
  public final static native void SqspLla_t_iTOW_set(long jarg1, SqspLla_t jarg1_, int jarg2);
  public final static native int SqspLla_t_iTOW_get(long jarg1, SqspLla_t jarg1_);
  public final static native void SqspLla_t_reserved_set(long jarg1, SqspLla_t jarg1_, int jarg2);
  public final static native int SqspLla_t_reserved_get(long jarg1, SqspLla_t jarg1_);
  public final static native void SqspLla_t_lon_set(long jarg1, SqspLla_t jarg1_, double jarg2);
  public final static native double SqspLla_t_lon_get(long jarg1, SqspLla_t jarg1_);
  public final static native void SqspLla_t_lat_set(long jarg1, SqspLla_t jarg1_, double jarg2);
  public final static native double SqspLla_t_lat_get(long jarg1, SqspLla_t jarg1_);
  public final static native void SqspLla_t_height_set(long jarg1, SqspLla_t jarg1_, double jarg2);
  public final static native double SqspLla_t_height_get(long jarg1, SqspLla_t jarg1_);
  public final static native void SqspLla_t_hMSL_set(long jarg1, SqspLla_t jarg1_, double jarg2);
  public final static native double SqspLla_t_hMSL_get(long jarg1, SqspLla_t jarg1_);
  public final static native void SqspLla_t_hAcc_set(long jarg1, SqspLla_t jarg1_, double jarg2);
  public final static native double SqspLla_t_hAcc_get(long jarg1, SqspLla_t jarg1_);
  public final static native void SqspLla_t_vAcc_set(long jarg1, SqspLla_t jarg1_, double jarg2);
  public final static native double SqspLla_t_vAcc_get(long jarg1, SqspLla_t jarg1_);
  public final static native long new_SqspLla_t();
  public final static native void delete_SqspLla_t(long jarg1);
  public final static native int SQSP_HOST_COMMAND_NONE_get();
  public final static native int SQSP_HOST_COMMAND_START_TRIPOD_AIDING_get();
  public final static native int SQSP_HOST_COMMAND_STOP_TRIPOD_AIDING_get();
  public final static native int SQSP_HOST_COMMAND_START_TRIPOD_AIDING_DELAYED_get();
  public final static native boolean sqspLbModeCInit(long jarg1, SqspLbModeC_t jarg1_, char jarg2, char jarg3, char jarg4, char jarg5, char jarg6, char jarg7, char jarg8, char jarg9);
  public final static native boolean sqspLbModeInit(long jarg1, SqspLbMode_t jarg1_, char jarg2, char jarg3, char jarg4, char jarg5, char jarg6, char jarg7, char jarg8, char jarg9);
  public final static native boolean sqspLbModeExpand(long jarg1, SqspLbMode_t jarg1_, long jarg2, SqspLbModeC_t jarg2_);
  public final static native boolean sqspLbModeCompact(long jarg1, SqspLbModeC_t jarg1_, long jarg2, SqspLbMode_t jarg2_);
  public final static native boolean sqspLbConfigCInit(long jarg1, SqspLbConfigC_t jarg1_, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, long jarg8, long jarg9, long jarg10, SqspLbModeC_t jarg10_, long jarg11, long jarg12, long jarg13);
  public final static native boolean sqspLbConfigInit(long jarg1, SqspLbConfig_t jarg1_, double jarg2, double jarg3, double jarg4, double jarg5, double jarg6, double jarg7, long jarg8, long jarg9, SqspLbMode_t jarg9_);
  public final static native boolean sqspLbConfigExpand(long jarg1, SqspLbConfig_t jarg1_, long jarg2, SqspLbConfigC_t jarg2_);
  public final static native boolean sqspLbConfigCompact(long jarg1, SqspLbConfigC_t jarg1_, long jarg2, SqspLbConfig_t jarg2_);
  public final static native boolean sqspRspCInit(long jarg1, SqspRspC_t jarg1_, short jarg2, char jarg3, char jarg4, short jarg5, char jarg6, char jarg7, char jarg8, char jarg9, char jarg10, char jarg11, char jarg12, char jarg13, char jarg14, char jarg15, char jarg16, char jarg17, char jarg18, long jarg19, SqspLbModeC_t jarg19_);
  public final static native boolean sqspRspInit(long jarg1, SqspRsp_t jarg1_, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, long jarg8, long jarg9, long jarg10, long jarg11, long jarg12, long jarg13, long jarg14, long jarg15, long jarg16, long jarg17, long jarg18, long jarg19, SqspLbMode_t jarg19_);
  public final static native boolean sqspRspExpand(long jarg1, SqspRsp_t jarg1_, long jarg2, SqspRspC_t jarg2_);
  public final static native boolean sqspRspCompact(long jarg1, SqspRspC_t jarg1_, long jarg2, SqspRsp_t jarg2_);
  public final static native boolean sqspStatusCInit(long jarg1, SqspStatusC_t jarg1_, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, long jarg8, long jarg9, long jarg10, long jarg11, long jarg12, SqspRspC_t jarg12_);
  public final static native boolean sqspStatusInit(long jarg1, SqspStatus_t jarg1_, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, SqspRsp_t jarg7_);
  public final static native boolean sqspStatusExpand(long jarg1, SqspStatus_t jarg1_, long jarg2, SqspStatusC_t jarg2_);
  public final static native boolean sqspStatusCompact(long jarg1, SqspStatusC_t jarg1_, long jarg2, SqspStatus_t jarg2_);
  public final static native boolean sqspRelPosCInit(long jarg1, SqspRelPosC_t jarg1_, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, long jarg8);
  public final static native boolean sqspRelPosInit(long jarg1, SqspRelPos_t jarg1_, long jarg2, double jarg3, double jarg4, double jarg5);
  public final static native boolean sqspRelPosExpand(long jarg1, SqspRelPos_t jarg1_, long jarg2, SqspRelPosC_t jarg2_);
  public final static native boolean sqspRelPosCompact(long jarg1, SqspRelPosC_t jarg1_, long jarg2, SqspRelPos_t jarg2_);
  public final static native boolean sqspLlaCInit(long jarg1, SqspLlaC_t jarg1_, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, long jarg8, long jarg9, long jarg10, long jarg11, long jarg12);
  public final static native boolean sqspLlaInit(long jarg1, SqspLla_t jarg1_, long jarg2, double jarg3, double jarg4, double jarg5, double jarg6, double jarg7, double jarg8);
  public final static native boolean sqspLlaExpand(long jarg1, SqspLla_t jarg1_, long jarg2, SqspLlaC_t jarg2_);
  public final static native boolean sqspLlaCompact(long jarg1, SqspLlaC_t jarg1_, long jarg2, SqspLla_t jarg2_);
  public final static native String sqspSolutionTypeLabel(char jarg1);
  public final static native void SqspSolTypeLabel_t_string_set(long jarg1, SqspSolTypeLabel_t jarg1_, String jarg2);
  public final static native String SqspSolTypeLabel_t_string_get(long jarg1, SqspSolTypeLabel_t jarg1_);
  public final static native long new_SqspSolTypeLabel_t();
  public final static native void delete_SqspSolTypeLabel_t(long jarg1);
  public final static native long sqspLbModeCCast(long jarg1);
  public final static native long sqspLbModeCCopy(long jarg1);
  public final static native long sqspLbModeCConvertCast(long jarg1);
  public final static native long sqspLbModeCConvertCopy(long jarg1, SqspLbMode_t jarg1_);
  public final static native long sqspLbModeCCreate(char jarg1, char jarg2, char jarg3, char jarg4, char jarg5, char jarg6, char jarg7, char jarg8);
  public final static native long sqspLbConfigCCast(byte[] jarg1);
  public final static native long sqspLbConfigCCopy(long jarg1);
  public final static native long sqspLbConfigCConvertCast(long jarg1);
  public final static native long sqspLbConfigCConvertCopy(long jarg1, SqspLbConfig_t jarg1_);
  public final static native long sqspLbConfigCCreate(long jarg1, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, long jarg8, long jarg9, SqspLbModeC_t jarg9_, long jarg10, long jarg11, long jarg12);
  public final static native long sqspRspCCast(byte[] jarg1);
  public final static native long sqspRspCCopy(byte[] jarg1);
  public final static native long sqspRspCConvertCast(long jarg1);
  public final static native long sqspRspCConvertCopy(long jarg1, SqspRsp_t jarg1_);
  public final static native long sqspRspCCreate(long jarg1, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, long jarg8, long jarg9, long jarg10, long jarg11, long jarg12, long jarg13, long jarg14, long jarg15, long jarg16, long jarg17, long jarg18, SqspLbModeC_t jarg18_);
  public final static native long sqspStatusCCast(long jarg1);
  public final static native long sqspStatusCCopy(long jarg1);
  public final static native long sqspStatusCConvertCast(long jarg1);
  public final static native long sqspStatusCConvertCopy(long jarg1, SqspStatus_t jarg1_);
  public final static native long sqspStatusCCreate(int jarg1, short jarg2, char jarg3, char jarg4, char jarg5, char jarg6, char jarg7, char jarg8, char jarg9, char jarg10, long jarg11, SqspRspC_t jarg11_);
  public final static native long sqspRelPosCCast(long jarg1);
  public final static native long sqspRelPosCCopy(long jarg1);
  public final static native long sqspRelPosCConvertCast(long jarg1);
  public final static native long sqspRelPosCConvertCopy(long jarg1, SqspRelPos_t jarg1_);
  public final static native long sqspRelPosCCreate(long jarg1, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7);
  public final static native long sqspLlaCCast(long jarg1);
  public final static native long sqspLlaCCopy(long jarg1);
  public final static native long sqspLlaCConvertCast(long jarg1);
  public final static native long sqspLlaCConvertCopy(long jarg1, SqspLla_t jarg1_);
  public final static native long sqspLlaCCreate(long jarg1, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, long jarg8, long jarg9, long jarg10, long jarg11);
  public final static native long sqspLbModeCast(long jarg1);
  public final static native long sqspLbModeCopy(long jarg1);
  public final static native long sqspLbModeConvertCast(long jarg1);
  public final static native long sqspLbModeConvertCopy(long jarg1, SqspLbModeC_t jarg1_);
  public final static native long sqspLbModeCreate(char jarg1, char jarg2, char jarg3, char jarg4, char jarg5, char jarg6, char jarg7, char jarg8);
  public final static native long sqspLbConfigCast(long jarg1);
  public final static native long sqspLbConfigCopy(byte[] jarg1);
  public final static native long sqspLbConfigConvertCast(long jarg1);
  public final static native long sqspLbConfigConvertCopy(long jarg1, SqspLbConfigC_t jarg1_);
  public final static native long sqspLbConfigCreate(double jarg1, double jarg2, double jarg3, double jarg4, double jarg5, double jarg6, int jarg7, long jarg8, SqspLbMode_t jarg8_);
  public final static native long sqspRspCast(byte[] jarg1);
  public final static native long sqspRspCopy(long jarg1);
  public final static native long sqspRspConvertCast(long jarg1);
  public final static native long sqspRspConvertCopy(long jarg1, SqspRspC_t jarg1_);
  public final static native long sqspRspCreate(long jarg1, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, long jarg7, long jarg8, long jarg9, long jarg10, long jarg11, long jarg12, long jarg13, long jarg14, long jarg15, long jarg16, long jarg17, long jarg18, SqspLbMode_t jarg18_);
  public final static native long sqspStatusCast(long jarg1);
  public final static native long sqspStatusCopy(byte[] jarg1);
  public final static native long sqspStatusConvertCast(long jarg1);
  public final static native long sqspStatusConvertCopy(long jarg1, SqspStatusC_t jarg1_);
  public final static native long sqspStatusCreate(long jarg1, long jarg2, long jarg3, long jarg4, long jarg5, long jarg6, SqspRsp_t jarg6_);
  public final static native long sqspRelPosCast(long jarg1);
  public final static native long sqspRelPosCopy(long jarg1);
  public final static native long sqspRelPosConvertCast(long jarg1);
  public final static native long sqspRelPosConvertCopy(long jarg1, SqspRelPosC_t jarg1_);
  public final static native long sqspRelPosCreate(long jarg1, double jarg2, double jarg3, double jarg4);
  public final static native long sqspLlaCast(long jarg1);
  public final static native long sqspLlaCopy(byte[] jarg1);
  public final static native long sqspLlaConvertCast(long jarg1);
  public final static native long sqspLlaConvertCopy(long jarg1, SqspLlaC_t jarg1_);
  public final static native long sqspLlaCreate(int jarg1, double jarg2, double jarg3, double jarg4, double jarg5, double jarg6, double jarg7);
  public final static native long sqspSolutionTypeLabelCopy(char jarg1);
  public final static native int SqspSqspLbModeC_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspLbModeC_t jarg3_);
  public final static native int SqspLbMode_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspLbMode_t jarg3_);
  public final static native int SqspRspC_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspRspC_t jarg3_);
  public final static native int SqspRsp_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspRsp_t jarg3_);
  public final static native int SqspStatus_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspStatus_t jarg3_);
  public final static native int SqspStatusC_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspStatusC_t jarg3_);
  public final static native int SqspLlaC_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspLlaC_t jarg3_);
  public final static native int SqspLla_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspLla_t jarg3_);
  public final static native int SqspLbConfig_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspLbConfig_t jarg3_);
  public final static native int SqspLbConfigC_tByteArray(byte[] jarg1, int jarg2, long jarg3, SqspLbConfigC_t jarg3_);
  public final static native int SqspLbModeC_tSize();
  public final static native int SqspLbMode_tSize();
  public final static native int SqspRspC_tSize();
  public final static native int SqspRsp_tSize();
  public final static native int SqspStatus_tSize();
  public final static native int SqspStatusC_tSize();
  public final static native int SqspLlaC_tSize();
  public final static native int SqspLla_tSize();
  public final static native int SqspLbConfig_tSize();
  public final static native int SqspLbConfigC_tSize();
  public final static native long sqspStatusCopyCast(byte[] jarg1);
}