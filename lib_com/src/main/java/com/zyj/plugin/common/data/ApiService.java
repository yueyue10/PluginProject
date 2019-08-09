package com.zyj.plugin.common.data;

import com.zyj.plugin.common.data.bean.AddressInfoBean;
import com.zyj.plugin.common.data.bean.BaseResponse;
import com.zyj.plugin.common.data.bean.BaseResponseV2;
import com.zyj.plugin.common.data.bean.CarNumberInfoBean;
import com.zyj.plugin.common.data.bean.CityInfoBean;
import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.data.bean.NewsDetailBean;
import com.zyj.plugin.common.data.bean.NoPayOrderBean;
import com.zyj.plugin.common.data.bean.OnLinePayOrderBean;
import com.zyj.plugin.common.data.bean.OrderBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.data.bean.ParkBean;
import com.zyj.plugin.common.data.bean.ParkPriiceBean;
import com.zyj.plugin.common.data.bean.ParkingInfoBean;
import com.zyj.plugin.common.data.bean.PassengerInfoBean;
import com.zyj.plugin.common.data.bean.PassengerInfoDetail;
import com.zyj.plugin.common.data.bean.PayHistoryBean;
import com.zyj.plugin.common.data.bean.PayInfoBean;
import com.zyj.plugin.common.data.bean.ReseverPayOrderBean;
import com.zyj.plugin.common.data.bean.UserInfo;
import com.zyj.plugin.common.data.bean.VenueBean;
import com.zyj.plugin.common.data.bean.VenueDetailBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @作者 zhouchao
 * @日期 2019/3/27
 * @描述
 */
public interface ApiService {

    /**
     * 更新昵称
     */
    @POST("member-v1/user/profile")
    Observable<BaseResponseV2<UserInfo>> getUserInfo();

    /**
     * 更新昵称
     */
    @POST("member-v1/user/update/nickname")
    @FormUrlEncoded
    Observable<BaseResponseV2> updateNickName(@Field("nickname") String nickname);

    /**
     * 更新昵称
     */
    @POST("member-v1/user/update/name")
    @FormUrlEncoded
    Observable<BaseResponseV2> updateUserName(@Field("name") String name);

    /**
     * 更新生日
     */
    @POST("member-v1/user/update/birthday")
    @FormUrlEncoded
    Observable<BaseResponseV2> updateBirthday(@Field("birthday") String birthday);

    /**
     * 上传头像
     */
    @POST("member-v1/user/update/head")
    @FormUrlEncoded
    Observable<BaseResponseV2> updateUserPhoto(@Field("headImg") String headImg);

    /**
     * 获取验证码
     *
     * @param phoneNumber 手机号码
     */
    @POST("member-v1/login/getValidateCode")
    @FormUrlEncoded
    Observable<BaseResponseV2> getValidateCode(@Field("accountCode") String phoneNumber);

    /**
     * 验证码登录
     *
     * @param phoneNumber 手机号码
     * @param code        验证码
     * @param appId       appID 与请求头中appID一样都是2
     * @return 用户信息
     */
    @POST("member-v1/login/loginByYZM")
    @FormUrlEncoded
    Observable<BaseResponseV2<UserInfo>> loginByCode(@Field("accountNum") String phoneNumber, @Field("YanZhengMa") String code, @Field("appId") String appId);

    /**
     * 密码登录
     *
     * @param phoneNumber 手机号码
     * @param password    密码
     * @return 用户信息
     */
    @POST("member-v1/login/login")
    @FormUrlEncoded
    Observable<BaseResponseV2<UserInfo>> loginByPassword(@Field("phone") String phoneNumber, @Field("password") String password);

    /**
     * 注册账号
     *
     * @param phoneNumber 手机号码
     * @param password    密码
     * @param code        验证码
     * @return 用户信息
     */
    @POST("member-v1/user/regist")
    @FormUrlEncoded
    Observable<BaseResponseV2<UserInfo>> register(@Field("phone") String phoneNumber, @Field("password") String password, @Field("code") String code);

    /**
     * 重置密码
     *
     * @param phoneNumber 手机号码
     * @param password    密码
     * @param code        验证码
     * @return 用户信息
     */
    @POST("member-v1/user/reset/password")
    @FormUrlEncoded
    Observable<BaseResponseV2<UserInfo>> resetPassword(@Field("phone") String phoneNumber, @Field("password") String password, @Field("passwordConfirm") String passwordConfirm, @Field("code") String code);

    /**
     * 退出登录
     */
    @POST("member-v1/login/logout")
    Observable<BaseResponseV2> logout();

    /**
     * 提交反馈意见
     *
     * @param files 图片文件
     */
    @POST("encdata-pandaro-mlf/suggest/insertFeedback")
    @Multipart
    Observable<BaseResponse> submitFeedback(@Query("suggestParameter") String suggestParameter, @Part MultipartBody.Part files);

