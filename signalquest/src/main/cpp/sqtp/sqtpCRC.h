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
**  File:     sqtpCRC.h
**  Purpose:  SignalQuest Transport Protocol CRC calculation declaration
**
*******************************************************************************/

#ifndef   __SQTPCRC_H__
#define   __SQTPCRC_H__

#ifdef    __cplusplus
extern "C" {
#endif  //__cplusplus

//  Includes
#include <stddef.h>
#include <stdint.h>

#include "os_target.h"

//  Defines

//  Data types

//  Constants

//  Variables

//  Functions
FUNC_ATTR uint16_t sqtpCRC( uint16_t fcs, const uint8_t *buf, size_t len );

#ifdef    __cplusplus
}
#endif  //__cplusplus

#endif  //__SQCPCRC_H__
