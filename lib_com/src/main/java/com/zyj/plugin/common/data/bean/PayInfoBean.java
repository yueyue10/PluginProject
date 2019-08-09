package com.zyj.plugin.common.data.bean;

/**
 * @作者 zhouchao
 * @日期 2019/7/12
 * @描述
 */
public class PayInfoBean {


    /**
     * alipayTradeWapPayResponse : {"body":"string","code":"string","errorCode":"string","merchantOrderNo":"string","msg":"string","outTradeNo":"string","params":{},"sellerId":"string","subCode":"string","subMsg":"string","success":true,"totalAmount":"string","tradeNo":"string"}
     * orderCode : string
     * payFlag : 0
     * payType : 0
     * wxPayUnifiedOrderResult : {"appid":"string","codeURL":"string","errCode":"string","errCodeDes":"string","mchId":"string","mwebUrl":"string","nonceStr":"string","prepayId":"string","resultCode":"string","returnCode":"string","returnMsg":"string","sign":"string","subAppId":"string","subMchId":"string","tradeType":"string","xmlString":"string"}
     */

    private AlipayTradeWapPayResponseBean alipayTradeWapPayResponse;
    private String orderCode;
    private int payFlag;
    private int payType;
    private WxPayUnifiedOrderResultBean wxPayUnifiedOrderResult;

    public AlipayTradeWapPayResponseBean getAlipayTradeWapPayResponse() {
        return alipayTradeWapPayResponse;
    }

    public void setAlipayTradeWapPayResponse(AlipayTradeWapPayResponseBean alipayTradeWapPayResponse) {
        this.alipayTradeWapPayResponse = alipayTradeWapPayResponse;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(int payFlag) {
        this.payFlag = payFlag;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public WxPayUnifiedOrderResultBean getWxPayUnifiedOrderResult() {
        return wxPayUnifiedOrderResult;
    }

    public void setWxPayUnifiedOrderResult(WxPayUnifiedOrderResultBean wxPayUnifiedOrderResult) {
        this.wxPayUnifiedOrderResult = wxPayUnifiedOrderResult;
    }

    public static class AlipayTradeWapPayResponseBean {
        /**
         * body : string
         * code : string
         * errorCode : string
         * merchantOrderNo : string
         * msg : string
         * outTradeNo : string
         * params : {}
         * sellerId : string
         * subCode : string
         * subMsg : string
         * success : true
         * totalAmount : string
         * tradeNo : string
         */

        private String body;
        private String code;
        private String errorCode;
        private String merchantOrderNo;
        private String msg;
        private String outTradeNo;
        private ParamsBean params;
        private String sellerId;
        private String subCode;
        private String subMsg;
        private boolean success;
        private String totalAmount;
        private String tradeNo;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMerchantOrderNo() {
            return merchantOrderNo;
        }

        public void setMerchantOrderNo(String merchantOrderNo) {
            this.merchantOrderNo = merchantOrderNo;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getSubCode() {
            return subCode;
        }

        public void setSubCode(String subCode) {
            this.subCode = subCode;
        }

        public String getSubMsg() {
            return subMsg;
        }

        public void setSubMsg(String subMsg) {
            this.subMsg = subMsg;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
        }

        public static class ParamsBean {
        }
    }


}