    /**
     * 获取首页接口
     */
    @GET("encdata-pandaro-mlf/suggest/app/home")
    Observable<BaseResponse<HomeBean>> getHomeData(@Query("scenicId") int scenicId);

    /**
     * 获取所有资讯接口
     */
    @GET("encdata-pandaro-mlf/suggest/app/strategyList")
    Observable<BaseResponse<List<NewsBean>>> getNewsList(@Query("scenicId") int scenicId);

    /**
     * 点赞接口
     */
    @POST("encdata-pandaro-mlf/suggest/app/strategyInfoVote")
    @FormUrlEncoded
    Observable<BaseResponse<Boolean>> strategyInfoVote(@Field("id") int id);

    /**
     * 获取资讯详情接口
     */
    @GET("encdata-pandaro-mlf/suggest/app/strategyInfo")
    Observable<BaseResponse<NewsDetailBean>> getNewsDetail(@Query("id") int id);

    /**
     * 上传图片到服务器
     */
    @POST("encdata-pandaro-mlf/suggest/uploadFiles")
    @Multipart
    Observable<BaseResponse<List<String>>> uploadFiles(@Part MultipartBody.Part file);

    /**
     * 获取场馆列表
     *
     * @param scenicId 与请求头中companyId 一样都是6
     * @return 场馆列表
     */
    @GET("encdata-pandaro-mlf/suggest/app/placeList")
    Observable<BaseResponse<List<VenueBean>>> getVenues(@Query("scenicId") String scenicId);

    /**
     * 获取场馆详情
     *
     * @param id 场馆id
     * @return 场馆详情
     */
    @GET("encdata-pandaro-mlf/suggest/app/placeInfo")
    Observable<BaseResponse<VenueDetailBean>> getVenueDetail(@Query("id") int id);

    //***************************************项目二期接口******************************************************

    /**
     * 获取订单列表接口
     */
    @POST("dream-app/api/me/query/all")
    @FormUrlEncoded
    Observable<BaseResponseV2<List<OrderBean>>> getOrderList(@Field("offset") int offset, @Field("limit") int limit, @Field("userId") int userId);

    /**
     * 获取订单详情接口
     */
    @GET("dream-app/api/me/detail")
    Observable<BaseResponseV2<OrderDetailBean>> getOrderDetail(@Query("orderCode") String orderCode);

    /**
     * 获取停车场列表
     */
    @GET("dream-app/api/park")
    Observable<BaseResponseV2<List<ParkBean>>> getParkList(@Query("offset") String offset, @Query("limit") String limit);

    /**
     * 获取预约停车场价格
     */
    @GET("dream-app/api/park/order/calculatePrice")
    Observable<BaseResponseV2<ParkPriiceBean>> getParkPirce(@Query("memberId") String userId, @Query("parkId") String parkId, @Query("plateNum") String plateNum, @Query("startTime") String startTime, @Query("endTime") String endTime);

    /**
     * 查询车辆是否在公司停车场内
     */
    @GET("dream-app/api/park/checkCarExist")
    Observable<BaseResponseV2> checkCarExist(@Query("plateNum") String plateNum);

    /**
     * 创建在线缴费账单
     */
    @POST("dream-app/api/park/payment/createOrder")
    @FormUrlEncoded
    Observable<BaseResponseV2<OnLinePayOrderBean>> createOnLinePayOrder(@Field("memberId") String memberId, @Field("plateNum") String plateNum);

    /**
     * 获取缴费需要信息
     *
     * @param type    type 1 在线缴费  2 预约支付
     * @param payType payType 1 支付宝  2 微信
     */
    @POST("dream-app/api/park/payment/wechatUnifiedOrder")
    @FormUrlEncoded
    Observable<BaseResponseV2<PayInfoBean>> getWechatPayInfo(@Field("orderCode") String orderCode, @Field("type") String type, @Field("payType") String payType);

    /**
     * 无费用，订单完成确认
     *
     * @param type type 1 在线缴费  2 预约支付
     */
    @POST("dream-app/api/park/payment/orderSuccess")
    @FormUrlEncoded
    Observable<BaseResponseV2> confirmOrderComplete(@Field("orderCode") String orderCode, @Field("type") String type);

    /**
     * 创建预约缴费订单
     */
    @POST("dream-app/api/park/reservation/createOrder")
    @FormUrlEncoded
    Observable<BaseResponseV2<ReseverPayOrderBean>> createReseverPayOrder(@Field("memberId") String memberId, @Field("parkId") String parkId, @Field("plateNum") String plateNum, @Field("startTime") String startTime, @Field("endTime") String endTime);

    /**
     * 查询车辆停车位置信息
     */
    @GET("dream-app/api/park/car/position")
    Observable<BaseResponseV2<ParkingInfoBean>> getParkingInfo(@Query("plateNum") String plateNum);

