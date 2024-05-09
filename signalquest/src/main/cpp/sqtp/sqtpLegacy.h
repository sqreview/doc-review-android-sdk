/*******************************************************************************
**  Copyright 2023 - SignalQuest, Inc.    PROPRIETARY & CONFIDENTIAL
********************************************************************************
**
**  This file is confidential and proprietary property of SignalQuest, Inc.
**  All rights are reserved. This file may not be used, reproduced or
**  distributed by any unauthorized party.  Unauthorized copies of this file
**  must be deleted or destroyed immediately.
**
********************************************************************************
**
**  File:     sqtpLegacy.h
**  Purpose:  SignalQuest Transport Protocol Legacy Subframe declarations
**
*******************************************************************************/

#ifndef   __SQTPLEGACY_H__
#define   __SQTPLEGACY_H__

#ifdef    __cplusplus
extern "C" {
#endif  //__cplusplus

//  Includes
#include <stdint.h>

//  Defines

//  Data types
typedef struct SqtpLegacyFirmwareResponse_s
{
  uint32_t address;
  uint32_t size;
  uint32_t version;                     //  Also used for serial number
  uint16_t imageCRC;
  uint16_t spare;
  uint8_t  name[16];
  uint8_t  versionLabel[16];
} SqtpLegacyFirmwareResponse_t;

typedef struct SqtpLegacyFirmwareStatus_s
{
  uint32_t error;
  uint32_t executing;
  uint32_t address;
  uint32_t size;
  uint32_t pageSize;
  uint32_t pageCount;
  uint16_t command;
  int16_t  status;
  uint16_t crc16;
  uint16_t eraseCount;
  uint8_t  reserved[16];
} SqtpLegacyFirmwareStatus_t;

//  Constants

//  Variables

//  Functions

#ifdef    __cplusplus
}
#endif  //__cplusplus


#endif  //__SQTPCOMMON_H__