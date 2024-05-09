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
**  File:     sqtpManager.h
**  Purpose:  SignalQuest Transport Protocol Frame Manager declarations
**
*******************************************************************************/

#ifndef   __SQTPMANAGER_H__
#define   __SQTPMANAGER_H__

#ifdef    __cplusplus
extern "C" {
#endif  //__cplusplus

//  Includes
#include <stddef.h>
#include <stdint.h>

#include "os_target.h"

#include "sqtpManager_config.h"
#include "sqtpStatus.h"
#include "sqtpFrame.h"

//  Defines

//  Data types

//  Callback: SqtpSubframeHandler_t
//  Purpose:  Callback for handling subframes that have been found within a
//              parsed SQTP frame.
//  Params:   status: The current parsing status for the subframe. Generally,
//              this should always be SQTP_STATUS_SUCCESS.
//            sf: The subframe to be handled.
//            cx: Context that the callback should understand.
//  Returns:  SQTP_STATUS_SUCCESS or relevant parsing status.
typedef SqtpStatus_t SqtpSubframeHandler_t( SqtpStatus_t status,
  const SqtpSubframe_t *sf, void *cx );

//  Callback: SqtpTransmitCompleteCallback_t
//  Purpose:  Callback called after a frame has been sent.
//  Params:   status: Status regarding the transmission of the frame. Ideally,
//              this should be SQTP_STATUS_SUCCESS.
//            cx: Context that the callback should understand.
//  Returns:  
typedef SqtpStatus_t SqtpTransmitCompleteCallback_t( SqtpStatus_t status,
    void *cx );

//  Callback: SqtpTransmitFrame_t
//  Purpose:  Callback called when it is desired to transmit a frame.
//  Params:   buf: Pointer to the frame to be transmit.
//            sz: Size of the frame in bytes.
//            cx: Context that the callback should understand
//            txd_cb: Callback to be called on transmit complete.
//            txd_cx: Context for the transmit complete callback.
//  Returns:  Relevant transmission status. This status should be passed to
//              txd_cb if it is a valid pointer.
typedef SqtpStatus_t SqtpTransmitFrame_t( const uint8_t *buf, size_t sz,
  void *cx, SqtpTransmitCompleteCallback_t *txd_cb, void *txd_cx );

//  Constants

//  Variables

//  Functions

//  Function: sqtpManagerParseFrame
//  Purpose:  Attempts to parse a buffer of data as a SQTP frame. The previously
//              set Subframe Handler will be called for each valid subframe.
//  Params:   ch: Channel for which to parse the buffer.
//            buf: Pointer to buffer of data to parse.
//            sz: Maximum size of buffer; actual frame could be shorter.
//  Returns:  Parsing status; parsing halts when an erroneous condition is hit.
FUNC_ATTR SqtpStatus_t sqtpManagerParseFrame( SqtpManagerChannel_t ch,
  const uint8_t *buf, size_t sz );

//  Function: sqtpManagerAddDirect
//  Purpose:  Given the individual fields of a subframe, adds a subframe to the
//              current frame being built.
//  Params:   ch: Channel for which to add a subframe.
//            id: Subframe ID
//            payload: Pointer to subframe payload to add to frame.
//            sz: Size of the payload.
//  Returns:  SQTP_STATUS_SUCCESS on success.
//            SQTP_STATUS_SUBFRAME_BAD_LENGTH if the subframe will not fit.
FUNC_ATTR SqtpStatus_t sqtpManagerAddDirect( SqtpManagerChannel_t ch,
  SqtpSubframeId_t id, const uint8_t *payload, size_t sz );

//  Function: sqtpManagerAddSubframe
//  Purpose:  Adds a subframe to the current frame being built. You are likely
//              to use this function when forwarding a subframe from a different
//              SQTP interface.
//  Params:   ch: Channel for which to add a subframe.
//            sf: Pointer to subframe to add
//  Returns:  SQTP_STATUS_SUCCESS on success.
//            SQTP_STATUS_SUBFRAME_BAD_LENGTH if the subframe will not fit.
FUNC_ATTR SqtpStatus_t sqtpManagerAddSubframe( SqtpManagerChannel_t ch,
  const SqtpSubframe_t *sf );

//  Function: sqtpManagerSendFrame
//  Purpose:  Finalizes the frame being built and sends it off via previously
//              set Transmit Frame callback.
//            If the channel is using a double buffered configuration, the
//              buffer that is not about to be transmit will be reset and ready
//              for new subframes.
//            If the channel is using a single buffered configuration, the
//              buffer will be reset and be ready for new subframes once the
//              transfer is complete.
//  Params:   ch: Channel for which to send a frame.
//  Returns:  SQTP_STATUS_SUCCESS on success.
FUNC_ATTR SqtpStatus_t sqtpManagerSendFrame( SqtpManagerChannel_t ch );

//  Function: sqtpManagerSetTransmitBuffers
//  Purpose:  Sets the locatipon and size of the transmit buffers to use for the
//            given channel.
//  Params:   ch: Channel for which to set buffers.
//            buf1: Pointer to first buffer; cannot be NULL.
//            buf2: Pointer to second buffer; can be NULL to imply using single-
//              buffered mode for this channel.
//            sz: The size of the buffer(s). When using double-buffered mode,
//              both buffers are required to be the same size.
//  Returns:  SQTP_STATUS_SUCCESS on success.
FUNC_ATTR SqtpStatus_t sqtpManagerSetTransmitBuffers( SqtpManagerChannel_t ch,
  uint8_t *buf1, uint8_t *buf2, size_t sz );

//  Function: sqtpManagerSetSubframeHandler
//  Purpose:  Sets the callback to handle subframes when parsing a frame.
//  Params:   ch: Channel for which to set the callback.
//            cb: Callback function.
//            cx: Context to be passed to the callback.
//  Returns:  SQTP_STATUS_SUCCESS on success.
FUNC_ATTR SqtpStatus_t sqtpManagerSetSubframeHandler( SqtpManagerChannel_t ch,
  SqtpSubframeHandler_t *cb, void *cx );

//  Function: sqtpManagerSetTransmitComplete
//  Purpose:  Sets the callback function to be called after a frame has been
//            transmitted. The values passed to this function are passed to the
//            function set via sqtpSetTransmitFrame().
//  Params:   ch: Channel for which to set the callback.
//            cb: Callback function.
//            cx: Context to be passed to the callback.
//  Returns:  SQTP_STATUS_SUCCESS on success.
FUNC_ATTR SqtpStatus_t sqtpManagerSetTransmitComplete( SqtpManagerChannel_t ch,
  SqtpTransmitCompleteCallback_t *cb, void *cx );

//  Function: sqtpManagerSetTransmitFrame
//  Purpose:  Sets the callback function to be called when a frame transmission
//            is desired.
//  Params:   ch: Channel for which to set the callback.
//            cb: Callback function.
//            cx: Context to be passed to the callback.
//  Returns:  SQTP_STATUS_SUCCESS on success.
FUNC_ATTR SqtpStatus_t sqtpManagerSetTransmitFrame( SqtpManagerChannel_t ch,
  SqtpTransmitFrame_t *cb, void *cx );

#ifdef    __cplusplus
}
#endif  //__cplusplus

#endif  //__SQTPMANAGER_H__