    /**
     * 分页查询在线缴费记录
     */
    @POST("dream-app/api/me/query/pay/all")
    @FormUrlEncoded
    Observable<BaseResponseV2<List<PayHistoryBean>>> getPayHistory(@Field("offset") int offset, @Field("limit") int limit, @Field("userId") int userId);

    /**
     * 取消订单
     */
    @POST("dream-app/api/me/cancel")
    @FormUrlEncoded
    Observable<BaseResponseV2<Boolean>> cancelOrder(@Field("orderCode") String orderCode);

    /**
     * 查询所有旅客信息
     */
    @POST("dream-app/traveller/query/all")
    @FormUrlEncoded
    Observable<BaseResponseV2<List<PassengerInfoBean>>> getAllTravellerInfo(@Field("userId") int userId);

    /**
     * 查询旅客信息详情
     */
    @GET("dream-app/traveller/detail")
    Observable<BaseResponseV2<PassengerInfoDetail>> getTravellerInfoDetail(@Query("id") int userId);

    /**
     * 添加旅客信息
     */
    @POST("dream-app/traveller/save")
    @FormUrlEncoded
    Observable<BaseResponseV2<Boolean>> addTravellerInfo(@Field("memberId") int userId, @Field("name") String name, @Field("sex") int sex, @Field("birthday") String birthday, @Field("idCard") String idCard);

    /**
     * 更新旅客信息
     */
    @POST("dream-app/traveller/update")
    @FormUrlEncoded
    Observable<BaseResponseV2<Boolean>> updateTravellerInfo(@Field("id") int id, @Field("memberId") int userId, @Field("name") String name, @Field("sex") int sex, @Field("birthday") String birthday, @Field("idCard") String idCard);

    /**
     * 更新旅客信息
     */
    @POST("dream-app/traveller/delete")
    @FormUrlEncoded
    Observable<BaseResponseV2<Boolean>> deleteTravellerInfo(@Field("id") int userId);

    /**
     * 查询车牌列表
     *
     * @param userId 用户id
     */
    @POST("dream-app/car/query/all")
    @FormUrlEncoded
    Observable<BaseResponseV2<List<CarNumberInfoBean>>> getCarNumberList(@Field("userId") int userId);

    /**
     * 保存车牌
     *
     * @param carNumber 车牌号
     * @param memberId  用户id
     */
    @POST("dream-app/car/save")
    @FormUrlEncoded
    Observable<BaseResponseV2> saveCarNumber(@Field("carNumber") String carNumber, @Field("memberId") int memberId);

    /**
     * 删除保存的车牌
     *
     * @param id 添加的车牌号id
     */
    @POST("dream-app/car/delete")
    @FormUrlEncoded
    Observable<BaseResponseV2> deleteCarNumber(@Field("id") int id);

    /**
     * 获取所有地址信息
     */
    @POST("dream-app/address/query/all")
    @FormUrlEncoded
    Observable<BaseResponseV2<List<AddressInfoBean>>> getAllAddressInfo(@Field("userId") int userId);

    /**
     * 获取地址详细信息
     */
    @POST("dream-app/address/query/all")
    @FormUrlEncoded
    Observable<BaseResponseV2<AddressInfoBean>> getAllAddressInfoDetail(@Field("id") int id);

    /**
     * 查询省市县
     */
    @GET("dream-app/address/city")
    Observable<BaseResponseV2<List<CityInfoBean>>> getAreaInfo(@Query("id") int id);

    /**
     * 删除地址信息
     */
    @POST("dream-app/address/delete")
    @FormUrlEncoded
    Observable<BaseResponseV2<Boolean>> deleteAddressInfo(@Field("id") int id);

    /**
     * 保存地址信息接口
     */
    @POST("dream-app/address/save")
    @FormUrlEncoded
    Observable<BaseResponseV2<Boolean>> addAddressInfo(
            @Field("memberId") int memberId, @Field("name") String name, @Field("telephone") String telephone,
            @Field("area") String area, @Field("detailedAddress") String detailedAddress, @Field("postCode") String postCode);

    /**
     * 更新地址信息接口
     */
    @POST("dream-app/address/update")
    @FormUrlEncoded
    Observable<BaseResponseV2<Boolean>> updateAddressInfo(
            @Field("id") int id, @Field("memberId") int memberId, @Field("name") String name,
            @Field("telephone") String telephone, @Field("area") String area, @Field("detailedAddress") String detailedAddress,
            @Field("postCode") String postCode);

    /**
     * 查看订单状态
     * 订单类型 1 缴费 2预约
     */
    @POST("dream-app/api/park/pay/checkOrderStatus")
    @FormUrlEncoded
    Observable<BaseResponseV2<Boolean>> checkOrderStatus(@Field("orderCode") String orderCode, @Field("type") int type);

    /**
     * 查看代付款信息
     */
    @POST("dream-app/api/me/no/pay")
    @FormUrlEncoded
    Observable<BaseResponseV2<List<NoPayOrderBean>>> getNoPayInfo(@Field("userId") int userId);


}
