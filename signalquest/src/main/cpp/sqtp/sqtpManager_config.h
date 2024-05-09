/*******************************************************************************
**  Copyright 2024 - SignalQuest, Inc.    PROPRIETARY & CONFIDENTIAL
********************************************************************************
**
**  This file is confidential and proprietary property of SignalQuest, Inc.
**  All rights are reserved. This file may not be used, reproduced or
**  distributed by any unauthorized party.  Unauthorized copies of this file
**  must be deleted or destroyed immediately.
**
********************************************************************************
**
**  File:     sqtpManager_config.h
**  Purpose:  SignalQuest Transport Protocol Frame Manager configuration
**
*******************************************************************************/

//  TODO: This file shouldn't be part of SQTP itself. SqtpManagerChannel_t
//        needs to be provided by the platform. Maybe adding this would be
//        better done by a "config.h" or "platform_config.h" being globally
//        included for all compiled files?
  
#ifndef   __SQTPMANAGER_CONFIG_H__
#define   __SQTPMANAGER_CONFIG_H__

#ifdef    __cplusplus
extern "C" {
#endif  //__cplusplus

//  Includes
#include "os_target.h"

//  Defines

//  Data types
typedef enum SqtpManagerChannel_e
{
  SQTP_MANAGER_CHANNEL_BLUETOOTH,
  SQTP_MANAGER_CHANNEL_SPISLAVE,
  SQTP_MANAGER_CHANNEL_COUNT
} ENUM_ATTR SqtpManagerChannel_t;

//  Constants

//  Variables

//  Functions

#ifdef    __cplusplus
}
#endif  //__cplusplus

#endif  //__SQTPMANAGER_CONFIG_H__
